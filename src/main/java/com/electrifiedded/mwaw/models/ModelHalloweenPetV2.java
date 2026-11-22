package com.electrifiedded.mwaw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHalloweenPetV2 extends ModelBase {
   ModelRenderer torso1;
   ModelRenderer torso2;
   ModelRenderer spine1;
   ModelRenderer spine2;
   ModelRenderer spine3;
   ModelRenderer clothes;
   ModelRenderer head1;
   ModelRenderer head2;
   ModelRenderer teeth;
   ModelRenderer jaw;
   ModelRenderer armright1;
   ModelRenderer armright2;
   ModelRenderer armleft1;
   ModelRenderer armleft2;
   ModelRenderer legright1;
   ModelRenderer legleft1;
   ModelRenderer legright2;
   ModelRenderer legleft2;

   public ModelHalloweenPetV2() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.torso1 = new ModelRenderer(this, 0, 0);
      this.torso1.addBox(-5.0F, 0.0F, 0.0F, 10, 8, 6);
      this.torso1.setRotationPoint(0.0F, -9.0F, -3.0F);
      this.torso1.setTextureSize(128, 64);
      this.torso1.mirror = true;
      this.setRotation(this.torso1, -0.0743572F, 0.0F, 0.0F);
      this.torso2 = new ModelRenderer(this, 0, 15);
      this.torso2.addBox(-4.0F, 8.4F, 0.4F, 8, 8, 5);
      this.torso2.setRotationPoint(0.0F, -9.0F, -3.0F);
      this.torso2.setTextureSize(128, 64);
      this.torso2.mirror = true;
      this.setRotation(this.torso2, 0.0F, 0.0F, 0.0F);
      this.spine1 = new ModelRenderer(this, 0, 29);
      this.spine1.addBox(-1.0F, 8.4F, 4.4F, 2, 8, 2);
      this.spine1.setRotationPoint(0.0F, -9.0F, -3.0F);
      this.spine1.setTextureSize(128, 64);
      this.spine1.mirror = true;
      this.setRotation(this.spine1, 0.0F, 0.0F, 0.0F);
      this.spine2 = new ModelRenderer(this, 9, 29);
      this.spine2.addBox(-1.0F, 0.0F, 5.0F, 2, 8, 2);
      this.spine2.setRotationPoint(0.0F, -9.0F, -3.0F);
      this.spine2.setTextureSize(128, 64);
      this.spine2.mirror = true;
      this.setRotation(this.spine2, -0.074351F, 0.0F, 0.0F);
      this.spine3 = new ModelRenderer(this, 18, 29);
      this.spine3.addBox(-1.0F, -2.0F, 4.7F, 2, 4, 2);
      this.spine3.setRotationPoint(0.0F, -9.0F, -3.0F);
      this.spine3.setTextureSize(128, 64);
      this.spine3.mirror = true;
      this.setRotation(this.spine3, 0.2230717F, 0.0F, 0.0F);
      this.clothes = new ModelRenderer(this, 0, 40);
      this.clothes.addBox(-4.0F, 16.4F, 0.4F, 8, 4, 5);
      this.clothes.setRotationPoint(0.0F, -9.0F, -3.0F);
      this.clothes.setTextureSize(128, 64);
      this.clothes.mirror = true;
      this.setRotation(this.clothes, 0.0F, 0.0F, 0.0F);
      this.head1 = new ModelRenderer(this, 40, 0);
      this.head1.addBox(-4.0F, -7.466667F, -6.0F, 8, 5, 8);
      this.head1.setRotationPoint(0.0F, -12.0F, 2.0F);
      this.head1.setTextureSize(128, 64);
      this.head1.mirror = true;
      this.setRotation(this.head1, 0.0F, 0.0F, 0.0F);
      this.head2 = new ModelRenderer(this, 40, 14);
      this.head2.addBox(-3.5F, -2.5F, -2.0F, 7, 3, 4);
      this.head2.setRotationPoint(0.0F, -12.0F, 2.0F);
      this.head2.setTextureSize(128, 64);
      this.head2.mirror = true;
      this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
      this.teeth = new ModelRenderer(this, 40, 22);
      this.teeth.addBox(-3.0F, -2.5F, -6.0F, 6, 1, 4);
      this.teeth.setRotationPoint(0.0F, -12.0F, 2.0F);
      this.teeth.setTextureSize(128, 64);
      this.teeth.mirror = true;
      this.setRotation(this.teeth, 0.0F, 0.0F, 0.0F);
      this.jaw = new ModelRenderer(this, 40, 28);
      this.jaw.addBox(-3.0F, -1.5F, -6.0F, 6, 2, 5);
      this.jaw.setRotationPoint(0.0F, -12.0F, 2.0F);
      this.jaw.setTextureSize(128, 64);
      this.jaw.mirror = true;
      this.setRotation(this.jaw, 0.1495997F, 0.0F, 0.0F);
      this.armright1 = new ModelRenderer(this, 80, 0);
      this.armright1.addBox(-1.5F, 0.0F, -1.0F, 2, 8, 2);
      this.armright1.setRotationPoint(-5.0F, -8.0F, 1.0F);
      this.armright1.setTextureSize(128, 64);
      this.armright1.mirror = true;
      this.setRotation(this.armright1, 0.1121997F, 0.0F, 0.2991993F);
      this.armright2 = new ModelRenderer(this, 89, 0);
      this.armright2.addBox(-1.8F, 0.0F, -1.2F, 2, 8, 2);
      this.armright2.setRotationPoint(-7.0F, -1.0F, 2.0F);
      this.armright2.setTextureSize(128, 64);
      this.armright2.mirror = true;
      this.setRotation(this.armright2, 0.0F, 0.0F, 0.0F);
      this.armleft1 = new ModelRenderer(this, 80, 11);
      this.armleft1.addBox(-0.5F, 0.0F, -1.0F, 2, 8, 2);
      this.armleft1.setRotationPoint(5.0F, -8.0F, 1.0F);
      this.armleft1.setTextureSize(128, 64);
      this.armleft1.mirror = true;
      this.setRotation(this.armleft1, 0.1121997F, 0.0F, -0.2992018F);
      this.armleft2 = new ModelRenderer(this, 89, 11);
      this.armleft2.addBox(-0.2F, 0.0F, -1.2F, 2, 8, 2);
      this.armleft2.setRotationPoint(7.0F, -1.0F, 2.0F);
      this.armleft2.setTextureSize(128, 64);
      this.armleft2.mirror = true;
      this.setRotation(this.armleft2, 0.0F, 0.0F, 0.0F);
      this.legright1 = new ModelRenderer(this, 80, 22);
      this.legright1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
      this.legright1.setRotationPoint(-2.5F, 7.0F, 0.0F);
      this.legright1.setTextureSize(128, 64);
      this.legright1.mirror = true;
      this.setRotation(this.legright1, -0.0373999F, 0.0947464F, 0.0373999F);
      this.legleft1 = new ModelRenderer(this, 89, 22);
      this.legleft1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
      this.legleft1.setRotationPoint(2.5F, 7.0F, 0.0F);
      this.legleft1.setTextureSize(128, 64);
      this.legleft1.mirror = true;
      this.setRotation(this.legleft1, -0.0374024F, -0.1296605F, -0.0374024F);
      this.legright2 = new ModelRenderer(this, 80, 33);
      this.legright2.addBox(-1.3F, 1.0F, -1.3F, 2, 9, 2);
      this.legright2.setRotationPoint(-2.5F, 14.0F, 0.0F);
      this.legright2.setTextureSize(128, 64);
      this.legright2.mirror = true;
      this.setRotation(this.legright2, 0.0F, 0.0F, 0.0F);
      this.legleft2 = new ModelRenderer(this, 89, 33);
      this.legleft2.addBox(-0.7F, 1.0F, -1.3F, 2, 9, 2);
      this.legleft2.setRotationPoint(2.5F, 14.0F, 0.0F);
      this.legleft2.setTextureSize(128, 64);
      this.legleft2.mirror = true;
      this.setRotation(this.legleft2, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.torso1.render(f5);
      this.torso2.render(f5);
      this.spine1.render(f5);
      this.spine2.render(f5);
      this.spine3.render(f5);
      this.clothes.render(f5);
      this.head1.render(f5);
      this.head2.render(f5);
      this.teeth.render(f5);
      this.jaw.render(f5);
      this.armright1.render(f5);
      this.armright2.render(f5);
      this.armleft1.render(f5);
      this.armleft2.render(f5);
      this.legright1.render(f5);
      this.legleft1.render(f5);
      this.legright2.render(f5);
      this.legleft2.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   @Override
   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.head1.rotateAngleY = f3 / (180.0F / (float)Math.PI);
      this.head1.rotateAngleX = f4 / (180.0F / (float)Math.PI);
      this.head2.rotateAngleY = this.head1.rotateAngleY;
      this.head2.rotateAngleX = this.head1.rotateAngleX;
      this.teeth.rotateAngleY = this.head1.rotateAngleY;
      this.teeth.rotateAngleX = this.head1.rotateAngleX;
      this.jaw.rotateAngleY = this.head1.rotateAngleY;
      this.jaw.rotateAngleX = this.head1.rotateAngleX;
      this.armright2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
      this.armleft2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
      this.legright2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      this.legleft2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
   }
}
