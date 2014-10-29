package fishingcraft.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fishingcraft.common.blocks.other.BlockBeeHive;
import fishingcraft.common.blocks.plants.BlockWaterPlant;
import fishingcraft.common.blocks.plants.BlockWeedWithEggs;
import fishingcraft.common.items.GenItem;
import fishingcraft.common.items.fish.ItemFish;
import net.minecraft.block.Block;
import net.minecraft.item.Item;


/**
 * This class is responsible for adding blocks.
 * 
 * @author Sharingan616
 *
 */
public class FCBlock
{
    public static Block weed = (new BlockWaterPlant("Weeds"));
    public static Block weedWithEggs = (new BlockWeedWithEggs("Weeds_With_Eggs"));
    public static Block beeHive = new BlockBeeHive("BeeHive");
    
    public static void addBlockNames()
    {
        addPlants();
        addMisc();
    }

    private static void addPlants()
    {
        addWaterPlants();
    }
    
    private static void addMisc()
    {
    	GameRegistry.registerBlock(beeHive, "BeeHive");
    }

    private static void addWaterPlants()
    {
        GameRegistry.registerBlock(weed, "Weeds");
        GameRegistry.registerBlock(weedWithEggs, "Weeds_With_Eggs");
    }

    public static boolean checkForChar(String s, char c)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == c)
            {
                return true;
            }
        }

        return false;
    }

    public static void removeStringAfterChar(String s, char c)
    {
        if (checkForChar(s, c))
        {
            s = s.substring(0, s.indexOf(c));
        }
    }
}
