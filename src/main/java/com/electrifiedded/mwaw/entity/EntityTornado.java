package com.electrifiedded.mwaw.entity;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import com.electrifiedded.mwaw.projectile.EntityWindShoot;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTornado extends EntityMob {
   public EntityTornado(World p_i1744_1_) {
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
      if (this.motionY <= -0.2) {
         this.motionY = -0.5;
      }

      if (this.world.isRemote) {
         for (int i = 0; i < 10; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 8.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 7.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 6.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 5.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 4.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 3.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 2.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY + 1.0, this.posZ, d, 2.35));
            MoWitchAndWizard.proxy.fx(new FxArgs("EntityTonadoFX", this.world, this.posX, this.posY, this.posZ, d, 2.35));
         }
      }

      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20.0, 20.0, 20.0));

      for (int j = 0; j < list.size(); j++) {
         Entity entity1 = (Entity)list.get(j);
         if (!(entity1 instanceof EntityAirWitch) && !(entity1 instanceof EntityWindShoot) && !(entity1 instanceof EntityAirClone)) {
            double xx = entity1.posX - this.posX;
            double zz = entity1.posZ - this.posZ;
            entity1.motionX = -xx / (9.0F * entity1.getDistance(this));
            entity1.motionZ = -zz / (9.0F * entity1.getDistance(this));
         }

         List list1 = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(2.0, 2.0, 2.0));

         for (int k = 0; k < list1.size(); k++) {
            Entity entity = (Entity)list1.get(k);
            if (!(entity instanceof EntityAirWitch) && !(entity1 instanceof EntityWindShoot) && !(entity1 instanceof EntityAirClone)) {
               entity.motionY = 3.0;
            }
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
      return source.getTrueSource() instanceof EntityTornado;
   }
}
