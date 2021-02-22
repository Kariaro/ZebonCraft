package com.hardcoded.zeboncraft.client.gui;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.capabilities.IFungusData;
import com.hardcoded.zeboncraft.utility.ModCapabilities;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = ZebonCraft.MOD_ID, bus = Bus.MOD)
public class FungusHeartGuiOverlay {
	private static final ResourceLocation VANILLA_ICONS = new ResourceLocation("minecraft:textures/gui/icons.png");
	private static final ResourceLocation ICON_HEARTS = new ResourceLocation(ZebonCraft.MOD_ID, "textures/gui/icons.png");
	
	private final Minecraft mc = Minecraft.getInstance();
	
	private void blit(MatrixStack stack, int x, int y, int tx, int ty, int width, int height) {
		mc.ingameGUI.blit(stack, x, y, tx, ty, width, height);
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void renderHealthBar(RenderGameOverlayEvent.Post event) {
		// Does not work as intended yet...
		if(true) return;
		
		Entity entity = mc.getRenderViewEntity();
		if(event.getType() != ElementType.HEALTH || event.isCanceled() || !(entity instanceof PlayerEntity)) return;
		
		IFungusData data = entity.getCapability(ModCapabilities.FUNGUS_DATA).orElse(null);
		if(data == null) return;
		
//		float test = (float)(((System.currentTimeMillis() % 3600) / 1800.0) * Math.PI);
//		test = (float)Math.sin(test) + 1;
//		data.setInfectedHearts(test * 10 + 1);
		
		mc.getTextureManager().bindTexture(ICON_HEARTS);
		renderInfectedHearts(event.getMatrixStack(), 0, 0, (PlayerEntity)entity, data);
		mc.getTextureManager().bindTexture(VANILLA_ICONS);
	}
	
	private void renderInfectedHearts(MatrixStack stack, int x, int y, PlayerEntity player, IFungusData data) {
		int sw = mc.getMainWindow().getScaledWidth();
		int sh = mc.getMainWindow().getScaledHeight();
		float infected = data.getInfectedHearts() / 2.0f;
		infected = (int)(infected * 2) / 2.0f;
		int xp = sw / 2 - 91;
		int yp = sh - 39;
		
		int wn = (int)Math.ceil(9 - infected);
		for(int i = 9; i > wn; i--) {
			blit(stack, xp + i * 8, yp, 9 * 3, 0, 9, 9);
		}
		
		float fraction = infected + wn - 9;
		
		if(fraction > 1 / 9.0) {
			int width = (int)Math.ceil(fraction * 9 - 1);
			blit(stack, xp + wn * 8 + 9 - width, yp, 9 * 3 + 9 - width, 0, width, 9);
		}
	}
}
