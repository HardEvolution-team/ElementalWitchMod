package com.electrifiedded.mwaw;

import com.electrifiedded.archaiclib.legacy.registry.LegacyGameRegistry;
import com.electrifiedded.archaiclib.legacy.world.LegacySpawns;
import com.electrifiedded.archaiclib.original.OriginalModAssets;
import com.electrifiedded.mwaw.entity.*;
import com.electrifiedded.mwaw.lib.MWAWEventHandler;
import com.electrifiedded.mwaw.potion.MWAWMPotionHandler;
import com.electrifiedded.mwaw.projectile.*;
import com.electrifiedded.mwaw.proxy.CommonProxy;
import com.electrifiedded.mwaw.registry.MWAWConfigHandler;
import com.electrifiedded.mwaw.registry.MWAWItemRegistry;
import com.electrifiedded.mwaw.tab.MWAWTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:archaiclib@[1.27.0,)")
public class MoWitchAndWizard {
    public static final String ORIGINAL_NAME = "mwaw_1.7.10_0.0.3.jar - Elemental Witch Mod 0.0.3 for 1.7.10 (HoopAWolf)";
    public static final String ORIGINAL_URL = "https://www.planetminecraft.com/member/hoopawolf/";

    public static Potion paralyzePotion;
    @Mod.Instance(Reference.MOD_ID)
    public static MoWitchAndWizard instance;
    @SidedProxy(clientSide = "com.electrifiedded.mwaw.client.ClientProxy", serverSide = "com.electrifiedded.mwaw.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabs tabMoWitchAndWizard = new MWAWTab("tabMoWitchAndWizard");

    private int nextId;

    public MoWitchAndWizard() {
        MinecraftForge.EVENT_BUS.register(this);
        // textures and the sound come from the player's own copy of the original jar (archaic_originals/)
        OriginalModAssets.hide(Reference.MOD_ID, "sounds.json");
        OriginalModAssets.hide(Reference.MOD_ID, "lang/en_us.lang");
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        OriginalModAssets.require(Reference.MOD_ID, "mwaw", ORIGINAL_NAME, ORIGINAL_URL, Reference.MOD_ID);
        com.electrifiedded.archaiclib.legacy.sound.LegacyModSounds.autoRegister(Reference.MOD_ID);
        MWAWConfigHandler.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new MWAWConfigHandler());
        registerEntities();
        proxy.preInit();
    }

    @SubscribeEvent
    public void onItems(RegistryEvent.Register<Item> e) {
        MWAWItemRegistry.createItems(e);
    }

    @SubscribeEvent
    public void onRecipes(RegistryEvent.Register<IRecipe> e) {
        MWAWItemRegistry.addRecipes(e);
    }

    @SubscribeEvent
    public void onPotions(RegistryEvent.Register<Potion> e) {
        paralyzePotion = new MWAWMPotionHandler(false, 0)
                .setIconIndex(1, 1)
                .setPotionName("Paralyze")
                .registerPotionAttributeModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -50.0, 2)
                .setRegistryName(new ResourceLocation(Reference.MOD_ID, "paralyze"));
        e.getRegistry().register(paralyzePotion);
    }

    private void entity(Class<? extends Entity> cls, String name, int range, int freq) {
        LegacyGameRegistry.registerModEntity(cls, name, nextId++, this, range, freq, true);
    }

    private void witch(Class<? extends Entity> cls, String name, int egg1, int egg2) {
        LegacyGameRegistry.registerModEntity(cls, name, nextId++, this, 80, 3, true, egg1, egg2);
    }

    private void registerEntities() {
        entity(EntityAirEgg.class, "airegg", 80, 3);
        entity(EntityEarthEgg.class, "earthegg", 80, 3);
        entity(EntityFireEgg.class, "fireegg", 80, 3);
        entity(EntityLightningEgg.class, "lightningegg", 80, 3);
        entity(EntityHalloweenEgg.class, "halloweenegg", 80, 3);
        entity(EntityLightningShoot.class, "lightningbolt", 64, 10);
        entity(EntityPumpkinSkull.class, "pumpkinskull", 64, 10);
        entity(EntityLightningBall.class, "lightningball", 64, 10);
        entity(EntityWindShoot.class, "windball", 64, 10);
        entity(EntityMWAWFireBall.class, "mwawfireball", 64, 10);
        entity(EntityMWAWWindBall.class, "mwawwindball", 64, 10);
        // the four witches had global (id 400-403) spawn eggs in 1.7
        witch(EntityFireWitch.class, "firewitch", 14614785, 9054472);
        entity(EntityFireBat.class, "firebat", 80, 3);
        entity(EntityMeteorite.class, "firemeteorite", 80, 3);
        entity(EntityFirePet.class, "firepet", 80, 3);
        witch(EntityLightningWitch.class, "lightningwitch", 13695576, 16252462);
        entity(EntityLightningGolem.class, "lightninggolem", 80, 3);
        entity(EntityLightningPet.class, "lightningpet", 80, 3);
        witch(EntityAirWitch.class, "airwitch", 14211288, 12434877);
        entity(EntityAirPhoenix.class, "windphoenix", 80, 3);
        entity(EntityAirClone.class, "airclone", 80, 3);
        entity(EntityTornado.class, "tornado", 80, 3);
        entity(EntityMiniTornado.class, "minitornado", 80, 3);
        witch(EntityEarthWitch.class, "earthwitch", 2757130, 3875851);
        entity(EntityEarthMinion.class, "earthminion", 80, 3);
        entity(EntityEarthMinionGood.class, "earthgoodminion", 80, 3);
        entity(EntityEarthPet.class, "earthpet", 80, 3);
        entity(EntityHalloweenWitch.class, "halloweenwitch", 80, 3);
        entity(EntityHalloweenPet.class, "halloweenpet", 80, 3);
        entity(EntityHalloweenPetEvolved.class, "halloweenpetevolved", 80, 3);
        entity(EntityPumpkinAirStrike.class, "pumpkinairstrike", 80, 3);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LegacySpawns.addSpawn(EntityHalloweenWitch.class, 1, 1, 1, EnumCreatureType.AMBIENT,
                Biomes.FOREST, Biomes.FOREST_HILLS, Biomes.DESERT, Biomes.MESA, Biomes.ICE_PLAINS, Biomes.SAVANNA, Biomes.BIRCH_FOREST);
        MinecraftForge.EVENT_BUS.register(new MWAWEventHandler());
    }
}
