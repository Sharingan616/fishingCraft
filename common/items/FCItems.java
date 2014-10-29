package fishingcraft.common.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import fishingcraft.common.items.fish.ItemFish;
import fishingcraft.common.items.fish.ItemFishMeat;
import fishingcraft.common.items.fish.ItemSeaFish;
import fishingcraft.common.items.rods.ItemIronFishingRod;
import fishingcraft.common.items.rods.ItemWoodenFishingRod;
import fishingcraft.main.FishingCraft;
import fishingcraft.shar.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

/**
 * Class responsible for adding all Fishing Craft items.
 * @author Sharingan616
 *
 */
public class FCItems
{
	//Misc
	public static final Item worm = new GenItem("Worm").setFull3D();
	public static final Item fishbones = new GenItem("Fish_Bones").setFull3D();
	public static final Item frogeggs = new GenItem("Frog_Eggs").setFull3D();
	
    //Fish
    //public static final Item fishSalmonRaw = (new ItemFish(2, "Salmon")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishCatRaw = (new ItemFish(2, "Catfish")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishBlueGillRaw = (new ItemFish(1, "Bluegill")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishSuckerRaw = (new ItemFish(2, "Suckerfish")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishCarpRaw = (new ItemFish(2, "Carp")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishBassRaw = (new ItemFish(2, "Bass")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishPikeRaw = (new ItemFish(2, "Pike")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishPeaBassRaw = (new ItemFish(3, "Peacock_Bass")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishSmallPiranhaRaw = (new ItemFish(1, "Small_Piranha")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishGoldFishRaw = (new ItemFish(1, "Goldfish")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishTenchRaw = (new ItemFish(2, "Tench")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishMackerelRaw = (new ItemFish(2, "Mackerel")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishYFTuna = (new ItemSeaFish("Yellowfin_Tuna-SEA")).setFull3D();
    public static final Item fishBFTuna = (new ItemSeaFish("Bluefin_Tuna-SEA")).setFull3D(); 
    public static final Item fishMarlin = (new ItemSeaFish("Marlin")).setFull3D();
    public static final Item fishMahi = (new ItemSeaFish("Mahi_Mahi")).setFull3D();
    //public static final Item fishSalmonCooked = (new ItemFish(6, "SalmonCooked"));
    public static final Item fishCatCooked = new ItemFish(6, "CatfishCooked");
    public static final Item fishBlueGillCooked = new ItemFish(3, "BluegillCooked");
    public static final Item fishSuckerCooked = new ItemFish(8, "SuckerfishCooked");
    public static final Item fishCarpCooked = new ItemFish(4, "CarpCooked");
    public static final Item fishBassCooked = new ItemFish(6, "BassCooked");
    public static final Item fishPikeCooked = new ItemFish(6, "PikeCooked");
    public static final Item fishPeaBassCooked = new ItemFish(6, "Peacock_BassCooked");
    public static final Item fishSmallPiranhaCooked = new ItemFish(4, "Small_PiranhaCooked");
    public static final Item fishGoldFishCooked = new ItemFish(3, "GoldfishCooked");
    public static final Item fishTenchCooked = new ItemFish(6, "TenchCooked");
    public static final Item fishMackerelCooked = (new ItemFish(4, "MackerelCooked"));

    //Meat
    public static final Item rawFishMorsel = new ItemFishMeat(1, "Fish_MorselRaw").setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item cookedFishMorsel = (new ItemFishMeat(2, "Fish_MorselCooked"));
    public static final Item rawTunaStrip = (new ItemFishMeat(2, "Tuna_StripRaw")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item cookedTunaStrip = (new ItemFishMeat(4, "Tuna_StripCooked"));
    
    //Rods
    public static final Item woodenFishingRod = (new ItemWoodenFishingRod("Wooden_Fishing_Rod#B"));
    public static final Item woodenFishingRodBG = (new ItemWoodenFishingRod("Baited_Wooden_Fishing_Rod-blueGill", fishBlueGillRaw));
    public static final Item woodenFishingRodFrogEggs = (new ItemWoodenFishingRod("Baited_Wooden_Fishing_Rod-frogEggs", frogeggs));
    public static final Item woodenFishingRodMorsel = (new ItemWoodenFishingRod("Baited_Wooden_Fishing_Rod-morsel", rawFishMorsel));
    public static final Item woodenFishingRodSP = (new ItemWoodenFishingRod("Baited_Wooden_Fishing_Rod-srrbPiranha", fishSmallPiranhaRaw));
    public static final Item woodenFishingRodWorm = (new ItemWoodenFishingRod("Baited_Wooden_Fishing_Rod-worm", worm));
    public static final Item ironFishingRod = (new ItemIronFishingRod("Iron_Fishing_Rod#B"));
    public static final Item ironFishingRodBG = (new ItemIronFishingRod("Baited_Iron_Fishing_Rod-blueGill", fishBlueGillRaw));
    public static final Item ironFishingRodFrogEggs = (new ItemIronFishingRod("Baited_Iron_Fishing_Rod-frogEggs", frogeggs));
    public static final Item ironFishingRodMorsel = (new ItemIronFishingRod("Baited_Iron_Fishing_Rod-morsel", rawFishMorsel));
    public static final Item ironFishingRodSP = (new ItemIronFishingRod("Baited_Iron_Fishing_Rod-srrbPiranha", fishSmallPiranhaRaw));
    public static final Item ironFishingRodWorm = (new ItemIronFishingRod("Baited_Iron_Fishing_Rod-worm", worm));    
}
