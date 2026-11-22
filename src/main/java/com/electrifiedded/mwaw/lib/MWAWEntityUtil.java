package com.electrifiedded.mwaw.lib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

import java.util.UUID;

public class MWAWEntityUtil {
    public static void spawnInWorld(World world, Entity entity) {
        if (!world.isRemote) world.spawnEntity(entity);
    }

    public static void spawnWeather(World world, Entity entity) {
        if (!world.isRemote) world.addWeatherEffect(entity);
    }

    /** 1.7 EntityTameable.func_152115_b(String uuid). */
    public static void setOwner(EntityTameable e, String uuid) {
        if (uuid == null || uuid.isEmpty()) return;
        try {
            e.setOwnerId(UUID.fromString(uuid));
        } catch (IllegalArgumentException ignored) {
        }
    }

    /** 1.7 EntityTameable.func_152113_b(). */
    public static String ownerString(EntityTameable e) {
        UUID id = e.getOwnerId();
        return id == null ? "" : id.toString();
    }
}
