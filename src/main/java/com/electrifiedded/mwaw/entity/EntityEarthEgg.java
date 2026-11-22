package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityEarthEgg extends EntityTameable {
   public int TimerToHatch = 1000;

   public EntityEarthEgg(World p_i1744_1_) {
      super(p_i1744_1_);
      this.setSize(0.7F, 0.9F);
      this.isImmuneToFire = true;
      this.tasks.addTask(1, new EntityAILookIdle(this));
      this.setTamed(true);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0);
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
   public void onLivingUpdate() {
      if (this.TimerToHatch == 0) {
         EntityEarthPet mob = new EntityEarthPet(this.world);
         mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         MWAWEntityUtil.setOwner(mob, MWAWEntityUtil.ownerString(this));
         mob.setCustomNameTag(this.getCustomNameTag());
         mob.setTamed(true);
         MWAWEntityUtil.spawnInWorld(this.world, mob);
         this.setDead();
      }

      for (int i = 0; i < 1; i++) {
         double d = this.rand.nextGaussian() * 0.02;
         double d1 = this.rand.nextGaussian() * 0.02;
         double d2 = this.rand.nextGaussian() * 0.02;
         double d3 = 10.0;
         LegacyWorld.spawnParticle(this.world, 
               "blockcrack_3_0",
               this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width - d * d3,
               this.posY + this.rand.nextFloat() * this.height - d1 * d3,
               this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width - d2 * d3,
               d,
               d1,
               d2
            );
      }

      if (!this.world.isRemote) {
         if (this.ticksExisted % 20 == 0
            && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) == Blocks.HAY_BLOCK) {
            this.TimerToHatch--;
            this.TimerToHatch--;
         } else if (this.ticksExisted % 20 == 0
            && LegacyWorld.getBlock(this.world, (int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.HAY_BLOCK) {
            this.TimerToHatch--;
         }
      }

      List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20.0, 20.0, 20.0));

      for (int i = 0; i < list.size(); i++) {
         Entity entity1 = (Entity)list.get(i);
         if (entity1 instanceof EntityMob) {
            ((EntityMob)entity1).setAttackTarget(this);
         }
      }

      super.onLivingUpdate();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setInteger("TimerToHatch", this.TimerToHatch);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.TimerToHatch = compund.getInteger("TimerToHatch");
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   public EntityEarthEgg createChild(EntityAgeable p_90011_1_) {
      return null;
   }

   public boolean processInteract(EntityPlayer p_70085_1_, net.minecraft.util.EnumHand hand) {
      if (hand != net.minecraft.util.EnumHand.MAIN_HAND) return false;
      if (this.isTamed()) {
         if (!this.isRiding()) {
            this.startRiding(p_70085_1_);
         } else if (this.isRiding()) {
            this.dismountRidingEntity();
         }
      }

      return super.processInteract(p_70085_1_, hand);
   }
}
