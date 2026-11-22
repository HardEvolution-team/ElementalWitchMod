package com.electrifiedded.mwaw.entity;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import net.minecraft.util.SoundEvent;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import com.electrifiedded.mwaw.projectile.EntityPumpkinSkull;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityHalloweenWitch extends EntityMob implements IRangedAttackMob {
   public void setSwingingArms(boolean swinging) {
   }

   int summonTeleport = 50;
   int summonPumkinAirStrike = 400;
   int summonSkeleton = 300;
   boolean deathSummon = false;
   boolean summoned = false;

   public EntityHalloweenWitch(World p_i1744_1_) {
      super(p_i1744_1_);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0, 60, 10.0F));
      this.tasks.addTask(2, new EntityAIWander(this, 1.0));
      this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(3, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("mob.witch.idle");
   }

   @Override
   protected SoundEvent getHurtSound(net.minecraft.util.DamageSource src) {
      return LegacySounds.get("mob.witch.hurt");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.witch.death");
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   

   @Override
   public void onLivingUpdate() {
      if (this.summonTeleport != 0 && this.getAttackTarget() != null) {
         this.summonTeleport--;
      }

      if (this.summonSkeleton != 0 && this.getAttackTarget() != null) {
         this.summonSkeleton--;
         this.world.setWorldTime(18000L);
      }

      if (this.summonPumkinAirStrike != 0 && this.getAttackTarget() != null) {
         this.summonPumkinAirStrike--;
      }

      if (this.getHealth() <= 20.0F && !this.deathSummon) {
         this.deathSummon = true;
      }

      if (this.getAttackTarget() != null) {
         if (this.summonTeleport == 0) {
            this.teleportRandomly(this.getAttackTarget());
            this.summonTeleport = 50;
         }

         if (this.deathSummon && !this.summoned) {
            for (int i = 0; i < 5; i++) {
               EntitySpider entitywitherskull1 = new EntitySpider(this.world);
               entitywitherskull1.setLocationAndAngles(
                  this.getAttackTarget().posX + this.rand.nextInt(4),
                  this.getAttackTarget().posY,
                  this.getAttackTarget().posZ + this.rand.nextInt(4),
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull1.setAttackTarget(this.getAttackTarget());
               entitywitherskull1.addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.INVISIBILITY, 5000, 2));
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
               if (i == 4) {
                  this.summoned = true;
               }
            }
         }

         if (this.summonSkeleton == 0) {
            for (int ix = 0; ix < 5; ix++) {
               EntitySkeleton entitywitherskull1 = new EntitySkeleton(this.world);
               entitywitherskull1.setLocationAndAngles(
                  this.posX + this.rand.nextInt(4),
                  this.posY,
                  this.posZ + this.rand.nextInt(4),
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull1.setAttackTarget(this.getAttackTarget());
               entitywitherskull1.addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.INVISIBILITY, 50000, 2));
               entitywitherskull1.setItemStackToSlot(net.minecraft.inventory.EntityEquipmentSlot.HEAD, new ItemStack(Blocks.PUMPKIN));
               entitywitherskull1.setCustomNameTag("Pumpkin Minion");
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
               if (ix == 4) {
                  this.summonSkeleton = 300;
               }
            }
         }

         if (this.summonPumkinAirStrike == 0) {
            for (int ixx = 0; ixx < 10; ixx++) {
               EntityPumpkinAirStrike entitywitherskull1 = new EntityPumpkinAirStrike(this.world);
               entitywitherskull1.setLocationAndAngles(
                  this.getAttackTarget().posX + this.rand.nextInt(10),
                  this.getAttackTarget().posY + 25.0,
                  this.getAttackTarget().posZ + this.rand.nextInt(10),
                  this.rotationYaw,
                  0.0F
               );
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
               if (ixx == 9) {
                  this.summonPumkinAirStrike = 200;
               }
            }
         }
      }

      if (this.world.isRemote) {
         for (int ixxx = 0; ixxx < 4; ixxx++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            MoWitchAndWizard.proxy.fx(new FxArgs("EntitySoulFX", 
                     this.world,
                     this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
                     this.posY + this.rand.nextFloat() * this.height - d1 * d3,
                     this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
                     d,
                     d1,
                     d2
                  )
               );
         }

         for (int ixxx = 0; ixxx < 1; ixxx++) {
            LegacyWorld.spawnParticle(this.world, 
                  "portal",
                  this.posX + (this.rand.nextDouble() - 0.5) * this.width,
                  this.posY + this.rand.nextDouble() * this.height - 0.25,
                  this.posZ + (this.rand.nextDouble() - 0.5) * this.width,
                  (this.rand.nextDouble() - 0.5) * 2.0,
                  -this.rand.nextDouble(),
                  (this.rand.nextDouble() - 0.5) * 2.0
               );
         }
      }

      if (this.rand.nextFloat() < 7.5E-4F) {
         this.world.setEntityState(this, (byte)15);
      }

      super.onLivingUpdate();
   }

   private double func_82214_u(int par1) {
      if (par1 <= 0) {
         return this.posX;
      } else {
         float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
         float f1 = MathHelper.cos(f);
         return this.posX + f1 * 1.3;
      }
   }

   private double func_82208_v(int par1) {
      return par1 <= 0 ? this.posY + 0.5 : this.posY + 0.5;
   }

   private double func_82213_w(int par1) {
      if (par1 <= 0) {
         return this.posZ;
      } else {
         float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
         float f1 = MathHelper.sin(f);
         return this.posZ + f1 * 1.3;
      }
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
      this.launchWitherSkullToEntity(0, p_82196_1_);
      this.world.playEvent((EntityPlayer)null, 1016, new net.minecraft.util.math.BlockPos(this), 0);
   }

   private void launchWitherSkullToEntity(int par1, EntityLivingBase par2EntityLivingBase) {
      this.launchWitherSkullToCoords(
         par1,
         par2EntityLivingBase.posX,
         par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() * 0.5,
         par2EntityLivingBase.posZ,
         par1 == 0 && this.rand.nextFloat() < 0.001F
      );
   }

   private void launchWitherSkullToCoords(int par1, double par2, double par4, double par6, boolean par8) {
      double d3 = this.func_82214_u(par1);
      double d4 = this.func_82208_v(par1);
      double d5 = this.func_82213_w(par1);
      double d6 = par2 - d3;
      double d7 = par4 - d4;
      double d8 = par6 - d5;
      EntityPumpkinSkull entitywitherskull = new EntityPumpkinSkull(this.world, this, d6, d7, d8);
      entitywitherskull.posY = d4 + 0.5;
      entitywitherskull.posX = d3;
      entitywitherskull.posZ = d5;
      this.world.spawnEntity(entitywitherskull);
   }

   @Override
   public void setDead() {
      List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20.0, 100.0, 20.0));

      for (int i = 0; i < targetList.size(); i++) {
         Entity entitytargetcheck = (Entity)targetList.get(i);
         if (entitytargetcheck instanceof EntityPumpkinAirStrike) {
            entitytargetcheck.setDead();
         }
      }

      if (!this.world.isRemote && this.getHealth() <= 0.0F && this.getAttackTarget() instanceof EntityPlayer) {
         // 1.7 printed this on the (integrated) client player; sent to the killer target here
         EntityPlayer winner = (EntityPlayer)this.getAttackTarget();
         winner.sendMessage(new net.minecraft.util.text.TextComponentTranslation("chat.mwaw.winone", new Object[0]));
         winner.sendMessage(new net.minecraft.util.text.TextComponentTranslation("chat.mwaw.wintwo", new Object[0]));
      }

      super.setDead();
   }

   @Override
   public int getBrightnessForRender() {
      return 15728880;
   }

   @Override
   public float getBrightness() {
      return 1.0F;
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setInteger("WitchTeleport", this.summonTeleport);
      compund.setInteger("WitchSummonSkeleton", this.summonSkeleton);
      compund.setInteger("WitchPumkinAirStrike", this.summonPumkinAirStrike);
      compund.setBoolean("WitchDeathSummon", this.deathSummon);
      compund.setBoolean("WitchDeathSummoned", this.summoned);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.summonTeleport = compund.getInteger("WitchTeleport");
      this.summonPumkinAirStrike = compund.getInteger("WitchPumkinAirStrike");
      this.summonSkeleton = compund.getInteger("WitchSummonSkeleton");
      this.deathSummon = compund.getBoolean("WitchDeathSummon");
      this.summoned = compund.getBoolean("WitchDeathSummoned");
   }

   protected boolean teleportRandomly(Entity p_70816_1_) {
      Vec3d vec3 = new Vec3d(
         this.posX - p_70816_1_.posX,
         this.getEntityBoundingBox().minY + this.height / 2.0F - p_70816_1_.posY + p_70816_1_.getEyeHeight(),
         this.posZ - p_70816_1_.posZ
      );
      vec3 = vec3.normalize();
      double d0 = 16.0;
      double d1 = this.posX + (this.rand.nextDouble() - 0.5) * 8.0 - vec3.x * d0;
      double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.y * d0;
      double d3 = this.posZ + (this.rand.nextDouble() - 0.5) * 8.0 - vec3.z * d0;
      return this.teleportTo(d1, d2, d3);
   }

   protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
      EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) {
         return false;
      } else {
         double d3 = this.posX;
         double d4 = this.posY;
         double d5 = this.posZ;
         this.posX = event.getTargetX();
         this.posY = event.getTargetY();
         this.posZ = event.getTargetZ();
         boolean flag = false;
         int i = MathHelper.floor(this.posX);
         int j = MathHelper.floor(this.posY);
         int k = MathHelper.floor(this.posZ);
         if (LegacyWorld.blockExists(this.world, i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
               Block block = LegacyWorld.getBlock(this.world, i, j - 1, k);
               if (block.getDefaultState().getMaterial().blocksMovement()) {
                  flag1 = true;
               } else {
                  this.posY--;
                  j--;
               }
            }

            if (flag1) {
               this.setPosition(this.posX, this.posY, this.posZ);
               if (this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
                  flag = true;
               }
            }
         }

         if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
         } else {
            short short1 = 128;

            for (int l = 0; l < short1; l++) {
               double d6 = l / (short1 - 1.0);
               float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
               double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               LegacyWorld.spawnParticle(this.world, "portal", d7, d8, d9, f, f1, f2);
            }

            LegacyWorld.playSoundEffect(this.world, d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            LegacySounds.playSoundAtEntity(this.world, this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
         }
      }
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }
}
