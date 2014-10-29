package fishingcraft.common.eventHooks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import fishingcraft.common.achievements.FCAchievements;
import fishingcraft.common.items.FCItems;

public class EventCrafting {

	@SubscribeEvent
	public void RodCrafting(ItemCraftedEvent event)
	{
		if(event.crafting.getItem() == FCItems.woodenFishingRod)
			event.player.addStat(FCAchievements.woodenFishingRod, 1);
		else if(event.crafting.getItem() == FCItems.ironFishingRod)
			event.player.addStat(FCAchievements.ironFishingRod, 1);
	}

}
