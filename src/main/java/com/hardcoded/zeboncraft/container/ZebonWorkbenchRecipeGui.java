package com.hardcoded.zeboncraft.container;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZebonWorkbenchRecipeGui extends RecipeBookGui {
	private static final ITextComponent text = new TranslationTextComponent("gui.recipebook.toggleRecipes.zebon_workbench");
	
	@SuppressWarnings("deprecation")
	private final Set<Item> ghostFuels = AbstractFurnaceTileEntity.getBurnTimes().keySet();
	private Iterator<Item> ghostFuelIterator;
	private Item currentGhostFuelItem;
	private float ghostFuelTime;
	protected Slot ghostFuelSlot;
	
	public ZebonWorkbenchRecipeGui() {
		
	}
	
	public void slotClicked(Slot slotIn) {
		if(slotIn != null && slotIn.slotNumber < 4) {
			ghostFuelSlot = null;
		}
		
		super.slotClicked(slotIn);
	}
	
	public void setupGhostRecipe(IRecipe<?> recipe, List<Slot> slots) {
		ghostRecipe.setRecipe(recipe);
		
		{
			ItemStack recipeResult = recipe.getRecipeOutput();
			Slot resultSlot = slots.get(ZebonWorkbenchContainer.RESULT_SLOT);
			ghostRecipe.addIngredient(Ingredient.fromStacks(recipeResult), resultSlot.xPos, resultSlot.yPos);
			
			NonNullList<Ingredient> list = recipe.getIngredients();
			ghostRecipe.addIngredient(list.get(0), slots.get(0).xPos, slots.get(0).yPos);
			ghostRecipe.addIngredient(list.get(1), slots.get(1).xPos, slots.get(1).yPos);
		}
		
		ghostFuelSlot = slots.get(ZebonWorkbenchContainer.FUEL_SLOT);
		ghostFuelIterator = ghostFuels.iterator();
		currentGhostFuelItem = null;
	}
	
	public void initSearchBar(boolean widthTooNarrowIn) {
		super.initSearchBar(widthTooNarrowIn);
		
		if(recipeTabs != null)
			recipeTabs.clear();
	}
	
	public void func_230477_a_(MatrixStack stack, int x, int y, boolean isResultSlotZero, float deltaTime) {
		super.func_230477_a_(stack, x, y, isResultSlotZero, deltaTime);
		
		if(ghostRecipe == null || ghostRecipe.getRecipe() == null) {
			ghostFuelSlot = null;
		}
		
		if(ghostFuelSlot != null) {
			if(!Screen.hasControlDown()) {
				ghostFuelTime += deltaTime;
			}
			
			if(!ghostFuelSlot.getHasStack()) {
				int i = ghostFuelSlot.xPos + x;
				int j = ghostFuelSlot.yPos + y;
				AbstractGui.fill(stack, i, j, i + 16, j + 16, 0x30FF0000);
				this.mc.getItemRenderer().renderItemAndEffectIntoGUI(mc.player, getFuelGhostIcon().getDefaultInstance(), i, j);
				RenderSystem.depthFunc(516);
				AbstractGui.fill(stack, i, j, i + 16, j + 16, 0x30FFFFFF);
				RenderSystem.depthFunc(515);
			}
		}
	}
	
	public ITextComponent func_230479_g_() {
		return text;
	}
	
	private Item getFuelGhostIcon() {
		if(currentGhostFuelItem == null || ghostFuelTime > 30.0F) {
			ghostFuelTime = 0.0F;
			if(ghostFuelIterator == null || !ghostFuelIterator.hasNext()) {
				ghostFuelIterator = ghostFuels.iterator();
			}
			currentGhostFuelItem = ghostFuelIterator.next();
		}
		return currentGhostFuelItem;
	}
}
