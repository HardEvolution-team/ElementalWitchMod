package com.electrifiedded.mwaw.projectile;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMWAWFireBall extends EntityFireball {
   private boolean mwawInvulnerable;
   public EntityMWAWFireBall(World par1World) {
      super(par1World);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityMWAWFireBall(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
      super(par1World, par2EntityLivingBase, par3, par5, par7);
      this.setSize(0.3125F, 0.3125F);
   }

   @SideOnly(Side.CLIENT)
   public EntityMWAWFireBall(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
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
         if (par1MovingObjectPosition.entityHit != null) {
            if (this.shootingEntity != null) {
               EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull2 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull3 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull4 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull5 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull6 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull7 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull8 = new EntityLargeFireball(this.world);
               EntityLargeFireball entitywitherskull9 = new EntityLargeFireball(this.world);
               entitywitherskull1.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull2.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ + 1.0,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull3.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ - 1.0,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull4.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX + 1.0,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull5.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX - 1.0,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull6.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX - 1.0,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ + 1.0,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull7.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX - 1.0,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ - 1.0,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull8.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX + 1.0,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ + 1.0,
                  this.rotationYaw,
                  0.0F
               );
               entitywitherskull9.setLocationAndAngles(
                  par1MovingObjectPosition.entityHit.posX + 1.0,
                  par1MovingObjectPosition.entityHit.posY + 20.0,
                  par1MovingObjectPosition.entityHit.posZ - 1.0,
                  this.rotationYaw,
                  0.0F
               );
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull2);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull3);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull4);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull5);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull6);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull7);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull8);
               MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull9);
               entitywitherskull1.motionY = -5.0;
               entitywitherskull2.motionY = -5.0;
               entitywitherskull3.motionY = -5.0;
               entitywitherskull4.motionY = -5.0;
               entitywitherskull5.motionY = -5.0;
               entitywitherskull6.motionY = -5.0;
               entitywitherskull7.motionY = -5.0;
               entitywitherskull8.motionY = -5.0;
               entitywitherskull9.motionY = -5.0;
            } else {
               com.electrifiedded.archaiclib.legacy.world.LegacyMOP mop_ = com.electrifiedded.archaiclib.legacy.world.LegacyMOP.of(par1MovingObjectPosition);
               int i = mop_.blockX;
               int j = mop_.blockY;
               int k = mop_.blockZ;
               switch (mop_.sideHit) {
                  case 0:
                     j--;
                     break;
                  case 1:
                     j++;
                     break;
                  case 2:
                     k--;
                     break;
                  case 3:
                     k++;
                     break;
                  case 4:
                     i--;
                     break;
                  case 5:
                     i++;
               }

               if (LegacyWorld.isAirBlock(this.world, i, j, k)) {
                  EntityLargeFireball entitywitherskull1 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull2 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull3 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull4 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull5 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull6 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull7 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull8 = new EntityLargeFireball(this.world);
                  EntityLargeFireball entitywitherskull9 = new EntityLargeFireball(this.world);
                  entitywitherskull1.setLocationAndAngles(i, j + 20, k, this.rotationYaw, 0.0F);
                  entitywitherskull2.setLocationAndAngles(i, j + 20, k + 1, this.rotationYaw, 0.0F);
                  entitywitherskull3.setLocationAndAngles(i, j + 20, k - 1, this.rotationYaw, 0.0F);
                  entitywitherskull4.setLocationAndAngles(i + 1, j + 20, k, this.rotationYaw, 0.0F);
                  entitywitherskull5.setLocationAndAngles(i - 1, j + 20, k, this.rotationYaw, 0.0F);
                  entitywitherskull6.setLocationAndAngles(i - 1, j + 20, k + 1, this.rotationYaw, 0.0F);
                  entitywitherskull7.setLocationAndAngles(i - 1, j + 20, k - 1, this.rotationYaw, 0.0F);
                  entitywitherskull8.setLocationAndAngles(i + 1, j + 20, k + 1, this.rotationYaw, 0.0F);
                  entitywitherskull9.setLocationAndAngles(i + 1, j + 20, k - 1, this.rotationYaw, 0.0F);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull2);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull3);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull4);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull5);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull6);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull7);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull8);
                  MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull9);
                  entitywitherskull1.motionY = -5.0;
                  entitywitherskull2.motionY = -5.0;
                  entitywitherskull3.motionY = -5.0;
                  entitywitherskull4.motionY = -5.0;
                  entitywitherskull5.motionY = -5.0;
                  entitywitherskull6.motionY = -5.0;
                  entitywitherskull7.motionY = -5.0;
                  entitywitherskull8.motionY = -5.0;
                  entitywitherskull9.motionY = -5.0;
               }
            }
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
