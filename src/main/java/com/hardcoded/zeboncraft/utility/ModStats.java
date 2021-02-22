package com.hardcoded.zeboncraft.utility;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ModStats {
	public static final ResourceLocation INTERACT_WITH_ZEBON_WORKBENCH = registerCustom("interact_with_zebon_workbench", IStatFormatter.DEFAULT);
	
	static void register() {
	}
	
	private static ResourceLocation registerCustom(String key, IStatFormatter formatter) {
		ResourceLocation resourcelocation = new ResourceLocation(key);
		Registry.register(Registry.CUSTOM_STAT, key, resourcelocation);
		Stats.CUSTOM.get(resourcelocation, formatter);
		return resourcelocation;
	}
}
