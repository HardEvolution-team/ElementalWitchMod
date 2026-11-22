package com.electrifiedded.mwaw.registry;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.v1710.api.item.ArchaicItem;
import com.electrifiedded.mwaw.MoWitchAndWizard;
import com.electrifiedded.mwaw.Reference;
import com.electrifiedded.mwaw.entity.EntityAirEgg;
import com.electrifiedded.mwaw.entity.EntityEarthEgg;
import com.electrifiedded.mwaw.entity.EntityFireEgg;
import com.electrifiedded.mwaw.entity.EntityLightningEgg;
import com.electrifiedded.mwaw.item.ItemMWAW;
import com.electrifiedded.mwaw.item.ItemPetEgg;
import com.electrifiedded.mwaw.item.ItemStaff;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/** Items of the original (1.7 registry names were "mwaw_" + unlocalized name; now plain names in the mwaw domain). */
public final class MWAWItemRegistry {
    public static Item itemnull, itemlightning, fireshard, lightningshard, airshard, earthshard,
            firestaff, windstaff, earthstaff, lightningstaff, emptyegg, airegg, lightningegg, earthegg, fireegg;

    private MWAWItemRegistry() {}

    private static Item item(Item item, String name, String translation, String texture, boolean tab) {
        item.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
        item.setTranslationKey(translation);
        if (tab) item.setCreativeTab(MoWitchAndWizard.tabMoWitchAndWizard);
        LegacyGameRegistry.registerItem(item, name);
        ArchaicItem.registerMetaTexture(item, 0, texture);
        return item;
    }

    public static void createItems(RegistryEvent.Register<Item> e) {
        itemnull = item(new ItemMWAW(1, EnumRarity.COMMON), "nothing", "nothing", "null", false);
        itemlightning = item(new ItemMWAW(1, EnumRarity.COMMON), "lightning", "lightning", "electricball", false);
        fireshard = item(new ItemMWAW(16, EnumRarity.EPIC), "fireshard", "fireshard", "fireshard", true);
        lightningshard = item(new ItemMWAW(16, EnumRarity.EPIC), "lightningshard", "lightningshard", "lightningshard", true);
        airshard = item(new ItemMWAW(16, EnumRarity.EPIC), "airshard", "airshard", "airshard", true);
        earthshard = item(new ItemMWAW(16, EnumRarity.EPIC), "earthshard", "earthshard", "earthshard", true);
        firestaff = item(new ItemStaff(ItemStaff.Kind.FIRE), "firestaff", "firestaff", "firestaff", true);
        windstaff = item(new ItemStaff(ItemStaff.Kind.AIR), "airstaff", "airstaff", "airstaff", true);
        earthstaff = item(new ItemStaff(ItemStaff.Kind.EARTH), "earthstaff", "earthstaff", "earthstaff", true);
        lightningstaff = item(new ItemStaff(ItemStaff.Kind.LIGHTNING), "lightningstaff", "lightningstaff", "lightningstaff", true);
        emptyegg = item(new ItemMWAW(1, EnumRarity.COMMON), "mwawegg", "MWAWegg", "mwawegg", true);
        airegg = item(new ItemPetEgg(EntityAirEgg::new), "airegg", "airegg", "airegg", true);
        lightningegg = item(new ItemPetEgg(EntityLightningEgg::new), "lightningegg", "lightningegg", "lightningegg", true);
        fireegg = item(new ItemPetEgg(EntityFireEgg::new), "fireegg", "fireegg", "fireegg", true);
        earthegg = item(new ItemPetEgg(EntityEarthEgg::new), "earthegg", "earthegg", "earthegg", true);
    }

    private static int recipeId;

    public static void addRecipes(RegistryEvent.Register<IRecipe> e) {
        staff(e, firestaff, fireshard);
        staff(e, earthstaff, earthshard);
        staff(e, lightningstaff, lightningshard);
        staff(e, windstaff, airshard);
        ShapelessOreRecipe egg = new ShapelessOreRecipe(null, new ItemStack(emptyegg), Items.EGG);
        egg.setRegistryName(new ResourceLocation(Reference.MOD_ID, "recipe_" + recipeId++));
        e.getRegistry().register(egg);
    }

    private static void staff(RegistryEvent.Register<IRecipe> e, Item staff, Item shard) {
        ShapedOreRecipe r = new ShapedOreRecipe(null, new ItemStack(staff), " / ", "/=/", " = ", '=', Items.STICK, '/', shard);
        r.setRegistryName(new ResourceLocation(Reference.MOD_ID, "recipe_" + recipeId++));
        e.getRegistry().register(r);
    }
}
