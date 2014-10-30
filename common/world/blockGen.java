package fishingcraft.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;
import fishingcraft.common.blocks.FCBlock;
import fishingcraft.shar.util.Debug;

/**
 * This class is used to generate custom blocks into the Minecraft world.
 * Currently generates weeds.
 * @author Sharingan616
 *
 */
public class blockGen implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int boundry = 6;

		for (int i = 0; i <= world.getActualHeight(); i++)
		{
			for (int j = 0; j < boundry; j++)
			{
				for (int u = 0; u < boundry; u++)
				{
					int xCoord = chunkX * 16 + random.nextInt(16);
					int zCoord = chunkZ * 16 + random.nextInt(16);
					int currentBlockId = Block.getIdFromBlock(world.getBlock(xCoord, i, zCoord));
					int blockIdBelow = Block.getIdFromBlock(world.getBlock(xCoord, i - 1, zCoord));
					int blockIdAbove = Block.getIdFromBlock(world.getBlock(xCoord, i + 1, zCoord));
					BiomeGenBase currentBiome = world.getBiomeGenForCoords(xCoord, zCoord);

					if (currentBlockId == Block.getIdFromBlock(Blocks.water) && blockIdBelow == Block.getIdFromBlock(Blocks.dirt) && blockIdAbove == Block.getIdFromBlock(Blocks.water))
					{
						if (currentBiome == BiomeGenBase.river || currentBiome == BiomeGenBase.swampland && !isNearBiome(30, BiomeGenBase.ocean, xCoord, zCoord, world))
						{
							int randomNum = random.nextInt(100);

							if (randomNum < 40)
							{
								world.setBlock(xCoord, i, zCoord, FCBlock.weed);
							}
							else if (randomNum < 50)
							{
								world.setBlock(xCoord, i, zCoord, FCBlock.weedWithEggs);
							}
						}
					}

					//Bee Hive Spawning
					if(currentBiome == BiomeGenBase.forest || currentBiome == BiomeGenBase.birchForest && Math.random() > 0.7)
					{
						if(blockIdAbove == Block.getIdFromBlock(Blocks.leaves) && Block.getIdFromBlock(world.getBlock(xCoord, i, zCoord-1)) == Block.getIdFromBlock(Blocks.log) && Block.getIdFromBlock(world.getBlock(xCoord-1, i, zCoord)) == Block.getIdFromBlock(Blocks.air) && blockIdBelow == Block.getIdFromBlock(Blocks.air) && Block.getIdFromBlock(world.getBlock(xCoord, i-2, zCoord)) == Block.getIdFromBlock(Blocks.air))
						{
							world.setBlock(xCoord, i, zCoord, FCBlock.beeHive);
							Debug.println("Spawning bee hive at "+xCoord+", "+zCoord);
						}
					}
				}
			}
		}
	}

	public boolean isNearBiome(int radius, BiomeGenBase biome, double X, double Z, World world)
	{
		int x = (int)(X);
		int z = (int)(Z);

		for (int i = -radius; i <= radius; i++)
		{
			for (int j = -radius; i <= radius; i++)
			{
				if (world.getBiomeGenForCoords(x + i, z + j) == biome)
				{
					return true;
				}
			}
		}

		return false;
	}
}