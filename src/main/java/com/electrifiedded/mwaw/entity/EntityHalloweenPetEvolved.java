package com.electrifiedded.mwaw.entity;

import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityHalloweenPetEvolved extends EntityTameable {
   public int TimerEvolve = 500;
   public int TimerControl = 200;
   public int TimerTeleport = 50;
   public boolean Control = false;

   public EntityHalloweenPetEvolved(World par1World) {
      super(par1World);
      this.setSize(0.7F, 2.9F);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(5, new EntityAIFollowOwner(this, 0.5, 10.0F, 2.0F));
      this.tasks.addTask(7, new EntityAIWander(this, 0.3));
      this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(9, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
      this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
      this.setTamed(false);
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0);
      this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
   }

   

   @Override
   public void onLivingUpdate() {
      if (!this.isTamed()) {
         this.setDead();
      }

      if (this.world.isRemote) {
         for (int i = 0; i < 10; i++) {
            double d = this.rand.nextGaussian() * 0.02;
            double d1 = this.rand.nextGaussian() * 0.02;
            double d2 = this.rand.nextGaussian() * 0.02;
            double d3 = 10.0;
            MoWitchAndWizard.proxy.fx(new FxArgs("EntitySoulFX", 
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

      if (this.ticksExisted % 20 == 0 && this.getHealth() != this.getMaxHealth()) {
         this.setHealth(this.getHealth() + 1.0F);
      }

      if (!this.world.isRemote) {
         if (this.TimerEvolve != 0) {
            this.TimerEvolve--;
         }

         if (this.TimerTeleport != 0) {
            this.TimerTeleport--;
         }

         if (this.TimerControl != 0) {
            this.TimerControl--;
         }
      }

      if (this.TimerControl == 0) {
         this.Control = true;
      }

      if (this.getAttackTarget() != null) {
         if (this.TimerTeleport == 0) {
            this.teleportRandomly(this.getAttackTarget());
            this.TimerTeleport = 10;
         }

         if (this.TimerControl == 0 && this.Control) {
            List list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(7.0, 7.0, 7.0));

            for (int i = 0; i < list.size(); i++) {
               Entity entity1 = (Entity)list.get(i);
               if (entity1 instanceof EntityZombie || entity1 instanceof EntityCreeper || entity1 instanceof EntitySkeleton || entity1 instanceof EntitySpider) {
                  ((EntityMob)entity1).setAttackTarget(this.getAttackTarget());
                  if (i == list.size()) {
                     this.Control = false;
                     this.TimerControl = 50;
                  }
               }
            }
         }
      }

      if (this.TimerEvolve == 0) {
         EntityHalloweenPet mob = new EntityHalloweenPet(this.world);
         mob.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         MWAWEntityUtil.setOwner(mob, MWAWEntityUtil.ownerString(this));
         mob.setCustomNameTag(this.getCustomNameTag());
         mob.setTamed(true);
         MWAWEntityUtil.spawnInWorld(this.world, mob);
         this.setDead();
      }

      super.onLivingUpdate();
   }

   @Override
   public boolean attackEntityAsMob(Entity p_70652_1_) {
      return !this.world.isDaytime()
         ? p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F)
         : p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
   }

   @Override
   public void fall(float distance, float damageMultiplier) {
   }

   @Override
   public EntityHalloweenPet createChild(EntityAgeable p_90011_1_) {
      return null;
   }

   public boolean shouldAttackEntity(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
      if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast)) {
         if (p_142018_1_ instanceof EntityHalloweenPetEvolved) {
            EntityHalloweenPetEvolved entitywolf = (EntityHalloweenPetEvolved)p_142018_1_;
            if (entitywolf.isTamed() && entitywolf.getOwner() == p_142018_2_) {
               return false;
            }
         }

         return p_142018_1_ instanceof EntityPlayer
               && p_142018_2_ instanceof EntityPlayer
               && !((EntityPlayer)p_142018_2_).canAttackPlayer((EntityPlayer)p_142018_1_)
            ? false
            : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse)p_142018_1_).isTame();
      } else {
         return false;
      }
   }

   @Override
   public void writeEntityToNBT(NBTTagCompound compund) {
      super.writeEntityToNBT(compund);
      compund.setInteger("TimerDevolve", this.TimerEvolve);
      compund.setInteger("TimerControl", this.TimerControl);
      compund.setInteger("TimerTeleport", this.TimerTeleport);
      compund.setBoolean("ControlBoolean", this.Control);
   }

   @Override
   public void readEntityFromNBT(NBTTagCompound compund) {
      super.readEntityFromNBT(compund);
      this.TimerEvolve = compund.getInteger("TimerDevolve");
      this.TimerControl = compund.getInteger("TimerControl");
      this.TimerTeleport = compund.getInteger("TimerTeleport");
      this.Control = compund.getBoolean("ControlBoolean");
   }

   protected boolean teleportRandomly(Entity p_70816_1_) {
      Vec3d vec3 = new Vec3d(
         this.posX - p_70816_1_.posX,
         this.getEntityBoundingBox().minY + this.height / 2.0F - p_70816_1_.posY + p_70816_1_.getEyeHeight(),
         this.posZ - p_70816_1_.posZ
      );
      vec3 = vec3.normalize();
      double d0 = 16.0;
      double d1 = this.posX + (this.rand.nextDouble() - 0.5) * 8.0 - vec3.x * d0;
      double d2 = this.posY + (this.rand.nextInt(16) - 8) - vec3.y * d0;
      double d3 = this.posZ + (this.rand.nextDouble() - 0.5) * 8.0 - vec3.z * d0;
      return this.teleportTo(d1, d2, d3);
   }

   protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
      EnderTeleportEvent event = new EnderTeleportEvent(this, p_70825_1_, p_70825_3_, p_70825_5_, 0.0F);
      if (MinecraftForge.EVENT_BUS.post(event)) {
         return false;
      } else {
         double d3 = this.posX;
         double d4 = this.posY;
         double d5 = this.posZ;
         this.posX = event.getTargetX();
         this.posY = event.getTargetY();
         this.posZ = event.getTargetZ();
         boolean flag = false;
         int i = MathHelper.floor(this.posX);
         int j = MathHelper.floor(this.posY);
         int k = MathHelper.floor(this.posZ);
         if (LegacyWorld.blockExists(this.world, i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
               Block block = LegacyWorld.getBlock(this.world, i, j - 1, k);
               if (block.getDefaultState().getMaterial().blocksMovement()) {
                  flag1 = true;
               } else {
                  this.posY--;
                  j--;
               }
            }

            if (flag1) {
               this.setPosition(this.posX, this.posY, this.posZ);
               if (this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
                  flag = true;
               }
            }
         }

         if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
         } else {
            short short1 = 128;

            for (int l = 0; l < short1; l++) {
               double d6 = l / (short1 - 1.0);
               float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
               double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * this.height;
               double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5) * this.width * 2.0;
               LegacyWorld.spawnParticle(this.world, "portal", d7, d8, d9, f, f1, f2);
            }

            LegacyWorld.playSoundEffect(this.world, d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            LegacySounds.playSoundAtEntity(this.world, this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
         }
      }
   }
}
