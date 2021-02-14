package com.hardcoded.mod.armor;

import com.hardcoded.utility.ModArmorMaterial;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class ZebonArmor extends ArmorItem {
	public ZebonArmor(EquipmentSlotType slot, Properties builderIn) {
		super(ModArmorMaterial.ZEBON, slot, builderIn);
	}
}
