package fishingcraft.common.entity;

import fishingcraft.common.achievements.FCAchievements;
import fishingcraft.common.items.fish.ItemFish;
import fishingcraft.common.items.fish.ItemSeaFish;
import fishingcraft.shar.util.Debug;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

/**
 * @author Sharingan616
 * This class is an extension of EntityItem, but with custom methods.
 */
public class EntityFish extends EntityItem {
	
	private double fishWeight;

	public EntityFish(World world, double xCoord, double zCoord, double yCoord) {
		super(world, xCoord, yCoord, zCoord);
	}

	public EntityFish(World world, double xCoord, double zCoord, double yCoord,
			ItemStack stack) {
		super(world, xCoord, zCoord, yCoord, stack);
	}
	
	public EntityFish(World world, double xCoord, double zCoord, double yCoord, ItemStack stack, double weight) {
		super(world, xCoord, zCoord, yCoord, stack);
		this.setWeight(weight);
	}

	public EntityFish(World world) {
		super(world);
	}
	
	public void setWeight(double w) {
		this.fishWeight = w;
	}
	
    /**
     * Called by a player entity when they collide with an entity
     */
	@Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (!this.worldObj.isRemote)
        {
            if (this.delayBeforeCanPickup > 0)
            {
                return;
            }

            ItemStack itemstack = this.getEntityItem();
            
            if(itemstack.getItem() instanceof ItemFish)
            {
            	ItemFish item = (ItemFish)itemstack.getItem();
            	item.setWeight(this.fishWeight, itemstack);
            }
            else if(itemstack.getItem() instanceof ItemSeaFish)
            {
            	ItemSeaFish item = (ItemSeaFish)itemstack.getItem();
            	item.setWeight(this.fishWeight, itemstack);
            }
             
            int i = itemstack.stackSize;

            if (this.delayBeforeCanPickup <= 0 && (i <= 0 || player.inventory.addItemStackToInventory(itemstack)))
            {
            	this.setAchievements(itemstack, player);
                //GameRegistry.onPickupNotification(player, this);

                this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                player.onItemPickup(this, i);

                if (itemstack.stackSize <= 0)
                    this.setDead();
            }
        }
    }

	private void setAchievements(ItemStack itemstack, EntityPlayer player) {
		if(itemstack.getItem() instanceof ItemFish)
			player.addStat(FCAchievements.firstFish, 1);
		else if(itemstack.getItem() instanceof ItemSeaFish)
			player.addStat(FCAchievements.seaFish, 1);
	}

}