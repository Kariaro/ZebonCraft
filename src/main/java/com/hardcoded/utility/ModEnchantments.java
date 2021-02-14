package com.hardcoded.utility;

import com.hardcoded.mod.enchantment.ZnchantEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;

public class ModEnchantments {
	public static final RegistryObject<Enchantment> ZNCHANT = Registration.ENCHANTMENTS.register("znchant", () ->
		new ZnchantEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND));
	
	static void register() {
		
	}
}
