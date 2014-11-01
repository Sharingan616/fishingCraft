package fishingcraft.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fishingcraft.main.FishingCraft;
import net.minecraft.item.Item;

/**
 * Class for generic mod items.
 * @author Sharingan616
 *
 */
public class GenItem extends Item
{
    public String name = "";

    /**
     * Constructor for GenItem
     * @param n
     */
    public GenItem(String n)
    {
        super();
        this.setName(n);
        this.setCreativeTab(FishingCraft.fcTab);
        GameRegistry.registerItem(this, n);
        setTextureName(FishingCraft.MODID.toLowerCase()+":misc/"+n);
    }

    public void setName(String n)
    {
        this.name = n;
        this.setUnlocalizedName(n);
    }

//    @Override
//    public void registerIcons(IconRegister iconRegister)
//    {
//        String folder = "misc";
//
//        if (name.endsWith("SEA"))
//        {
//            folder = "fish/sea";
//        }
//
//        itemIcon = iconRegister.registerIcon("fishingCraft:" + folder + "/" + name);
//    }
}
