package fishingcraft.common.renderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

/**
 * Custom rendering class for rods.
 * Incomplete and unused.
 * @author Sharingan616
 *
 */
public class RodRenderer implements IItemRenderer
{
    private static RenderItem renderItem = new RenderItem();

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType type)
    {
        return type == ItemRenderType.EQUIPPED;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data)
    {
        Icon iconIndex = itemStack.getIconIndex();
        //renderItem.renderIcon(0, 0, iconIndex, 1, 1);
    }
}
