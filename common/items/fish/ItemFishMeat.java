package fishingCraft.common.items.fish;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import fishingCraft.common.dictionary.ItemDictionary;
import fishingCraft.common.items.FCItem;

/**
 * This class defines the meat which you can obtain from fish. 
 * @author Sharingan616
 *
 */
public class ItemFishMeat extends ItemFish
{
    public ItemFishMeat(int par1, int par2, float par3, boolean par4, String n)
    {
        super(par1, par2, par3, par4, n);
        this.setName(n);
    }

    public ItemFishMeat(int par1, int par2, boolean par3, String n)
    {
        super(par1, par2, par3, n);
        this.setName(n);
    }

    public ItemFishMeat(int par1, int par2, String n)
    {
        super(par1, par2, false, n);
        this.setName(n);
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        //Reduce stack size of consumed fishMeat by 1
        --par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }

    public void setName(String n)
    {
        this.name = n;
        this.setUnlocalizedName(n);
        ItemDictionary.add(this);
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("fishingCraft:meat/" + name);
    }
}