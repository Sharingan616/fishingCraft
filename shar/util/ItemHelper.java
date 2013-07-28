package fishingcraft.shar.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * This class provides utility methods for items.
 * 
 * @author Sharingan616 / Benjamin Tovar-Prince (http://benjamintovar-prince.com)
 */
public class ItemHelper {
	//Method modified by Sharingan616. Thanks to yope_fried and pigalot for original.
	/**
	 * Removes the recipe(s) of the inputed item stack.
	 * @param resultItem ItemStack of what the removed recipe should result to.
	 */
	public static void removeRecipe(ItemStack resultItem)
	{
	    ItemStack recipeResult = null;
	    ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

	    for (int scan = 0; scan < recipes.size(); scan++)
	    {
	        IRecipe tmpRecipe = (IRecipe) recipes.get(scan);

	        if (tmpRecipe instanceof ShapelessRecipes)
	        {
	            ShapelessRecipes recipe = (ShapelessRecipes)tmpRecipe;
	            recipeResult = recipe.getRecipeOutput();
	        }
	        else if (tmpRecipe instanceof ShapedRecipes)
	        {
	            ShapedRecipes recipe = (ShapedRecipes)tmpRecipe;
	            recipeResult = recipe.getRecipeOutput();
	        }
	        else if (tmpRecipe instanceof ShapedOreRecipe)
	        {
	        	ShapedOreRecipe recipe = (ShapedOreRecipe)tmpRecipe;
	            recipeResult = recipe.getRecipeOutput();
	        }
	        else if (tmpRecipe instanceof ShapelessOreRecipe)
	        {
	        	ShapelessOreRecipe recipe = (ShapelessOreRecipe)tmpRecipe;
	            recipeResult = recipe.getRecipeOutput();
	        }
	        if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
	        {
	            Debug.println("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult);
	            recipes.remove(scan);
	        }

	    }
	}
}

