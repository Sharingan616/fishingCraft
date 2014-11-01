/**
 * 
 */
package fishingcraft.common.achievements;

import fishingcraft.common.items.FCItems;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

/**
 * Contains all achievement Fishing Craft adds to the game.
 * @author Sharingan616
 */
public class FCAchievements {
	public static Achievement woodenFishingRod = new Achievement("achievement.woodenFishingRod", "woodenFishingRod", 0, 0, FCItems.woodenFishingRod, (Achievement)null);
	public static Achievement ironFishingRod = new Achievement("achievement.ironFishingRod", "ironFishingRod", 0, -2, FCItems.ironFishingRod, woodenFishingRod);
	public static Achievement firstFish = new Achievement("achievement.firstFish", "firstFish", 2, 0, FCItems.fishCarpRaw, woodenFishingRod);
	public static Achievement seaFish = new Achievement("achievement.seaFish", "seaFish", 2, 2, FCItems.fishYFTuna, firstFish);
	public static Achievement seaFish20lb = new Achievement("achievement.seaFish20lb", "seaFish20lb", 4, 3, /*TODO other fish */ FCItems.fishYFTuna, seaFish);
	public static Achievement freshFish20lb = new Achievement("achievement.freshFish20lb", "freshFish20lb", 2, -2, FCItems.fishPeaBassRaw, firstFish);
	public static Achievement bassEffect = new Achievement("achievement.bassEffect", "bassEffect", 4, -2, FCItems.fishBassRaw, freshFish20lb);	
	
	/**
	 * Registers all the previously described achievements
	 */
	public static void registerAchievments()
	{
		woodenFishingRod.registerStat();
		firstFish.registerStat();
		ironFishingRod.registerStat();
		seaFish.registerStat();
		seaFish20lb.registerStat();
		freshFish20lb.registerStat();
		bassEffect.registerStat();
	}
}
