package com.hardcoded.mod.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.item.crafting.ServerRecipePlacer;

public class ServerRecipePlacerZebonWorkbench<C extends IInventory> extends ServerRecipePlacer<C> {
	public ServerRecipePlacerZebonWorkbench(RecipeBookContainer<C> recipeBookContainer) {
		super(recipeBookContainer);
	}
	
	protected void tryPlaceRecipe(IRecipe<C> recipe, boolean placeAll) {
		System.out.println("TryPlaceRecipe");
		boolean flag = recipeBookContainer.matches(recipe);
		int i = this.recipeItemHelper.getBiggestCraftableStack(recipe, (IntList)null);
		
		if(flag) {
			for(int j = 0; j < 3; ++j) {
				if(j != this.recipeBookContainer.getOutputSlot()) {
					ItemStack itemstack = this.recipeBookContainer.getSlot(j).getStack();
					if(!itemstack.isEmpty() && Math.min(i, itemstack.getMaxStackSize()) < itemstack.getCount() + 1) {
						return;
					}
				}
			}
		}
		
		int j1 = getMaxAmount(placeAll, i, flag);
		
		IntList intlist = new IntArrayList();
		if(recipeItemHelper.canCraft(recipe, intlist, j1)) {
			int k = j1;

			for(int l : intlist) {
				int i1 = RecipeItemHelper.unpack(l).getMaxStackSize();
				if(i1 < k) {
					k = i1;
				}
			}
			
			if(recipeItemHelper.canCraft(recipe, intlist, k)) {
				this.clear();
				this.placeRecipe((ZebonWorkbenchRecipe)recipe, intlist.iterator(), k);
			}
		}
	}
	
	@Override
	public void placeRecipe(int width, int height, int outputSlot, IRecipe<?> recipe, Iterator<Integer> ingredients, int maxAmount) {
		if(width != 1 || height != 2) throw new IllegalArgumentException("Invalid recipe dimension. Expected 2x1 but got " + width + "x" + height);
		placeRecipe((ZebonWorkbenchRecipe)recipe, ingredients, maxAmount);
	}
	
	protected void placeRecipe(ZebonWorkbenchRecipe recipe, Iterator<Integer> ingredients, int maxAmount) {
		System.out.println("Recipe: " + recipe);
		System.out.println("   : iter: " + ingredients.getClass());
		System.out.println("   : max: " + maxAmount);
		
		if(ingredients instanceof IntListIterator) {
			IntListIterator array = (IntListIterator)ingredients;
			
			while(array.hasPrevious()) {
				array.previous();
			}
			
			List<Integer> list = new ArrayList<>();
			while(array.hasNext()) {
				list.add(array.next());
			}
			
			System.out.println(list);
		}
		
		List<Integer> list = new ArrayList<>();
		while(ingredients.hasNext()) {
			int value = ingredients.next();
			list.add(value);
			System.out.println("   : ing: " + value);
		}
		
		ingredients = list.iterator();
		
		for(int k = 0; k < 2; k++) {
			if(!ingredients.hasNext()) return;
			setSlotContents(ingredients, k + 2, maxAmount, 0, k);
		}
	}
	
	protected void clear() {
		for(int i = 0; i < 2; i++) {
			giveToPlayer(i + 2);
		}
		
		recipeBookContainer.clear();
	}
}
