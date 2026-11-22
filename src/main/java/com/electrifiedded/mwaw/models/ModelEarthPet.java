package com.electrifiedded.mwaw.models;

import net.minecraft.client.renderer.GlStateManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelEarthPet extends ModelBase {
   ModelRenderer body1;
   ModelRenderer body2;
   ModelRenderer body3;
   ModelRenderer body4;
   ModelRenderer body5;
   ModelRenderer arm1;
   ModelRenderer arm2;
   ModelRenderer arm3;
   ModelRenderer arm4;
   ModelRenderer head1;
   ModelRenderer head2;
   ModelRenderer head3;
   ModelRenderer head4;
   ModelRenderer cape1;

   public ModelEarthPet() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.body1 = new ModelRenderer(this, 13, 0);
      this.body1.addBox(0.0F, 0.0F, 0.0F, 5, 2, 4);
      this.body1.setRotationPoint(0.0F, 9.0F, 0.0F);
      this.body1.setTextureSize(64, 32);
      this.body1.mirror = true;
      this.setRotation(this.body1, 0.0F, 0.0F, 0.0F);
      this.body2 = new ModelRenderer(this, 32, 0);
      this.body2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3);
      this.body2.setRotationPoint(1.0F, 11.0F, 0.5F);
      this.body2.setTextureSize(64, 32);
      this.body2.mirror = true;
      this.setRotation(this.body2, 0.0F, 0.0F, 0.0F);
      this.body3 = new ModelRenderer(this, 0, 6);
      this.body3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.body3.setRotationPoint(2.0F, 13.0F, 1.5F);
      this.body3.setTextureSize(64, 32);
      this.body3.mirror = true;
      this.setRotation(this.body3, 0.0F, 0.0F, 0.0F);
      this.body4 = new ModelRenderer(this, 5, 6);
      this.body4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.body4.setRotationPoint(-1.1F, 8.9F, -0.1F);
      this.body4.setTextureSize(64, 32);
      this.body4.mirror = true;
      this.setRotation(this.body4, 0.2268928F, 0.0F, 0.0F);
      this.body5 = new ModelRenderer(this, 5, 6);
      this.body5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.body5.setRotationPoint(5.1F, 8.9F, -0.1F);
      this.body5.setTextureSize(64, 32);
      this.body5.mirror = true;
      this.setRotation(this.body5, 0.2268928F, 0.0F, 0.0F);
      this.arm1 = new ModelRenderer(this, 0, 9);
      this.arm1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.arm1.setRotationPoint(-2.0F, 9.0F, -1.0F);
      this.arm1.setTextureSize(64, 32);
      this.arm1.mirror = true;
      this.setRotation(this.arm1, 0.0F, 0.0F, 0.0F);
      this.arm2 = new ModelRenderer(this, 0, 9);
      this.arm2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.arm2.setRotationPoint(6.0F, 9.0F, -1.0F);
      this.arm2.setTextureSize(64, 32);
      this.arm2.mirror = true;
      this.setRotation(this.arm2, 0.0F, 0.0F, 0.0F);
      this.arm3 = new ModelRenderer(this, 12, 8);
      this.arm3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.arm3.setRotationPoint(7.0F, 9.5F, -2.5F);
      this.arm3.setTextureSize(64, 32);
      this.arm3.mirror = true;
      this.setRotation(this.arm3, 0.3316126F, 0.0F, 0.0F);
      this.arm4 = new ModelRenderer(this, 12, 8);
      this.arm4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.arm4.setRotationPoint(-3.0F, 9.5F, -2.5F);
      this.arm4.setTextureSize(64, 32);
      this.arm4.mirror = true;
      this.setRotation(this.arm4, 0.3316126F, 0.0F, 0.0F);
      this.head1 = new ModelRenderer(this, 0, 21);
      this.head1.addBox(-3.0F, -2.0F, -2.0F, 7, 5, 5);
      this.head1.setRotationPoint(2.0F, 6.0F, 1.5F);
      this.head1.setTextureSize(64, 32);
      this.head1.mirror = true;
      this.setRotation(this.head1, 0.0F, 0.0F, 0.0F);
      this.head2 = new ModelRenderer(this, 0, 0);
      this.head2.addBox(-0.5F, -3.0F, -0.5F, 2, 2, 3);
      this.head2.setRotationPoint(2.0F, 6.0F, 1.5F);
      this.head2.setTextureSize(64, 32);
      this.head2.mirror = true;
      this.convertToChild(this.head1, this.head2);
      this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
      this.head3 = new ModelRenderer(this, 19, 7);
      this.head3.addBox(-3.1F, -2.5F, 0.5F, 1, 1, 2);
      this.head3.setRotationPoint(2.0F, 6.0F, 1.5F);
      this.head3.setTextureSize(64, 32);
      this.head3.mirror = true;
      this.convertToChild(this.head1, this.head3);
      this.setRotation(this.head3, 0.0F, 0.0F, 0.0F);
      this.head4 = new ModelRenderer(this, 19, 7);
      this.head4.addBox(3.1F, -2.5F, 0.5F, 1, 1, 2);
      this.head4.setRotationPoint(2.0F, 6.0F, 1.5F);
      this.head4.setTextureSize(64, 32);
      this.head4.mirror = true;
      this.convertToChild(this.head1, this.head4);
      this.setRotation(this.head4, 0.0F, 0.0F, 0.0F);
      this.cape1 = new ModelRenderer(this, 0, 12);
      this.cape1.addBox(-2.0F, 0.0F, 0.0F, 5, 1, 7);
      this.cape1.setRotationPoint(2.0F, 7.0F, 4.0F);
      this.cape1.setTextureSize(64, 32);
      this.cape1.mirror = true;
      this.setRotation(this.cape1, -1.570796F, 0.0F, 0.0F);
   }

   protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild) {
      parChild.rotationPointX = parChild.rotationPointX - parParent.rotationPointX;
      parChild.rotationPointY = parChild.rotationPointY - parParent.rotationPointY;
      parChild.rotationPointZ = parChild.rotationPointZ - parParent.rotationPointZ;
      parChild.rotateAngleX = parChild.rotateAngleX - parParent.rotateAngleX;
      parChild.rotateAngleY = parChild.rotateAngleY - parParent.rotateAngleY;
      parChild.rotateAngleZ = parChild.rotateAngleZ - parParent.rotateAngleZ;
      parParent.addChild(parChild);
   }

   @Override
   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      GlStateManager.scale(1.5F, 1.5F, 1.5F);
      GlStateManager.translate(-0.3F, -0.1F, -0.3F);
      this.body1.render(f5);
      this.body2.render(f5);
      this.body3.render(f5);
      this.body4.render(f5);
      this.body5.render(f5);
      this.arm1.render(f5);
      this.arm2.render(f5);
      this.arm3.render(f5);
      this.arm4.render(f5);
      this.head1.render(f5);
      this.cape1.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   @Override
   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head1.rotateAngleX = f4 / (180.0F / (float)Math.PI);
      this.head1.rotateAngleY = f3 / (180.0F / (float)Math.PI);
   }
}
