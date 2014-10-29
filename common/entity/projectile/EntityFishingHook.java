package fishingcraft.common.entity.projectile;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fishingcraft.common.entity.EntityFish;
import fishingcraft.common.items.FCItems;
import fishingcraft.common.items.fish.ItemFish;
import fishingcraft.common.items.fish.ItemSeaFish;
import fishingcraft.common.items.rods.*;
import fishingcraft.main.FishingCraft;
import fishingcraft.shar.util.Debug;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;


public class EntityFishingHook extends EntityFishHook
{    
	private static final List field_146039_d = Arrays.asList(new WeightedRandomFishable[] {(new WeightedRandomFishable(new ItemStack(Items.leather_boots), 10)).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.leather), 10), new WeightedRandomFishable(new ItemStack(Items.bone), 10), new WeightedRandomFishable(new ItemStack(Items.potionitem), 10), new WeightedRandomFishable(new ItemStack(Items.string), 5), (new WeightedRandomFishable(new ItemStack(Items.fishing_rod), 2)).func_150709_a(0.9F), new WeightedRandomFishable(new ItemStack(Items.bowl), 10), new WeightedRandomFishable(new ItemStack(Items.stick), 5), new WeightedRandomFishable(new ItemStack(Items.dye, 10, 0), 1), new WeightedRandomFishable(new ItemStack(Blocks.tripwire_hook), 10), new WeightedRandomFishable(new ItemStack(Items.rotten_flesh), 10)});
	private static final List field_146041_e = Arrays.asList(new WeightedRandomFishable[] {new WeightedRandomFishable(new ItemStack(Blocks.waterlily), 1), new WeightedRandomFishable(new ItemStack(Items.name_tag), 1), new WeightedRandomFishable(new ItemStack(Items.saddle), 1), (new WeightedRandomFishable(new ItemStack(Items.bow), 1)).func_150709_a(0.25F).func_150707_a(), (new WeightedRandomFishable(new ItemStack(Items.fishing_rod), 1)).func_150709_a(0.25F).func_150707_a(), (new WeightedRandomFishable(new ItemStack(Items.book), 1)).func_150707_a()});
	private static final List field_146036_f = Arrays.asList(new WeightedRandomFishable[] {new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a()), 60), new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a()), 25), new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.func_150976_a()), 2), new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a()), 13)});
	private int field_146037_g;
	private int field_146048_h;
	private int field_146050_i;
	private Block water;
	private boolean inGround;
	private int field_146049_av;
	private int field_146047_aw;
	private int field_146045_ax;
	private int field_146040_ay;
	private int ticksCatchable;
	private float field_146054_aA;
	private int fishPosRotationIncrements;
	private double field_146056_aC;
	private double field_146057_aD;
	private double field_146058_aE;
	private double field_146059_aF;
	private double field_146060_aG;
	@SideOnly(Side.CLIENT)
	private double field_146061_aH;
	@SideOnly(Side.CLIENT)
	private double field_146052_aI;
	@SideOnly(Side.CLIENT)
	private double field_146053_aJ;

	//Un-inherited variables
	private ItemStack fish = null;
	private ItemWoodenFishingRod eq;
	private Item equippedItem;
	private Item theBait;
	private boolean hasBeenCast = false;
	private EntityPlayer player = field_146042_b;
	private Entity bobber = field_146043_c;
	private BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ);
	private int waterDepthAtBobber = this.getWaterDepth(this.posX, this.posY, this.posZ);
	private int shake = this.field_146044_a;
	private long random = Math.round(Math.random() * 100);

	/**
	 * Constructor class.
	 * @param world
	 * @param angler
	 * @param rod
	 * @param bait
	 */
	public EntityFishingHook(World world, EntityPlayer angler, Item rod, Item bait) {
		super(world, angler);
		this.equippedItem = rod;
		this.theBait = bait;
	}

	/**
	 * INHERITED: This method controls when a fish is on the line.
	 * Old: catchFish()
	 */
	@Override
	public int func_146034_e()
	{
		if (this.worldObj.isRemote)
		{
			return 0;
		}
		else
		{
			byte b0 = 0;
			double d0 = this.player.posX - this.posX;
			double d2 = this.player.posY - this.posY;
			double d4 = this.player.posZ - this.posZ;
			double d6 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2 + d4 * d4);
			double d8 = 0.1D;

			if (this.bobber != null)
			{
				this.bobber.motionX += d0 * d8;
				this.bobber.motionY += d2 * d8 + (double)MathHelper.sqrt_double(d6) * 0.08D;
				this.bobber.motionZ += d4 * d8;
				b0 = 3;
			}
			else if (this.field_146045_ax > 0)
			{
				if (this.theBait != null)
	                destroyBait();
				if(!(this.equippedItem instanceof ItemIronFishingRod) && (this.currentBiome == BiomeGenBase.ocean || this.currentBiome == BiomeGenBase.deepOcean))
					this.breakRod();
				else
				{
					this.fish = this.fishToCatch();
					if(this.equippedItem instanceof ItemWoodenFishingRod)
						this.eq = (ItemWoodenFishingRod)this.equippedItem;
					this.eq.isThereAFish = true;
				}
				b0 = 1;
			}

			if (this.inGround)
			{
				b0 = 2;
			}

			WorldServer worldserver = (WorldServer)this.worldObj;
			float fi1 = this.field_146054_aA * 0.017453292F;
			float fi7 = MathHelper.sin(fi1);
			float fi2 = MathHelper.cos(fi1);
			double di11 = this.posX + (double)(fi7 * (float)this.ticksCatchable * 0.1F);
			double di5 = (double)((float)MathHelper.floor_double(this.boundingBox.minY) + 1.0F);
			double di6 = this.posZ + (double)(fi2 * (float)this.ticksCatchable * 0.1F);
			float fi3 = fi7 * 0.04F;
			float fi4 = fi2 * 0.04F;

			if (this.inWater)
			{
				this.inWater = false;
				fi1 = (float)MathHelper.floor_double(this.boundingBox.minY);

				worldserver.func_147487_a("wake", di11, di5, d6, 0, (double)fi4, 0.01D, (double)(-fi3), 1.0D);
				worldserver.func_147487_a("wake", di11, di5, d6, 0, (double)(-fi4), 0.01D, (double)fi3, 1.0D);
				
				
				this.motionX = d0 / 4 * d8;
				this.motionZ = d4 / 4 * d8;
				this.setPosition(this.posX, this.posY, this.posZ);
			}
			this.field_146045_ax = 0;
			return b0;
		}
	}

	public void onCollideWithPlayer(EntityPlayer player)
	{
		if(this.hasBeenCast || this.inGround)
		{
			Debug.println("Is there a fish? "+this.eq.isThereAFish);
			if(this.eq.isThereAFish)
			{
				EntityItem entityitem = new EntityFish(this.worldObj, this.posX, this.posY, this.posZ, this.fish, determineWeight());
				this.worldObj.spawnEntityInWorld(entityitem);
				this.player.worldObj.spawnEntityInWorld(new EntityXPOrb(this.player.worldObj, this.player.posX, this.player.posY + 0.5D, this.player.posZ + 0.5D, this.rand.nextInt(6) + 1));
				this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			}

			if(Mouse.isButtonDown(1))
			{
				if(this.equippedItem instanceof ItemWoodenFishingRod || this.equippedItem instanceof ItemIronFishingRod )
				{
					eq.MouseButtonIsDown = true;
				}
			}
			this.setDead();
		}
	}

	/**
	 * INHERITED: Updates every MineCraft tick.
	 */
	@Override
	public void onUpdate()
	{
		if(this.equippedItem instanceof ItemWoodenFishingRod || this.equippedItem instanceof ItemIronFishingRod)
			eq = (ItemWoodenFishingRod)this.equippedItem;
		this.currentBiome = this.worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ);
		this.waterDepthAtBobber = this.getWaterDepth(this.posX, this.posY, this.posZ);
		if (this.fishPosRotationIncrements > 0)
		{
			double d7 = this.posX + (this.field_146056_aC - this.posX) / (double)this.fishPosRotationIncrements;
			double d8 = this.posY + (this.field_146057_aD - this.posY) / (double)this.fishPosRotationIncrements;
			double d9 = this.posZ + (this.field_146058_aE - this.posZ) / (double)this.fishPosRotationIncrements;
			double d1 = MathHelper.wrapAngleTo180_double(this.field_146059_aF - (double)this.rotationYaw);
			this.rotationYaw = (float)((double)this.rotationYaw + d1 / (double)this.fishPosRotationIncrements);
			this.rotationPitch = (float)((double)this.rotationPitch + (this.field_146060_aG - (double)this.rotationPitch) / (double)this.fishPosRotationIncrements);
			--this.fishPosRotationIncrements;
			this.setPosition(d7, d8, d9);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				ItemStack itemstack = this.player.getCurrentEquippedItem();

				if (this.player.isDead || !this.player.isEntityAlive() || itemstack == null || itemstack.getItem() != this.equippedItem || this.getDistanceSqToEntity(this.player) > 1024.0D)
				{
					this.setDead();
					this.player.fishEntity = null;
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
				if (this.worldObj.getBlock(this.field_146037_g, this.field_146048_h, this.field_146050_i) == this.water)
				{
					++this.field_146049_av;

					if (this.field_146049_av == 1200)
					{
						this.setDead();
					}

					return;
				}

				this.inGround = false;
				this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
				this.field_146049_av = 0;
				this.field_146047_aw = 0;
			}
			else
			{
				++this.field_146047_aw;
			}

			Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3);
			vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (movingobjectposition != null)
			{
				vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}

			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			double d2;

			for (int i = 0; i < list.size(); ++i)
			{
				Entity entity1 = (Entity)list.get(i);

				if (entity1.canBeCollidedWith() && (entity1 != this.player || this.field_146047_aw >= 5))
				{
					float f = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec31, vec3);

					if (movingobjectposition1 != null)
					{
						d2 = vec31.distanceTo(movingobjectposition1.hitVec);

						if (d2 < d0 || d0 == 0.0D)
						{
							entity = entity1;
							d0 = d2;
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
					if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.player), 0.0F))
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
				float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
				this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

				for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f5) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
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
				float f6 = 0.92F;

				if (this.onGround || this.isCollidedHorizontally)
				{
					f6 = 0.5F;
				}

				byte b0 = 5;
				double d10 = 0.0D;

				for (int j = 0; j < b0; ++j)
				{
					double d3 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(j + 0) / (double)b0 - 0.125D + 0.125D;
					double d4 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(j + 1) / (double)b0 - 0.125D + 0.125D;
					AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d3, this.boundingBox.minZ, this.boundingBox.maxX, d4, this.boundingBox.maxZ);

					if (this.worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
					{
						d10 += 1.0D / (double)b0;
                        this.inWater = true;
						this.hasBeenCast = true;
					}
				}

				if (!this.worldObj.isRemote && d10 > 0.0D)
				{
					WorldServer worldserver = (WorldServer)this.worldObj;
					int k = 1;

					if (this.rand.nextFloat() < 0.25F && this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1, MathHelper.floor_double(this.posZ)))
					{
						k = 2;
					}

					if (this.rand.nextFloat() < 0.5F && !this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1, MathHelper.floor_double(this.posZ)))
					{
						--k;
					}

					if (this.field_146045_ax > 0)
					{
						--this.field_146045_ax;

						if (this.field_146045_ax <= 0)
						{
							this.field_146040_ay = 0;
							this.ticksCatchable = 0;
						}
					}
					else
					{
						float f1;
						float f2;
						double d5;
						double d6;
						float f7;
						double d11;

						//********* Fish Biting Start
						if ((this.ticksCatchable > 0 && this.canCatchFish()) && !Mouse.isButtonDown(1)|| (this.player.isSneaking() && FishingCraft.debug && this.canCatchFish()))
						{
							this.ticksCatchable -= k;

							//Fish bites the hook
							if (this.ticksCatchable <= 0)
							{
								this.motionY -= 0.20000000298023224D;
								this.playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
								f1 = (float)MathHelper.floor_double(this.boundingBox.minY);
								worldserver.func_147487_a("bubble", this.posX, (double)(f1 + 1.0F), this.posZ, (int)(1.0F + this.width * 20.0F), (double)this.width, 0.0D, (double)this.width, 0.20000000298023224D);
								worldserver.func_147487_a("wake", this.posX, (double)(f1 + 1.0F), this.posZ, (int)(1.0F + this.width * 20.0F), (double)this.width, 0.0D, (double)this.width, 0.20000000298023224D);
								this.field_146045_ax = MathHelper.getRandomIntegerInRange(this.rand, 10, 30);
							}
							//Fish is going towards the hook, leaving a trail of bubbles (wake)
							else
							{
								this.field_146054_aA = (float)((double)this.field_146054_aA + this.rand.nextGaussian() * 4.0D);
								f1 = this.field_146054_aA * 0.017453292F;
								f7 = MathHelper.sin(f1);
								f2 = MathHelper.cos(f1);
								d11 = this.posX + (double)(f7 * (float)this.ticksCatchable * 0.1F);
								d5 = (double)((float)MathHelper.floor_double(this.boundingBox.minY) + 1.0F);
								d6 = this.posZ + (double)(f2 * (float)this.ticksCatchable * 0.1F);

								if (this.rand.nextFloat() < 0.15F)
								{
									worldserver.func_147487_a("bubble", d11, d5 - 0.10000000149011612D, d6, 1, (double)f7, 0.1D, (double)f2, 0.0D);
								}

								float f3 = f7 * 0.04F;
								float f4 = f2 * 0.04F;
								worldserver.func_147487_a("wake", d11, d5, d6, 0, (double)f4, 0.01D, (double)(-f3), 1.0D);
								worldserver.func_147487_a("wake", d11, d5, d6, 0, (double)(-f4), 0.01D, (double)f3, 1.0D);
							}

						}
						//********* Fish Biting End
						else if (this.field_146040_ay > 0)
						{
							this.field_146040_ay -= k;
							f1 = 0.15F;

							if (this.field_146040_ay < 20)
							{
								f1 = (float)((double)f1 + (double)(20 - this.field_146040_ay) * 0.05D);
							}
							else if (this.field_146040_ay < 40)
							{
								f1 = (float)((double)f1 + (double)(40 - this.field_146040_ay) * 0.02D);
							}
							else if (this.field_146040_ay < 60)
							{
								f1 = (float)((double)f1 + (double)(60 - this.field_146040_ay) * 0.01D);
							}

							if (this.rand.nextFloat() < f1)
							{
								f7 = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F) * 0.017453292F;
								f2 = MathHelper.randomFloatClamp(this.rand, 25.0F, 60.0F);
								d11 = this.posX + (double)(MathHelper.sin(f7) * f2 * 0.1F);
								d5 = (double)((float)MathHelper.floor_double(this.boundingBox.minY) + 1.0F);
								d6 = this.posZ + (double)(MathHelper.cos(f7) * f2 * 0.1F);
								worldserver.func_147487_a("splash", d11, d5, d6, 2 + this.rand.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
							}

							if (this.field_146040_ay <= 0)
							{
								this.field_146054_aA = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F);
								this.ticksCatchable = MathHelper.getRandomIntegerInRange(this.rand, 20, 80);
							}
						}
						else
						{
							this.field_146040_ay = MathHelper.getRandomIntegerInRange(this.rand, 100, 900);
							this.field_146040_ay -= EnchantmentHelper.func_151387_h(this.player) * 20 * 5;
						}
					}

					if (this.field_146045_ax > 0)
					{
						this.motionY -= (double)(this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat()) * 0.2D;
					}
				}

				d2 = d10 * 2.0D - 1.0D;
				this.motionY += 0.03999999910593033D * d2;

				if (d10 > 0.0D)
				{
					f6 = (float)((double)f6 * 0.9D);
					this.motionY *= 0.8D;
				}

				this.motionX *= (double)f6;
				this.motionY *= (double)f6;
				this.motionZ *= (double)f6;
				this.setPosition(this.posX, this.posY, this.posZ);
			}
		}
	}

	/**
	 * This method controls what fish should be returned when a player catches a fish,
	 * depending on location, bait, and other factors.
	 * @return A fish.
	 */
	private ItemStack fishToCatch()
	{
		ItemStack fish = new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a());
		ItemStack clownFish = new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.func_150976_a());
		ItemStack pufferFish = new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a());
		ItemStack salmon = new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a());

		if(currentBiome == BiomeGenBase.deepOcean) //2 fish
		{
			if(this.theBait == null)
				return this.fairChance(clownFish, pufferFish);
			else if(this.theBait == FCItems.fishBlueGillRaw || this.theBait == FCItems.fishSmallPiranhaRaw)
			{
				return fairChance(FCItems.fishYFTuna, FCItems.fishBFTuna);
			}
		}
		else if(this.currentBiome == BiomeGenBase.river) //5 fish
		{
			if(tempIs("mild") && this.theBait == FCItems.worm)
			{
				if (isBlockNear(Blocks.tallgrass, 3) && this.theBait == FCItems.worm)
				{
					return fairChance(new ItemStack(FCItems.fishBassRaw, 1),new ItemStack(FCItems.fishSuckerRaw, 1), salmon);
				}
				else if (this.theBait == FCItems.worm)
				{
					return fairChance(new ItemStack(FCItems.fishBassRaw, 1), salmon);
				}
			}
			if(tempIs("humid"))
			{
				if (this.theBait == FCItems.rawFishMorsel)
				{
					return new ItemStack(FCItems.fishSmallPiranhaRaw, 1);
				}
				else if (this.theBait == FCItems.fishSmallPiranhaRaw)
				{
					return new ItemStack(FCItems.fishPeaBassRaw, 1);
				}
			}
			if (tempIs("cold") && (this.theBait == FCItems.fishBlueGillRaw || this.theBait == FCItems.fishSmallPiranhaRaw))
			{
				return new ItemStack(FCItems.fishPikeRaw, 1);
			}
		}
		else if (this.currentBiome == BiomeGenBase.swampland) //3 fish
		{
			if (this.theBait == FCItems.frogeggs || this.theBait == FCItems.worm)
			{
				if (isBlockNear(Blocks.log, 3) || this.worldObj.isRaining())
				{
					return fairChance(FCItems.fishBlueGillRaw, FCItems.fishGoldFishRaw, FCItems.fishCatRaw);
				}
				else
				{
					return fairChance(FCItems.fishBlueGillRaw, FCItems.fishGoldFishRaw);
				}
			}
		}
		//1 fish
		else if (this.currentBiome == BiomeGenBase.forest || this.currentBiome == BiomeGenBase.forestHills || this.currentBiome == BiomeGenBase.birchForest || this.currentBiome == BiomeGenBase.birchForestHills)
		{
			if (this.theBait == null || this.theBait == FCItems.worm)
			{
				return new ItemStack(FCItems.fishCarpRaw,1);
			}
		}
		else if(this.currentBiome instanceof BiomeGenJungle)
		{
			if (this.theBait == FCItems.rawFishMorsel)
			{
				return new ItemStack(FCItems.fishSmallPiranhaRaw, 1);
			}
			else if (this.theBait == FCItems.fishSmallPiranhaRaw)
			{
				return new ItemStack(FCItems.fishPeaBassRaw, 1);
			}
		}
		return fish;	
	}

	/**
	 * This method controls whether a fish can be caught or not, based on location and rod type.
	 * @return
	 */
	private boolean canCatchFish()
	{
		if(this.currentBiome == BiomeGenBase.deepOcean)
		{
			if(this.equippedItem instanceof ItemIronFishingRod || this.equippedItem == FCItems.woodenFishingRodBG || this.equippedItem == FCItems.woodenFishingRodSP)
			{
				return true;
			}
			return false;
		}
		else
		{
			return true;
			//			if(this.equippedItem instanceof ItemIronFishingRod && this.theBait != null)
			//			{
			//				//If the player is fishing with a baited iron rod in a body of water that
			//				//is not the ocean, a fish should not bite.
			//				return false;
			//			}
			//			else
			//				//If the player is fishing with a baited wooden rod or a non-baited iron rod
			//				//in a body of water that is not the ocean, a fish should bite.
			//				return true;
		}
	}

	/**
	 * Destroys the rod's bait, if there is any.
	 * Note: the bait will not be destroyed if the user is in creative mode.
	 */
	public void destroyBait()
	{
		int damage = this.player.getCurrentEquippedItem().getItemDamage();

		if(!this.player.capabilities.isCreativeMode && this.theBait != null)
		{
			if (this.equippedItem instanceof ItemIronFishingRod)
			{
				this.player.inventory.setInventorySlotContents(this.player.inventory.currentItem, new ItemStack(FCItems.ironFishingRod, 1, damage + 2));
				Debug.println("Destroyed iron fishing rod bait.");
				this.equippedItem = FCItems.ironFishingRod;
			}
			else if (this.equippedItem instanceof ItemWoodenFishingRod)
			{
				this.player.inventory.setInventorySlotContents(this.player.inventory.currentItem, new ItemStack(FCItems.woodenFishingRod, 1, damage + 2));
				Debug.println("Destroyed wooden fishing rod bait.");
				this.equippedItem = FCItems.woodenFishingRod;
			}
		}
	}

	/**
	 * Breaks the fishing rod.
	 * Note: Only a wooden fishing rod should be broken. However, the method does
	 * support the destruction of an iron fishing rod.
	 */
	public void breakRod()
	{
		this.playSound("random.break", 0.8F, 0.8F + this.worldObj.rand.nextFloat() * 0.4F);

		if (!this.worldObj.isRemote)
		{
			this.player.entityDropItem(new ItemStack(Items.string, 1), 0);

			if(this.equippedItem instanceof ItemIronFishingRod)
				this.player.entityDropItem(new ItemStack(Items.iron_ingot, 2), 0);
			else if(this.equippedItem instanceof ItemWoodenFishingRod)
				this.player.entityDropItem(new ItemStack(Items.stick, 2), 0);

			this.setDead();
			this.player.destroyCurrentEquippedItem();
			Debug.println("The rod broke.");
		}
	}

	/**
	 * Returns the water depth of a specific position.
	 * 
	 * @param X - pos x
	 * @param Y - pos y
	 * @param Z - pos z
	 * @return	how many consecutive water blocks exist from given coordinates down
	 */
	public int getWaterDepth(double X, double Y, double Z)
	{
		int x = (int)(X);
		int y = (int)(Y);
		int z = (int)(Z);
		int count = 0;

		try
		{
			int radius = this.worldObj.getActualHeight();
			for (int i = 0; i < radius; i++)
			{
				Block block = this.worldObj.getBlock(x, y - i, z);
				if (block == Blocks.water)
					count++;
			}
		}
		catch(Exception e)
		{
			Debug.println(e.getMessage());
			Debug.println(e.getStackTrace());
		}
		return count;
	}

	/**
	 * Returns whether or not the temperature of the current location is mild, humid, or cold.
	 * @param temp - mild, humid, or cold
	 * @return 	true or false depending on temperature parameter and location
	 */
	private boolean tempIs(String temp)
	{
		if (temp.toLowerCase().equals("mild"))
		{
			if (!isBiomeNear(BiomeGenBase.jungle) && !isBiomeNear(BiomeGenBase.coldTaiga) && !isBiomeNear(BiomeGenBase.ocean) && !isBiomeNear(BiomeGenBase.swampland) && !isBiomeNear(BiomeGenBase.coldBeach) && !isBiomeNear(BiomeGenBase.coldTaigaHills))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (temp.toLowerCase().equals("humid"))
		{
			if (isBiomeNear(BiomeGenBase.jungle) && !isBiomeNear(BiomeGenBase.coldTaiga) && !isBiomeNear(BiomeGenBase.coldBeach) && !isBiomeNear(BiomeGenBase.coldTaigaHills))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if (temp.toLowerCase().equals("cold"))
		{
			if (isBiomeNear(BiomeGenBase.taiga) || isBiomeNear(BiomeGenBase.coldBeach) || isBiomeNear(BiomeGenBase.coldTaigaHills))
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

	/**
	 * Returns whether or not a biome is near the bobber, within a given radius.
	 * @param biome
	 * @param rad - radius
	 * @return	if the specified biome is near the bobber
	 */
	public boolean isBiomeNear(BiomeGenBase biome, int rad)
	{
		int x = (int)(this.posX);
		int y = (int)(this.posY);
		int z = (int)(this.posZ);

		for (int i = -rad; i <= rad; i++)
		{
			for (int j = -rad; j <= rad; j++)
			{
				if (this.worldObj.getBiomeGenForCoords(x + i, z + j) == biome)
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Triggers the isBiomeNear method with a default radius of 30
	 * @param biome
	 * @return	if the specified biome is near the bobber
	 */
	public boolean isBiomeNear(BiomeGenBase biome)
	{
		return this.isBiomeNear(biome, 30);
	}

	/**
	 * Returns either item a or item b randomly.
	 * @param a
	 * @param b
	 * @return	item of random
	 */
	private ItemStack fairChance(Item a, Item b)
	{
		if (random < 100 / 2)
		{
			return new ItemStack(a,1);
		}
		else
		{
			return new ItemStack(b,1);
		}
	}

	/**
	 * Returns either item a, item b, or item c randomly.
	 * @param a
	 * @param b
	 * @param c
	 * @return	item of random
	 */
	private ItemStack fairChance(Item a, Item b, Item c)
	{
		Debug.println("Rand num: "+random);
		if (random < 100 / 3)
		{
			return new ItemStack(a,1);
		}
		else if (random < (100 / 3) * 2)
		{
			return new ItemStack(b,1);
		}
		else
		{
			return new ItemStack(c,1);
		}
	}

	/**
	 * Returns either item a or item b randomly.
	 * @param a
	 * @param b
	 * @return	item of random
	 */
	private ItemStack fairChance(ItemStack a, ItemStack b)
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

	/**
	 * Returns either item a, item b, or item c randomly.
	 * @param a
	 * @param b
	 * @param c
	 * @return	item of random
	 */
	private ItemStack fairChance(ItemStack a, ItemStack b, ItemStack c)
	{
		Debug.println("Rand num: "+random);
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

	/**
	 * Determines whether or not a particular block is within a certain radius of the entity.
	 * @param block - the block to look for
	 * @param radius
	 * @return
	 */
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
					Block currentBlock = this.worldObj.getBlock(x + i, y + j, z + k);

					if (currentBlock == block)
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	private double determineWeight() {
		Item fish = this.fishToCatch().getItem();
		double weight = 100.0;

		if(fish == FCItems.fishBlueGillRaw || fish == FCItems.fishGoldFishRaw)
		{
			if(this.theBait == FCItems.frogeggs)
				weight = rand.nextInt(21)/100.0;
			else if (this.theBait == FCItems.worm)
				weight = this.nextInt(21, 61)/100.0;
		}
		else if(fish == FCItems.fishCatRaw ||  fish == FCItems.fishSuckerRaw)
			weight = this.nextInt(21, 151);
		else if(fish == FCItems.fishSmallPiranhaRaw)
			weight = this.nextInt(31, 101)/100.0;
		else if(fish == FCItems.fishBassRaw || fish == Items.fish)
		{
			if(this.theBait == FCItems.worm)
			{
				int randomNum = rand.nextInt(101);
				if(randomNum < 71)
					weight = this.nextInt(500, 1100)/100.0;
				else if(randomNum <= 101)
					weight = this.nextInt(500, 2100)/100.0;
				else
				{
					Debug.println("Problem with determineWeight() in EntityFishingHook.class.");
					Debug.println("The random number is "+randomNum+", thus the weight is "+weight);
				}
			}
		}
		else if(fish == FCItems.fishPikeRaw)
		{
			if(this.theBait == FCItems.fishBlueGillRaw)
			{
				int randomNum = rand.nextInt(101);
				if(randomNum < 71)
					weight = this.nextInt(700, 1300)/100.0;
				else if(randomNum <= 101)
					weight = this.nextInt(700, 2500)/100.0;
				else
				{
					Debug.println("Problem with determineWeight() in EntityFishingHook.class.");
					Debug.println("The random number is "+randomNum+", thus the weight is "+weight);
				}
			}
		}
		else if(fish == FCItems.fishPeaBassRaw)
		{
			if(this.theBait == FCItems.fishSmallPiranhaRaw)
			{
				int randomNum = rand.nextInt(101);
				if(randomNum < 71)
					weight = this.nextInt(700, 1300)/100.0;
				else if(randomNum <= 101)
					weight = this.nextInt(700, 2500)/100.0;
				else
				{
					Debug.println("Problem with determineWeight() in EntityFishingHook.class.");
					Debug.println("The random number is "+randomNum+", thus the weight is "+weight);
				}
			}
		}
		else if(fish == FCItems.fishCarpRaw)
		{
			if(this.theBait == null)
			{
				int randomNum = rand.nextInt(101);
				if(randomNum < 71)
					weight = this.nextInt(21, 201)/100.0;
				else if(randomNum <= 101)
					weight = this.nextInt(501, 1501)/100.0;
				else
				{
					Debug.println("Problem with determineWeight() in EntityFishingHook.class.");
					Debug.println("The random number is "+randomNum+", thus the weight is "+weight);
				}
			}
		}


		// Sea Fish //

		else if(fish == FCItems.fishYFTuna || fish == FCItems.fishBFTuna)
		{
			if(this.theBait == FCItems.fishBlueGillRaw)
			{
				int randomNum = rand.nextInt(101);
				if(randomNum < 71)
					weight = this.nextInt(510, 710)/100.0;
				else if(randomNum <= 101)
					weight = this.nextInt(510, 1300)/100.0;
				else
				{
					Debug.println("Problem with determineWeight() in EntityFishingHook.class.");
					Debug.println("The random number is "+randomNum+", thus the weight is "+weight);
				}	
			}
			if(this.theBait == FCItems.fishSmallPiranhaRaw)
			{
				int randomNum = rand.nextInt(101);
				if(randomNum < 71)
					weight = this.nextInt(710, 1100)/100.0;
				else if(randomNum <= 101)
					weight = this.nextInt(710, 1800)/100.0;
				else
				{
					Debug.println("Problem with determineWeight() in EntityFishingHook.class.");
					Debug.println("The random number is "+randomNum+", thus the weight is "+weight);
				}	
			}
		}

		return weight;
	}

	/**
	 * This method is a revision of the Random method nextInt(int n).
	 * It includes a lower limit as well as the default upper limit.
	 * @param lowerLimit lower limit int
	 * @param upperLimit upper limit int
	 * @return a random int between the 2 parameters
	 */
	private int nextInt(int lowerLimit, int upperLimit)
	{
		//TODO add to a utility class
		int re = rand.nextInt(upperLimit);
		while(re < lowerLimit)
			re = rand.nextInt(upperLimit);
		return re;
	}
}