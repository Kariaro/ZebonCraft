package com.hardcoded.utility;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;

public class ModBiomes {
	public static final RegistryKey<Biome> ZERING = addBiomeKey("zering");
	
	static void register() {
		//BiomeManager.addBiome(BiomeType.DESERT, new BiomeManager.BiomeEntry(ZEBRIOME, 10));
	}
	
	private static RegistryKey<Biome> addBiomeKey(String name) {
		return null;
		//return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(name));
	}
}
