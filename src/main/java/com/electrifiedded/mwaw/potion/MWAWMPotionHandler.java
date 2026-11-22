package com.electrifiedded.mwaw.potion;

import net.minecraft.potion.Potion;

public class MWAWMPotionHandler extends Potion {
    public MWAWMPotionHandler(boolean bad, int color) {
        super(bad, color);
    }

    @Override
    public Potion setIconIndex(int x, int y) {
        super.setIconIndex(x, y);
        return this;
    }
}
