package fishingcraft.common.items.rods;

import java.util.List;

import fishingcraft.main.FishingCraft;

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
	private Item theBait;
    public ItemIronFishingRod()
    {
        super();
    }
    
    public ItemIronFishingRod(String n)
    {
        super(n);
        this.setMaxDamage(128);
        this.canBreak = false;
        this.setName(n);
        this.theBait = null;
		this.setTextureName(FishingCraft.MODID+":rods/iron/Iron_Fishing_Rod#A");
        //this.setTextureName(FishingCraft.MODID+":rods/iron/"+n);
    }

    public ItemIronFishingRod(String n, Item bait)
    {
        super(n, bait);
        this.setMaxDamage(128);
        this.canBreak = false;
        this.setName(n);
        this.theBait = bait;
		this.setTextureName(FishingCraft.MODID+":rods/iron/Iron_Fishing_Rod#A");
        //this.setTextureName(FishingCraft.MODID+":rods/iron/baited/"+n);
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (this.theBait != null)
        {
            par3List.add("Bait: " + this.theBait.getItemStackDisplayName(par1ItemStack));
        }
    }
}
