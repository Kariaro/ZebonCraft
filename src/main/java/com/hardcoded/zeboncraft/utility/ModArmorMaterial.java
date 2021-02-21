package com.hardcoded.zeboncraft.utility;

import java.util.function.Supplier;

import com.hardcoded.zeboncraft.ZebonCraft;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ModArmorMaterial implements IArmorMaterial {
	ZEBON(ZebonCraft.MOD_ID + ":zebon", new int[] { 2, 4, 2, 2 }, new int[] { 4, 2, 2, 2 }, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.fromItems(ModItems.ZEBON_INGOT.get()), 10.0f, 3.0f)
	;
	
	private final int[] durability;
	private final int[] damage_reduction;
	private final int enchantability;
	private final Supplier<Ingredient> repair_material;
	private final SoundEvent sound_event;
	private final String texture_name;
	private final float toughness;
	private final float knockback_resistance;
	
	private ModArmorMaterial(String texture_name, int[] durability, int[] damage_reduction, int enchantability, SoundEvent sound_event,
			Supplier<Ingredient> repair_material, float toughness, float knockback_resistance) {
		this.durability = durability;
		this.damage_reduction = damage_reduction;
		this.enchantability = enchantability;
		this.sound_event = sound_event;
		this.repair_material = repair_material;
		this.texture_name = texture_name;
		this.toughness = toughness;
		this.knockback_resistance = knockback_resistance;
	}
	
	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		if(slotIn.ordinal() >= durability.length) return 0;
		return durability[slotIn.ordinal()];
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		if(slotIn.ordinal() >= damage_reduction.length) return 0;
		return damage_reduction[slotIn.ordinal()];
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return sound_event;
	}

	@Override
	public String getName() {
		return texture_name;
	}
	
	@Override
	public float getToughness() {
		return toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knockback_resistance;
	}
	
	@Override
	public Ingredient getRepairMaterial() {
		return repair_material.get();
	}
}
