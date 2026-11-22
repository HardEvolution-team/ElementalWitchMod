package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMeteorite extends EntityMob {
   public EntityMeteorite(World p_i1744_1_) {
      super(p_i1744_1_);
      this.setSize(3.0F, 3.0F);
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

      if (this.onGround && !this.world.isRemote) {
         this.world
            .newExplosion(
               this, this.posX, this.posY, this.posZ, 100.0F, true, this.world.getGameRules().getBoolean("mobGriefing")
            );
         this.setDead();
      }

      for (int i = 0; i < 10; i++) {
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
         LegacyWorld.spawnParticle(this.world, 
               "lava",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
               this.posY + this.rand.nextFloat() * this.height - d1 * d3,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
               d,
               d1,
               d2
            );
         LegacyWorld.spawnParticle(this.world, 
               "enchantmenttable",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
               this.posY + this.rand.nextFloat() * this.height - d1 * d3,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
               d,
               d1,
               d2
            );
         LegacyWorld.spawnParticle(this.world, 
               "largesmoke",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
               this.posY + this.rand.nextFloat() * this.height - d1 * d3,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
               d,
               d1,
               d2
            );
      }

      super.onLivingUpdate();
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      return source.getTrueSource() instanceof EntityMeteorite;
   }
}
