package fishingCraft.common.items.rods;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Class for the Iron Fishing rod item.
 * Builds off of the wooden fishing rod class.
 * @author Sharingan616
 *
 */
public class ItemIronFishingRod extends ItemWoodenFishingRod
{
    public ItemIronFishingRod(int par1)
    {
        super(par1);
    }

    public ItemIronFishingRod(int id, String n, CreativeTabs tab, Item bait)
    {
        super(id);
        this.setCreativeTab(null);
        this.setMaxDamage(128);
        this.canBreak = false;
        this.setName(n);
        this.theBait = bait;
    }

    public ItemIronFishingRod(int id, String n, Item bait)
    {
        super(id);
        this.setCreativeTab(null);
        this.setMaxDamage(128);
        this.canBreak = false;
        this.setName(n);
        this.theBait = bait;
    }

    public ItemIronFishingRod(int id, String n, CreativeTabs tab)
    {
        super(id);
        this.setCreativeTab(tab);
        this.setMaxDamage(128);
        this.canBreak = false;
        this.setName(n);
        this.theBait = null;
    }

    public ItemIronFishingRod(int id, String n)
    {
        super(id);
        this.setCreativeTab(null);
        this.setMaxDamage(128);
        this.canBreak = false;
        this.setName(n);
        this.theBait = null;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (this.theBait != null)
        {
            par3List.add("Bait: " + this.theBait.getItemDisplayName(par1ItemStack));
        }
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        String folder = "";
        //if(this.theBait == null)
        folder = "rods/iron";
        //else
        //	folder = "rods/iron/baited";
        itemIcon = iconRegister.registerIcon("fishingCraft:" + folder + "/" + "Iron_Fishing_Rod#A");
    }
}
