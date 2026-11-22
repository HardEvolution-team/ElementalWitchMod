package com.electrifiedded.mwaw.entity;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import net.minecraft.util.SoundEvent;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import com.electrifiedded.mwaw.projectile.EntityLightningBall;
import com.electrifiedded.mwaw.projectile.EntityLightningShoot;
import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import java.util.List;
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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityLightningWitch extends EntityMob implements IRangedAttackMob {
   public void setSwingingArms(boolean swinging) {
   }

   int timer = 52;
   boolean setWeather = false;
   int summonGolem = 300;
   int summonLightning = 600;
   int summonLightningBall = 200;

   public EntityLightningWitch(World p_i1744_1_) {
      super(p_i1744_1_);
      this.isImmuneToFire = true;
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
      if (this.summonGolem != 0 && this.getAttackTarget() != null) {
         this.summonGolem--;
      }

      if (this.summonLightning != 0 && this.getAttackTarget() != null) {
         this.summonLightning--;
      }

      if (this.summonLightningBall != 0 && this.getAttackTarget() != null) {
         this.summonLightningBall--;
      }

      if (this.world.isRemote) {
         for (int i = 0; i < 10; i++) {
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

      if (this.getAttackTarget() != null) {
         if (this.summonGolem == 0) {
            EntityLightningGolem entitywitherskull1 = new EntityLightningGolem(this.world);
            entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
            entitywitherskull1.setAttackTarget(this.getAttackTarget());
            EntityLightningGolem entitywitherskull3 = new EntityLightningGolem(this.world);
            entitywitherskull3.setLocationAndAngles(this.posX, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
            entitywitherskull3.setAttackTarget(this.getAttackTarget());
            EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
            entitywitherskull4.setLocationAndAngles(this.posX, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
            EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
            entitywitherskull5.setLocationAndAngles(this.posX, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull3);
            MWAWEntityUtil.spawnWeather(this.world, entitywitherskull4);
            MWAWEntityUtil.spawnWeather(this.world, entitywitherskull5);
            this.summonGolem = 300;
         }

         if (this.summonLightning == 0) {
            double xx = this.getAttackTarget().posX;
            double yy = this.getAttackTarget().posY;
            double zz = this.getAttackTarget().posZ;
            EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
            entitywitherskull1.setLocationAndAngles(xx, yy + 1.0, zz, this.rotationYaw, 0.0F);
            MWAWEntityUtil.spawnWeather(this.world, entitywitherskull1);
            this.summonLightning = 600;
         }

         if (!this.setWeather) {
            this.world.getWorldInfo().setRaining(true);
            this.world.getWorldInfo().setThundering(true);
            this.setWeather = true;
         }

         if (this.getHealth() <= 30.0F) {
            this.timer--;

            for (int i = 0; i < 10; i++) {
               if (this.world.isRemote) {
                  MoWitchAndWizard.proxy.fx(new FxArgs("EntityLightningFX", 
                           this.world,
                           this.posX + this.rand.nextGaussian() * 8.0 * 0.5,
                           this.posY,
                           this.posZ + this.rand.nextGaussian() * 8.0 * 0.5,
                           0.0,
                           0.5,
                           0.0
                        )
                     );
               }
            }

            if (this.timer == 50) {
               EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull2 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull2.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ + 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull3 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull3.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ + 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull4.setLocationAndAngles(this.posX + 2.0, this.posY, this.posZ, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull5.setLocationAndAngles(this.posX + 2.0, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull6 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull6.setLocationAndAngles(this.posX + 2.0, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull7 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ - 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull8 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull2.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ - 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull9 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull3.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ - 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull10 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull10.setLocationAndAngles(this.posX - 2.0, this.posY, this.posZ, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull11 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull11.setLocationAndAngles(this.posX - 2.0, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull12 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull12.setLocationAndAngles(this.posX - 2.0, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull1);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull2);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull3);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull4);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull5);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull6);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull7);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull8);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull9);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull10);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull11);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull12);
            } else if (this.timer == 25) {
               EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull2 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull2.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull3 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull3.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull4.setLocationAndAngles(this.posX - 2.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull5.setLocationAndAngles(this.posX + 2.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull6 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull6.setLocationAndAngles(this.posX - 3.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull7 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull7.setLocationAndAngles(this.posX + 3.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskul35 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskul35.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskul36 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskul36.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull8 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull8.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull9 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull9.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull10 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull10.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull11 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull11.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ - 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull12 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull12.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ + 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull13 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull13.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ - 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull14 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull14.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ + 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull33 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull33.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull34 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull34.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull15 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull15.setLocationAndAngles(this.posX, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull16 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull16.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull17 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull17.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull18 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull18.setLocationAndAngles(this.posX + 2.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull19 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull19.setLocationAndAngles(this.posX - 2.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull20 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull20.setLocationAndAngles(this.posX + 3.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull21 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull21.setLocationAndAngles(this.posX - 3.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull22 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull22.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull23 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull23.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull24 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull24.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull25 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull25.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull26 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull26.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull27 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull27.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ - 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull28 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull28.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ + 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull29 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull29.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ - 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull30 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull30.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ + 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull31 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull31.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull32 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull32.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull1);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull2);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull3);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull4);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull5);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull6);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull7);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull8);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull9);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull10);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull11);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull12);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull13);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull14);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull15);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull16);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull17);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull18);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull19);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull20);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull21);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull22);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull23);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull24);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull25);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull26);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull27);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull28);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull29);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull30);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull31);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull32);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull33);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull34);
            } else if (this.timer == 0) {
               EntityLightningBolt entitywitherskull1 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull1.setLocationAndAngles(this.posX, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull2 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull2.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull3 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull3.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull4 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull4.setLocationAndAngles(this.posX - 2.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull5 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull5.setLocationAndAngles(this.posX + 2.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull6 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull6.setLocationAndAngles(this.posX - 3.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull7 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull7.setLocationAndAngles(this.posX + 3.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull8 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull8.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull9 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull9.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull10 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull10.setLocationAndAngles(this.posX - 5.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull11 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull11.setLocationAndAngles(this.posX + 5.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull45 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull45.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull46 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull46.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull12 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull12.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull13 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull13.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull14 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull14.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull15 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull15.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ - 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull16 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull16.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ + 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull17 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull17.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ - 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull18 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull18.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ + 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull19 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull19.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull20 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull20.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull21 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull21.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ - 5.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull22 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull22.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ + 5.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull47 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull47.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull48 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull48.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull23 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull23.setLocationAndAngles(this.posX, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull24 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull24.setLocationAndAngles(this.posX - 1.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull25 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull25.setLocationAndAngles(this.posX + 1.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull26 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull26.setLocationAndAngles(this.posX - 2.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull27 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull27.setLocationAndAngles(this.posX + 2.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull28 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull28.setLocationAndAngles(this.posX - 3.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull29 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull29.setLocationAndAngles(this.posX + 3.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull30 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull30.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull31 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull31.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull32 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull32.setLocationAndAngles(this.posX - 5.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull33 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull33.setLocationAndAngles(this.posX + 5.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull49 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull49.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull50 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull50.setLocationAndAngles(this.posX + 6.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull34 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull34.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull35 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull35.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ - 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull36 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull36.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ + 1.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull37 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull37.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ - 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull38 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull38.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ + 2.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull39 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull39.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ - 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull40 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull40.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ + 3.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull41 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull41.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ - 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull42 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull42.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ + 4.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull43 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull43.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ - 5.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull44 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull44.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ + 5.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull51 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull51.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ - 6.0, this.rotationYaw, 0.0F);
               EntityLightningBolt entitywitherskull52 = new EntityLightningBolt(this.world, 1.0, 1.0, 1.0, false);
               entitywitherskull52.setLocationAndAngles(this.posX - 6.0, this.posY, this.posZ + 6.0, this.rotationYaw, 0.0F);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull1);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull2);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull3);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull4);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull5);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull6);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull7);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull8);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull9);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull10);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull11);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull12);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull13);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull14);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull15);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull16);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull17);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull18);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull19);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull20);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull21);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull22);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull23);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull24);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull25);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull26);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull27);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull28);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull29);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull30);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull31);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull32);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull33);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull35);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull36);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull37);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull38);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull39);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull41);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull42);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull43);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull44);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull45);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull46);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull47);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull48);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull49);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull50);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull51);
               MWAWEntityUtil.spawnWeather(this.world, entitywitherskull52);
               this.timer = 52;
            }
         }
      }

      if (this.rand.nextFloat() < 7.5E-4F) {
         this.world.setEntityState(this, (byte)15);
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
      if (this.summonLightningBall != 0) {
         for (int i = 0; i < 10; i++) {
            this.launchWitherSkullToEntity(0, p_82196_1_);
         }
      } else {
         this.launchWitherSkullToEntity(0, p_82196_1_);
         this.summonLightningBall = 300;
      }
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
      double d3 = this.func_82214_u(par1);
      double d4 = this.func_82208_v(par1);
      double d5 = this.func_82213_w(par1);
      double d6 = par2 - d3;
      double d7 = par4 - d4;
      double d8 = par6 - d5;
      if (this.summonLightningBall != 0) {
         EntityLightningShoot entitywitherskull = new EntityLightningShoot(this.world, this, d6, d7, d8);
         entitywitherskull.posY = d4 + 0.5;
         entitywitherskull.posX = d3;
         entitywitherskull.posZ = d5;
         this.world.spawnEntity(entitywitherskull);
      } else {
         EntityLightningBall entitywitherskull = new EntityLightningBall(this.world, this, d6, d7, d8);
         entitywitherskull.posY = d4 + 0.5;
         entitywitherskull.posX = d3;
         entitywitherskull.posZ = d5;
         this.world.spawnEntity(entitywitherskull);
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      return source.getTrueSource() instanceof EntityLightningBolt ? false : super.attackEntityFrom(source, damage);
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setInteger("WitchLightningTimer", this.timer);
      compund.setInteger("WitchLightningSummonGolem", this.summonGolem);
      compund.setInteger("WitchSummonLighting", this.summonLightning);
      compund.setInteger("WitchSummonLightningBall", this.summonLightningBall);
      compund.setBoolean("WitchSetWeather", this.setWeather);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.timer = compund.getInteger("WitchLightningTimer");
      this.summonGolem = compund.getInteger("WitchLightningSummonGolem");
      this.setWeather = compund.getBoolean("WitchSetWeather");
      this.summonLightning = compund.getInteger("WitchSummonLighting");
      this.summonLightningBall = compund.getInteger("WitchSummonLightningBall");
   }

   @Override
   public void setDead() {
      List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20.0, 100.0, 20.0));

      for (int i = 0; i < targetList.size(); i++) {
         Entity entitytargetcheck = (Entity)targetList.get(i);
         if (entitytargetcheck instanceof EntityLightningGolem) {
            entitytargetcheck.setDead();
         }

         super.setDead();
      }
   }

   @Override
   protected Item getDropItem() {
      return MWAWItemRegistry.lightningshard;
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
         p_70085_1_.dropItem(MWAWItemRegistry.lightningegg, 1);
         this.setDead();
      }

      return super.processInteract(p_70085_1_, hand);
   }
}
