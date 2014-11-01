package fishingcraft.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fishingcraft.common.achievements.FCAchievements;
import fishingcraft.common.blocks.FCBlock;
import fishingcraft.common.entity.projectile.EntityFishingHook;
import fishingcraft.common.eventHooks.EventCrafting;
import fishingcraft.common.eventHooks.EventLancesHoe;
import fishingcraft.common.items.FCItems;
import fishingcraft.common.recipes.FCRecipe;
import fishingcraft.common.renderer.BobberRenderer;
import fishingcraft.common.world.blockGen;
import fishingcraft.shar.util.Debug;

@Mod(modid = FishingCraft.MODID, version = FishingCraft.VERSION)

/**
 * Fishing Craft adds multiple fish, blocks, and other items into the Minecraft world,
 * as well as redesigns the fishing functionality.
 * 
 * The goal of this mod is to enhance the fishing experience within the game.
 * 
 * @author Sharingan616 (Benjamin Tovar-Prince @ http://benjamintovar-prince.com)
 * @version 2.0
 */
public class FishingCraft
{
    public static final String MODID = "FishingCraft";
    public static final String VERSION = "2.0";
    public static final boolean debug = true;
    private FCItems fcItem;
    private FCBlock fcBlock;
    private FCRecipe fcRecipe;
    
    public static CreativeTabs fcTab = new CreativeTabs("fishingcraft_tab")
    {
		public Item getTabIconItem() {
			return Items.fishing_rod;
		}
    };
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        Debug.modName = MODID;
        Debug.debugger = this.debug;
        fcBlock.registerBlockNames();
        fcRecipe.addRecipes();
        FCAchievements.registerAchievments();
    	AchievementPage.registerAchievementPage(new AchievementPage("Fishing Craft", new Achievement[]{FCAchievements.woodenFishingRod, FCAchievements.ironFishingRod, FCAchievements.firstFish, FCAchievements.seaFish, FCAchievements.bassEffect, FCAchievements.freshFish20lb, FCAchievements.seaFish20lb}));
        MinecraftForge.EVENT_BUS.register(new EventLancesHoe());
        FMLCommonHandler.instance().bus().register(new EventCrafting());
        RenderingRegistry.registerEntityRenderingHandler(EntityFishHook.class, new BobberRenderer());
     }
    @EventHandler
    public void postLoad(FMLPostInitializationEvent event)
    {
        GameRegistry.registerWorldGenerator(blockGenerator, 0);
    }
    
    public static blockGen blockGenerator = new blockGen();
}
