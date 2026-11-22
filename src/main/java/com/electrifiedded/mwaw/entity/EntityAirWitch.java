package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import net.minecraft.util.SoundEvent;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import com.electrifiedded.mwaw.projectile.EntityWindShoot;
import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAirWitch extends EntityMob implements IRangedAttackMob {
   public void setSwingingArms(boolean swinging) {
   }

   boolean summonTornado = false;
   int summonMiniTornado = 600;
   int summonWindClone = 300;

   public EntityAirWitch(World p_i1744_1_) {
      super(p_i1744_1_);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0, 60, 10.0F));
      this.tasks.addTask(2, new EntityAIWander(this, 1.0));
      this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(3, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
   }

   protected SoundEvent getAmbientSound() {
      return LegacySounds.get("mob.witch.idle");
   }

   @Override
   protected SoundEvent getHurtSound(net.minecraft.util.DamageSource src) {
      return LegacySounds.get("mob.witch.hurt");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.witch.death");
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   

   @Override
   public void onLivingUpdate() {
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      if (this.summonMiniTornado != 0 && this.getAttackTarget() != null) {
         this.summonMiniTornado--;
      }

      if (this.summonWindClone != 0 && this.getAttackTarget() != null) {
         this.summonWindClone--;
      }

      if (this.rand.nextFloat() < 7.5E-4F) {
         this.world.setEntityState(this, (byte)15);
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

      if (this.getAttackTarget() != null) {
         if (this.summonMiniTornado == 0) {
            EntityMiniTornado entitywitherskull1 = new EntityMiniTornado(this.world);
            entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entitywitherskull1.startRiding(this.getAttackTarget());
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            this.summonMiniTornado = 600;
         }

         if (this.summonWindClone == 0) {
            EntityAirClone entitywitherskull1 = new EntityAirClone(this.world);
            entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
            EntityAirClone entitywitherskull2 = new EntityAirClone(this.world);
            entitywitherskull2.setLocationAndAngles(this.posX, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
            entitywitherskull1.setAttackTarget(this.getAttackTarget());
            entitywitherskull1.setHealth(this.getHealth());
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            entitywitherskull2.setAttackTarget(this.getAttackTarget());
            entitywitherskull2.setHealth(this.getHealth());
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull2);
            this.summonWindClone = 300;
         }

         if (this.getHealth() <= 30.0F && !this.summonTornado) {
            EntityTornado entitywitherskull1 = new EntityTornado(this.world);
            entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entitywitherskull1.startRiding(this);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            this.summonTornado = true;
         }
      }

      super.onLivingUpdate();
   }

   private double func_82214_u(int par1) {
      if (par1 <= 0) {
         return this.posX;
      } else {
         float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
         float f1 = MathHelper.cos(f);
         return this.posX + f1 * 1.3;
      }
   }

   private double func_82208_v(int par1) {
      return par1 <= 0 ? this.posY + 0.5 : this.posY + 0.5;
   }

   private double func_82213_w(int par1) {
      if (par1 <= 0) {
         return this.posZ;
      } else {
         float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
         float f1 = MathHelper.sin(f);
         return this.posZ + f1 * 1.3;
      }
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
      this.launchWitherSkullToEntity(0, p_82196_1_);
   }

   private void launchWitherSkullToEntity(int par1, EntityLivingBase par2EntityLivingBase) {
      this.launchWitherSkullToCoords(
         par1,
         par2EntityLivingBase.posX,
         par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight() * 0.5,
         par2EntityLivingBase.posZ,
         par1 == 0 && this.rand.nextFloat() < 0.001F
      );
   }

   private void launchWitherSkullToCoords(int par1, double par2, double par4, double par6, boolean par8) {
      Random rand = new Random();
      int randomNumber = rand.nextInt(4);
      double d3 = this.func_82214_u(par1);
      double d4 = this.func_82208_v(par1);
      double d5 = this.func_82213_w(par1);
      double d6 = par2 - d3;
      double d7 = par4 - d4;
      double d8 = par6 - d5;
      EntityWindShoot entitywitherskull = new EntityWindShoot(this.world, this, d6, d7, d8, randomNumber);
      entitywitherskull.posY = d4 + 0.5;
      entitywitherskull.posX = d3;
      entitywitherskull.posZ = d5;
      this.world.spawnEntity(entitywitherskull);
   }

   @Override
   public void setDead() {
      List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20.0, 20.0, 20.0));

      for (int i = 0; i < targetList.size(); i++) {
         Entity entitytargetcheck = (Entity)targetList.get(i);
         if (entitytargetcheck instanceof EntityTornado) {
            entitytargetcheck.setDead();
         }

         if (entitytargetcheck instanceof EntityMiniTornado) {
            entitytargetcheck.setDead();
         }
      }

      super.setDead();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setBoolean("WitchSummonTornado", this.summonTornado);
      compund.setInteger("WitchSummonMiniTornado", this.summonMiniTornado);
      compund.setInteger("WitchSummonWindClone", this.summonWindClone);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.summonTornado = compund.getBoolean("WitchSummonTornado");
      this.summonMiniTornado = compund.getInteger("WitchSummonMiniTornado");
      this.summonWindClone = compund.getInteger("WitchSummonWindClone");
   }

   @Override
   protected Item getDropItem() {
      return MWAWItemRegistry.airshard;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      return !(source.getTrueSource() instanceof EntityAirClone) && !(source.getTrueSource() instanceof EntityMiniTornado)
         ? super.attackEntityFrom(source, damage)
         : false;
   }

   @Override
   protected boolean canDespawn() {
      return false;
   }

   public boolean processInteract(EntityPlayer p_70085_1_, net.minecraft.util.EnumHand hand) {
      if (hand != net.minecraft.util.EnumHand.MAIN_HAND) return false;
      ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
      if (itemstack != null
         && itemstack != null
         && itemstack.getItem() == MWAWItemRegistry.emptyegg
         && this.getHealth() <= 13.0F
         && this.rand.nextInt(1000) <= 10) {
         itemstack.shrink(1);
         p_70085_1_.dropItem(MWAWItemRegistry.airegg, 1);
         this.setDead();
      }

      return super.processInteract(p_70085_1_, hand);
   }
}
