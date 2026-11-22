package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
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
import net.minecraft.world.World;

public class EntityHalloweenPet extends EntityTameable {
   public EntityHalloweenPet(World par1World) {
      super(par1World);
      this.setSize(0.7F, 1.4F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5, 10.0F, 2.0F));
      this.tasks.addTask(7, new EntityAIWander(this, 0.3));
      this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
      this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.setTamed(false);
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
      if (!this.isTamed()) {
         this.setDead();
      }

      super.onLivingUpdate();
   }

   @Override
   public boolean attackEntityAsMob(Entity p_70652_1_) {
      return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   public EntityHalloweenPet createChild(EntityAgeable p_90011_1_) {
      return null;
   }

   public boolean shouldAttackEntity(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
      if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
         if (p_142018_1_ instanceof EntityHalloweenPet) {
            EntityHalloweenPet entitywolf = (EntityHalloweenPet)p_142018_1_;
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

   public boolean processInteract(EntityPlayer p_70085_1_, net.minecraft.util.EnumHand hand) {
      if (hand != net.minecraft.util.EnumHand.MAIN_HAND) return false;
      if (this.isTamed()) {
         EntityHalloweenPetEvolved mob = new EntityHalloweenPetEvolved(this.world);
         mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         MWAWEntityUtil.setOwner(mob, MWAWEntityUtil.ownerString(this));
         mob.setCustomNameTag(this.getCustomNameTag());
         mob.setTamed(true);
         p_70085_1_.setHealth(p_70085_1_.getHealth() - 6.5F);
         MWAWEntityUtil.spawnInWorld(this.world, mob);
         LegacySounds.playSoundAtEntity(p_70085_1_.world, p_70085_1_, "mob.enderdragon.growl", 2.0F, 0.6F);
         this.setDead();
      }

      return super.processInteract(p_70085_1_, hand);
   }
}
