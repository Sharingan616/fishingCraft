package fishingcraft.common.blocks.other;

import java.awt.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fishingcraft.common.items.FCItems;
import fishingcraft.main.FishingCraft;
import fishingcraft.shar.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockBeeHive extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon side;
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.CLIENT)
	private IIcon bottom;
	private String dir = "/other/BeeHive";
	public BlockBeeHive(String name) {
		super(Material.ground);
		this.setCreativeTab(FishingCraft.fcTab);
		this.setBlockName(name);
		this.setHardness(3);
	}

	//TODO add Bee particles
	
	@SideOnly(Side.CLIENT)
	/**
	 * Returns different textures for different sides of the block
	 */
	public IIcon getIcon(int side, int par2){

		if(side == 3){
			return this.blockIcon;
		}
		else if(side == 1)
		{
			return this.top;
		}
		else if(side == 0)
		{
			return this.bottom;
		}
		else{
			return this.side;
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister){
		this.blockIcon = par1IIconRegister.registerIcon(FishingCraft.MODID+":"+dir+"_front");
		this.top = par1IIconRegister.registerIcon(FishingCraft.MODID+":"+dir+"_top");
		this.bottom = par1IIconRegister.registerIcon(FishingCraft.MODID+":"+dir+"_bottom");
		this.side = par1IIconRegister.registerIcon(FishingCraft.MODID+":"+dir+"_side");
	}

	@Override
	/**
	 * Returns the item the block should drop when destroyed.
	 */
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return FCItems.honeyComb;  
	}

	@Override
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random rand)
	{
		return 3;
	}


}
