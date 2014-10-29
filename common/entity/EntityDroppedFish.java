package fishingcraft.common.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityDroppedFish extends EntityItem{

	public EntityDroppedFish(World world, double xCoord, double zCoord, double yCoord, ItemStack stack) {
		super(world, xCoord, zCoord, yCoord, stack);
		this.setSize(.1F, .1F);
	}
}
