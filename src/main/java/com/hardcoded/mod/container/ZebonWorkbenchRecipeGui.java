package com.hardcoded.mod.container;

import java.util.HashSet;
import java.util.Set;

import com.hardcoded.utility.ModBlocks;

import net.minecraft.client.gui.recipebook.AbstractRecipeBookGui;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ZebonWorkbenchRecipeGui extends AbstractRecipeBookGui {
	private static final ITextComponent text = new TranslationTextComponent("gui.recipebook.toggleRecipes.zebon_workbench");
	private static final Set<Item> recipe_items = new HashSet<>();
	
	static {
		recipe_items.add(ModBlocks.ZEBON_POWERED_RAIL.get().asItem());
	}
	
	public ITextComponent func_230479_g_() {
		return text;
	}
	
	protected Set<Item> func_212958_h() {
		return recipe_items;
	}
}
