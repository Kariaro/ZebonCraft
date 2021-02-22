package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.enchantment.AirFilterEnchantment;
import com.hardcoded.zeboncraft.enchantment.ZnchantEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;

public class ModEnchantments {
	public static final RegistryObject<Enchantment> ZNCHANT = Registration.ENCHANTMENTS.register("znchant", () ->
			new ZnchantEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND));
	public static final RegistryObject<Enchantment> AIR_FILTER = Registration.ENCHANTMENTS.register("air_filter", () ->
			new AirFilterEnchantment(Rarity.UNCOMMON, EquipmentSlotType.HEAD));
	
	static void register() {
		
	}
}
