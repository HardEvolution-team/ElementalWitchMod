package com.electrifiedded.mwaw.models;

import net.minecraft.client.renderer.GlStateManager;

import com.electrifiedded.mwaw.entity.EntityMeteorite;
import com.electrifiedded.mwaw.entity.EntityPumpkinAirStrike;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class ModelCube extends ModelBase {
   ModelRenderer Shape1;

   public ModelCube() {
      this.textureWidth = 512;
      this.textureHeight = 512;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(-24.0F, -24.0F, -24.0F, 48, 48, 48);
      this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Shape1.setTextureSize(512, 512);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
   }

   @Override
   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      if (entity.isRiding() && entity.getRidingEntity() instanceof EntityLivingBase) {
         GlStateManager.translate(0.0F, 2.5F, 0.0F);
      } else {
         GlStateManager.translate(0.0F, 0.0F, 0.0F);
      }

      if (entity instanceof EntityPumpkinAirStrike) {
         GlStateManager.scale(-0.5F, -0.5F, -0.5F);
      }

      this.Shape1.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   @Override
   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      if (entity instanceof EntityMeteorite || entity instanceof EntityPumpkinAirStrike) {
         this.Shape1.rotateAngleY = f2 / 10.0F;
         this.Shape1.rotateAngleX = f2 / 20.0F;
         this.Shape1.rotateAngleZ = f2 / 20.0F;
      }
   }
}
