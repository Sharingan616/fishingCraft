package fishingcraft.common.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import fishingcraft.common.items.FCItem;
import fishingcraft.shar.util.Debug;
import fishingcraft.shar.util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * This class defines the recipes used to create both items and blocks.
 * @author Sharingan616
 *
 */
public class FCRecipe
{
    public static void addRecipes()
    {
        ItemHelper.removeRecipe(new ItemStack(Item.fishingRod));
        addRodRecipes();
        addFishRecipes();
        addSeaFishRecipes();
        addFishMeatRecipes();
        addMiscRecipes();
    }

    private static void addRodRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(FCItem.woodenFishingRod), "  x", " xy", "x y",
                'x', new ItemStack(Item.stick), 'y', new ItemStack(Item.silk));
        GameRegistry.addRecipe(new ItemStack(FCItem.ironFishingRod), "  z", " zy", "x y",
                'x', new ItemStack(Item.stick), 'z', new ItemStack(Item.ingotIron), 'y', new ItemStack(Item.silk));

        for (int i = 0; i <= FCItem.woodenFishingRod.getMaxDamage(); i++)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.woodenFishingRodWorm, 1, i), new ItemStack(FCItem.woodenFishingRod, 1, i), new ItemStack(FCItem.worm));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.woodenFishingRodBG, 1, i), new ItemStack(FCItem.woodenFishingRod, 1, i), new ItemStack(FCItem.fishBlueGillRaw));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.woodenFishingRodMorsel, 1, i), new ItemStack(FCItem.woodenFishingRod, 1, i), new ItemStack(FCItem.rawFishMorsel));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.woodenFishingRodFrogEggs, 1, i), new ItemStack(FCItem.woodenFishingRod, 1, i), new ItemStack(FCItem.frogEggs));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.woodenFishingRodSP, 1, i), new ItemStack(FCItem.woodenFishingRod, 1, i), new ItemStack(FCItem.fishSmallPiranhaRaw));
        }

        for (int i = 0; i <= FCItem.ironFishingRod.getMaxDamage(); i++)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.ironFishingRodWorm, 1, i), new ItemStack(FCItem.ironFishingRod, 1, i), new ItemStack(FCItem.worm));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.ironFishingRodBG, 1, i), new ItemStack(FCItem.ironFishingRod, 1, i), new ItemStack(FCItem.fishBlueGillRaw));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.ironFishingRodMorsel, 1, i), new ItemStack(FCItem.ironFishingRod, 1, i), new ItemStack(FCItem.rawFishMorsel));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.ironFishingRodFrogEggs, 1, i), new ItemStack(FCItem.ironFishingRod, 1, i), new ItemStack(FCItem.frogEggs));
            GameRegistry.addShapelessRecipe(new ItemStack(FCItem.ironFishingRodSP, 1, i), new ItemStack(FCItem.ironFishingRod, 1, i), new ItemStack(FCItem.fishSmallPiranhaRaw));
        }
    }

    private static void addFishRecipes()
    {
        GameRegistry.addSmelting(FCItem.fishSalmonRaw.itemID, new ItemStack(FCItem.fishSalmonCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishCatRaw.itemID, new ItemStack(FCItem.fishCatCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishBlueGillRaw.itemID, new ItemStack(FCItem.fishBlueGillCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishSuckerRaw.itemID, new ItemStack(FCItem.fishSuckerCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishCarpRaw.itemID, new ItemStack(FCItem.fishCarpCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishBassRaw.itemID, new ItemStack(FCItem.fishBassCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishPikeRaw.itemID, new ItemStack(FCItem.fishPikeCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishPeaBassRaw.itemID, new ItemStack(FCItem.fishPeaBassCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishGoldFishRaw.itemID, new ItemStack(FCItem.fishGoldFishCooked), 0.35F);
        GameRegistry.addSmelting(FCItem.fishSmallPiranhaRaw.itemID, new ItemStack(FCItem.fishSmallPiranhaCooked), 0.35F);
    }

    private static void addSeaFishRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(FCItem.rawTunaStrip, 2), new ItemStack(FCItem.fishYFTuna));
        GameRegistry.addShapelessRecipe(new ItemStack(FCItem.rawTunaStrip, 2), new ItemStack(FCItem.fishBFTuna));
    }

    private static void addFishMeatRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(FCItem.rawFishMorsel, 2), new ItemStack(FCItem.rawTunaStrip));
        GameRegistry.addShapelessRecipe(new ItemStack(FCItem.cookedFishMorsel, 2), new ItemStack(FCItem.cookedTunaStrip));
        GameRegistry.addSmelting(FCItem.rawTunaStrip.itemID, new ItemStack(FCItem.cookedTunaStrip), 0.35F);
        GameRegistry.addSmelting(FCItem.rawFishMorsel.itemID, new ItemStack(FCItem.cookedFishMorsel), 0.35F);
    }

    private static void addMiscRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(Item.carrotOnAStick), new ItemStack(FCItem.woodenFishingRod), new ItemStack(Item.carrot));
        GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCItem.fishBones));
    }
}
