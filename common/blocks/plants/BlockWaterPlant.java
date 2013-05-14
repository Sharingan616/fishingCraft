package fishingCraft.common.blocks.plants;

import java.util.ArrayList;
import java.util.Random;

import fishingCraft.common.CommonProxyFishingCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IShearable;

/**
 * Class for all water type plants.
 * @author Sharingan616
 *
 */
public class BlockWaterPlant extends BlockFlower implements IShearable
{
    public static String name;
    protected Random rand = new Random();

    public BlockWaterPlant(int id, String n)
    {
        super(id, Material.water);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
        this.blockHardness = 0.02F;
        this.setStepSound(soundGrassFootstep);
        this.setName(n);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID)
    {
        if (!canBlockStay(world, x, y, z))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return (par1World.getBlockId(par2, par3 + 1, par4) == Block.waterStill.blockID && par1World.getBlockId(par2, par3 - 1, par4) == Block.dirt.blockID);
    }

    @Override
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        if (par1World.getBlockId(par2, par3, par4) == Block.waterStill.blockID)
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
    public boolean isShearable(ItemStack item, World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }

    public void setName(String n)
    {
        this.name = n;
        this.setUnlocalizedName(n);
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        String folder = "plants";
        this.blockIcon = par1IconRegister.registerIcon("fishingCraft:" + folder + "/" + name);
    }
}
