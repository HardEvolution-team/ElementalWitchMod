package com.electrifiedded.mwaw.projectile;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWindShoot extends EntityFireball {
   private boolean mwawInvulnerable;
   int form = 1;

   public EntityWindShoot(World par1World) {
      super(par1World);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityWindShoot(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7, int par8) {
      super(par1World, par2EntityLivingBase, par3, par5, par7);
      this.form = par8;
      this.setSize(0.3125F, 0.3125F);
   }

   @SideOnly(Side.CLIENT)
   public EntityWindShoot(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
      super(par1World, par2, par4, par6, par8, par10, par12);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   protected float getMotionFactor() {
      return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
   }

   @Override
   public boolean isBurning() {
      return false;
   }

   @Override
   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      if (!this.world.isRemote && par1MovingObjectPosition.entityHit != null && this.shootingEntity != null) {
         if (this.form == 1) {
            if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {
               float f4 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
               if (f4 > 0.0F) {
                  par1MovingObjectPosition.entityHit.addVelocity(this.motionX * 3.0 * 0.6F / f4, 0.1, this.motionZ * 3.0 * 0.6F / f4);
               }

               par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.MAGIC, 1.0F);
               this.setDead();
            }
         } else if (this.form == 2) {
            if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase) {
               par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.MAGIC, 1.0F);
               par1MovingObjectPosition.entityHit.motionY++;
               this.setDead();
            }
         } else if (this.form == 3) {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.MAGIC, 3.0F);
         }
      }
   }

   @Override
   public boolean canBeCollidedWith() {
      return false;
   }

   @Override
   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      return false;
   }

   @Override
   protected void entityInit() {
   }

   public boolean isInvulnerable() {
      return this.mwawInvulnerable;
   }

   public void setInvulnerable(boolean par1) {
      this.mwawInvulnerable = par1;
   }

   @Override
   public void onUpdate() {
      if (this.ticksExisted >= 100) {
         this.setDead();
      }

      float f3 = 0.25F;
      if (this.isInWater()) {
         for (int k = 0; k < 4; k++) {
            LegacyWorld.spawnParticle(this.world, 
                  "bubble",
                  this.posX - this.motionX * 0.25,
                  this.posY - this.motionY * 0.25,
                  this.posZ - this.motionZ * 0.25,
                  this.motionX,
                  this.motionY,
                  this.motionZ
               );
         }
      }

      for (int i = 0; i < 1; i++) {
         double d = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = 10.0;
         LegacyWorld.spawnParticle(this.world, 
               "explode",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
               this.posY + this.rand.nextFloat() * this.height - d1 * d3,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
               d,
               d1,
               d2
            );
      }

      this.setPosition(this.posX, this.posY, this.posZ);
      super.onUpdate();
   }
}
