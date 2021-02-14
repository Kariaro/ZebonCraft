package com.hardcoded.mod.tileentity;

import com.hardcoded.mod.HardcodedMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZrateTileEntityRenderer extends TileEntityRenderer<ZrateTileEntity> {
	private final ZrateModel model;
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(HardcodedMod.MOD_ID, "textures/entity/zrate_tile_entity.png");
	
	public ZrateTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn, ZrateModel model) {
		super(rendererDispatcherIn);
		this.model = model;
	}
	
	@Override
	public void render(ZrateTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		
		matrixStackIn.push();
		matrixStackIn.translate(0.5, 0.5, 0.5);
		matrixStackIn.scale(0.9995f, 0.9995f, 0.9995f);
		//matrixStackIn.rotate(direction.getRotation());
		matrixStackIn.scale(1.0f, -1.0f, -1.0f);
		matrixStackIn.translate(0.0, -1.0, 0.0);
		
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getCutout());
		this.model.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn);
		
		matrixStackIn.pop();
	}
}
