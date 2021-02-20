package com.hardcoded.mod.recipe;

import com.hardcoded.mod.container.ZebonWorkbenchContainer;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.item.crafting.ServerRecipePlacer;

public class ServerRecipePlacerZebonWorkbench<C extends IInventory> extends ServerRecipePlacer<C> {
	public ServerRecipePlacerZebonWorkbench(RecipeBookContainer<C> recipeBookContainer) {
		super(recipeBookContainer);
	}
	
	protected void clear() {
		giveToPlayer(ZebonWorkbenchContainer.INGREDIENT_1_SLOT);
		giveToPlayer(ZebonWorkbenchContainer.INGREDIENT_2_SLOT);
	}
}
