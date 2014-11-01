package fishingcraft.common.blocks.plants;

import java.util.ArrayList;
import java.util.Random;

import fishingcraft.common.blocks.FCBlock;
import fishingcraft.common.items.FCItems;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Class for Weed blocks that contain eggs
 * @author Sharingan616
 *
 */
public class BlockWeedWithEggs extends BlockWaterPlant
{
    public static String name;

    public BlockWeedWithEggs(String n)
    {
        super(n);
        this.blockHardness = 0.0F;
    }

    @Override
    /**
     * Run when the player removes the block.
     */
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        int i = this.rand.nextInt(100);
        int n = 0;

        if (i > 80)
        {
            n = 1;
        }
        else
        {
            n = 2;
        }

        dropBlockAsItem(world, x, y, z, new ItemStack(FCItems.frogeggs, n));
        world.setBlock(x, y, z, FCBlock.weed);
        return false;
    }

    /**
     * Determines whether or not the block can be farmed with shears.
     * @return the block cannot be sheared
     */
    public boolean isShearable()
    {
        return false;
    }

    @Override
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {}

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world,
			int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 0, world.getBlockMetadata(x, y, z)));
        return ret;
    }

    /**
     * Sets the name of the block
     * @param n - name of block
     */
    public void setName(String n)
    {
        this.name = n;
    }
}
