package com.hardcoded.zeboncraft.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class AirFilterEnchantment extends Enchantment {
	public AirFilterEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {
		super(rarityIn, EnchantmentType.ARMOR_HEAD, slots);
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 20;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
}
