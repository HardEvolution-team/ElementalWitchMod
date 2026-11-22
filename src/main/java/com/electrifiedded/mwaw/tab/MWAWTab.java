package com.electrifiedded.mwaw.tab;

import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MWAWTab extends CreativeTabs {
    public MWAWTab(String label) {
        super(label);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack createIcon() {
        return new ItemStack(MWAWItemRegistry.firestaff);
    }
}
