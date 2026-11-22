package com.electrifiedded.mwaw.registry;

import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class MWAWConfigHandler {
   public static Configuration configuration;
   public static boolean ShowParticleWand = true;
   public static int PotionIDParalyze = 36;

   public static void init(File configFile) {
      if (configuration == null) {
         configuration = new Configuration(configFile);
         loadConfiguration();
      }
   }

   private static void loadConfiguration() {
      ShowParticleWand = configuration.getBoolean("Show Wand's Particles", "general", true, "Default: true");
      PotionIDParalyze = configuration.getInt("Potion ID Paralyze", "general", 36, 0, Integer.MAX_VALUE, "Min: 0, Max: âˆž Default: 36");
      if (configuration.hasChanged()) {
         configuration.save();
      }
   }

   @SubscribeEvent
   public void onConfigurationChangedEvent(OnConfigChangedEvent event) {
      if (event.getModID().equalsIgnoreCase("mwaw")) {
         loadConfiguration();
      }
   }
}
