package com.electrifiedded.mwaw.lib;

import com.electrifiedded.archaiclib.legacy.world.LegacyWorld;
import com.electrifiedded.mwaw.FxArgs;
import com.electrifiedded.mwaw.MoWitchAndWizard;
import com.electrifiedded.mwaw.registry.MWAWConfigHandler;
import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class MWAWEventHandler {
    private final Random rand = new Random();

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event) {
        EntityLivingBase e = event.getEntityLiving();
        if (MoWitchAndWizard.paralyzePotion != null && e.isPotionActive(MoWitchAndWizard.paralyzePotion) && e instanceof EntityMob) {
            ((EntityMob) e).setAttackTarget(null);
        }
        if (!MWAWConfigHandler.ShowParticleWand || !e.world.isRemote) return;
        ItemStack held = e.getHeldItemMainhand();
        if (held.isEmpty()) return;
        Item item = held.getItem();
        String particle;
        if (item == MWAWItemRegistry.firestaff) particle = "flame";
        else if (item == MWAWItemRegistry.windstaff) particle = "explode";
        else if (item == MWAWItemRegistry.earthstaff) particle = "blockcrack_3_0";
        else if (item == MWAWItemRegistry.lightningstaff) particle = null;
        else return;
        int dy = e instanceof EntityPlayer ? 2 : 0;
        double d = rand.nextGaussian() * 0.02, d1 = rand.nextGaussian() * 0.02, d2 = rand.nextGaussian() * 0.02, d3 = 10.0;
        double x = e.posX + rand.nextFloat() * e.width * 2.0F - e.width - d * d3;
        double y = e.posY + rand.nextFloat() * e.height - d1 * d3 - dy;
        double z = e.posZ + rand.nextFloat() * e.width * 2.0F - e.width - d2 * d3;
        if (particle == null) MoWitchAndWizard.proxy.fx(new FxArgs("EntityLightningFX", e.world, x, y, z, d, d1, d2));
        else LegacyWorld.spawnParticle(e.world, particle, x, y, z, d, d1, d2);
    }
}
