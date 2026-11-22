package com.electrifiedded.mwaw.entity;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMiniTornado extends EntityMob {
   public EntityMiniTornado(World p_i1744_1_) {
      super(p_i1744_1_);
      this.setSize(0.1F, 0.1F);
      this.isImmuneToFire = true;
      this.tasks.addTask(3, new EntityAILookIdle(this));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.147483647E9);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(100.0);
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
   public void onLivingUpdate() {
      List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(1.0, 1.0, 1.0));

      for (int i = 0; i < targetList.size(); i++) {
         Entity entitytargetcheck = (Entity)targetList.get(i);
         if (entitytargetcheck instanceof EntityLivingBase) {
            entitytargetcheck.attackEntityFrom(DamageSource.MAGIC, 0.5F);
         }
      }

      if (this.ticksExisted >= 100) {
         this.setDead();
      }

      if (this.world.isRemote) {
         for (int ix = 0; ix < 10; ix++) {
            double d = this.rand.nextGaussian() * 0.004;
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY - 0.5, this.posZ, d, 1.0));
         }
      }

      super.onLivingUpdate();
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      return source.getTrueSource() instanceof EntityTornado || source.getTrueSource() instanceof EntityAirWitch;
   }
}
