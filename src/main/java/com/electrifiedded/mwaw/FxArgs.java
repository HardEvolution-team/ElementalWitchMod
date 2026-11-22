package com.electrifiedded.mwaw;

import net.minecraft.world.World;

/** Server-safe description of one of the mod's custom client particles (spawned through the proxy). */
public final class FxArgs {
    public final String kind;
    public final World world;
    public final double[] a;

    public FxArgs(String kind, World world, double... a) {
        this.kind = kind;
        this.world = world;
        this.a = a;
    }
}
