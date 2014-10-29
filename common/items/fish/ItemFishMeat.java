package fishingcraft.common.items.fish;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import fishingcraft.common.items.FCItems;
import fishingcraft.main.FishingCraft;

/**
 * This class defines the meat which you can obtain from fish. 
 * @author Sharingan616
 *
 */
public class ItemFishMeat extends ItemFish
{
    public ItemFishMeat(int par1, float par2, String n)
    {
        super(par1, par2, n);
        setTextureName(FishingCraft.MODID.toLowerCase()+":meat/"+n);
        this.setName(n);
    }

    public ItemFishMeat(int par1, String n)
    {
        super(par1, n);
        setTextureName(FishingCraft.MODID.toLowerCase()+":meat/"+n);
        this.setName(n);
    }
    
    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        //Reduce stack size of consumed fishMeat by 1
        --par1ItemStack.stackSize;
//        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }
    
    @Override
    public int getItemStackLimit()
    {
    	return 64;
    }
}