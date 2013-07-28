package fishingcraft.common.client;

import fishingcraft.common.CommonProxyFishingCraft;
import fishingcraft.common.items.FCItem;
import fishingcraft.common.renderer.RodRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Client Proxy class
 * @author Sharingan616
 *
 */
public class ClientProxyFishingCraft extends CommonProxyFishingCraft
{
    @Override
    public void registerRenderers()
    {
    	MinecraftForgeClient.registerItemRenderer(FCItem.woodenFishingRod.itemID, new RodRenderer());
        //Done!
    }
}
