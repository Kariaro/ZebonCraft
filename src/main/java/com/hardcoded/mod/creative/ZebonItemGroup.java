package com.hardcoded.mod.creative;

import com.hardcoded.utility.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ZebonItemGroup extends ItemGroup {
	private ItemStack icon;
	
	public ZebonItemGroup() {
		super("zeboncraft");
	}
	
	public ItemStack createIcon() {
		if(icon == null)
			icon = new ItemStack(ModItems.ZEBON_INGOT.get());
		
		return icon;
	}
}
