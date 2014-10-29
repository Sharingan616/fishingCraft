package fishingcraft.common.items.fish;

import java.util.List;

import fishingcraft.common.items.GenItem;
import fishingcraft.main.FishingCraft;
import fishingcraft.shar.util.Debug;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * This class defines the sea fish items. They cannot be eaten.
 * Instead, they are crafted into fishing meat items.
 * @author Sharingan616
 *
 */
public class ItemSeaFish extends GenItem
{
    public String name = "";

    public ItemSeaFish(String n)
    {
        super(n);
        this.setMaxDamage(-10000);
        setTextureName(FishingCraft.MODID.toLowerCase()+":fish/sea/"+n);
    }
    
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
}
