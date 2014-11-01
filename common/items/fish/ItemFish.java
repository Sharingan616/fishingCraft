package fishingcraft.common.items.fish;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;

import fishingcraft.common.entity.EntityDroppedFish;
import fishingcraft.common.items.FCItems;
import fishingcraft.main.FishingCraft;
import fishingcraft.shar.util.Debug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
//import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

/**
 * This class defines the normal fish items.
 * @author Sharingan616
 *
 */
public class ItemFish extends ItemFood
{
    public String name = "";

    /**
     * Constructor for ItemFish
     * @param healAmount
     * @param isWolfsFavoriteMeat
     * @param n
     */
    public ItemFish(int healAmount, float isWolfsFavoriteMeat, String n)
    {
    	super(healAmount, isWolfsFavoriteMeat, false);
    	this.setName(n);
        this.setMaxDamage(-10000);
        GameRegistry.registerItem(this,n);
        setTextureName(FishingCraft.MODID.toLowerCase()+":fish/"+n);
    	this.setCreativeTab(FishingCraft.fcTab);
    }
    
    /**
     * Second constructor for ItemFish. With this method, you do not have to define
     * whether or not the fish is a favorite food for wolves.
     * @param healamount
     * @param n
     */
    public ItemFish(int healamount, String n)
    {
    	super(healamount, false);
    	this.setName(n);
        this.setMaxDamage(-10000);
        GameRegistry.registerItem(this,n);
        setTextureName(FishingCraft.MODID.toLowerCase()+":fish/"+n);
    	this.setCreativeTab(FishingCraft.fcTab);
    }

    /**
     * Runs when the item is eaten
     */
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        //Reduce stack size of consumed fish by 1
    	double weight = this.getWeight(stack);
    	int calculation = (int)(weight/weight)*100;
    	
    	this.setDamage(stack, getDamage(stack)+calculation);
        Debug.println("Damage: "+calculation+", Weight: "+weight);
        if(this.getWeight(stack) <= 0){
            //Drop Fish Bones
            ForgeHooks.onPlayerTossEvent(player, new ItemStack(FCItems.fishbones), true);
            //Destroy Item
        	--stack.stackSize;
        }
        player.getFoodStats().func_151686_a(this, stack);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(stack, world, player);
        return stack;
    }

    /**
     * Sets the name of the item
     * @param n - item name
     */
    public void setName(String n)
    {
        this.name = n;
        this.setUnlocalizedName(n);
    }
    
    /**
     * Sets the weight of the item. The weight is based off of a negative amount of damage.
     * The damage must be negative in order for the health bar to not be rendered.
     * @param w - weight of the item
     * @param stack - item stack
     */
    public void setWeight(double w, ItemStack stack)
    {
    	int calculation = (int)((this.getWeight(stack)-w)*100.0);
    	Debug.println("Calculation: "+calculation);
    	this.setDamage(stack, calculation);
    }
    
    public double getWeight(ItemStack stack)
    {
    	return (Math.abs(this.getMaxDamage())-this.getDamage(stack))/100.0;
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	if(this.getWeight(par1ItemStack) != 0)
    		par3List.add("Weight: "+this.getWeight(par1ItemStack)+" lbs.");
    }
    
    @Override
    public int getItemStackLimit()
    {
    	return 1;
    }
    
    //TODO custom entity
//    public boolean hasCustomEntity(ItemStack stack)
//    {
//        return true;
//    }
//    
//    public Entity createEntity(World world, Entity location, ItemStack itemstack)
//    {
//    	Debug.println("Dropping fish.");
//        return new EntityItem(world, location.posX, location.posY, location.posZ, itemstack);
//    }
    
}
