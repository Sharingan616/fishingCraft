package fishingCraft.config;

import net.minecraftforge.common.Configuration;

/**
 * Defines all config definitions and ids, then saves it as a file.
 * @author Sharingan616
 *
 */
public class Config
{
    public static void getConfigurations(Configuration config)
    {
        weeds_ID = config.getBlock("weed", 3400).getInt();
        weedsWithEggs_ID = config.getBlock("weedWithEggs", 3401).getInt();
        cage_ID = config.getBlock("cage", 3402).getInt();
        beeHive_ID = config.getBlock("beeHive", 3403).getInt();
        placeholder_ID = config.getItem("placeholder", 3899).getInt();
        fishBones_ID = config.getItem("fishBones", 3900).getInt();
        worm_ID = config.getItem("worm", 3901).getInt();
        chickenWorm_ID = config.getItem("chickenWorm", 3902).getInt();
        tackleBox_ID = config.getItem("tackleBox", 3903).getInt();
        frogEggs_ID = config.getItem("frogEggs", 3904).getInt();
        fishSalmonRaw_ID = config.getItem("fishSalmonRaw", 4001).getInt();
        fishSalmonCooked_ID = config.getItem("fishSalmonCooked", 4002).getInt();
        fishCatRaw_ID = config.getItem("fishCatRaw", 4003).getInt();
        fishCatCooked_ID = config.getItem("fishCatCooked", 4004).getInt();
        fishBlueGillRaw_ID = config.getItem("fishBlueGillRaw", 4005).getInt();
        fishBlueGillCooked_ID = config.getItem("fishBlueGillCooked", 4006).getInt();
        fishSuckerRaw_ID = config.getItem("fishSuckerRaw", 4007).getInt();
        fishSuckerCooked_ID = config.getItem("fishSuckerCooked", 4008).getInt();
        fishCarpRaw_ID = config.getItem("fishCarpRaw", 4009).getInt();
        fishCarpCooked_ID = config.getItem("fishCarpCooked", 4010).getInt();
        fishBassRaw_ID = config.getItem("fishBassRaw", 4011).getInt();
        fishBassCooked_ID = config.getItem("fishBassCooked", 4012).getInt();
        fishPikeRaw_ID = config.getItem("fishPikeRaw", 4013).getInt();
        fishPikeCooked_ID = config.getItem("fishPikeCooked", 4014).getInt();
        fishPeaBassRaw_ID = config.getItem("fishPeaBassRaw", 4015).getInt();
        fishPeaBassCooked_ID = config.getItem("fishPeaBassCooked", 4016).getInt();
        fishSmallPiranhaRaw_ID = config.getItem("fishSmallPiranhaRaw", 4017).getInt();
        fishSmallPiranhaCooked_ID = config.getItem("fishSmallPiranhaCooked", 4018).getInt();
        fishGoldFishRaw_ID = config.getItem("fishGoldFishRaw", 4019).getInt();
        fishGoldFishCooked_ID = config.getItem("fishGoldFishCooked", 4020).getInt();
        fishYFTuna_ID = config.getItem("fishYFTuna", 4101).getInt();
        fishBFTuna_ID = config.getItem("fishBFTuna", 4102).getInt();
        rawTunaStrip_ID = config.getItem("rawTunaStrip", 4201).getInt();
        cookedTunaStrip_ID = config.getItem("cookedTunaStrip", 4202).getInt();
        rawFishMorsel_ID = config.getItem("rawFishMorsel", 4203).getInt();
        cookedFishMorsel_ID = config.getItem("cookedFishMorsel", 4204).getInt();
        woodenFishingRod_ID = config.getItem("woodenFishingRod", 4500).getInt();
        ironFishingRod_ID = config.getItem("ironFishingRod", 4501).getInt();
        woodenFishingRodWorm_ID = config.getItem("woodenFishingRodWorm", 4502).getInt();
        ironFishingRodWorm_ID = config.getItem("ironFishingRodWorm", 4503).getInt();
        woodenFishingRodBG_ID = config.getItem("woodenFishingRodBG", 4504).getInt();
        ironFishingRodBG_ID = config.getItem("ironFishingRodBG", 4505).getInt();
        woodenFishingRodMorsel_ID = config.getItem("woodenFishingRodMorsel", 4506).getInt();
        ironFishingRodMorsel_ID = config.getItem("ironFishingRodMorsel", 4507).getInt();
        woodenFishingRodFrogEggs_ID = config.getItem("woodenFishingRodFrogEggs", 4508).getInt();
        ironFishingRodFrogEggs_ID = config.getItem("ironFishingRodFrogEggs", 4509).getInt();
        woodenFishingRodSP_ID = config.getItem("woodenFishingRodSP", 4510).getInt();
        ironFishingRodSP_ID = config.getItem("ironFishingRodSP", 4511).getInt();
    }

    /*** Block IDs ***/
    public static int weeds_ID;
    public static int weedsWithEggs_ID;
    public static int cage_ID;
    public static int beeHive_ID;

    /*** Item IDs ***/
    public static int placeholder_ID;
    public static int frogEggs_ID;

    public static int fishBones_ID;
    public static int tackleBox_ID;

    public static int fishSalmonRaw_ID;
    public static int fishSalmonCooked_ID;
    public static int fishCatRaw_ID;
    public static int fishCatCooked_ID;
    public static int fishBlueGillRaw_ID;
    public static int fishBlueGillCooked_ID;
    public static int fishSuckerRaw_ID;
    public static int fishSuckerCooked_ID;
    public static int fishCarpRaw_ID;
    public static int fishCarpCooked_ID;
    public static int fishBassRaw_ID;
    public static int fishBassCooked_ID;
    public static int fishPikeRaw_ID;
    public static int fishPikeCooked_ID;
    public static int fishPeaBassRaw_ID;
    public static int fishPeaBassCooked_ID;
    public static int fishSmallPiranhaRaw_ID;
    public static int fishSmallPiranhaCooked_ID;
    public static int fishGoldFishRaw_ID;
    public static int fishGoldFishCooked_ID;

    public static int fishYelDoradoRaw_ID;
    public static int fishYelDoradoCooked_ID;
    public static int fishCobraSnakeRaw_ID;
    public static int fishCobraSnakeCooked_ID;

    public static int fishYFTuna_ID;
    public static int fishBFTuna_ID;

    public static int rawTunaStrip_ID;
    public static int cookedTunaStrip_ID;
    public static int rawFishMorsel_ID;
    public static int cookedFishMorsel_ID;

    public static int worm_ID;
    public static int chickenWorm_ID;

    public static int woodenFishingRod_ID;
    public static int ironFishingRod_ID;
    public static int woodenFishingRodWorm_ID;
    public static int ironFishingRodWorm_ID;
    public static int woodenFishingRodBG_ID;
    public static int ironFishingRodBG_ID;
    public static int woodenFishingRodMorsel_ID;
    public static int ironFishingRodMorsel_ID;
    public static int woodenFishingRodFrogEggs_ID;
    public static int ironFishingRodFrogEggs_ID;
    public static int woodenFishingRodSP_ID;
    public static int ironFishingRodSP_ID;
}
