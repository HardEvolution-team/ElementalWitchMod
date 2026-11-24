package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import net.minecraft.util.SoundEvent;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
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
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityEarthWitch extends EntityMob implements IRangedAttackMob {
   public void setSwingingArms(boolean swinging) {
   }

   public int blockSuffo = 300;
   public int summonGolem = 600;
   public boolean healContainer = false;

   public EntityEarthWitch(World p_i1744_1_) {
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

      if (this.summonGolem != 0 && this.getAttackTarget() != null) {
         this.summonGolem--;
      }

      if (this.blockSuffo != 0 && this.getAttackTarget() != null) {
         this.blockSuffo--;
      }

      if (this.getAttackTarget() != null && !this.world.isRemote) {
         if (this.getHealth() <= 30.0F) {
            if (this.ticksExisted % 20 == 0) {
               this.heal(4.0F);
            }

            if (!this.healContainer) {
               this.motionZ++;
               Block block = Blocks.STONE;
               int xx = (int)this.posX;
               int yy = (int)this.posY;
               int zz = (int)this.posZ;
               LegacyWorld.setBlock(this.world, xx, yy + 3, zz, block);
               LegacyWorld.setBlock(this.world, xx, yy + 3, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx, yy + 3, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy + 3, zz, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy + 3, zz, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy + 3, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy + 3, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy + 3, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy + 3, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx, yy - 1, zz, block);
               LegacyWorld.setBlock(this.world, xx, yy - 1, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx, yy - 1, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy - 1, zz, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy - 1, zz, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy - 1, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy - 1, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy - 1, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy - 1, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx, yy + 2, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx, yy + 2, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy + 2, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy + 2, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy + 2, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy + 2, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx, yy + 1, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx, yy + 1, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy + 1, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy + 1, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy + 1, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy + 1, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx, yy, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx, yy, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy, zz, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy, zz, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy, zz + 2, block);
               LegacyWorld.setBlock(this.world, xx - 1, yy, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 1, yy, zz - 2, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx + 2, yy, zz + 1, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy, zz - 1, block);
               LegacyWorld.setBlock(this.world, xx - 2, yy, zz + 1, block);

               for (int i = 0; i < 20; i++) {
                  Random rand = new com.electrifiedded.archaiclib.util.FastRandom();
                  int randomNumber = rand.nextInt(5);
                  EntityEarthMinion entity = new EntityEarthMinion(this.world);
                  if (randomNumber == 1) {
                     entity.setLocationAndAngles(this.posX + 4.0, this.posY, this.posZ - 4.0, 0.0F, 0.0F);
                     entity.setAttackTarget(this.getAttackTarget());
                     MWAWEntityUtil.spawnInWorld(this.world, entity);
                  } else if (randomNumber == 2) {
                     entity.setLocationAndAngles(this.posX - 5.0, this.posY, this.posZ + 4.0, 0.0F, 0.0F);
                     entity.setAttackTarget(this.getAttackTarget());
                     MWAWEntityUtil.spawnInWorld(this.world, entity);
                  } else if (randomNumber == 3) {
                     entity.setLocationAndAngles(this.posX + 5.0, this.posY, this.posZ - 5.0, 0.0F, 0.0F);
                     entity.setAttackTarget(this.getAttackTarget());
                     MWAWEntityUtil.spawnInWorld(this.world, entity);
                  } else if (randomNumber == 4) {
                     entity.setLocationAndAngles(this.posX - 4.0, this.posY, this.posZ + 5.0, 0.0F, 0.0F);
                     entity.setAttackTarget(this.getAttackTarget());
                     MWAWEntityUtil.spawnInWorld(this.world, entity);
                  }
               }

               this.healContainer = true;
            }
         }

         if (this.summonGolem == 0) {
            this.motionZ = 2.0;
            Block block = Blocks.STONE;
            int xx = (int)this.getAttackTarget().posX;
            int yy = (int)this.getAttackTarget().posY;
            int zz = (int)this.getAttackTarget().posZ;
            LegacyWorld.setBlock(this.world, xx, yy + 3, zz, block);
            LegacyWorld.setBlock(this.world, xx, yy + 3, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx, yy + 3, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy + 3, zz, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy + 3, zz, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy + 3, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy + 3, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy + 3, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy + 3, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx, yy - 1, zz, block);
            LegacyWorld.setBlock(this.world, xx, yy - 1, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx, yy - 1, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy - 1, zz, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy - 1, zz, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy - 1, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy - 1, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy - 1, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy - 1, zz + 1, block);
            EntityEarthMinion entitywitherskull1 = new EntityEarthMinion(this.world);
            entitywitherskull1.setLocationAndAngles(xx, yy, zz, this.rotationYaw, 0.0F);
            entitywitherskull1.setAttackTarget(this.getAttackTarget());
            EntityEarthMinion entitywitherskull3 = new EntityEarthMinion(this.world);
            entitywitherskull3.setLocationAndAngles(xx, yy, zz, this.rotationYaw, 0.0F);
            entitywitherskull3.setAttackTarget(this.getAttackTarget());
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull1);
            MWAWEntityUtil.spawnInWorld(this.world, entitywitherskull3);
            LegacyWorld.setBlock(this.world, xx, yy + 2, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx, yy + 2, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy + 2, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy + 2, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy + 2, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy + 2, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 2, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 2, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx, yy + 1, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx, yy + 1, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy + 1, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy + 1, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy + 1, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy + 1, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy + 1, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy + 1, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx, yy, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx, yy, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy, zz, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy, zz, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy, zz + 2, block);
            LegacyWorld.setBlock(this.world, xx - 1, yy, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 1, yy, zz - 2, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx + 2, yy, zz + 1, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy, zz - 1, block);
            LegacyWorld.setBlock(this.world, xx - 2, yy, zz + 1, block);
            this.summonGolem = 300;
         }

         if (this.blockSuffo == 0) {
            int xx = (int)Math.floor(this.getAttackTarget().posX);
            int yy = (int)Math.floor(this.getAttackTarget().posY);
            int zz = (int)Math.floor(this.getAttackTarget().posZ);
            EntityBlock entitywitherskull = new EntityBlock(this.world, xx, yy + 3, zz, Blocks.STONE);
            EntityBlock entitywitherskull1 = new EntityBlock(this.world, xx, yy + 3, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskull2 = new EntityBlock(this.world, xx, yy + 3, zz - 1, Blocks.STONE);
            EntityBlock entitywitherskull3 = new EntityBlock(this.world, xx + 1, yy + 3, zz, Blocks.STONE);
            EntityBlock entitywitherskul4 = new EntityBlock(this.world, xx - 1, yy + 3, zz, Blocks.STONE);
            EntityBlock entitywitherskul5 = new EntityBlock(this.world, xx - 1, yy + 3, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskul6 = new EntityBlock(this.world, xx - 1, yy + 3, zz - 1, Blocks.STONE);
            EntityBlock entitywitherskul7 = new EntityBlock(this.world, xx + 1, yy + 3, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskul8 = new EntityBlock(this.world, xx + 1, yy + 3, zz - 1, Blocks.STONE);
            this.world.spawnEntity(entitywitherskull);
            this.world.spawnEntity(entitywitherskull1);
            this.world.spawnEntity(entitywitherskull2);
            this.world.spawnEntity(entitywitherskull3);
            this.world.spawnEntity(entitywitherskul4);
            this.world.spawnEntity(entitywitherskul5);
            this.world.spawnEntity(entitywitherskul6);
            this.world.spawnEntity(entitywitherskul7);
            this.world.spawnEntity(entitywitherskul8);
            EntityBlock entitywitherskull9 = new EntityBlock(this.world, xx, yy + 4, zz, Blocks.STONE);
            EntityBlock entitywitherskull10 = new EntityBlock(this.world, xx, yy + 4, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskull11 = new EntityBlock(this.world, xx, yy + 4, zz - 1, Blocks.STONE);
            EntityBlock entitywitherskull12 = new EntityBlock(this.world, xx + 1, yy + 4, zz, Blocks.STONE);
            EntityBlock entitywitherskul13 = new EntityBlock(this.world, xx - 1, yy + 4, zz, Blocks.STONE);
            EntityBlock entitywitherskul14 = new EntityBlock(this.world, xx - 1, yy + 4, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskul15 = new EntityBlock(this.world, xx - 1, yy + 4, zz - 1, Blocks.STONE);
            EntityBlock entitywitherskul16 = new EntityBlock(this.world, xx + 1, yy + 4, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskul17 = new EntityBlock(this.world, xx + 1, yy + 4, zz - 1, Blocks.STONE);
            this.world.spawnEntity(entitywitherskull9);
            this.world.spawnEntity(entitywitherskull10);
            this.world.spawnEntity(entitywitherskull11);
            this.world.spawnEntity(entitywitherskull12);
            this.world.spawnEntity(entitywitherskul13);
            this.world.spawnEntity(entitywitherskul14);
            this.world.spawnEntity(entitywitherskul15);
            this.world.spawnEntity(entitywitherskul16);
            this.world.spawnEntity(entitywitherskul17);
            EntityBlock entitywitherskull18 = new EntityBlock(this.world, xx, yy + 5, zz, Blocks.STONE);
            EntityBlock entitywitherskull19 = new EntityBlock(this.world, xx, yy + 5, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskull20 = new EntityBlock(this.world, xx, yy + 5, zz - 1, Blocks.STONE);
            EntityBlock entitywitherskull21 = new EntityBlock(this.world, xx + 1, yy + 5, zz, Blocks.STONE);
            EntityBlock entitywitherskul22 = new EntityBlock(this.world, xx - 1, yy + 5, zz, Blocks.STONE);
            EntityBlock entitywitherskul23 = new EntityBlock(this.world, xx - 1, yy + 5, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskul24 = new EntityBlock(this.world, xx - 1, yy + 5, zz - 1, Blocks.STONE);
            EntityBlock entitywitherskul25 = new EntityBlock(this.world, xx + 1, yy + 5, zz + 1, Blocks.STONE);
            EntityBlock entitywitherskul26 = new EntityBlock(this.world, xx + 1, yy + 5, zz - 1, Blocks.STONE);
            this.world.spawnEntity(entitywitherskull18);
            this.world.spawnEntity(entitywitherskull19);
            this.world.spawnEntity(entitywitherskull20);
            this.world.spawnEntity(entitywitherskull21);
            this.world.spawnEntity(entitywitherskul22);
            this.world.spawnEntity(entitywitherskul23);
            this.world.spawnEntity(entitywitherskul24);
            this.world.spawnEntity(entitywitherskul25);
            this.world.spawnEntity(entitywitherskul26);
            this.blockSuffo = 500;
         }
      }

      if (this.rand.nextFloat() < 7.5E-4F) {
         this.world.setEntityState(this, (byte)15);
      }

      super.onLivingUpdate();
   }

   public boolean isArmored() {
      return this.getHealth() <= 50.0F;
   }

   @Override
   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
      EntityBlock entitysnowball = new EntityBlock(this.world, this.posX, this.posY + 2.0, this.posZ, Blocks.DIRT);
      entitysnowball.motionX = -MathHelper.sin(this.rotationYawHead / 180.0F * (float) Math.PI)
            * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)
            * 0.4F
         * 2.0;
      entitysnowball.motionZ = MathHelper.cos(this.rotationYawHead / 180.0F * (float) Math.PI)
            * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI)
            * 0.4F
         * 2.0;
      entitysnowball.motionY = -MathHelper.sin((this.rotationYawHead + 1.5F) / 180.0F * (float) Math.PI) * 1.0F;
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      if (!(source.getTrueSource() instanceof EntityFallingBlock)
         && !(source.getTrueSource() instanceof EntityBlock)
         && !source.equals(DamageSource.IN_WALL)
         && !source.equals(DamageSource.FALLING_BLOCK)) {
         if (this.isArmored()) {
            Entity entity = source.getImmediateSource();
            if (entity instanceof EntityArrow) {
               return false;
            }
         }

         return super.attackEntityFrom(source, damage);
      } else {
         return false;
      }
   }

   @Override
   public void setDead() {
      List targetList = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(20.0, 100.0, 20.0));

      for (int i = 0; i < targetList.size(); i++) {
         Entity entitytargetcheck = (Entity)targetList.get(i);
         if (entitytargetcheck instanceof EntityEarthMinion) {
            entitytargetcheck.setDead();
         }

         if (entitytargetcheck instanceof EntityBlock) {
            entitytargetcheck.setDead();
         }
      }

      super.setDead();
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setInteger("WitchBlockSuffo", this.blockSuffo);
      compund.setInteger("WitchMinionSummon", this.summonGolem);
      compund.setBoolean("WitchHealContainer", this.healContainer);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.blockSuffo = compund.getInteger("WitchBlockSuffo");
      this.summonGolem = compund.getInteger("WitchMinionSummon");
      this.healContainer = compund.getBoolean("WitchHealContainer");
   }

   @Override
   protected Item getDropItem() {
      return MWAWItemRegistry.earthshard;
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
         p_70085_1_.dropItem(MWAWItemRegistry.earthegg, 1);
         this.setDead();
      }

      return super.processInteract(p_70085_1_, hand);
   }
}
