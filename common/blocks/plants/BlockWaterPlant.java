package fishingcraft.common.blocks.plants;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;

import fishingcraft.main.FishingCraft;
import fishingcraft.shar.util.Debug;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

/**
 * Class for all water type plants.
 * @author Sharingan616
 *
 */
public class BlockWaterPlant extends BlockBush implements IShearable
{
    public static String name;
    protected Random rand = new Random();

    public BlockWaterPlant(String n)
    {
        super(Material.water);
        this.setCreativeTab(FishingCraft.fcTab);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
        this.blockHardness = 0.02F;
        this.setStepSound(soundTypeGrass);
        this.setBlockName(n);
        this.setBlockTextureName(FishingCraft.MODID+":plants/"+n);
    }

//    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID)
//    {
//        if (!canBlockStay(world, x, y, z))
//        {
//            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
//            world.setBlockToAir(x, y, z);
//        }
//    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
    	//Debug.println("Can block stay? "+ (par1World.getBlock(par2, par3 + 1, par4) == Blocks.water && par1World.getBlock(par2, par3 - 1, par4) == Blocks.dirt));
        return (par1World.getBlock(par2, par3 + 1, par4) == Blocks.water && par1World.getBlock(par2, par3 - 1, par4) == Blocks.dirt);
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        if (par1World.getBlock(par2, par3, par4) == Blocks.water)
        {
            return super.canPlaceBlockAt(par1World, par2, par3, par4);
        }
        else
        {
            return false;
        }
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }

    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return null;
    }

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x,
			int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world,
			int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 0, world.getBlockMetadata(x, y, z)));
        return ret;
	}
}
