package com.hardcoded.mod.creative;

import com.hardcoded.utility.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ZebonItemGroup extends ItemGroup {
	public ZebonItemGroup() {
		super("zeboncraft");
	}
	
	public ItemStack createIcon() {
		return new ItemStack(ModItems.ZEBON_INGOT.get());
	}
}
