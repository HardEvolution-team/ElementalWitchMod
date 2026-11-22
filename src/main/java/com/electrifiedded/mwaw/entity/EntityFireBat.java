package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import net.minecraft.util.SoundEvent;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFireBat extends EntityMob {
   private BlockPos spawnPosition;

   public EntityFireBat(World par1World) {
      super(par1World);
      this.setSize(0.5F, 0.9F);
      this.isImmuneToFire = true;
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.4));
      this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.4, 32.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0);
   }


   @Override
   protected void entityInit() {
      super.entityInit();
   }

   @Override
   protected float getSoundVolume() {
      return 0.1F;
   }

   @Override
   protected float getSoundPitch() {
      return super.getSoundPitch() * 0.95F;
   }

   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("mob.bat.idle");
   }

   @Override
   protected SoundEvent getHurtSound(net.minecraft.util.DamageSource src) {
      return LegacySounds.get("mob.bat.hurt");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.bat.death");
   }

   @Override
   public boolean canBePushed() {
      return false;
   }

   @Override
   protected void collideWithEntity(Entity p_82167_1_) {
   }

   @Override
   protected void collideWithNearbyEntities() {
   }

   @Override
   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.UNDEAD;
   }

   @Override
   protected boolean canDespawn() {
      return true;
   }

   @Override
   public void fall(float par1, float damageMultiplier) {
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      if (source.getTrueSource() instanceof EntityFireWitch) {
         return false;
      } else {
         return source.getTrueSource() instanceof EntityMeteorite ? false : super.attackEntityFrom(source, damage);
      }
   }

   @Override
   public void onLivingUpdate() {
      if (this.motionY <= -0.2) {
         this.motionY = -0.2;
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

      if (this.getAttackTarget() == null) {
         this.motionY *= 0.6F;
      }

      super.onLivingUpdate();
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
   protected void updateAITasks() {
      super.updateAITasks();
      if (this.getAttackTarget() == null) {
         if (this.spawnPosition != null
            && (
               !LegacyWorld.isAirBlock(this.world, this.spawnPosition.getX(), this.spawnPosition.getY(), this.spawnPosition.getZ())
                  || this.spawnPosition.getY() < 1
            )) {
            this.spawnPosition = null;
         }

         if (this.spawnPosition == null
            || this.rand.nextInt(30) == 0
            || this.spawnPosition.distanceSq((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F) {
            this.spawnPosition = new BlockPos(
               (int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7),
               (int)this.posY + this.rand.nextInt(6) - 2,
               (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7)
            );
         }

         double d0 = this.spawnPosition.getX() + 0.5 - this.posX;
         double d1 = this.spawnPosition.getY() + 0.1 - this.posY;
         double d2 = this.spawnPosition.getZ() + 0.5 - this.posZ;
         this.motionX = this.motionX + (Math.signum(d0) * 0.5 - this.motionX) * 0.1F;
         this.motionY = this.motionY + (Math.signum(d1) * 0.7F - this.motionY) * 0.1F;
         this.motionZ = this.motionZ + (Math.signum(d2) * 0.5 - this.motionZ) * 0.1F;
         float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0F;
         float f1 = MathHelper.wrapDegrees(f - this.rotationYaw);
         this.moveForward = 0.5F;
         this.rotationYaw += f1;
      }
   }

   @Override
   public boolean attackEntityAsMob(Entity par1Entity) {
      if (super.attackEntityAsMob(par1Entity)) {
         if (par1Entity instanceof EntityLivingBase) {
            par1Entity.setFire(10);
         }

         return true;
      } else {
         return false;
      }
   }
}
