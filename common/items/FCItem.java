package fishingCraft.common.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import fishingCraft.common.config.Config;
import fishingCraft.common.items.fish.ItemFish;
import fishingCraft.common.items.fish.ItemFishMeat;
import fishingCraft.common.items.fish.ItemSeaFish;
import fishingCraft.common.items.rods.ItemIronFishingRod;
import fishingCraft.common.items.rods.ItemWoodenFishingRod;
import fishingCraft.shar.util.Debug;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;

/**
 * Class responsible for adding all Fishing Craft items.
 * @author Sharingan616
 *
 */
public class FCItem
{
    //Misc
    public static final Item frogEggs = (new GenItem(Config.frogEggs_ID, "Frog_Eggs")).setFull3D().setCreativeTab(CreativeTabs.tabMisc);
    public static final Item fishBones = (new GenItem(Config.fishBones_ID, "Fish_Bones")).setFull3D().setCreativeTab(CreativeTabs.tabMisc);
    public static final Item worm = (new GenItem(Config.worm_ID, "Worm")).setFull3D().setCreativeTab(CreativeTabs.tabMisc);
    public static final Item chickenWorm = (new GenItem(Config.chickenWorm_ID, "Chicken_Worm"));

    //Fish
    public static final Item fishSalmonRaw = (new ItemFish(Config.fishSalmonRaw_ID, 2, 0.3F, "Salmon")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishSalmonCooked = (new ItemFish(Config.fishSalmonCooked_ID, 6, 0.6F, "SalmonCooked"));
    public static final Item fishCatRaw = (new ItemFish(Config.fishCatRaw_ID, 2, 0.3F, "Catfish")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishCatCooked = (new ItemFish(Config.fishCatCooked_ID, 6, 0.6F, "CatfishCooked"));
    public static final Item fishBlueGillRaw = (new ItemFish(Config.fishBlueGillRaw_ID, 1, 0.3F, "Bluegill")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishBlueGillCooked = (new ItemFish(Config.fishBlueGillCooked_ID, 3, 0.6F, "BluegillCooked"));
    public static final Item fishSuckerRaw = (new ItemFish(Config.fishSuckerRaw_ID, 2, 0.3F, "Suckerfish")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishSuckerCooked = (new ItemFish(Config.fishSuckerCooked_ID, 8, 0.6F, "SuckerfishCooked"));
    public static final Item fishCarpRaw = (new ItemFish(Config.fishCarpRaw_ID, 2, 0.3F, "Carp")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishCarpCooked = (new ItemFish(Config.fishCarpCooked_ID, 4, 0.6F, "CarpCooked"));
    public static final Item fishBassRaw = (new ItemFish(Config.fishBassRaw_ID, 2, 0.3F, "Bass")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishBassCooked = (new ItemFish(Config.fishBassCooked_ID, 6, 0.6F, "BassCooked"));
    public static final Item fishPikeRaw = (new ItemFish(Config.fishPikeRaw_ID, 2, 0.3F, "Pike")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishPikeCooked = (new ItemFish(Config.fishPikeCooked_ID, 6, 0.6F, "PikeCooked"));
    public static final Item fishPeaBassRaw = (new ItemFish(Config.fishPeaBassRaw_ID, 3, 0.3F, "Peacock_Bass")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishPeaBassCooked = (new ItemFish(Config.fishPeaBassCooked_ID, 6, 0.6F, "Peacock_BassCooked"));
    public static final Item fishSmallPiranhaRaw = (new ItemFish(Config.fishSmallPiranhaRaw_ID, 1, 0.3F, "Small_Piranha")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishSmallPiranhaCooked = (new ItemFish(Config.fishSmallPiranhaCooked_ID, 4, 0.6F, "Small_PiranhaCooked"));
    public static final Item fishGoldFishRaw = (new ItemFish(Config.fishGoldFishRaw_ID, 1, 0.3f, "Goldfish")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item fishGoldFishCooked = (new ItemFish(Config.fishGoldFishCooked_ID, 3, 0.6F, "GoldfishCooked"));
    public static final Item fishYFTuna = (new GenItem(Config.fishYFTuna_ID, "Yellowfin_Tuna-SEA")).setFull3D().setCreativeTab(CreativeTabs.tabFood);
    public static final Item fishBFTuna = (new GenItem(Config.fishBFTuna_ID, "Bluefin_Tuna-SEA")).setFull3D().setCreativeTab(CreativeTabs.tabFood);

    //Meat
    public static final Item rawFishMorsel = (new ItemFishMeat(Config.rawFishMorsel_ID, 1, "Fish_MorselRaw")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item cookedFishMorsel = (new ItemFishMeat(Config.cookedFishMorsel_ID, 2, "Fish_MorselCooked"));
    public static final Item rawTunaStrip = (new ItemFishMeat(Config.rawTunaStrip_ID, 2, "Tuna_StripRaw")).setPotionEffect(Potion.hunger.id, 30, 0, 0.8F);
    public static final Item cookedTunaStrip = (new ItemFishMeat(Config.cookedTunaStrip_ID, 4, "Tuna_StripCooked"));

    //Rods
    public static final Item woodenFishingRod = (new ItemWoodenFishingRod(Config.woodenFishingRod_ID, "Wooden_Fishing_Rod#B", CreativeTabs.tabTools));
    public static final Item woodenFishingRodBG = (new ItemWoodenFishingRod(Config.woodenFishingRodBG_ID, "Baited_Wooden_Fishing_Rod-blueGill", fishBlueGillRaw));
    public static final Item woodenFishingRodFrogEggs = (new ItemWoodenFishingRod(Config.woodenFishingRodFrogEggs_ID, "Baited_Wooden_Fishing_Rod-frogEggs", frogEggs));
    public static final Item woodenFishingRodMorsel = (new ItemWoodenFishingRod(Config.woodenFishingRodMorsel_ID, "Baited_Wooden_Fishing_Rod-morsel", rawFishMorsel));
    public static final Item woodenFishingRodSP = (new ItemWoodenFishingRod(Config.woodenFishingRodSP_ID, "Baited_Wooden_Fishing_Rod-srrbPiranha", fishSmallPiranhaRaw));
    public static final Item woodenFishingRodWorm = (new ItemWoodenFishingRod(Config.woodenFishingRodWorm_ID, "Baited_Wooden_Fishing_Rod-worm", worm));
    public static final Item ironFishingRod = (new ItemIronFishingRod(Config.ironFishingRod_ID, "Iron_Fishing_Rod#B", CreativeTabs.tabTools));
    public static final Item ironFishingRodBG = (new ItemIronFishingRod(Config.ironFishingRodBG_ID, "Baited_Iron_Fishing_Rod-blueGill", fishBlueGillRaw));
    public static final Item ironFishingRodFrogEggs = (new ItemIronFishingRod(Config.ironFishingRodFrogEggs_ID, "Baited_Iron_Fishing_Rod-frogEggs", frogEggs));
    public static final Item ironFishingRodMorsel = (new ItemIronFishingRod(Config.ironFishingRodMorsel_ID, "Baited_Iron_Fishing_Rod-morsel", rawFishMorsel));
    public static final Item ironFishingRodSP = (new ItemIronFishingRod(Config.ironFishingRodSP_ID, "Baited_Iron_Fishing_Rod-srrbPiranha", fishSmallPiranhaRaw));
    public static final Item ironFishingRodWorm = (new ItemIronFishingRod(Config.ironFishingRodWorm_ID, "Baited_Iron_Fishing_Rod-worm", worm));

    public static void addItemNames()
    {
        addRods();
        addFish();
        addSeaFish();
        addFishMeat();
        addMisc();
    }

    private static void addRods()
    {
        setupName(woodenFishingRod);
        setupName(woodenFishingRodBG);
        setupName(woodenFishingRodFrogEggs);
        setupName(woodenFishingRodMorsel);
        setupName(woodenFishingRodSP);
        setupName(woodenFishingRodWorm);
        setupName(ironFishingRod);
        setupName(ironFishingRodBG);
        setupName(ironFishingRodFrogEggs);
        setupName(ironFishingRodMorsel);
        setupName(ironFishingRodSP);
        setupName(ironFishingRodWorm);
    }

    private static void addFish()
    {
        setupName(fishSalmonRaw);
        setupName(fishSalmonCooked);
        setupName(fishCatRaw);
        setupName(fishCatCooked);
        setupName(fishBlueGillRaw);
        setupName(fishBlueGillCooked);
        setupName(fishSuckerRaw);
        setupName(fishSuckerCooked);
        setupName(fishCarpRaw);
        setupName(fishCarpCooked);
        setupName(fishBassRaw);
        setupName(fishBassCooked);
        setupName(fishPikeRaw);
        setupName(fishPikeCooked);
        setupName(fishPeaBassRaw);
        setupName(fishPeaBassCooked);
        setupName(fishSmallPiranhaRaw);
        setupName(fishSmallPiranhaCooked);
        setupName(fishGoldFishRaw);
        setupName(fishGoldFishCooked);
    }

    private static void addSeaFish()
    {
        setupName(fishYFTuna);
        setupName(fishBFTuna);
    }

    private static void addFishMeat()
    {
        setupName(rawFishMorsel);
        setupName(cookedFishMorsel);
        setupName(rawTunaStrip);
        setupName(cookedTunaStrip);
    }

    private static void addMisc()
    {
        setupName(frogEggs);
        setupName(fishBones);
        setupName(worm);
        setupName(chickenWorm);
    }

    private static void setupName(Item i)
    {
        String newName = "placeHolder";

        if (i instanceof ItemWoodenFishingRod)
        {
            newName = (((ItemWoodenFishingRod) i).name);
        }

        if (i instanceof ItemIronFishingRod)
        {
            newName = (((ItemIronFishingRod) i).name);
        }

        if (i instanceof ItemFish)
        {
            newName = (((ItemFish) i).name);
        }
        else if (i instanceof ItemSeaFish)
        {
            newName = (((ItemSeaFish) i).name);
        }
        else if (i instanceof GenItem)
        {
            newName = (((GenItem) i).name);
        }

        newName = removeStringAfterChar(newName, '-');
        newName = removeStringAfterChar(newName, '#');

        if (newName.startsWith("Baited_"))
        {
            newName = newName.substring("Baited_".length());
        }

        if (newName.endsWith("Cooked"))
        {
            newName = "Cooked " + newName.substring(0, newName.indexOf("Cooked"));
        }

        if (newName.endsWith("Raw"))
        {
            newName = "Raw " + newName.substring(0, newName.indexOf("Raw"));
        }

        newName = newName.replaceAll("_", " ");
        LanguageRegistry.addName(i, newName);
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

    public static String removeStringAfterChar(String s, char c)
    {
        if (checkForChar(s, c))
        {
            return s.substring(0, s.indexOf(c));
        }

        return s;
    }
}
