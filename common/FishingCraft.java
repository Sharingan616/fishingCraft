package fishingCraft.common;

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
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import fishingCraft.common.blocks.FCBlock;
import fishingCraft.common.entity.projectile.EntityFishingHook;
import fishingCraft.common.eventHooks.EventLancesHoe;
import fishingCraft.common.items.FCItem;
import fishingCraft.common.recipes.FCRecipe;
import fishingCraft.common.renderer.BobberRenderer;
import fishingCraft.common.world.blockGen;
import fishingCraft.config.Config;
import fishingCraft.shar.util.Debug;

@Mod(modid = "FishingCraft", name = "Fishing Craft", version = "1.2.4")
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

    @SidedProxy(clientSide = "fishingCraft.common.client.ClientProxyFishingCraft", serverSide = "fishingCraft.common.CommonProxyFishingCraft")
    public static CommonProxyFishingCraft proxy;
    public static FCItem fcItem;
    public static FCRecipe fcRecipe;
    public static FCBlock fcBlock;
    @PreInit
    public void preLoad(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        Config.getConfigurations(config);
        config.save();
    }

    @Init
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

    @PostInit
    public void postLoad(FMLPostInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(blockGenerator);
    }

    public static blockGen blockGenerator = new blockGen();
}
