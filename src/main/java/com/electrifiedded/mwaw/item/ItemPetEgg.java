package com.electrifiedded.mwaw.item;

import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.function.Function;

/** Elemental egg item: places the matching (owned) egg entity at the player's feet. */
public class ItemPetEgg extends ItemMWAW {
    private final Function<World, ? extends EntityTameable> factory;

    public ItemPetEgg(Function<World, ? extends EntityTameable> factory) {
        super(1, EnumRarity.RARE);
        this.factory = factory;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            EntityTameable mob = factory.apply(world);
            mob.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, 0.0F);
            MWAWEntityUtil.spawnInWorld(world, mob);
            mob.setTamed(true);
            mob.setOwnerId(player.getUniqueID());
            mob.setCustomNameTag("Owner: " + player.getName());
        }
        if (!player.capabilities.isCreativeMode) stack.shrink(1);
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
