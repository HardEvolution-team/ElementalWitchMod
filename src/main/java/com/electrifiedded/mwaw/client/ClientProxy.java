package com.electrifiedded.mwaw.client;

import com.electrifiedded.archaiclib.legacy.client.LegacyChargeLayer;
import com.electrifiedded.archaiclib.legacy.client.LegacyRenderLiving;
import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.entity.*;
import com.electrifiedded.mwaw.models.*;
import com.electrifiedded.mwaw.projectile.*;
import com.electrifiedded.mwaw.proxy.CommonProxy;
import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.electrifiedded.archaiclib.legacy.client.LegacyRenderLiving.register;
import static com.electrifiedded.archaiclib.legacy.client.LegacyRenderLiving.tex;

public class ClientProxy extends CommonProxy {
    private static final String T = "mwaw:textures/entity/";

    @Override
    public void preInit() {
        register(EntityFireWitch.class, () -> new ModelMWAWWitch(0.5F), 0.5F, T + "firewitch.png");
        register(EntityFireBat.class, ModelFireBat::new, 0.5F, T + "firebat.png");
        register(EntityMeteorite.class, ModelCube::new, 5.0F, T + "cube.png");
        register(EntityFirePet.class, ModelFirePet::new, 0.5F, T + "firepet.png");
        register(EntityFireEgg.class, ModelEgg::new, 0.5F, T + "fire_egg.png");
        charged(EntityLightningWitch.class, () -> new ModelMWAWWitch(0.5F), T + "lightningwitch.png",
                LegacyChargeLayer.CREEPER_ARMOR, e -> e.getHealth() <= 30.0F);
        charged(EntityLightningGolem.class, ModelLightningGolem::new, T + "lightninggolem.png",
                LegacyChargeLayer.CREEPER_ARMOR, e -> true);
        register(EntityLightningPet.class, ModelLightningPet::new, 0.5F, T + "lightningpet.png");
        register(EntityLightningEgg.class, ModelEgg::new, 0.5F, T + "lightning_egg.png");
        register(EntityAirWitch.class, () -> new ModelMWAWWitch(0.5F), 0.5F, T + "airwitch.png");
        register(EntityAirClone.class, () -> new ModelMWAWWitch(0.5F), 0.5F, T + "airwitch.png");
        register(EntityTornado.class, ModelCube::new, 0.5F, T + "lightninggolem.png");
        register(EntityAirPhoenix.class, ModelWindPhoenix::new, 0.5F, T + "windpet1.png");
        register(EntityMiniTornado.class, ModelCube::new, 0.5F, T + "lightninggolem.png");
        register(EntityAirEgg.class, ModelEgg::new, 0.5F, T + "air_egg.png");
        register(EntityEarthMinion.class, () -> new ModelMWAWWitch(0.5F), 0.5F, T + "clayminion.png");
        register(EntityEarthMinionGood.class, () -> new ModelMWAWWitch(0.5F), 0.5F, T + "clayminion.png");
        charged(EntityEarthWitch.class, () -> new ModelMWAWWitch(0.5F), T + "earthwitch.png",
                tex(T + "earth_armor.png"), EntityEarthWitch::isArmored);
        register(EntityEarthPet.class, ModelEarthPet::new, 0.5F, T + "earthpet.png");
        register(EntityEarthEgg.class, ModelEgg::new, 0.5F, T + "earth_egg.png");
        register(EntityHalloweenWitch.class, () -> new ModelMWAWWitch(0.5F), 0.5F, T + "halloweenwitch.png");
        register(EntityHalloweenEgg.class, ModelEgg::new, 0.5F, T + "halloween_egg.png");
        register(EntityHalloweenPet.class, ModelHalloweenPet::new, 0.5F, T + "darkpumpkinv1.png");
        register(EntityHalloweenPetEvolved.class, ModelHalloweenPetV2::new, 0.5F, T + "darkpumpkinv2.png");
        register(EntityPumpkinAirStrike.class, ModelCube::new, 0.5F, T + "pumpkin.png");

        sprite(EntityLightningShoot.class, () -> MWAWItemRegistry.itemnull);
        sprite(EntityLightningBall.class, () -> MWAWItemRegistry.itemlightning);
        sprite(EntityWindShoot.class, () -> MWAWItemRegistry.itemnull);
        sprite(EntityMWAWFireBall.class, () -> MWAWItemRegistry.itemnull);
        sprite(EntityMWAWWindBall.class, () -> MWAWItemRegistry.itemnull);
        RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinSkull.class, RenderPumpkinSkull::new);
    }

    private static <T extends EntityLiving> void charged(Class<T> cls, Supplier<? extends ModelBase> model, String texture,
                                                         ResourceLocation overlay, Predicate<T> when) {
        ResourceLocation rl = tex(texture);
        RenderingRegistry.registerEntityRenderingHandler(cls, m -> {
            LegacyRenderLiving<T> r = new LegacyRenderLiving<>(m, model.get(), 0.5F, e -> rl);
            r.addLayer(new LegacyChargeLayer<>(r, model.get(), overlay, when));
            return r;
        });
    }

    private static <T extends Entity> void sprite(Class<T> cls, Supplier<Item> item) {
        RenderingRegistry.registerEntityRenderingHandler(cls, m -> new RenderSnowball<>(m, item.get(), Minecraft.getMinecraft().getRenderItem()));
    }

    @Override
    public void fx(FxArgs args) {
        if (args.world != null && args.world.isRemote) MWAWParticles.spawn(args);
    }

    /** 1.7 RenderPumpkinSkull: a wither-skull style head with the pumpkin texture. */
    static final class RenderPumpkinSkull extends Render<EntityPumpkinSkull> {
        private static final ResourceLocation TEX = tex(T + "pumpkin_shoot.png");
        private final ModelSkeletonHead head = new ModelSkeletonHead();

        RenderPumpkinSkull(RenderManager m) {
            super(m);
        }

        private static float lerpYaw(float a, float b, float p) {
            float d = b - a;
            while (d < -180.0F) d += 360.0F;
            while (d >= 180.0F) d -= 360.0F;
            return a + p * d;
        }

        @Override
        public void doRender(EntityPumpkinSkull e, double x, double y, double z, float yaw, float partial) {
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            float f = lerpYaw(e.prevRotationYaw, e.rotationYaw, partial);
            float f1 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * partial;
            GlStateManager.translate((float) x, (float) y, (float) z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            GlStateManager.enableAlpha();
            bindEntityTexture(e);
            head.render(e, 0.0F, 0.0F, 0.0F, f, f1, 0.0625F);
            GlStateManager.popMatrix();
            super.doRender(e, x, y, z, yaw, partial);
        }

        @Override
        protected ResourceLocation getEntityTexture(EntityPumpkinSkull e) {
            return TEX;
        }
    }
}
