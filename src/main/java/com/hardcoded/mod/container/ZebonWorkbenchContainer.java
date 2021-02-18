package com.hardcoded.mod.container;

import com.hardcoded.utility.ModContainers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.common.Tags;

// TODO: Clone RecipeBookContainer

public class ZebonWorkbenchContainer extends Container {
	private final IInventory workbenchInv;
	private final IIntArray data;
	
	public ZebonWorkbenchContainer(int id, PlayerInventory inv) {
		this(id, inv, new Inventory(4), new IntArray(4));
	}
	
	public ZebonWorkbenchContainer(int id, PlayerInventory inv, IInventory workbenchInv, IIntArray data) {
		super(ModContainers.ZEBON_WORKBENCH.get(), id);
		assertInventorySize(workbenchInv, 4);
		this.workbenchInv = workbenchInv;
		this.data = data;
		
		// Top:    (48,15)
		// Bot:    (48,51)
		// Fuel:   (79,51)
		// Result: (120,33)
		
		addSlot(new IngotSlot(workbenchInv, 0, 48, 15));
		addSlot(new IngredientSlot(workbenchInv, 1, 48, 51));
		addSlot(new FuelSlot(workbenchInv, 2, 79, 51));
		addSlot(new IngotSlot(workbenchInv, 3, 120, 33));
		
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; ++k) {
			addSlot(new Slot(inv, k, 8 + k * 18, 142));
		}
	}
	
	public boolean canInteractWith(PlayerEntity playerIn) {
		return workbenchInv.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		// FIXME: Implement shift clicking
		return ItemStack.EMPTY;
	}
	
	
	
	static class FuelSlot extends Slot {
		public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		public boolean isItemValid(ItemStack stack) {
			return AbstractFurnaceTileEntity.isFuel(stack);
		}
	}
	
	static class IngotSlot extends Slot {
		public IngotSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) {
			return stack.getItem().getTags().contains(Tags.Items.INGOTS.getName());
		}
	}
	
	static class IngredientSlot extends Slot {
		public IngredientSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		public boolean isItemValid(ItemStack stack) {
			// TODO: Add more recipes
			
			// Currently the only recipe we have is with powered rails
			return stack.getItem() == Items.POWERED_RAIL;
		}
	}
	
}
