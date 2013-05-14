package fishingCraft.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fishingCraft.common.blocks.plants.BlockWaterPlant;
import fishingCraft.common.blocks.plants.BlockWeedWithEggs;
import fishingCraft.common.config.Config;
import fishingCraft.common.items.GenItem;
import fishingCraft.common.items.fish.ItemFish;
import fishingCraft.common.items.fish.ItemSeaFish;
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
    public static Block weed = (new BlockWaterPlant(Config.weeds_ID, "Weeds"));
    public static Block weedWithEggs = (new BlockWeedWithEggs(Config.weedsWithEggs_ID, "Weeds_With_Eggs"));

    public static void addBlockNames()
    {
        addPlants();
    }

    private static void addPlants()
    {
        addWaterPlants();
    }

    private static void addWaterPlants()
    {
        setupName(weed);
        setupName(weedWithEggs);
    }

    private static void setupName(Block b)
    {
        String name = "";
        String newName = "";

        if (b instanceof BlockWaterPlant)
        {
            name = (((BlockWaterPlant) b).name);
            newName = name;
        }

        if (b instanceof BlockWeedWithEggs)
        {
            name = (((BlockWeedWithEggs) b).name);
            newName = name;
        }

        removeStringAfterChar(newName, '-');
        removeStringAfterChar(newName, '#');
        newName = newName.replaceAll("_", " ");
        GameRegistry.registerBlock(b, name);
        LanguageRegistry.addName(b, newName);
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
