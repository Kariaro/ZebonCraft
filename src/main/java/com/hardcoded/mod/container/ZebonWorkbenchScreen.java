package com.hardcoded.mod.container;

import com.hardcoded.mod.HardcodedMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZebonWorkbenchScreen extends ContainerScreen<ZebonWorkbenchContainer> implements IRecipeShownListener {
	private static final ResourceLocation ZEBON_WORKBENCH_GUI_TEXTURES = new ResourceLocation(HardcodedMod.MOD_ID, "textures/gui/container/zebon_workbench.png");
	private static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation("minecraft", "textures/gui/recipe_button.png");
	public final ZebonWorkbenchRecipeGui recipeGui;
	private boolean widthTooNarrowIn;
	
	public ZebonWorkbenchScreen(ZebonWorkbenchContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.recipeGui = new ZebonWorkbenchRecipeGui();
	}
	
	@Override
	protected void init() {
		super.init();
		
		widthTooNarrowIn = width < 379;
		recipeGui.init(width, height, minecraft, widthTooNarrowIn, container);
		guiLeft = recipeGui.updateScreenPosition(widthTooNarrowIn, width, xSize);
		addButton(new ImageButton(guiLeft + 20, height / 2 - 49, 20, 18, 0, 0, 19, BUTTON_TEXTURE, (button) -> {
			recipeGui.initSearchBar(this.widthTooNarrowIn);
			recipeGui.toggleVisibility();
			
			guiLeft = recipeGui.updateScreenPosition(widthTooNarrowIn, width, xSize);
			((ImageButton)button).setPosition(guiLeft + 20, height / 2 - 49);
		}));
		
		titleX = (xSize - font.getStringPropertyWidth(title)) / 2;
	}
	
	public void tick() {
		super.tick();
		recipeGui.tick();
	}
	
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrixStack);
		if(recipeGui.isVisible() && widthTooNarrowIn) {
			drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);
			recipeGui.render(matrixStack, mouseX, mouseY, partialTicks);
		} else {
			recipeGui.render(matrixStack, mouseX, mouseY, partialTicks);
			super.render(matrixStack, mouseX, mouseY, partialTicks);
			recipeGui.func_230477_a_(matrixStack, guiLeft, guiTop, true, partialTicks);
		}
		
		renderHoveredTooltip(matrixStack, mouseX, mouseY);
		recipeGui.func_238924_c_(matrixStack, guiLeft, guiTop, mouseX, mouseY);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		minecraft.getTextureManager().bindTexture(ZEBON_WORKBENCH_GUI_TEXTURES);
		int i = guiLeft;
		int j = (height - ySize) / 2;
		blit(matrixStack, i, j, 0, 0, xSize, ySize);
		
		if(container.isBurning()) {
			int total_burn_time = container.getTotalBurnTime();
			float v0 = (container.getBurnTime() + 1) / (total_burn_time + 0.0f);
			int k = (int)(v0 * 12);
			
			if(k > 12) k = 12;
			blit(matrixStack, i + 77, j + 35 + 12 - k, 176, 12 - k, 14, k + 1);
		}
		
		if(container.getCraftTime() > 0) {
			int arrow_1 = 0;
			int arrow_2 = 0;
			if(container.getTotalCraftTime() < 2) {
				arrow_1 = 24;
				arrow_2 = 22;
			} else {
				float v1 = (container.getCraftTime() + 1) / (container.getTotalCraftTime() + 0.0f);
				arrow_1 = Math.min((int)(63 * v1), 24);      // 24 pixels
				arrow_2 = Math.min((int)(63 * v1) - 41, 22); // 22 pixels
				if(arrow_2 < 0) arrow_2 = 0;
			}
			
			blit(matrixStack, i + 51, j + 35, 176, 14, arrow_1, 15);
			blit(matrixStack, i + 92, j + 38, 176, 30, arrow_2, 8);
		}
	}
	
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if(recipeGui.mouseClicked(mouseX, mouseY, button)) {
			return true;
		} else {
			return widthTooNarrowIn && recipeGui.isVisible() ? true : super.mouseClicked(mouseX, mouseY, button);
		}
	}
	
	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
		super.handleMouseClick(slotIn, slotId, mouseButton, type);
		recipeGui.slotClicked(slotIn);
	}

	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		return recipeGui.keyPressed(keyCode, scanCode, modifiers) ? false : super.keyPressed(keyCode, scanCode, modifiers);
	}
	
	protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
		boolean flag = mouseX < guiLeftIn || mouseY < guiTopIn || mouseX >= (guiLeftIn + xSize) || mouseY >= (guiTopIn + ySize);
		return recipeGui.func_195604_a(mouseX, mouseY, guiLeft, guiTop, xSize, ySize, mouseButton) && flag;
	}

	public boolean charTyped(char codePoint, int modifiers) {
		return recipeGui.charTyped(codePoint, modifiers) ? true : super.charTyped(codePoint, modifiers);
	}
	
	public void recipesUpdated() {
		recipeGui.recipesUpdated();
	}
	
	public RecipeBookGui getRecipeGui() {
		return recipeGui;
	}
	
	public void onClose() {
		recipeGui.removed();
		super.onClose();
	}
}
