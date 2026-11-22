package com.electrifiedded.mwaw.projectile;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityPumpkinSkull extends EntityFireball {
   private boolean mwawInvulnerable;
   public EntityPumpkinSkull(World p_i1793_1_) {
      super(p_i1793_1_);
      this.setSize(0.3125F, 0.3125F);
   }

   public EntityPumpkinSkull(World p_i1794_1_, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_) {
      super(p_i1794_1_, p_i1794_2_, p_i1794_3_, p_i1794_5_, p_i1794_7_);
      this.setSize(0.3125F, 0.3125F);
   }

   @SideOnly(Side.CLIENT)
   public EntityPumpkinSkull(
      World p_i1795_1_, double p_i1795_2_, double p_i1795_4_, double p_i1795_6_, double p_i1795_8_, double p_i1795_10_, double p_i1795_12_
   ) {
      super(p_i1795_1_, p_i1795_2_, p_i1795_4_, p_i1795_6_, p_i1795_8_, p_i1795_10_, p_i1795_12_);
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
   public float getExplosionResistance(Explosion explosionIn, World worldIn, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState state) {
      float f = super.getExplosionResistance(explosionIn, worldIn, pos, state);
      Block blockIn = state.getBlock();
      if (this.isInvulnerable()
         && blockIn != Blocks.BEDROCK
         && blockIn != Blocks.END_PORTAL
         && blockIn != Blocks.END_PORTAL_FRAME
         && blockIn != Blocks.COMMAND_BLOCK) {
         f = Math.min(0.8F, f);
      }

      return f;
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
   protected void onImpact(RayTraceResult p_70227_1_) {
      if (!this.world.isRemote) {
         if (p_70227_1_.entityHit != null) {
            if (this.shootingEntity != null) {
               if (p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F) && !p_70227_1_.entityHit.isEntityAlive()) {
               }
            } else {
               p_70227_1_.entityHit.attackEntityFrom(DamageSource.MAGIC, 3.0F);
            }

            if (p_70227_1_.entityHit instanceof EntityLivingBase) {
               byte b0 = 0;
               if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
                  b0 = 5;
               } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
                  b0 = 10;
               }

               if (b0 > 0) {
                  ((EntityLivingBase)p_70227_1_.entityHit).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.WITHER, 20 * b0, 1));
               }
            }
         } else {
            com.electrifiedded.archaiclib.legacy.world.LegacyMOP mop_ = com.electrifiedded.archaiclib.legacy.world.LegacyMOP.of(p_70227_1_);
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
               LegacyWorld.setBlock(this.world, i, j, k, Blocks.LIT_PUMPKIN);
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
   public boolean attackEntityFrom(DamageSource source, float amount) {
      return false;
   }

   @Override
   protected void entityInit() {
   }

   public boolean isInvulnerable() {
      return this.mwawInvulnerable;
   }

   public void setInvulnerable(boolean p_82343_1_) {
      this.mwawInvulnerable = p_82343_1_;
   }
}
