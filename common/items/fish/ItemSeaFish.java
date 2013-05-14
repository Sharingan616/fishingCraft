package fishingCraft.common.items.fish;

import fishingCraft.common.CommonProxyFishingCraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * This class defines the sea fish items. They cannot be eaten.
 * Instead, they are crafted into fishing meat items.
 * @author Sharingan616
 *
 */
public class ItemSeaFish extends Item
{
    public String name = "";

    public ItemSeaFish(int par1, String n)
    {
        super(par1);
        this.setName(n);
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    public void setName(String n)
    {
        this.name = n;
        this.setUnlocalizedName(n);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("fishingCraft:fish/" + name);
    }
}
