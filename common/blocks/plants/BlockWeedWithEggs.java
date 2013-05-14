package fishingCraft.common.blocks.plants;

import java.util.ArrayList;
import java.util.Random;

import fishingCraft.common.blocks.FCBlock;
import fishingCraft.common.items.FCItem;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Class for Weed blocks that contain eggs
 * @author Sharingan616
 *
 */
public class BlockWeedWithEggs extends BlockWaterPlant
{
    public static String name;

    public BlockWeedWithEggs(int id, String n)
    {
        super(id, n);
        this.blockHardness = 0.0F;
    }

    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
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

        dropBlockAsItem_do(world, x, y, z, new ItemStack(FCItem.frogEggs, n));
        world.setBlock(x, y, z, FCBlock.weed.blockID);
        return false;
    }

//	@Override
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
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 0, world.getBlockMetadata(x, y, z)));
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
