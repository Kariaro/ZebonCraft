package com.hardcoded.mod.recipe.render;

import java.util.List;

import com.hardcoded.mod.recipe.ZebonWorkbenchRecipe;
import com.hardcoded.utility.ModTags;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.RecipeTabToggleWidget;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.util.ClientRecipeBook;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomRecipeTabToggleWidget extends RecipeTabToggleWidget {
	private final ItemStack[] icons;
	private float animationTime;
	
	public CustomRecipeTabToggleWidget(ItemStack icon) {
		super(RecipeBookCategories.UNKNOWN);
		this.icons = new ItemStack[] { icon.copy() };
	}
	
	public CustomRecipeTabToggleWidget(ItemStack icon1, ItemStack icon2) {
		super(RecipeBookCategories.UNKNOWN);
		this.icons = new ItemStack[] { icon1.copy(), icon2.copy() };
	}
	
	public void startAnimation(Minecraft game) {
		if(game.player.openContainer instanceof RecipeBookContainer) {
			ClientRecipeBook clientrecipebook = game.player.getRecipeBook();
			List<ZebonWorkbenchRecipe> recipes = game.player.world.getRecipeManager().getRecipesForType(ModTags.ZEBON_WORKBENCH);
			
			for(ZebonWorkbenchRecipe recipe : recipes) {
				if(clientrecipebook.isNew(recipe)) {
					animationTime = 15.0F;
					return;
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		if(animationTime > 0) {
			float f = 1.0F + 0.1F * (float)Math.sin(animationTime / 15.0F * Math.PI);
			RenderSystem.pushMatrix();
			RenderSystem.translatef(x + 8, y + 12, 0);
			RenderSystem.scalef(1, f, 1);
			RenderSystem.translatef(-(x + 8), -(y + 12), 0);
		}

		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bindTexture(resourceLocation);
		RenderSystem.disableDepthTest();
		
		int i = xTexStart + (stateTriggered ? xDiffTex:0);
		int j = yTexStart + (isHovered() ? yDiffTex:0);
		int k = x - (stateTriggered ? 2:0);
		
		RenderSystem.color4f(1, 1, 1, 1);
		this.blit(matrixStack, k, y, i, j, width, height);
		RenderSystem.enableDepthTest();
		renderIcon(minecraft.getItemRenderer());
		
		if(animationTime > 0.0F) {
			RenderSystem.popMatrix();
			animationTime -= partialTicks;
		}
	}
	
	private void renderIcon(ItemRenderer renderer) {
		int i = this.stateTriggered ? -2 : 0;
		
		if(icons.length == 1) {
			renderer.renderItemAndEffectIntoGuiWithoutEntity(icons[0], x + 9 + i, y + 5);
		} else if(icons.length == 2) {
			renderer.renderItemAndEffectIntoGuiWithoutEntity(icons[0], x + 3 + i, y + 5);
			renderer.renderItemAndEffectIntoGuiWithoutEntity(icons[1], x + 14 + i, y + 5);
		}
	}
}
