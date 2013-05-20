package fishingCraft.common.entity.projectile;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fishingCraft.common.items.FCItem;
import fishingCraft.common.items.fish.ItemFish;
import fishingCraft.common.items.fish.ItemSeaFish;
import fishingCraft.common.items.rods.ItemIronFishingRod;
import fishingCraft.common.items.rods.ItemWoodenFishingRod;
import fishingCraft.shar.util.Debug;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * This class overrides EntityFishHook.class for the fishing rod items.
 * This class also determines which fish should/can be caught where.
 * @author Sharingan616
 *
 */
public class EntityFishingHook extends EntityFishHook
{
    /** The tile this entity is on, X position */
    private int xTile;

    /** The tile this entity is on, Y position */
    private int yTile;

    /** The tile this entity is on, Z position */
    private int zTile;
    private int inTile;
    private boolean inWater;
    private boolean inGround;
    private int ticksInGround;
    private int ticksInAir;

    /** the number of ticks remaining until this fish can no longer be caught */
    private int ticksCatchable;

    /**
     * The entity that the fishing rod is connected to, if any. When you right click on the fishing rod and the hook
     * falls on to an entity, this it that entity.
     */
    private int fishPosRotationIncrements;
    private double fishX;
    private double fishY;
    private double fishZ;
    private double fishYaw;
    private double fishPitch;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;

    private BiomeGenBase jungle = BiomeGenBase.jungle;
    private BiomeGenBase jungHills = BiomeGenBase.jungleHills;
    private BiomeGenBase river = BiomeGenBase.river;
    private BiomeGenBase swamp = BiomeGenBase.swampland;
    private BiomeGenBase taiga = BiomeGenBase.taiga;
    private BiomeGenBase ocean = BiomeGenBase.ocean;
    private BiomeGenBase forest = BiomeGenBase.forest;
    private BiomeGenBase forestHills = BiomeGenBase.forestHills;
    private BiomeGenBase plains = BiomeGenBase.plains;
    private BiomeGenBase extremeHills = BiomeGenBase.extremeHills;
    private BiomeGenBase beach = BiomeGenBase.beach;
    private BiomeGenBase riverJungle;
    private BiomeGenBase riverTaiga;
    private BiomeGenBase riverOcean;
    private BiomeGenBase riverSwamp;
    private BiomeGenBase riverPlains;

    public ItemWoodenFishingRod eq;
    private Item equippedItem;
    private Item theBait;
    private Item fishToCatch = null;
    private ItemFish fish = null;
    private ItemSeaFish seaFish = null;
    private long random = Math.round(Math.random()) * 100;
    private boolean shouldBreakRod = false;
    private boolean hasBeenCast = false;

    public EntityFishingHook(World par1World)
    {
        super(par1World);
    }

    public EntityFishingHook(World world, EntityPlayer thePlayer, Item rod, Item bait)
    {
        super(world, thePlayer);
        this.equippedItem = rod;
        this.theBait = bait;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
    	if (this.hasBeenCast || this.inGround)
        {
        	if(this.eq.isThereAFish && this.fishToCatch != null)
        	{
	            EntityItem fishy = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(fishToCatch));
        		if(this.fishToCatch instanceof ItemFish)
        		{
        			this.fish = (ItemFish) fishToCatch;
        			fishy = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(fish));
        		}
	            this.worldObj.spawnEntityInWorld(fishy);
	            Debug.println("Caught a "+this.fishToCatch.getItemDisplayName(new ItemStack(this.fishToCatch))+".");
	            this.angler.addStat(StatList.fishCaughtStat, 1);
	            this.angler.worldObj.spawnEntityInWorld(new EntityXPOrb(this.angler.worldObj, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
        	}
            this.setDead();
            this.angler.fishEntity = null;
            this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            
            if(Mouse.isButtonDown(1))
            {
            	if(this.equippedItem instanceof ItemWoodenFishingRod || this.equippedItem instanceof ItemIronFishingRod )
            	{
            		eq.MouseButtonIsDown = true;
            	}
            }
        }
    }

    @Override
    public void onUpdate()
    {
    	if(this.equippedItem instanceof ItemWoodenFishingRod || this.equippedItem instanceof ItemIronFishingRod)
    		eq = (ItemWoodenFishingRod)this.equippedItem;
        if (this.fishPosRotationIncrements > 0)
        {
            double d0 = this.posX + (this.fishX - this.posX) / (double)this.fishPosRotationIncrements;
            double d1 = this.posY + (this.fishY - this.posY) / (double)this.fishPosRotationIncrements;
            double d2 = this.posZ + (this.fishZ - this.posZ) / (double)this.fishPosRotationIncrements;
            double d3 = MathHelper.wrapAngleTo180_double(this.fishYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.fishPosRotationIncrements);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.fishPitch - (double)this.rotationPitch) / (double)this.fishPosRotationIncrements);
            --this.fishPosRotationIncrements;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                ItemStack itemstack = this.angler.getCurrentEquippedItem();

