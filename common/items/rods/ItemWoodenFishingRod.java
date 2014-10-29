package fishingcraft.common.items.rods;

import java.util.List;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fishingcraft.common.entity.projectile.EntityFishingHook;
import fishingcraft.main.FishingCraft;
import fishingcraft.shar.util.Debug;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
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
	private Item theBait;
	public boolean MouseButtonIsDown = false;
	public boolean isThereAFish = false;
	@SideOnly(Side.CLIENT)
	private IIcon theIcon;

	public ItemWoodenFishingRod()
	{
		super();
	}

	public ItemWoodenFishingRod(String n, Item bait)
	{
		super();
		this.setCreativeTab(FishingCraft.fcTab);
		this.setMaxDamage(64);
		this.canBreak = true;
		this.setName(n);
		this.theBait = bait;
		this.setTextureName(FishingCraft.MODID+":rods/wooden/Wooden_Fishing_Rod#A");
		//this.setTextureName(FishingCraft.MODID+":rods/wooden/baited/"+n);
		GameRegistry.registerItem(this, n);
	}

	public ItemWoodenFishingRod(String n)
	{
		super();
		this.setCreativeTab(FishingCraft.fcTab);        
		this.setMaxDamage(64);
		this.canBreak = false;
		this.setName(n);
		this.theBait = null;
		this.setTextureName(FishingCraft.MODID+":rods/wooden/Wooden_Fishing_Rod#A");
		//this.setTextureName(FishingCraft.MODID+":rods/wooden/"+n);
		GameRegistry.registerItem(this, n);
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		if (this.theBait != null)
		{
			par3List.add("Bait: " + this.theBait.getItemStackDisplayName(par1ItemStack));
		}
	}
	
	public Item addBait(Item bait)
	{
		this.theBait = bait;
		
		return this;
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
		IIcon ico = par1ItemStack.getItem().getIcon(par1ItemStack, 0, par3EntityPlayer, par1ItemStack, par1ItemStack.getItemDamage());
		if (par3EntityPlayer.fishEntity != null)
		{
			int i = par3EntityPlayer.fishEntity.func_146034_e();

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
				par2World.spawnEntityInWorld(new EntityFishingHook(par2World, par3EntityPlayer, this, theBait));
			}

			par3EntityPlayer.swingItem();
		}

		return par1ItemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconReg)
	{

		if(this instanceof ItemIronFishingRod)
			this.itemIcon = iconReg.registerIcon(this.getIconString());
		else
			this.itemIcon = iconReg.registerIcon(this.getIconString());
		//this.itemIcon = iconReg.registerIcon("FishingCraft:rods/wooden/Wooden_Fishing_Rod#A");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon func_94597_g()
	{
		return this.theIcon;
	}

	public void setName(String n)
	{
		this.name = n;
		this.setUnlocalizedName(name);
	}

	public Item getBait()
	{
		return theBait;
	}
}
