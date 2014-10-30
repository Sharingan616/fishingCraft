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
		super(Material.wood);
		this.setCreativeTab(FishingCraft.fcTab);
		this.setBlockName(name);
		this.setHardness(3);
	}

	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		for (int var6 = 0; var6 < 3; ++var6)
		{
			for(int i = 0; i < 10; ++i)
			{
				double var10000 = (double)((float)par2 + par5Random.nextFloat());
				double var9 = (double)((float)par3 + par5Random.nextFloat());
				var10000 = (double)((float)par4 + par5Random.nextFloat());
				double var13 = 0.0D;
				double var15 = 0.0D;
				double var17 = 0.0D;
				int var19 = par5Random.nextInt(2) * 2 - 1;
				int var20 = par5Random.nextInt(2) * 2 - 1;
				var13 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				var15 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				var17 = ((double)par5Random.nextFloat() - 0.5D) * 0.125D;
				double var11 = (double)par4 + 0.5D + 0.25D * (double)var20;
				var17 = (double)(par5Random.nextFloat() * 1.0F * (float)var20);
				double var7 = (double)par2 + 0.5D + 0.25D * (double)var19;
				var13 = (double)(par5Random.nextFloat() * 1.0F * (float)var19);
				//TODO Add Bee particles
				//par1World.spawnParticle("portal", var7, var9, var11, var13, var15, var17);
			}
		}
	}

	@SideOnly(Side.CLIENT)
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
