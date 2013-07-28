package fishingcraft.common.items.rods;

import java.util.List;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fishingcraft.common.dictionary.ItemDictionary;
import fishingcraft.common.entity.projectile.EntityFishingHook;
import fishingcraft.shar.util.Debug;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * Class for the Wooden Fishing rod item.a
 * @author Sharingan616
 *
 */
public class ItemWoodenFishingRod extends ItemFishingRod
{
    public String name = "";
    public boolean canBreak;
    public Item theBait;
	public boolean MouseButtonIsDown = false;
	public boolean isThereAFish = false;

    public ItemWoodenFishingRod(int par1)
    {
        super(par1);
    }

    public ItemWoodenFishingRod(int id, String n, CreativeTabs tab, Item bait)
    {
        super(id);
        this.setCreativeTab(tab);
        this.setMaxDamage(64);
        this.canBreak = true;
        this.setName(n);
        this.theBait = bait;
    }

    public ItemWoodenFishingRod(int id, String n, Item bait)
    {
        super(id);
        this.setCreativeTab(null);
        this.setMaxDamage(64);
        this.canBreak = true;
        this.setName(n);
        this.theBait = bait;
    }

    public ItemWoodenFishingRod(int id, String n, CreativeTabs tab)
    {
        super(id);
        this.setCreativeTab(tab);
        this.setMaxDamage(64);
        this.canBreak = false;
        this.setName(n);
        this.theBait = null;
    }

    public ItemWoodenFishingRod(int id, String n)
    {
        super(id);
        this.setCreativeTab(null);
        this.setMaxDamage(64);
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
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if(!Mouse.isButtonDown(1))
        {
        	this.MouseButtonIsDown = false;
        	this.isThereAFish = false;
        }
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        //TODO change icon when rod is cast
        //Icon ico = par1ItemStack.getItem().getIcon(par1ItemStack, 0, par3EntityPlayer, par1ItemStack, par1ItemStack.getItemDamage());
        if (par3EntityPlayer.fishEntity != null)
        {
            int i = par3EntityPlayer.fishEntity.catchFish();

            if (theBait == null)
            {
            	//TODO review this snippit
                par1ItemStack.damageItem(i, par3EntityPlayer);
            }

            //par3EntityPlayer.swingItem();
        }
        else if(!this.MouseButtonIsDown)
        {
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(new EntityFishingHook(par2World, par3EntityPlayer, par1ItemStack.getItem(), this.theBait));
            }

            par3EntityPlayer.swingItem();
        }

        return par1ItemStack;
    }

    public void setName(String n)
    {
        this.name = n;
        this.setUnlocalizedName(name);
        ItemDictionary.add(this);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        String folder = "";
        //if(this.theBait == null)
        folder = "rods/wooden";
        //else
        //	folder = "rods/wooden/baited";
        itemIcon = iconRegister.registerIcon("fishingCraft:" + folder + "/" + "Wooden_Fishing_Rod#A");
    }
}
