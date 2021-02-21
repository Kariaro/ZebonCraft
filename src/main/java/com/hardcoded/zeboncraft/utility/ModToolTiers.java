package com.hardcoded.zeboncraft.utility;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

// FIXME: Add repair material
public enum ModToolTiers implements IItemTier {
	ZEBON_TIER(2000, 3, 40, 7.5f, 12.0f, () -> Ingredient.fromItems(ModItems.ZEBON_INGOT.get())),
	
	;
	
	private final int durability;
	private final int harvest_level;
	private final int enchantability;
	private final float efficiency;
	private final float attack_damage;
	private final Supplier<Ingredient> repair_material;
	
	private ModToolTiers(int durability, int harvest_level, int enchantability, float efficiency, float attack_damage, Supplier<Ingredient> repair_material) {
		this.durability = durability;
		this.harvest_level = harvest_level;
		this.enchantability = enchantability;
		this.efficiency = efficiency;
		this.attack_damage = attack_damage;
		this.repair_material = repair_material;
	}
	
	@Override
	public int getMaxUses() {
		return durability;
	}
	
	@Override
	public int getHarvestLevel() {
		return harvest_level;
	}
	
	@Override
	public int getEnchantability() {
		return enchantability;
	}
	
	@Override
	public float getEfficiency() {
		return efficiency;
	}
	
	@Override
	public float getAttackDamage() {
		return attack_damage;
	}
	
	@Override
	public Ingredient getRepairMaterial() {
		return repair_material.get();
	}
}