                if (this.angler.isDead || !this.angler.isEntityAlive() || itemstack == null || /*itemstack.getItem() != equippedItem || */this.getDistanceSqToEntity(this.angler) > 1024.0D)
                {
                    this.setDead();
                    this.angler.fishEntity = null;
                    return;
                }

                if (this.bobber != null)
                {
                    if (!this.bobber.isDead)
                    {
                        this.posX = this.bobber.posX;
                        this.posY = this.bobber.boundingBox.minY + (double)this.bobber.height * 0.8D;
                        this.posZ = this.bobber.posZ;
                        return;
                    }

                    this.bobber = null;
                }
            }

            if (this.shake > 0)
            {
                --this.shake;
            }

            if (this.inGround)
            {
                int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

                if (i == this.inTile)
                {
                    ++this.ticksInGround;

                    if (this.ticksInGround == 1200)
                    {
                        this.setDead();
                    }

                    return;
                }

                //TODO fix hook bouncing
//
//                this.inGround = false;
//                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
//                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
//                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
//                this.ticksInGround = 0;
//                this.ticksInAir = 0;
            }
            else
            {
                ++this.ticksInAir;
            }

            Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
            vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d4 = 0.0D;
            double d5;

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity1 = (Entity)list.get(j);

                if (entity1.canBeCollidedWith() && (entity1 != this.angler || this.ticksInAir >= 5))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null)
                    {
                        d5 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d5 < d4 || d4 == 0.0D)
                        {
                            entity = entity1;
                            d4 = d5;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null)
            {
                if (movingobjectposition.entityHit != null)
                {
                    if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.angler), 0))
                    {
                        this.bobber = movingobjectposition.entityHit;
                    }
                }
                else
                {
                    this.inGround = true;
                }
            }

            if (!this.inGround)
            {
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
                float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

                for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
                {
                    ;
                }

                while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
                {
                    this.prevRotationPitch += 360.0F;
                }

                while (this.rotationYaw - this.prevRotationYaw < -180.0F)
                {
                    this.prevRotationYaw -= 360.0F;
                }

                while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
                {
                    this.prevRotationYaw += 360.0F;
                }

                this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
                this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
                float f2 = 0.92F;

                if (this.onGround || this.isCollidedHorizontally)
                {
                    f2 = 0.5F;
                }

                byte b0 = 5;
                double d6 = 0.0D;

                for (int k = 0; k < b0; ++k)
                {
                    double d7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(k + 0) / (double)b0 - 0.125D + 0.125D;
                    double d8 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(k + 1) / (double)b0 - 0.125D + 0.125D;
                    AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d7, this.boundingBox.minZ, this.boundingBox.maxX, d8, this.boundingBox.maxZ);

                    if (this.worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
                    {
                        this.hasBeenCast = true;
                        this.inWater = true;
                        d6 += 1.0D / (double)b0;
                    }
                }

                if (d6 > 0.0D)
                {
                    if (this.ticksCatchable > 0)
                    {
                        --this.ticksCatchable;
                    }
                    else
                    {
                        short short1 = 500;

                        if (this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1, MathHelper.floor_double(this.posZ)))
                        {
                            short1 = 300;
                        }
                        //TODO refine fish fighting
//                        if(this.eq.isThereAFish)
//                        {
//                        	if(this.rand.nextInt(100)>90)
//                        	{
//                        		this.worldObj.spawnParticle("bubble",this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
//        	                    this.playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
//                        	}
//                        }
                        
                        if (!this.eq.isThereAFish && (this.rand.nextInt(short1) == 0 || (this.angler.isSneaking() && Debug.debugger)) && canCatch())
                        {
                        	Debug.println("There is a "+this.fishToCatch.getItemDisplayName(new ItemStack(this.fishToCatch))+" on the line.");
                            this.ticksCatchable = this.rand.nextInt(30) + 10;
                            this.motionY -= 0.20000000298023224D;
                            this.playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                            float f3 = (float)MathHelper.floor_double(this.boundingBox.minY);
                            int l;
                            float f4;
                            float f5;

                            for (l = 0; (float)l < 1.0F + this.width * 20.0F; ++l)
                            {
                                f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                this.worldObj.spawnParticle("bubble", this.posX + (double)f5, (double)(f3 + 1.0F), this.posZ + (double)f4, this.motionX, this.motionY - (double)(this.rand.nextFloat() * 0.2F), this.motionZ);
                            }

                            for (l = 0; (float)l < 1.0F + this.width * 20.0F; ++l)
                            {
                                f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                this.worldObj.spawnParticle("splash", this.posX + (double)f5, (double)(f3 + 1.0F), this.posZ + (double)f4, this.motionX, this.motionY, this.motionZ);
                            }
                        }
                    }
                }

                if (this.ticksCatchable > 0)
                {
                    this.motionY -= (double)(this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat()) * 0.2D;
                }

                d5 = d6 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * d5;

                if (d6 > 0.0D)
                {
                    f2 = (float)((double)f2 * 0.9D);
                    this.motionY *= 0.8D;
                }

                this.motionX *= (double)f2;
                this.motionY *= (double)f2;
                this.motionZ *= (double)f2;
                this.setPosition(this.posX, this.posY, this.posZ);
            }
        }
    }

    @Override
    public int catchFish()
    {
        if (this.worldObj.isRemote)
        {
            return 0;
        }
        else
        {
            byte b0 = 0;
            double d5 = this.angler.posX - this.posX;
            double d6 = this.angler.posY - this.posY;
            double d7 = this.angler.posZ - this.posZ;
            double d8 = (double)MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
            double d9 = 0.1D;

            if (this.bobber != null)
            {
                double d0 = this.angler.posX - this.posX;
                double d1 = this.angler.posY - this.posY;
                double d2 = this.angler.posZ - this.posZ;
                double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                double d4 = 0.1D;
                b0 = 3;
            }
            else if (this.ticksCatchable > 0)
            {
            	this.eq.isThereAFish = true;
	            if (this.theBait != null)
	                destroyBait();
	            else
	                Debug.println("Bait is apparently null.");
                if (this.shouldBreakRod)
                    breakRod();
                b0 = 1;
            }

            if (this.inGround)
            {
                b0 = 2;
            }

            if (this.inWater)
            {
                this.inWater = false;
                this.motionX = d5 / 4 * d9;
                this.motionZ = d7 / 4 * d9;
                this.setPosition(this.posX, this.posY, this.posZ);
            }

            return b0;
        }
    }

    private boolean canCatch()
    {
        // TODO refine method for catching fish
        // TODO add LotsOfFood mod support
        int waterdepth = getWaterDepth(this.posX, this.posY, this.posZ);

        if (biomeIs(river))
        {
            if (tempIs(0) && baitIs(FCItem.worm))
            {
                if (isBlockNear(Block.tallGrass, 3) && baitIs(FCItem.worm))
                {
                    fishToCatch = fairChance(FCItem.fishSuckerRaw, FCItem.fishBassRaw, FCItem.fishSalmonRaw);
                }
                else if (baitIs(FCItem.worm))
                {
                    fishToCatch = fairChance(FCItem.fishBassRaw, FCItem.fishSalmonRaw);
                }
            }
            else if (tempIs(1))
            {
                if (baitIs(FCItem.rawFishMorsel))
                {
                    fishToCatch = FCItem.fishSmallPiranhaRaw;
                }
                else if (baitIs(FCItem.fishSmallPiranhaRaw))
                {
                    fishToCatch = FCItem.fishPeaBassRaw;
                }
            }
            else if (tempIs(2) && (baitIs(FCItem.fishGoldFishRaw) || baitIs(FCItem.fishBlueGillRaw)))
            {
                fishToCatch = FCItem.fishPikeRaw;
            }
        }
        else if (biomeIs(swamp))
        {
            if (baitIs(FCItem.frogEggs) || baitIs(FCItem.worm))
            {
                if (isBlockNear(Block.wood, 3) || this.worldObj.isRaining())
                {
                    fishToCatch = fairChance(FCItem.fishBlueGillRaw, FCItem.fishGoldFishRaw, FCItem.fishCatRaw);
                }
                else
                {
                    fishToCatch = fairChance(FCItem.fishBlueGillRaw, FCItem.fishGoldFishRaw);
                }
            }
        }
        else if (biomeIs(ocean))
        {
            if (waterdepth > 11) //Deep sea fish
            {
                if (baitIs(FCItem.fishBlueGillRaw))
                {
                    fishToCatch = fairChance(FCItem.fishYFTuna, FCItem.fishBFTuna);
                }
            }

            if (!(this.equippedItem instanceof ItemIronFishingRod))
            {
                shouldBreakRod = true;
            }
        }
        else if (biomeIs(forest) || biomeIs(forestHills))
        {
            if (baitIs(null) || baitIs(FCItem.worm))
            {
                fishToCatch = FCItem.fishCarpRaw;
            }
        }

        if (fishToCatch != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private Item fairChance(Item a, Item b)
    {
        if (random < 100 / 2)
        {
            return a;
        }
        else
        {
            return b;
        }
    }

    private Item fairChance(Item a, Item b, Item c)
    {
        if (random < 100 / 3)
        {
            return a;
        }
        else if (random < (100 / 3) * 2)
        {
            return b;
        }
        else
        {
            return c;
        }
    }

    private boolean baitIs(Item i)
    {
        return (this.theBait == i);
    }

    private boolean tempIs(int temp)
    {
        if (temp == 0) //mild
        {
            if (!isBiomeNear(jungle) && !isBiomeNear(taiga) && !isBiomeNear(ocean) && !isBiomeNear(swamp))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (temp == 1) //humid
        {
            if (isBiomeNear(jungle) && !isBiomeNear(taiga))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (temp == 2) //cold
        {
            if (isBiomeNear(taiga))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private boolean biomeIs(BiomeGenBase biome)
    {
        return (biome == getCurrentBiome());
    }

    private BiomeGenBase getCurrentBiome()
    {
        return this.worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ);
    }

    public boolean isNearBiome(int radius, BiomeGenBase biome, double X, double Y, double Z)
    {
        int x = (int)(X);
        int y = (int)(Y);
        int z = (int)(Z);

        for (int i = -radius; i <= radius; i++)
        {
            for (int j = -radius; j <= radius; j++)
            {
                if (this.worldObj.getBiomeGenForCoords(x + i, z + j) == biome)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBlockNear(Block block, int radius)
    {
        int x = (int)this.posX;
        int y = (int)this.posY;
        int z = (int)this.posZ;

        //Thanks to rich1051414 for this for loop
        for (int i = -radius; i <= radius; i++)
        {
            for (int j = -radius; j <= radius; j++)
            {
                for (int k = -radius; k <= radius; k++)
                {
                    int blockID = this.worldObj.getBlockId(x + i, y + j, z + k);

                    if (blockID == block.blockID)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isBiomeNear(BiomeGenBase biome, int rad)
    {
        return isNearBiome(rad, biome, this.posX, this.posY, this.posZ);
    }

    public boolean isBiomeNear(BiomeGenBase biome)
    {
        return isNearBiome(30, biome, this.posX, this.posY, this.posZ);
    }

    public void destroyBait()
    {
        int damage = this.angler.getCurrentEquippedItem().getItemDamage();

        if(!this.angler.capabilities.isCreativeMode)
        {
	        if (this.equippedItem instanceof ItemIronFishingRod)
	        {
	            this.angler.inventory.setInventorySlotContents(this.angler.inventory.currentItem, new ItemStack(FCItem.ironFishingRod, 1, damage + 2));
	            Debug.println("Destroyed iron fishing rod bait.");
	            this.equippedItem = FCItem.ironFishingRod;
	        }
	        else if (this.equippedItem instanceof ItemWoodenFishingRod)
	        {
	            this.angler.inventory.setInventorySlotContents(this.angler.inventory.currentItem, new ItemStack(FCItem.woodenFishingRod, 1, damage + 2));
	            Debug.println("Destroyed wooden fishing rod bait.");
	            this.equippedItem = FCItem.woodenFishingRod;

	        }
        }
    }

    public void breakRod()
    {
        this.playSound("random.break", 0.8F, 0.8F + this.worldObj.rand.nextFloat() * 0.4F);

        if (!this.worldObj.isRemote)
        {
            this.setDead();
            this.angler.entityDropItem(new ItemStack(Item.stick, 2), 0);
            this.angler.entityDropItem(new ItemStack(Item.silk, 1), 0);
            this.angler.destroyCurrentEquippedItem();
            Debug.println("The rod broke.");
        }
    }

    public int getWaterDepth(double X, double Y, double Z)
    {
        int x = (int)(X);
        int y = (int)(Y);
        int z = (int)(Z);
        int radius = this.worldObj.getActualHeight();
        int count = 0;

        for (int i = 0; i < radius; i++)
        {
            int blockID = this.worldObj.getBlockId(x, y - i, z);

            if (blockID == Block.waterStill.blockID || blockID == Block.waterMoving.blockID)
            {
                count++;
            }
        }

        return count;
    }
}
