package com.electrifiedded.mwaw.item;

import com.electrifiedded.mwaw.entity.EntityEarthMinionGood;
import com.electrifiedded.mwaw.lib.MWAWEntityUtil;
import com.electrifiedded.mwaw.projectile.EntityLightningBall;
import com.electrifiedded.mwaw.projectile.EntityMWAWFireBall;
import com.electrifiedded.mwaw.projectile.EntityMWAWWindBall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;

/** The four elemental staffs: each use costs hunger (2 or 4 points) and 1 durability. */
public class ItemStaff extends ItemMWAW {
    public enum Kind { FIRE, AIR, LIGHTNING, EARTH }

    private final Kind kind;

    public ItemStaff(Kind kind) {
        super(1, EnumRarity.RARE);
        this.kind = kind;
        setMaxDamage(500);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.getFoodStats().getFoodLevel() > 1) {
            int cost = 2;
            if (kind == Kind.EARTH) {
                cost = 4;
                for (int s = -1; s <= 1; s += 2) {
                    EntityEarthMinionGood e = new EntityEarthMinionGood(world);
                    e.setLocationAndAngles(player.posX + 1.5 * s * -1, player.posY, player.posZ, 0.0F, 0.0F);
                    MWAWEntityUtil.spawnInWorld(world, e);
                }
            } else {
                Vec3d look = player.getLookVec();
                EntityFireball ball;
                if (kind == Kind.FIRE) ball = new EntityMWAWFireBall(world, player, 1.0, 1.0, 1.0);
                else if (kind == Kind.AIR) ball = new EntityMWAWWindBall(world, player, 1.0, 1.0, 1.0);
                else { ball = new EntityLightningBall(world, player, 1.0, 1.0, 1.0); cost = 4; }
                ball.setPosition(player.posX + look.x, player.posY + look.y + player.getEyeHeight(), player.posZ + look.z);
                ball.accelerationX = look.x * 0.1;
                ball.accelerationY = look.y * 0.1;
                ball.accelerationZ = look.z * 0.1;
                MWAWEntityUtil.spawnInWorld(world, ball);
            }
            if (!player.capabilities.isCreativeMode) {
                player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() - cost);
                stack.damageItem(1, player);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
