package com.electrifiedded.mwaw.models;

import net.minecraft.client.renderer.GlStateManager;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFirePet extends ModelBase {
   ModelRenderer body1;
   ModelRenderer body2;
   ModelRenderer body3;
   ModelRenderer rightlegup;
   ModelRenderer rightlegbackup;
   ModelRenderer leftlegup;
   ModelRenderer leftlegbackup;
   ModelRenderer rightlegdown;
   ModelRenderer rightlegbackdown;
   ModelRenderer leftlegdown;
   ModelRenderer leftlegbackdown;
   ModelRenderer head1;
   ModelRenderer head2;
   ModelRenderer head3;
   ModelRenderer head4;
   ModelRenderer head5;
   ModelRenderer head6;
   ModelRenderer head7;
   ModelRenderer tail1;
   ModelRenderer tail2;
   ModelRenderer tail3;
   ModelRenderer tail4;

   public ModelFirePet() {
      this.textureWidth = 128;
      this.textureHeight = 32;
      this.body1 = new ModelRenderer(this, 22, 0);
      this.body1.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4);
      this.body1.setRotationPoint(0.0F, 10.0F, 0.0F);
      this.body1.setTextureSize(128, 32);
      this.body1.mirror = true;
      this.setRotation(this.body1, 0.0F, 0.0F, 0.0F);
      this.body2 = new ModelRenderer(this, 44, 0);
      this.body2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 4);
      this.body2.setRotationPoint(1.0F, 10.0F, 3.0F);
      this.body2.setTextureSize(128, 32);
      this.body2.mirror = true;
      this.setRotation(this.body2, -0.122173F, 0.0F, 0.0F);
      this.body3 = new ModelRenderer(this, 0, 6);
      this.body3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3);
      this.body3.setRotationPoint(1.486667F, 9.0F, 1.0F);
      this.body3.setTextureSize(128, 32);
      this.body3.mirror = true;
      this.setRotation(this.body3, 0.0F, 0.0F, 0.0F);
      this.rightlegup = new ModelRenderer(this, 18, 11);
      this.rightlegup.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.rightlegup.setRotationPoint(5.0F, 12.0F, 0.0F);
      this.rightlegup.setTextureSize(128, 32);
      this.rightlegup.mirror = true;
      this.setRotation(this.rightlegup, 0.0F, 0.0F, 0.0F);
      this.rightlegbackup = new ModelRenderer(this, 18, 11);
      this.rightlegbackup.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.rightlegbackup.setRotationPoint(4.0F, 12.0F, 4.5F);
      this.rightlegbackup.setTextureSize(128, 32);
      this.rightlegbackup.mirror = true;
      this.setRotation(this.rightlegbackup, 0.0F, 0.0F, 0.0F);
      this.leftlegup = new ModelRenderer(this, 18, 11);
      this.leftlegup.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.leftlegup.setRotationPoint(-1.0F, 12.0F, 0.0F);
      this.leftlegup.setTextureSize(128, 32);
      this.leftlegup.mirror = true;
      this.setRotation(this.leftlegup, 0.0F, 0.0F, 0.0F);
      this.leftlegbackup = new ModelRenderer(this, 18, 11);
      this.leftlegbackup.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.leftlegbackup.setRotationPoint(0.0F, 12.0F, 4.5F);
      this.leftlegbackup.setTextureSize(128, 32);
      this.leftlegbackup.mirror = true;
      this.setRotation(this.leftlegbackup, 0.0F, 0.0F, 0.0F);
      this.rightlegdown = new ModelRenderer(this, 0, 27);
      this.rightlegdown.addBox(0.0F, 0.0F, 2.0F, 1, 2, 1);
      this.rightlegdown.setRotationPoint(5.0F, 12.0F, 0.0F);
      this.rightlegdown.setTextureSize(128, 32);
      this.rightlegdown.mirror = true;
      this.convertToChild(this.rightlegup, this.rightlegdown);
      this.setRotation(this.rightlegdown, -0.6806784F, 0.0F, 0.0F);
      this.rightlegbackdown = new ModelRenderer(this, 0, 27);
      this.rightlegbackdown.addBox(0.0F, 0.0F, 2.0F, 1, 2, 1);
      this.rightlegbackdown.setRotationPoint(4.0F, 12.0F, 4.5F);
      this.rightlegbackdown.setTextureSize(128, 32);
      this.rightlegbackdown.mirror = true;
      this.convertToChild(this.rightlegbackup, this.rightlegbackdown);
      this.setRotation(this.rightlegbackdown, -0.6806784F, 0.0F, 0.0F);
      this.leftlegdown = new ModelRenderer(this, 0, 27);
      this.leftlegdown.addBox(0.0F, 0.0F, 2.0F, 1, 2, 1);
      this.leftlegdown.setRotationPoint(-1.0F, 12.0F, 0.0F);
      this.leftlegdown.setTextureSize(128, 32);
      this.leftlegdown.mirror = true;
      this.convertToChild(this.leftlegup, this.leftlegdown);
      this.setRotation(this.leftlegdown, -0.6806784F, 0.0F, 0.0F);
      this.leftlegbackdown = new ModelRenderer(this, 0, 27);
      this.leftlegbackdown.addBox(0.0F, 0.0F, 2.0F, 1, 2, 1);
      this.leftlegbackdown.setRotationPoint(0.0F, 12.0F, 4.5F);
      this.leftlegbackdown.setTextureSize(128, 32);
      this.leftlegbackdown.mirror = true;
      this.convertToChild(this.leftlegbackup, this.leftlegbackdown);
      this.setRotation(this.leftlegbackdown, -0.6806784F, 0.0F, 0.0F);
      this.head1 = new ModelRenderer(this, 26, 10);
      this.head1.addBox(-3.0F, -2.0F, -2.0F, 6, 5, 4);
      this.head1.setRotationPoint(2.5F, 10.0F, -1.0F);
      this.head1.setTextureSize(128, 32);
      this.head1.mirror = true;
      this.setRotation(this.head1, 0.0F, 0.0F, 0.0F);
      this.head2 = new ModelRenderer(this, 0, 12);
      this.head2.addBox(-0.5F, -2.0F, -3.5F, 4, 4, 4);
      this.head2.setRotationPoint(2.5F, 10.0F, -1.0F);
      this.head2.setTextureSize(128, 32);
      this.head2.mirror = true;
      this.convertToChild(this.head1, this.head2);
      this.setRotation(this.head2, 0.0523599F, (float) (Math.PI / 4), 0.0523599F);
      this.head3 = new ModelRenderer(this, 0, 21);
      this.head3.addBox(-1.0F, 1.0F, -4.0F, 2, 1, 3);
      this.head3.setRotationPoint(2.5F, 10.0F, -1.0F);
      this.head3.setTextureSize(128, 32);
      this.head3.mirror = true;
      this.convertToChild(this.head1, this.head3);
      this.setRotation(this.head3, 0.1570796F, 0.0F, 0.0F);
      this.head4 = new ModelRenderer(this, 10, 27);
      this.head4.addBox(2.5F, -1.0F, 2.0F, 1, 1, 3);
      this.head4.setRotationPoint(2.5F, 10.0F, -1.0F);
      this.head4.setTextureSize(128, 32);
      this.head4.mirror = true;
      this.convertToChild(this.head1, this.head4);
      this.setRotation(this.head4, 0.4712389F, 0.0F, 0.0F);
      this.head5 = new ModelRenderer(this, 10, 27);
      this.head5.addBox(-3.5F, -1.0F, 2.0F, 1, 1, 3);
      this.head5.setRotationPoint(2.5F, 10.0F, -1.0F);
      this.head5.setTextureSize(128, 32);
      this.head5.mirror = true;
      this.convertToChild(this.head1, this.head5);
      this.setRotation(this.head5, 0.4712389F, 0.0F, 0.0F);
      this.head6 = new ModelRenderer(this, 11, 0);
      this.head6.addBox(-1.5F, -3.0F, 1.0F, 3, 2, 2);
      this.head6.setRotationPoint(2.5F, 10.0F, -1.0F);
      this.head6.setTextureSize(128, 32);
      this.head6.mirror = true;
      this.convertToChild(this.head1, this.head6);
      this.setRotation(this.head6, 0.0F, 0.0F, 0.0F);
      this.head7 = new ModelRenderer(this, 11, 6);
      this.head7.addBox(-0.5F, -3.0F, 3.0F, 1, 1, 2);
      this.head7.setRotationPoint(2.5F, 10.0F, -1.0F);
      this.head7.setTextureSize(128, 32);
      this.head7.mirror = true;
      this.convertToChild(this.head1, this.head7);
      this.setRotation(this.head7, 0.2094395F, 0.0F, 0.0F);
      this.tail1 = new ModelRenderer(this, 61, 0);
      this.tail1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
      this.tail1.setRotationPoint(2.0F, 11.0F, 6.0F);
      this.tail1.setTextureSize(128, 32);
      this.tail1.mirror = true;
      this.setRotation(this.tail1, 0.0F, 0.0F, 0.0F);
      this.tail2 = new ModelRenderer(this, 15, 22);
      this.tail2.addBox(0.0F, 1.0F, 3.0F, 1, 1, 2);
      this.tail2.setRotationPoint(2.0F, 11.0F, 6.0F);
      this.tail2.setTextureSize(128, 32);
      this.tail2.mirror = true;
      this.convertToChild(this.tail1, this.tail2);
      this.setRotation(this.tail2, 0.2443461F, 0.0F, 0.0F);
      this.tail3 = new ModelRenderer(this, 26, 23);
      this.tail3.addBox(0.0F, -1.0F, 5.0F, 1, 2, 2);
      this.tail3.setRotationPoint(2.0F, 11.0F, 6.0F);
      this.tail3.setTextureSize(128, 32);
      this.tail3.mirror = true;
      this.convertToChild(this.tail1, this.tail3);
      this.setRotation(this.tail3, 0.0F, 0.0F, 0.0F);
      this.tail4 = new ModelRenderer(this, 34, 22);
      this.tail4.addBox(0.0F, -2.0F, 6.0F, 1, 1, 2);
      this.tail4.setRotationPoint(2.0F, 11.0F, 6.0F);
      this.tail4.setTextureSize(128, 32);
      this.tail4.mirror = true;
      this.convertToChild(this.tail1, this.tail4);
      this.setRotation(this.tail4, 0.0F, 0.0F, 0.0F);
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
      GlStateManager.translate(-0.3F, 0.1F, -0.3F);
      this.body1.render(f5);
      this.body2.render(f5);
      this.body3.render(f5);
      this.rightlegup.render(f5);
      this.rightlegbackup.render(f5);
      this.leftlegup.render(f5);
      this.leftlegbackup.render(f5);
      this.head1.render(f5);
      this.tail1.render(f5);
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
      this.leftlegup.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.rightlegup.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
      this.rightlegbackup.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.leftlegbackup.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
   }
}
