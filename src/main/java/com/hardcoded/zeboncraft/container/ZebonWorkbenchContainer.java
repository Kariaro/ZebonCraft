package com.hardcoded.zeboncraft.container;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.hardcoded.zeboncraft.recipe.ServerRecipePlacerZebonWorkbench;
import com.hardcoded.zeboncraft.tileentity.ZebonWorkbenchTileEntity;
import com.hardcoded.zeboncraft.utility.ModContainers;
import com.hardcoded.zeboncraft.utility.ModTags;

import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZebonWorkbenchContainer extends RecipeBookContainer<IInventory> {
	private static final List<RecipeBookCategories> RECIPE_CATEGORIES = ImmutableList.of(RecipeBookCategories.UNKNOWN);
	public static final int INGREDIENT_1_SLOT = 0;
	public static final int INGREDIENT_2_SLOT = 1;
	public static final int RESULT_SLOT = 2;
	public static final int FUEL_SLOT = 3;
	
	protected final IInventory workbenchInv;
	private final IIntArray data;
	protected final World world;
	
	public ZebonWorkbenchContainer(int id, PlayerInventory inv) {
		this(id, inv, new Inventory(4), new IntArray(4));
	}
	
	public ZebonWorkbenchContainer(int id, PlayerInventory inv, IInventory workbenchInv, IIntArray data) {
		super(ModContainers.ZEBON_WORKBENCH.get(), id);
		assertInventorySize(workbenchInv, 4);
		this.workbenchInv = workbenchInv;
		this.world = inv.player.world;
		this.data = data;
		
		// Top:    (48,17)
		// Bot:    (48,53)
		// Fuel:   (79,53)
		// Result: (120,35)
		
		addSlot(new IngredientSlot(workbenchInv, INGREDIENT_1_SLOT, 45, 17));
		addSlot(new IngredientSlot(workbenchInv, INGREDIENT_2_SLOT, 45, 53));
		addSlot(new ResultSlot(workbenchInv, RESULT_SLOT, 123, 35));
		addSlot(new FuelSlot(workbenchInv, FUEL_SLOT, 76, 53));
		
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; ++k) {
			addSlot(new Slot(inv, k, 8 + k * 18, 142));
		}
		
		trackIntArray(data);
	}
	
	public boolean canInteractWith(PlayerEntity playerIn) {
		return workbenchInv.isUsableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		// FIXME: Investigate if this works as intended.
		
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
		
		// net.minecraft.inventory.container.WorkbenchContainer
		if(slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(index == RESULT_SLOT) {
				if(!mergeItemStack(itemstack1, 4, 40, true)) {
					return ItemStack.EMPTY;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			} else if(index >= 4 && index < 41) {
				// Inventory click
				//   If is fuel Then Try Put in FUEL_SLOT Then
				//   If inventory Then
				//      If has space. Put in 2, 3
				//      Else Put in Hotbar
				//   Else
				//      If has space Put in 2,3
				//      Else Put in Inventory
				
				if(AbstractFurnaceTileEntity.isFuel(itemstack1)) {
					if(!mergeItemStack(itemstack1, 3, 4, false)) {
						// Could not merge...
					}
				}
				
				if(!mergeItemStack(itemstack1, 0, 2, false)) {
					if(index < 30) {
						if(!mergeItemStack(itemstack1, 31, 40, false)) {
							return ItemStack.EMPTY;
						}
					} else if(!mergeItemStack(itemstack1, 4, 31, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if(!mergeItemStack(itemstack1, 4, 40, false)) {
				return ItemStack.EMPTY;
			}
			
			if(itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			
			if(itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			
			slot.onTake(playerIn, itemstack1);
		}
		
		return itemstack;
	}
	
	public void fillStackedContents(RecipeItemHelper itemHelperIn) {
		if(workbenchInv instanceof IRecipeHelperPopulator) {
			((IRecipeHelperPopulator)workbenchInv).fillStackedContents(itemHelperIn);
		}
	}
	
	protected boolean hasRecipe(ItemStack stack1, ItemStack stack2) {
		return world.getRecipeManager().getRecipe(ModTags.ZEBON_WORKBENCH, new Inventory(stack1, stack2), world).isPresent();
	}
	
	public void clear() {
		workbenchInv.clear();
	}
	
	public boolean matches(IRecipe<? super IInventory> recipeIn) {
		return recipeIn.matches(workbenchInv, world);
	}
	
	public int getOutputSlot() {
		return RESULT_SLOT;
	}
	
	public int getWidth() {
		return 1;
	}
	
	public int getHeight() {
		return 2;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getSize() {
		return 4;
	}
	
	@OnlyIn(Dist.CLIENT)
	public RecipeBookCategory func_241850_m() {
		return RecipeBookCategory.FURNACE;
	}
	
	public List<RecipeBookCategories> getRecipeBookCategories() {
		return RECIPE_CATEGORIES;
	}
	
	@SuppressWarnings("unchecked")
	public void func_217056_a(boolean placeAll, IRecipe<?> recipe, ServerPlayerEntity player) {
		(new ServerRecipePlacerZebonWorkbench<>(this)).place(player, (IRecipe<IInventory>)recipe, placeAll);
	}
	
	@OnlyIn(Dist.CLIENT)
	protected boolean isBurning() {
		return data.get(ZebonWorkbenchTileEntity.BURN_TIME) > 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	protected int getTotalCraftTime() {
		return data.get(ZebonWorkbenchTileEntity.TOTAL_CRAFT_TIME);
	}
	
	@OnlyIn(Dist.CLIENT)
	protected int getCraftTime() {
		return data.get(ZebonWorkbenchTileEntity.CRAFT_TIME);
	}
	
	@OnlyIn(Dist.CLIENT)
	protected int getBurnTime() {
		return data.get(ZebonWorkbenchTileEntity.BURN_TIME);
	}
	
	@OnlyIn(Dist.CLIENT)
	protected int getTotalBurnTime() {
		return data.get(ZebonWorkbenchTileEntity.TOTAL_BURN_TIME);
	}
	
	static class FuelSlot extends Slot {
		public FuelSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		public boolean isItemValid(ItemStack stack) {
			return AbstractFurnaceTileEntity.isFuel(stack);
		}
	}
	
	static class ResultSlot extends Slot {
		public ResultSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		public boolean isItemValid(ItemStack stack) {
			return false;
		}
	}
	
	static class IngredientSlot extends Slot {
		public IngredientSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		public boolean isItemValid(ItemStack stack) {
			return true;
		}
	}
}
