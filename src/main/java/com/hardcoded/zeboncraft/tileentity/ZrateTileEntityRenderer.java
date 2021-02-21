package com.hardcoded.zeboncraft.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZrateTileEntityRenderer extends TileEntityRenderer<ZrateTileEntity> {
	@SuppressWarnings("unused")
	private final ZrateModel model;
	
	//protected static final ResourceLocation TEXTURE = new ResourceLocation(HardcodedMod.MOD_ID, "textures/entity/zrate_tile_entity.png");
	
	public ZrateTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		this.model = new ZrateModel();
	}
	
	@Override
	public void render(ZrateTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		
		matrixStackIn.push();
		//matrixStackIn.translate(-scale / 2.0, -scale / 2.0, -scale / 2.0);
		//matrixStackIn.translate(-0.5, -0.5, -0.5);
		//float scale = 1.2f;
		//matrixStackIn.scale(scale, scale, scale);
		
		//IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getCutout());
		//this.model.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		
		matrixStackIn.pop();
	}
}
