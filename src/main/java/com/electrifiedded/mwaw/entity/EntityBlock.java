package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityBlock extends EntityFallingBlock {
   public Block blockType = net.minecraft.init.Blocks.STONE;

   public EntityBlock(World world) {
      super(world);
      this.rotationYaw = (float)(Math.random() * 360.0);
      this.rotationPitch = (float)(Math.random() * 360.0);
   }

   public EntityBlock(World world, double x, double y, double z, Block block) {
      super(world, x, y, z, block.getDefaultState());
      this.blockType = block;
   }

   @Override
   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      this.fallTime++;
      this.motionY -= 0.15;
      this.move(net.minecraft.entity.MoverType.SELF, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9;
      this.motionY *= 0.9;
      this.motionZ *= 0.9;
      this.prevRotationPitch = this.rotationPitch;
      this.prevRotationYaw = this.rotationYaw;
      this.rotationPitch = (float)((this.rotationPitch + 3.0) % 360.0);
      this.rotationYaw = (float)((this.rotationPitch + 3.0) % 360.0);
      List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(1.0, 1.0, 1.0));

      for (int i = 0; i < targetList.size(); i++) {
         Entity entitytargetcheck = (Entity)targetList.get(i);
         if (entitytargetcheck instanceof EntityLivingBase
            && !(entitytargetcheck instanceof EntityEarthWitch)
            && !(entitytargetcheck instanceof EntityEarthMinion)
            && !(entitytargetcheck instanceof EntityBlock)) {
            int k = MathHelper.ceil(1.0F);
            entitytargetcheck.attackEntityFrom(DamageSource.FALLING_BLOCK, Math.min(MathHelper.floor(k * 2.0F), 60));
            ((EntityLivingBase)entitytargetcheck).addPotionEffect(new PotionEffect(net.minecraft.init.MobEffects.SLOWNESS, 30, 1));
         }
      }

      int ix = (int)this.posX;
      int iy = (int)this.posY;
      int iz = (int)this.posZ;
      if (this.fallTime == 1 && LegacyWorld.getBlock(this.world, ix, iy, iz) == this.blockType) {
         LegacyWorld.setBlockToAir(this.world, ix, iy, iz);
      }

      if (this.onGround) {
         this.motionX *= 0.7;
         this.motionZ *= 0.7;
         this.motionY *= -0.5;
         if (this.fallTime > 5
            && LegacyWorld.getBlock(this.world, ix, iy, iz) != Blocks.PISTON_EXTENSION
            && LegacyWorld.getBlock(this.world, ix, iy - 1, iz) != Blocks.AIR
            && LegacyWorld.setBlock(this.world, ix, iy, iz, this.blockType)) {
            this.setDead();
         }
      } else if (!this.world.isRemote && (this.fallTime > 100 && (iy < 1 || iy > 256) || this.fallTime > 600)) {
         this.dropItem(Item.getItemFromBlock(this.blockType), 1);
         this.setDead();
      }

      if (this.ticksExisted >= 30) {
         LegacyWorld.setBlock(this.world, ix, iy, iz, this.blockType);
         this.setDead();
      }
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
      int i = MathHelper.ceil(distance - 1.0F);
      if (i > 0) {
         boolean hurt = false;

         for (Object o : this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox())) {
            ((Entity)o).attackEntityFrom(DamageSource.FALLING_BLOCK, Math.min(MathHelper.floor(i * 3.0F), 60));
            if (!hurt) {
               hurt = true;
            }
         }
      }
   }

}
