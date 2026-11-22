package com.electrifiedded.mwaw.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/** Plain item with a fixed rarity (shards, empty egg, render-only items). */
public class ItemMWAW extends Item {
    private final EnumRarity rarity;

    public ItemMWAW(int stack, EnumRarity rarity) {
        this.maxStackSize = stack;
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }
}
