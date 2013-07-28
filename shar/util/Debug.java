package fishingcraft.shar.util;

import java.util.ArrayList;

import fishingcraft.common.items.rods.ItemWoodenFishingRod;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;


/**
 * This class is used to aid in debugging.
 * 
 * @author Sharingan616 / Benjamin Tovar-Prince (http://benjamintovar-prince.com)
 */
public class Debug {

	/**
	 * If this boolean returns true, all debugging prints will be executed.
	 */
	public static boolean debugger;
	
	/**
	 * The name of your mod.
	 */
	public static String modName = "";
	
	public Debug() {}

	/**
	 * Prints out givin input under the givin mod name if a boolean returns true, then ends the line.
	 * @param s Object for printing.
	 */
	public static void println(Object s)
	{
		if(debugger)
			System.out.println("["+modName+"] "+s);
	}
	
	/**
	 * Prints out givin input under the givin mod name if a boolean returns true.
	 * @param s Object for printing.
	 */
	public static void print(Object s)
	{
		if(debugger)
			System.out.print(s);
	}
}