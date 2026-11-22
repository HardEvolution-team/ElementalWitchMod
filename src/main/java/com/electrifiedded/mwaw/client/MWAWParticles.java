package com.electrifiedded.mwaw.client;

import com.electrifiedded.mwaw.FxArgs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleCloud;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** 1.12 versions of the mod's EntityFX classes (lightning / small wind / soul sprites from mwawparticles.png, tornado swirl). */
@SideOnly(Side.CLIENT)
public final class MWAWParticles {
    private static final ResourceLocation SHEET = new ResourceLocation("mwaw", "textures/particles/mwawparticles.png");

    private MWAWParticles() {}

    public static void spawn(FxArgs f) {
        double[] a = f.a;
        Particle p;
        switch (f.kind) {
            case "EntityLightningFX": p = new Sprite(f.world, a[0], a[1], a[2], a[3], a[4], a[5], 0); break;
            case "EntitySmallWindFX": p = new Sprite(f.world, a[0], a[1], a[2], a[3], a[4], a[5], 1); break;
            case "EntitySoulFX": p = new Sprite(f.world, a[0], a[1], a[2], a[3], a[4], a[5], 2); break;
            case "EntityTonadoFX": p = new Tornado(f.world, a[0], a[1], a[2], a[3], a[4]); break;
            default: return;
        }
        Minecraft.getMinecraft().effectRenderer.addEffect(p);
    }

    /** Short-lived fullbright sprite, index into a 16x16 grid of the mod's particle sheet. */
    static final class Sprite extends Particle {
        private final int index;

        Sprite(World w, double x, double y, double z, double vx, double vy, double vz, int index) {
            super(w, x, y, z, vx, vy, vz);
            this.index = index;
            this.particleAlpha = 0.5F;
            this.particleScale--;
            this.particleMaxAge = 3;
            this.canCollide = false;
        }

        @Override
        public void onUpdate() {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            if (this.particleAge++ >= this.particleMaxAge) this.setExpired();
            if (this.particleAge > this.particleMaxAge / 2)
                this.setAlphaF(1.0F - ((float) this.particleAge - this.particleMaxAge / 2) / this.particleMaxAge);
        }

        @Override
        public int getFXLayer() {
            return 3;
        }

        @Override
        public int getBrightnessForRender(float partial) {
            return 15728880;
        }

        @Override
        public void renderParticle(BufferBuilder unused, Entity view, float partial, float rx, float rz, float ryz, float rxy, float rxz) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(SHEET);
            float u0 = (index % 16) / 16.0F, u1 = u0 + 0.0624375F;
            float v0 = (index / 16) / 16.0F, v1 = v0 + 0.0624375F;
            float s = 0.1F * this.particleScale;
            float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * partial - interpPosX);
            float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * partial - interpPosY);
            float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * partial - interpPosZ);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.depthMask(false);
            Tessellator t = Tessellator.getInstance();
            BufferBuilder b = t.getBuffer();
            b.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
            int j = 240;
            b.pos(x - rx * s - ryz * s, y - rz * s, z - rxy * s - rxz * s).tex(u1, v1).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, j).endVertex();
            b.pos(x - rx * s + ryz * s, y + rz * s, z - rxy * s + rxz * s).tex(u1, v0).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, j).endVertex();
            b.pos(x + rx * s + ryz * s, y + rz * s, z + rxy * s + rxz * s).tex(u0, v0).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, j).endVertex();
            b.pos(x + rx * s - ryz * s, y - rz * s, z + rxy * s - rxz * s).tex(u0, v1).color(particleRed, particleGreen, particleBlue, particleAlpha).lightmap(j, j).endVertex();
            t.draw();
            GlStateManager.depthMask(true);
        }
    }

    /** EntityTonadoFX: cloud particle orbiting a vertical axis. */
    static final class Tornado extends ParticleCloud {
        private final double orbitSpeed, centerX, centerZ, size;
        private final float baseScale;
        private byte customAge, customMaxAge;
        private double orbitAngle = 314.1592653589793;

        Tornado(World world, double x, double y, double z, double motionY, double radius) {
            super(world, x, y, z, 0.0, motionY, 0.0);
            this.centerX = x;
            this.centerZ = z;
            this.motionX = this.motionZ = 0.0;
            this.size = radius;
            this.orbitSpeed = (world.rand.nextDouble() * 0.3217 + 0.1954) * (this.rand.nextBoolean() ? 1 : -1);
            this.customMaxAge = (byte) ((this.particleAge = this.particleMaxAge = this.rand.nextInt(15) + 10) * 2);
            this.particleScale = this.baseScale = 0.6F;
        }

        @Override
        public void renderParticle(BufferBuilder buffer, Entity view, float partial, float rx, float rz, float ryz, float rxy, float rxz) {
            float sc = 1.0F - (this.particleAge + partial) / this.particleMaxAge;
            sc *= sc;
            this.particleScale = this.baseScale * (1.0F - sc);
            super.renderParticle(buffer, view, partial, rx, rz, ryz, rxy, rxz);
        }

        @Override
        public void onUpdate() {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            this.posX = this.centerX + Math.cos(this.orbitAngle) * this.size;
            this.posY += this.motionY;
            this.posZ = this.centerZ + Math.sin(this.orbitAngle) * this.size;
            this.orbitAngle += this.orbitSpeed;
            if (++this.customAge > this.customMaxAge) this.setExpired();
            else if (this.customAge >= this.customMaxAge >> 1) this.particleAge--;
        }
    }
}
