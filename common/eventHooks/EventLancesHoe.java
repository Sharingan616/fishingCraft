package fishingcraft.common.eventHooks;

import fishingcraft.common.items.FCItem;
import fishingcraft.shar.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.UseHoeEvent;

/**
 * Class which details the recurring events of Lance and his hoe.
 * @author Sharingan616
 *
 */
public class EventLancesHoe
{
    @ForgeSubscribe
    public void LancesHoe(UseHoeEvent event)
    {
        World cheapMotel = event.world;
        double chanceOfOrgasmicFluids = Math.random();
        Block bed = Block.tallGrass;
        int Hoe = Block.grass.blockID;
        BiomeGenBase smellyRoom = BiomeGenBase.swampland;
        boolean LanceIsAloneWithHisHoe = !cheapMotel.isRemote;
        boolean itsRainingMen = cheapMotel.isRaining();
        EntityItem benjaminFranklin = new EntityItem(cheapMotel, event.x, event.y, event.z, new ItemStack(FCItem.worm));

        //debug.println("chance: "+chanceOfOrgasmicFluids);
        if (LanceIsAloneWithHisHoe && locationIs(smellyRoom, event.x, event.z, cheapMotel) && isNear(bed, 1, event.x, event.y, event.z, cheapMotel) && cheapMotel.isAirBlock(event.x, event.y + 1, event.z))
        {
            if (chanceOfOrgasmicFluids > 0.7 && cheapMotel.getBlockId(event.x, event.y, event.z) == Hoe && itsRainingMen)
            {
                cheapMotel.spawnEntityInWorld(benjaminFranklin);
            }
        }
    }

    public boolean isNear(Block hoe, int radius, double X, double Y, double Z, World world)
    {
        int x = (int)X;
        int y = (int)Y;
        int z = (int)Z;

        //Thanks to rich1051414 for this for loop
        for (int i = -radius; i <= radius; i++)
        {
            for (int j = -radius; j <= radius; j++)
            {
                for (int k = -radius; k <= radius; k++)
                {
                    int blockID = world.getBlockId(x + i, y + j, z + k);

                    if (blockID == hoe.blockID)
                    {
                        Debug.println("Hoe and Lance are near " + world.getBlockId(x + i, y + j, z + k) + ".");
                        Debug.println("Hoe and Lance's Location: x:" + Math.round(x) + " y:" + Math.round(y) + " z:" + Math.round(z) + ".");
                        Debug.println("Object's Location: x" + Math.round(x + i) + " y:" + Math.round(y + j) + " z:" + Math.round(z + k) + ".");
                        Debug.println("Returning true for method 'isNear(" + hoe + ")'.");
                        return true;
                    }
                }
            }
        }

        Debug.println("Lance and his hoe are not nearby.");
        return false;
    }

    public boolean locationIs(BiomeGenBase biome, double X, double Z, World world)
    {
        int x = (int)X;
        int z = (int)Z;

        if (world.getBiomeGenForCoords(x, z) == biome)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
