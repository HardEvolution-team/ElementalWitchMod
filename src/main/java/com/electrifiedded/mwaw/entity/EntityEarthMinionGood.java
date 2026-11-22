package com.electrifiedded.mwaw.entity;

import com.electrifiedded.archaiclib.legacy.sound.LegacySounds;
import net.minecraft.util.SoundEvent;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEarthMinionGood extends EntityCreature {
   public EntityEarthMinionGood(World par1World) {
      super(par1World);
      this.isImmuneToFire = true;
      this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0, true));
      this.tasks.addTask(7, new EntityAIWander(this, 0.2));
      this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, 0.2));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.tasks.addTask(1, new EntityAIMoveTowardsTarget(this, 0.2, 32.0F));
      this.targetTasks.addTask(6, new EntityAINearestAttackableTarget<>(this, EntityMob.class, true));
   }

   @Override
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
      this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(5.0);
   }


   @Override
   protected SoundEvent getHurtSound(net.minecraft.util.DamageSource src) {
      return LegacySounds.get("dig.stone");
   }

   @Override
   protected SoundEvent getDeathSound() {
      return LegacySounds.get("mob.zombie.woodbreak");
   }

   @Override
   public boolean attackEntityFrom(DamageSource source, float damage) {
      if (source.getTrueSource() instanceof EntityFallingBlock
         || source.getTrueSource() instanceof EntityArrow
         || source.getTrueSource() instanceof EntityBlock
         || source.equals(DamageSource.IN_WALL)
         || source.equals(DamageSource.FALLING_BLOCK)
         || source.equals(DamageSource.FALL)
         || source.equals(DamageSource.WITHER)
         || source.equals(DamageSource.LAVA)
         || source.equals(DamageSource.CACTUS)
         || source.equals(DamageSource.DROWN)
         || source.equals(DamageSource.IN_FIRE)
         || source.equals(DamageSource.ON_FIRE)) {
         return false;
      } else if (!(source.getTrueSource() instanceof EntityPlayer)) {
         return super.attackEntityFrom(source, damage);
      } else if ((
            ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().isEmpty()
               || ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().getItem() != Items.DIAMOND_PICKAXE
         )
         && (
            ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().isEmpty()
               || ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().getItem() != Items.WOODEN_PICKAXE
         )
         && (
            ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().isEmpty()
               || ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().getItem() != Items.STONE_PICKAXE
         )
         && (
            ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().isEmpty()
               || ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().getItem() != Items.IRON_PICKAXE
         )
         && (
            ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().isEmpty()
               || ((EntityPlayer)source.getTrueSource()).getHeldItemMainhand().getItem() != Items.GOLDEN_PICKAXE
         )) {
         return false;
      } else {
         this.attackEntityFrom(DamageSource.GENERIC, 3.0F);
         return true;
      }
   }

   @Override
   protected void collideWithEntity(Entity p_82167_1_) {
      if (p_82167_1_ instanceof IMob && this.getRNG().nextInt(20) == 0) {
         this.setAttackTarget((EntityLivingBase)p_82167_1_);
      }

      super.collideWithEntity(p_82167_1_);
   }

   @Override
   public boolean attackEntityAsMob(Entity p_70652_1_) {
      this.world.setEntityState(this, (byte)4);
      return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);
   }

   @Override
   public void onLivingUpdate() {
      if (this.getAttackTarget() instanceof EntityEarthMinionGood) {
         this.setAttackTarget(null);
      }

      if (this.ticksExisted >= 330) {
         this.setDead();
      }

      super.onLivingUpdate();
   }
}
