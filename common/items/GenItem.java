package fishingCraft.common.items;

import fishingCraft.common.CommonProxyFishingCraft;
import fishingCraft.common.dictionary.ItemDictionary;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

/**
 * Class for generic mod items.
 * @author Sharingan616
 *
 */
public class GenItem extends Item
{
    public String name = "";

    public GenItem(int par1, String n)
    {
        super(par1);
        this.setName(n);
    }

    public void setName(String n)
    {
        this.name = n;
        this.setUnlocalizedName(n);
        ItemDictionary.add(this);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        String folder = "misc";

        if (name.endsWith("SEA"))
        {
            folder = "fish/sea";
        }

        itemIcon = iconRegister.registerIcon("fishingCraft:" + folder + "/" + name);
    }
}
