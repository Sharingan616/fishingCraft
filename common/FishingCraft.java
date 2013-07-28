package fishingcraft.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fishingcraft.common.blocks.FCBlock;
import fishingcraft.common.config.Config;
import fishingcraft.common.entity.projectile.EntityFishingHook;
import fishingcraft.common.eventHooks.EventLancesHoe;
import fishingcraft.common.items.FCItem;
import fishingcraft.common.recipes.FCRecipe;
import fishingcraft.common.renderer.BobberRenderer;
import fishingcraft.common.world.blockGen;
import fishingcraft.shar.util.Debug;

@Mod(modid = "fishingcraft", name = "Fishing Craft", version = "2.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)


/**
 * Fishing Craft adds multiple fish, blocks, and other items into the Minecraft world,
 * as well as redesigns the fishing functionality.
 * 
 * The goal of this mod is to enhance the fishing experience within the game.
 * 
 * @author Sharingan616 (Benjamin Tovar-Prince @ http://benjamintovar-prince.com)
 * @version 1.2.4
 */
public class FishingCraft
{
    public static String version = "1.2.4";
    public static boolean isLotsOfFoodInstalled = false;
    @Instance("FishingCraft")
    public static FishingCraft instance;

    @SidedProxy(clientSide = "fishingcraft.common.client.ClientProxyFishingCraft", serverSide = "fishingcraft.common.CommonProxyFishingCraft")
    public static CommonProxyFishingCraft proxy;
    public static FCItem fcItem;
    public static FCRecipe fcRecipe;
    public static FCBlock fcBlock;
    @EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        Config.getConfigurations(config);
        config.save();
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        Debug.modName = "Fishing Craft";
        Debug.debugger = true;
        fcBlock.addBlockNames();
        fcItem.addItemNames();
        fcRecipe.addRecipes();
        MinecraftForge.EVENT_BUS.register(new EventLancesHoe());
        RenderingRegistry.registerEntityRenderingHandler(EntityFishHook.class, new BobberRenderer());
        List<ModContainer> ActiveList = Loader.instance().getActiveModList();

        //Lots Of Food mod support (temp removed)
        //TODO add back in LOF support
        for (int i = 0; i < ActiveList.size(); i++)
        {
            if (ActiveList.get(i).getName().equals("mod_aliments"))
            {
                isLotsOfFoodInstalled = true;
            }
        }
        
        proxy.registerRenderers();
    }

    @EventHandler
    public void postLoad(FMLPostInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(blockGenerator);
    }

    public static blockGen blockGenerator = new blockGen();
}
