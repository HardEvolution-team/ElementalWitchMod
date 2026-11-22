package com.electrifiedded.mwaw.projectile;

import com.electrifiedded.mwaw.entity.EntityMiniTornado;
import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMWAWWindBall extends EntityFireball {
   private boolean mwawInvulnerable;
   public EntityMWAWWindBall(World par1World) {
      super(par1World);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityMWAWWindBall(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
      super(par1World, par2EntityLivingBase, par3, par5, par7);
      this.setSize(0.3125F, 0.3125F);
   }

   @SideOnly(Side.CLIENT)
   public EntityMWAWWindBall(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
      super(par1World, par2, par4, par6, par8, par10, par12);
      this.setSize(0.3125F, 0.3125F);
   }

   @Override
   protected float getMotionFactor() {
      return 100.95F;
   }

   @Override
   public boolean isBurning() {
      return false;
   }

   @Override
   protected void onImpact(RayTraceResult par1MovingObjectPosition) {
      if (!this.world.isRemote) {
         if (par1MovingObjectPosition.entityHit != null && this.shootingEntity != null) {
            EntityMiniTornado entitywitherskull1 = new EntityMiniTornado(this.world);
            entitywitherskull1.setLocationAndAngles(
               par1MovingObjectPosition.entityHit.posX,
               par1MovingObjectPosition.entityHit.posY + 20.0,
               par1MovingObjectPosition.entityHit.posZ,
               this.rotationYaw,
               0.0F
            );
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            entitywitherskull1.startRiding(par1MovingObjectPosition.entityHit);
         }

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
      if (this.ticksExisted >= 5) {
         this.setDead();
      }

      this.setPosition(this.posX, this.posY, this.posZ);
      super.onUpdate();
   }
}
