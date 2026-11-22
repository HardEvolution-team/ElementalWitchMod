package com.electrifiedded.mwaw.projectile;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.MoWitchAndWizard;
import com.electrifiedded.mwaw.entity.EntityLightningGolem;
import com.electrifiedded.mwaw.entity.EntityLightningWitch;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityLightningBall extends EntityFireball {
   private boolean mwawInvulnerable;
   public EntityLightningBall(World par1World) {
      super(par1World);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityLightningBall(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
      super(par1World, par2EntityLivingBase, par3, par5, par7);
      this.setSize(0.3125F, 0.3125F);
   }

   @SideOnly(Side.CLIENT)
   public EntityLightningBall(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
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
      if (!this.world.isRemote
         && par1MovingObjectPosition.entityHit != null
         && this.shootingEntity != null
         && par1MovingObjectPosition.entityHit instanceof EntityLivingBase
         && !(par1MovingObjectPosition.entityHit instanceof EntityLightningGolem)
         && !(par1MovingObjectPosition.entityHit instanceof EntityLightningWitch)) {
         par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.MAGIC, 3.0F);
         ((EntityLivingBase)par1MovingObjectPosition.entityHit).addPotionEffect(new PotionEffect(MoWitchAndWizard.paralyzePotion, 30, 1));
         this.setDead();
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

      if (this.world.isRemote) {
         for (int i = 0; i < 4; i++) {
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

      this.setPosition(this.posX, this.posY, this.posZ);
      super.onUpdate();
   }
}
