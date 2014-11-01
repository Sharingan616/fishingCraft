package fishingcraft.common.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import fishingcraft.common.items.FCItems;
import fishingcraft.common.items.fish.ItemFish;
import fishingcraft.common.items.rods.ItemIronFishingRod;
import fishingcraft.shar.util.Debug;
import fishingcraft.shar.util.ItemHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * This class defines the recipes used to create both items and blocks.
 * @author Sharingan616
 *
 */
public class FCRecipe
{
	/**
	 * Adds all of the Fishing Craft recipes
	 */
	public static void addRecipes()
	{
		ItemHelper.removeRecipe(new ItemStack(Items.fishing_rod));
		addRodRecipes();
		addFishRecipes();
		addSeaFishRecipes();
		addFishMeatRecipes();
		addMiscRecipes();
	}

	/**
	 * This method is used to add all the recipes of the rods to the came
	 */
	private static void addRodRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(FCItems.woodenFishingRod), "  x", " xy", "x y",
				'x', new ItemStack(Items.stick), 'y', new ItemStack(Items.string));
		GameRegistry.addRecipe(new ItemStack(FCItems.ironFishingRod), "  z", " zy", "x y",
				'x', new ItemStack(Items.stick), 'z', new ItemStack(Items.iron_ingot), 'y', new ItemStack(Items.string));

		for (int i = 0; i <= FCItems.woodenFishingRod.getMaxDamage(); i++)
		{
			addRodRecipe(FCItems.woodenFishingRodWorm, FCItems.worm, i);
			addRodRecipe(FCItems.woodenFishingRodBG, FCItems.fishBlueGillRaw, i);
			addRodRecipe(FCItems.woodenFishingRodMorsel, FCItems.rawFishMorsel, i);
			addRodRecipe(FCItems.woodenFishingRodFrogEggs, FCItems.frogeggs, i);
			addRodRecipe(FCItems.woodenFishingRodSP, FCItems.fishSmallPiranhaRaw, i);
		}

		for (int i = 0; i <= FCItems.ironFishingRod.getMaxDamage(); i++)
		{
			addRodRecipe(FCItems.ironFishingRodWorm, FCItems.worm, i);
			addRodRecipe(FCItems.ironFishingRodBG, FCItems.fishBlueGillRaw, i);
			addRodRecipe(FCItems.ironFishingRodMorsel, FCItems.rawFishMorsel, i);
			addRodRecipe(FCItems.ironFishingRodFrogEggs, FCItems.frogeggs, i);
			addRodRecipe(FCItems.ironFishingRodSP, FCItems.fishSmallPiranhaRaw, i);
		}
	}

	/**
	 * This method is used to add all the recipes of the fish to the game.
	 * This pertains to smelting and crafting.
	 */
	private static void addFishRecipes()
	{
		GameRegistry.addSmelting(FCItems.fishCatRaw, new ItemStack(FCItems.fishCatCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishBlueGillRaw, new ItemStack(FCItems.fishBlueGillCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishSuckerRaw, new ItemStack(FCItems.fishSuckerCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishCarpRaw, new ItemStack(FCItems.fishCarpCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishBassRaw, new ItemStack(FCItems.fishBassCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishPikeRaw, new ItemStack(FCItems.fishPikeCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishPeaBassRaw, new ItemStack(FCItems.fishPeaBassCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishGoldFishRaw, new ItemStack(FCItems.fishGoldFishCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishSmallPiranhaRaw, new ItemStack(FCItems.fishSmallPiranhaCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishTenchRaw, new ItemStack(FCItems.fishTenchCooked), 0.35F);
		GameRegistry.addSmelting(FCItems.fishMackerelRaw, new ItemStack(FCItems.fishMackerelCooked), 0.35F);
	}

	private static void addSeaFishRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(FCItems.rawTunaStrip, 2), new ItemStack(FCItems.fishYFTuna));
		GameRegistry.addShapelessRecipe(new ItemStack(FCItems.rawTunaStrip, 2), new ItemStack(FCItems.fishBFTuna));
	}

	private static void addFishMeatRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(FCItems.rawFishMorsel, 2), new ItemStack(FCItems.rawTunaStrip));
		GameRegistry.addShapelessRecipe(new ItemStack(FCItems.cookedFishMorsel, 2), new ItemStack(FCItems.cookedTunaStrip));
		GameRegistry.addSmelting(FCItems.rawTunaStrip, new ItemStack(FCItems.cookedTunaStrip), 0.35F);
		GameRegistry.addSmelting(FCItems.rawFishMorsel, new ItemStack(FCItems.cookedFishMorsel), 0.35F);
	}

	private static void addMiscRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(Items.carrot_on_a_stick), new ItemStack(FCItems.woodenFishingRod), new ItemStack(Items.carrot));
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 15), new ItemStack(FCItems.fishbones));
	}

	private static void addRodRecipe(Item rod, Item bait, int damage)
	{
		Item baseRod;
		if(rod instanceof ItemIronFishingRod)
			baseRod = FCItems.ironFishingRod;
		else
			baseRod = FCItems.woodenFishingRod;
		if(bait instanceof ItemFish)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(rod, 1, damage), new ItemStack(baseRod, 1, damage), new ItemStack(bait, 1, OreDictionary.WILDCARD_VALUE));
		}
		else
		{
			GameRegistry.addShapelessRecipe(new ItemStack(rod, 1, damage), new ItemStack(baseRod, 1, damage), new ItemStack(bait));
		}
	}
}
