package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.ZebonCraft;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber(modid = ZebonCraft.MOD_ID, bus = Bus.MOD)
public class ZebonConfig {
	public static final ForgeConfigSpec MOD_CONFIG;
	private static final String MUSHROOMS = "mushrooms";
	
	public static boolean enableInfectedHearts = true;
	public static boolean hurtfullMushroomsDealDamage = false;
	
	static {
		ForgeConfigSpec.Builder config = new ForgeConfigSpec.Builder();
		
		config.push(MUSHROOMS);
		
		ENABLE_INFECTED_HEARTS = config.comment("Enable or disable hearts getting infected by some mushrooms.")
				.define("enableInfectedHearts", enableInfectedHearts);
		
		HARMFULL_MUSHROOMS_DEAL_DAMAGE = config.comment("Change if harmfull mushrooms deals damage to the player.")
				.define("harmfullMushroomsDealDamage", hurtfullMushroomsDealDamage);
		
		config.pop();
		
		MOD_CONFIG = config.build();
	}
	
	private static final ForgeConfigSpec.BooleanValue ENABLE_INFECTED_HEARTS;
	private static final ForgeConfigSpec.BooleanValue HARMFULL_MUSHROOMS_DEAL_DAMAGE;
	
	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading event) {
		load();
	}
	
	@SubscribeEvent
	public static void onReload(final ModConfig.Loading event) {
		load();
	}
	
	public static void load() {
		enableInfectedHearts = ENABLE_INFECTED_HEARTS.get();
		hurtfullMushroomsDealDamage = HARMFULL_MUSHROOMS_DEAL_DAMAGE.get();
	}
}
