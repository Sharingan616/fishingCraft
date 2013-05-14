package fishingCraft.common.client;

import fishingCraft.common.CommonProxyFishingCraft;
import fishingCraft.common.items.FCItem;
import fishingCraft.common.renderer.RodRenderer;
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
        //TODO MinecraftForgeClient.registerItemRenderer(FCItem.woodenFishingRod.itemID, new RodRenderer());
    }
}
