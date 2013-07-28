package fishingcraft.common.items.fish;

import java.util.List;
import java.util.Random;

import fishingcraft.common.CommonProxyFishingCraft;
import fishingcraft.common.dictionary.ItemDictionary;
import fishingcraft.common.items.FCItem;
import fishingcraft.shar.util.Debug;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

/**
 * This class defines the normal fish items.
 * @author Sharingan616
 *
 */
public class ItemFish extends ItemFood
{
	public double weight;
    public String name = "";

    public ItemFish(int par1, int par2, float par3, boolean par4, String n)
    {
        super(par1, par2, par3, par4);
        this.setName(n);
    }

    public ItemFish(int par1, int par2, boolean par3, String n)
    {
        super(par1, par2, par3);
        this.setName(n);
    }

    public ItemFish(int par1, int par2, float par3, String n)
    {
        super(par1, par2, par3, false);
        this.setName(n);
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        //Drop Fish Bones
        ForgeHooks.onPlayerTossEvent(par3EntityPlayer, new ItemStack(FCItem.fishBones));
        //Reduce stack size of consumed fish by 1
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
        itemIcon = iconRegister.registerIcon("fishingCraft:fish/" + name);
    }
    
    public void setWeight(double w)
    {
    	this.weight = w;
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        	par3List.add("Weight: "+this.weight);
    }
    
    @Override
    public int getItemStackLimit()
    {
    	return 1;
    }
}
