package fishingcraft.common.world;

import java.util.Random;

import net.minecraft.block.Block;
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
                    int currentBlockId = world.getBlockId(xCoord, i, zCoord);
                    int blockIdBelow = world.getBlockId(xCoord, i - 1, zCoord);
                    int blockIdAbove = world.getBlockId(xCoord, i + 1, zCoord);
                    BiomeGenBase currentBiome = world.getBiomeGenForCoords(xCoord, zCoord);

                    if ((currentBlockId == Block.waterStill.blockID || currentBlockId == Block.waterMoving.blockID) && blockIdBelow == Block.dirt.blockID && blockIdAbove == Block.waterStill.blockID)
                    {
                        if (currentBiome == BiomeGenBase.river || currentBiome == BiomeGenBase.swampland && !isNearBiome(30, BiomeGenBase.ocean, xCoord, zCoord, world))
                        {
                            int randomNum = random.nextInt(100);

                            if (randomNum < 40)
                            {
                                world.setBlock(xCoord, i, zCoord, FCBlock.weed.blockID);
                            }
                            else if (randomNum < 50)
                            {
                                world.setBlock(xCoord, i, zCoord, FCBlock.weedWithEggs.blockID);
                            }
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