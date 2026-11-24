package com.electrifiedded.mwaw.entity;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLightningPet extends EntityTameable {
   public EntityLightningPet(World par1World) {
      super(par1World);
      this.setSize(0.7F, 0.9F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5, 10.0F, 2.0F));
      this.tasks.addTask(7, new EntityAIWander(this, 0.3));
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
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5);
   }

   

   @Override
   public void onLivingUpdate() {
      if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth()) {
         this.setHealth(this.getHealth() + 1.0F);
      }

      if (this.world.isRemote) {
         for (int i = 0; i < 10; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityLightningFX", 
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
      Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
      int form = rand.nextInt(50);
      if (form <= 24) {
         EntityLightningBolt entity = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
         entity.setLocationAndAngles(this.getAttackTarget().posX, this.getAttackTarget().posY, this.getAttackTarget().posZ, 0.0F, 0.0F);
         MWAWEntityUtil.spawnWeather(this.world, entity);
         p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
         return true;
      } else {
         return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      return source.getTrueSource() instanceof EntityLightningBolt ? false : super.attackEntityFrom(source, damage);
   }

   @Override
   public EntityLightningPet createChild(EntityAgeable p_90011_1_) {
      return null;
   }

   public boolean shouldAttackEntity(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
      if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
         if (p_142018_1_ instanceof EntityLightningPet) {
            EntityLightningPet entitywolf = (EntityLightningPet)p_142018_1_;
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
