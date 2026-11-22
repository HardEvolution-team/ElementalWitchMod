package com.electrifiedded.mwaw.entity;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAirPhoenix extends EntityTameable {
   public EntityAirPhoenix(World par1World) {
      super(par1World);
      this.setSize(0.7F, 0.9F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5, 10.0F, 2.0F));
      this.tasks.addTask(7, new EntityAIWander(this, 0.4));
      this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
      this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.setTamed(true);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
   }

   

   @Override
   public void onLivingUpdate() {
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth()) {
         this.setHealth(this.getHealth() + 1.0F);
      }

      if (this.world.isRemote) {
         for (int i = 0; i < 10; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            MoWitchAndWizard.proxy.fx(new FxArgs("EntitySmallWindFX", 
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
      }

      super.onLivingUpdate();
   }

   @Override
   public boolean attackEntityAsMob(Entity p_70652_1_) {
      Random rand = new Random();
      int form = rand.nextInt(4);
      if (form == 1) {
         float f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
         if (f4 > 0.0F) {
            p_70652_1_.addVelocity(this.motionX * 3.0 * 0.6F / f4, 0.1, this.motionZ * 3.0 * 0.6F / f4);
         }

         p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
         return true;
      } else if (form == 2) {
         p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
         p_70652_1_.motionY = 1.0;
         return true;
      } else if (form == 3) {
         p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   public EntityAirPhoenix createChild(EntityAgeable p_90011_1_) {
      return null;
   }

   public boolean shouldAttackEntity(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
      if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
         if (p_142018_1_ instanceof EntityAirPhoenix) {
            EntityAirPhoenix entitywolf = (EntityAirPhoenix)p_142018_1_;
            if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_) {
               return false;
            }
         }

         return p_142018_1_ instanceof EntityPlayer
               && p_142018_2_ instanceof EntityPlayer
               && !((EntityPlayer)p_142018_2_).canAttackPlayer((EntityPlayer)p_142018_1_)
            ? false
            : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse)p_142018_1_).isTame();
      } else {
         return false;
      }
   }
}
