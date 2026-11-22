package com.electrifiedded.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHalloweenPet extends ModelBase {
   ModelRenderer Head;
   ModelRenderer Torso;
   ModelRenderer Right_Arm;
   ModelRenderer Right_Glove;
   ModelRenderer Right_Hand;
   ModelRenderer Left_Arm;
   ModelRenderer Left_Glove;
   ModelRenderer Left_Hand;
   ModelRenderer Right_Leg;
   ModelRenderer Right_Boot_1;
   ModelRenderer Right_Boot_2;
   ModelRenderer Right_Boot_3;
   ModelRenderer Left_Leg;
   ModelRenderer Left_Boot_1;
   ModelRenderer Left_Boot_2;
   ModelRenderer Left_Boot_3;

   public ModelHalloweenPet() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Head = new ModelRenderer(this, 42, 0);
      this.Head.addBox(-6.0F, -12.0F, -6.0F, 12, 12, 12);
      this.Head.setRotationPoint(0.0F, 11.0F, 0.0F);
      this.Head.setTextureSize(128, 64);
      this.Head.mirror = true;
      this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
      this.Torso = new ModelRenderer(this, 62, 24);
      this.Torso.addBox(-1.0F, 0.0F, -1.0F, 2, 5, 2);
      this.Torso.setRotationPoint(0.0F, 11.0F, 0.0F);
      this.Torso.setTextureSize(128, 64);
      this.Torso.mirror = true;
      this.setRotation(this.Torso, 0.0F, 0.0F, 0.0F);
      this.Right_Arm = new ModelRenderer(this, 58, 24);
      this.Right_Arm.addBox(0.0F, 0.0F, -0.5F, 1, 5, 1);
      this.Right_Arm.setRotationPoint(-1.0F, 12.0F, 0.0F);
      this.Right_Arm.setTextureSize(128, 64);
      this.Right_Arm.mirror = true;
      this.setRotation(this.Right_Arm, 0.0F, 0.0F, (float) (Math.PI * 2.0 / 9.0));
      this.Right_Glove = new ModelRenderer(this, 50, 25);
      this.Right_Glove.addBox(-0.5F, 5.0F, -1.0F, 2, 2, 2);
      this.Right_Glove.setRotationPoint(-1.0F, 12.0F, 0.0F);
      this.Right_Glove.setTextureSize(128, 64);
      this.Right_Glove.mirror = true;
      this.setRotation(this.Right_Glove, 0.0F, 0.0F, (float) (Math.PI * 2.0 / 9.0));
      this.Right_Hand = new ModelRenderer(this, 46, 26);
      this.Right_Hand.addBox(0.0F, 6.5F, -0.5F, 1, 1, 1);
      this.Right_Hand.setRotationPoint(-1.0F, 12.0F, 0.0F);
      this.Right_Hand.setTextureSize(128, 64);
      this.Right_Hand.mirror = true;
      this.setRotation(this.Right_Hand, 0.0F, 0.0F, (float) (Math.PI * 2.0 / 9.0));
      this.Left_Arm = new ModelRenderer(this, 70, 24);
      this.Left_Arm.addBox(-1.0F, 0.0F, -0.5F, 1, 5, 1);
      this.Left_Arm.setRotationPoint(1.0F, 12.0F, 0.0F);
      this.Left_Arm.setTextureSize(128, 64);
      this.Left_Arm.mirror = true;
      this.setRotation(this.Left_Arm, 0.0F, 0.0F, (float) (-Math.PI * 2.0 / 9.0));
      this.Left_Glove = new ModelRenderer(this, 74, 25);
      this.Left_Glove.addBox(-1.5F, 5.0F, -1.0F, 2, 2, 2);
      this.Left_Glove.setRotationPoint(1.0F, 12.0F, 0.0F);
      this.Left_Glove.setTextureSize(128, 64);
      this.Left_Glove.mirror = true;
      this.setRotation(this.Left_Glove, 0.0F, 0.0F, (float) (-Math.PI * 2.0 / 9.0));
      this.Left_Hand = new ModelRenderer(this, 82, 26);
      this.Left_Hand.addBox(-1.0F, 6.5F, -0.5F, 1, 1, 1);
      this.Left_Hand.setRotationPoint(1.0F, 12.0F, 0.0F);
      this.Left_Hand.setTextureSize(128, 64);
      this.Left_Hand.mirror = true;
      this.setRotation(this.Left_Hand, 0.0F, 0.0F, (float) (-Math.PI * 2.0 / 9.0));
      this.Right_Leg = new ModelRenderer(this, 60, 31);
      this.Right_Leg.addBox(-1.0F, -1.0F, -0.5F, 1, 6, 1);
      this.Right_Leg.setRotationPoint(-1.0F, 16.0F, 0.0F);
      this.Right_Leg.setTextureSize(128, 64);
      this.Right_Leg.mirror = true;
      this.setRotation(this.Right_Leg, -0.0872665F, 0.0F, 0.122173F);
      this.Right_Boot_1 = new ModelRenderer(this, 57, 38);
      this.Right_Boot_1.addBox(-2.0F, 4.0F, -1.5F, 2, 2, 2);
      this.Right_Boot_1.setRotationPoint(-1.0F, 16.0F, 0.0F);
      this.Right_Boot_1.setTextureSize(128, 64);
      this.Right_Boot_1.mirror = true;
      this.setRotation(this.Right_Boot_1, 0.0F, 0.0F, 0.0F);
      this.Right_Boot_2 = new ModelRenderer(this, 51, 42);
      this.Right_Boot_2.addBox(-2.5F, 6.0F, -3.0F, 3, 2, 4);
      this.Right_Boot_2.setRotationPoint(-1.0F, 16.0F, 0.0F);
      this.Right_Boot_2.setTextureSize(128, 64);
      this.Right_Boot_2.mirror = true;
      this.setRotation(this.Right_Boot_2, 0.0F, 0.0F, 0.0F);
      this.Right_Boot_3 = new ModelRenderer(this, 59, 48);
      this.Right_Boot_3.addBox(-2.0F, 7.0F, -3.5F, 2, 1, 1);
      this.Right_Boot_3.setRotationPoint(-1.0F, 16.0F, 0.0F);
      this.Right_Boot_3.setTextureSize(128, 64);
      this.Right_Boot_3.mirror = true;
      this.setRotation(this.Right_Boot_3, 0.0F, 0.0F, 0.0F);
      this.Left_Leg = new ModelRenderer(this, 68, 31);
      this.Left_Leg.addBox(0.0F, -1.0F, -0.5F, 1, 6, 1);
      this.Left_Leg.setRotationPoint(1.0F, 16.0F, 0.0F);
      this.Left_Leg.setTextureSize(128, 64);
      this.Left_Leg.mirror = true;
      this.setRotation(this.Left_Leg, -0.0872665F, 0.0F, -0.122173F);
      this.Left_Boot_1 = new ModelRenderer(this, 67, 38);
      this.Left_Boot_1.addBox(0.0F, 4.0F, -1.5F, 2, 2, 2);
      this.Left_Boot_1.setRotationPoint(1.0F, 16.0F, 0.0F);
      this.Left_Boot_1.setTextureSize(128, 64);
      this.Left_Boot_1.mirror = true;
      this.setRotation(this.Left_Boot_1, 0.0F, 0.0F, 0.0F);
      this.Left_Boot_2 = new ModelRenderer(this, 67, 42);
      this.Left_Boot_2.addBox(-0.5F, 6.0F, -3.0F, 3, 2, 4);
      this.Left_Boot_2.setRotationPoint(1.0F, 16.0F, 0.0F);
      this.Left_Boot_2.setTextureSize(128, 64);
      this.Left_Boot_2.mirror = true;
      this.setRotation(this.Left_Boot_2, 0.0F, 0.0F, 0.0F);
      this.Left_Boot_3 = new ModelRenderer(this, 67, 48);
      this.Left_Boot_3.addBox(0.0F, 7.0F, -3.5F, 2, 1, 1);
      this.Left_Boot_3.setRotationPoint(1.0F, 16.0F, 0.0F);
      this.Left_Boot_3.setTextureSize(128, 64);
      this.Left_Boot_3.mirror = true;
      this.setRotation(this.Left_Boot_3, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Head.render(f5);
      this.Torso.render(f5);
      this.Right_Arm.render(f5);
      this.Right_Glove.render(f5);
      this.Right_Hand.render(f5);
      this.Left_Arm.render(f5);
      this.Left_Glove.render(f5);
      this.Left_Hand.render(f5);
      this.Right_Leg.render(f5);
      this.Right_Boot_1.render(f5);
      this.Right_Boot_2.render(f5);
      this.Right_Boot_3.render(f5);
      this.Left_Leg.render(f5);
      this.Left_Boot_1.render(f5);
      this.Left_Boot_2.render(f5);
      this.Left_Boot_3.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   @Override
   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Right_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.Right_Boot_1.rotateAngleX = this.Right_Leg.rotateAngleX;
      this.Right_Boot_2.rotateAngleX = this.Right_Leg.rotateAngleX;
      this.Right_Boot_3.rotateAngleX = this.Right_Leg.rotateAngleX;
      this.Left_Leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
      this.Left_Boot_1.rotateAngleX = this.Left_Leg.rotateAngleX;
      this.Left_Boot_2.rotateAngleX = this.Left_Leg.rotateAngleX;
      this.Left_Boot_3.rotateAngleX = this.Left_Leg.rotateAngleX;
      this.Head.rotateAngleY = f3 / (180.0F / (float)Math.PI);
      this.Head.rotateAngleX = f4 / (180.0F / (float)Math.PI);
   }
}
