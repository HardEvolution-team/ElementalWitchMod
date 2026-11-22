package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import net.minecraft.util.SoundEvent;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import java.util.List;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFireWitch extends EntityMob implements IRangedAttackMob {
   public void setSwingingArms(boolean swinging) {
   }

   int summonLava = 600;
   int summonFireRain = 200;
   int summonFireBat = 300;
   boolean deathSummon = false;
   boolean summoned = false;

   public EntityFireWitch(World p_i1744_1_) {
      super(p_i1744_1_);
      this.isImmuneToFire = true;
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
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   

   @Override
   public void onLivingUpdate() {
      if (this.summonLava != 0 && this.getAttackTarget() != null) {
         this.summonLava--;
      }

      if (this.summonFireBat != 0 && this.getAttackTarget() != null) {
         this.summonFireBat--;
      }

      if (this.summonFireRain != 0 && this.getAttackTarget() != null) {
         this.summonFireRain--;
      }

      if (this.getHealth() < 30.0F) {
         this.deathSummon = true;
      }

      for (int i = 0; i < 1; i++) {
         double d = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = 10.0;
         LegacyWorld.spawnParticle(this.world, 
               "flame",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
               this.posY + this.rand.nextFloat() * this.height - d1 * d3,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
               d,
               d1,
               d2
            );
      }

      if (this.getAttackTarget() != null) {
         if (this.getHealth() <= 30.0F) {
            for (int i = 0; i < 1; i++) {
               LegacyWorld.spawnParticle(this.world, 
                     "lava",
                     this.posX + this.rand.nextGaussian() * 8.0 * 0.5,
                     this.posY,
                     this.posZ + this.rand.nextGaussian() * 8.0 * 0.5,
                     0.0,
                     0.5,
                     0.0
                  );
            }
         }

         if (this.summonFireBat == 0) {
            EntityFireBat entitywitherskull1 = new EntityFireBat(this.world);
            entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
            entitywitherskull1.setAttackTarget(this.getAttackTarget());
            EntityFireBat entitywitherskull2 = new EntityFireBat(this.world);
            entitywitherskull2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entitywitherskull2.setAttackTarget(this.getAttackTarget());
            EntityFireBat entitywitherskull3 = new EntityFireBat(this.world);
            entitywitherskull3.setLocationAndAngles(this.posX, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
            entitywitherskull3.setAttackTarget(this.getAttackTarget());
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull2);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull3);
            this.summonFireBat = 300;
         }

         if (this.deathSummon && !this.summoned) {
            EntityMeteorite entitywitherskull6 = new EntityMeteorite(this.world);
            entitywitherskull6.setLocationAndAngles(
               this.getAttackTarget().posX, this.getAttackTarget().posY + 99.0, this.getAttackTarget().posZ, this.rotationYaw, 0.0F
            );
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull6);

            for (int i = 0; i < 10; i++) {
               EntityFireBat entitywitherskull1 = new EntityFireBat(this.world);
               entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
               entitywitherskull1.setAttackTarget(this.getAttackTarget());
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            }

            this.summoned = true;
         }

         if (this.summonFireRain == 0) {
            EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull2 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull3 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull4 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull5 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull6 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull7 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull8 = new EntityLargeFireball(this.world);
            EntityLargeFireball entitywitherskull9 = new EntityLargeFireball(this.world);
            entitywitherskull1.setLocationAndAngles(
               this.getAttackTarget().posX, this.getAttackTarget().posY + 20.0, this.getAttackTarget().posZ, this.rotationYaw, 0.0F
            );
            entitywitherskull2.setLocationAndAngles(
               this.getAttackTarget().posX,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ + 1.0,
               this.rotationYaw,
               0.0F
            );
            entitywitherskull3.setLocationAndAngles(
               this.getAttackTarget().posX,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ - 1.0,
               this.rotationYaw,
               0.0F
            );
            entitywitherskull4.setLocationAndAngles(
               this.getAttackTarget().posX + 1.0,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ,
               this.rotationYaw,
               0.0F
            );
            entitywitherskull5.setLocationAndAngles(
               this.getAttackTarget().posX - 1.0,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ,
               this.rotationYaw,
               0.0F
            );
            entitywitherskull6.setLocationAndAngles(
               this.getAttackTarget().posX - 1.0,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ + 1.0,
               this.rotationYaw,
               0.0F
            );
            entitywitherskull7.setLocationAndAngles(
               this.getAttackTarget().posX - 1.0,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ - 1.0,
               this.rotationYaw,
               0.0F
            );
            entitywitherskull8.setLocationAndAngles(
               this.getAttackTarget().posX + 1.0,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ + 1.0,
               this.rotationYaw,
               0.0F
            );
            entitywitherskull9.setLocationAndAngles(
               this.getAttackTarget().posX + 1.0,
               this.getAttackTarget().posY + 20.0,
               this.getAttackTarget().posZ - 1.0,
               this.rotationYaw,
               0.0F
            );
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull2);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull3);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull4);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull5);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull6);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull7);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull8);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull9);
            entitywitherskull1.motionY = -5.0;
            entitywitherskull2.motionY = -5.0;
            entitywitherskull3.motionY = -5.0;
            entitywitherskull4.motionY = -5.0;
            entitywitherskull5.motionY = -5.0;
            entitywitherskull6.motionY = -5.0;
            entitywitherskull7.motionY = -5.0;
            entitywitherskull8.motionY = -5.0;
            entitywitherskull9.motionY = -5.0;
            this.summonFireRain = 200;
         }

         if (this.summonLava == 0) {
            if (this.getAttackTarget() instanceof EntityLivingBase
               && LegacyWorld.getBlock(this.world, 
                        (int)this.getAttackTarget().posX, (int)this.getAttackTarget().posY - 1, (int)this.getAttackTarget().posZ
                     )
                  != Blocks.AIR
               && LegacyWorld.getBlock(this.world, 
                        (int)this.getAttackTarget().posX, (int)this.getAttackTarget().posY - 1, (int)this.getAttackTarget().posZ
                     )
                  != Blocks.LAVA
               && LegacyWorld.getBlock(this.world, 
                        (int)this.getAttackTarget().posX, (int)this.getAttackTarget().posY - 1, (int)this.getAttackTarget().posZ
                     )
                  != Blocks.FLOWING_LAVA
               && !this.getAttackTarget().isInWater()) {
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX - 1,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ + 1,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ - 1,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX + 1,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX + 1,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ - 1,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX + 1,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ + 1,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX - 1,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ - 1,
                     Blocks.LAVA
                  );
               LegacyWorld.setBlock(this.world, 
                     (int)this.getAttackTarget().posX - 1,
                     (int)this.getAttackTarget().posY - 1,
                     (int)this.getAttackTarget().posZ + 1,
                     Blocks.LAVA
                  );
            }

            this.summonLava = 600;
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
      for (int i = 0; i < 10; i++) {
         this.launchWitherSkullToEntity(0, p_82196_1_);
         this.world.playEvent((EntityPlayer)null, 1016, new net.minecraft.util.math.BlockPos(this), 0);
      }
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
      EntitySmallFireball entitywitherskull = new EntitySmallFireball(this.world, this, d6, d7, d8);
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
         if (entitytargetcheck instanceof EntityMeteorite) {
            entitytargetcheck.setDead();
         }

         if (entitytargetcheck instanceof EntityFireBat) {
            entitytargetcheck.setDead();
         }
      }

      super.setDead();
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      return source.getTrueSource() instanceof EntityMeteorite ? false : super.attackEntityFrom(source, damage);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setInteger("WitchSummonLava", this.summonLava);
      compund.setInteger("WitchSummonFireBat", this.summonFireBat);
      compund.setInteger("WitchSummonFireRain", this.summonFireRain);
      compund.setBoolean("WitchDeathSummon", this.deathSummon);
      compund.setBoolean("WitchDeathSummoned", this.summoned);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.summonLava = compund.getInteger("WitchSummonLava");
      this.summonFireRain = compund.getInteger("WitchSummonFireRain");
      this.summonFireBat = compund.getInteger("WitchSummonFireBat");
      this.deathSummon = compund.getBoolean("WitchDeathSummon");
      this.summoned = compund.getBoolean("WitchDeathSummoned");
   }

   @Override
   protected Item getDropItem() {
      return MWAWItemRegistry.fireshard;
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   public boolean processInteract(EntityPlayer p_70085_1_, net.minecraft.util.EnumHand hand) {
      if (hand != net.minecraft.util.EnumHand.MAIN_HAND) return false;
      ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
      if (itemstack != null
         && itemstack != null
         && itemstack.getItem() == MWAWItemRegistry.emptyegg
         && this.getHealth() <= 13.0F
         && this.rand.nextInt(1000) <= 10) {
         itemstack.shrink(1);
         p_70085_1_.dropItem(MWAWItemRegistry.fireegg, 1);
         this.setDead();
      }

      return super.processInteract(p_70085_1_, hand);
   }
}
