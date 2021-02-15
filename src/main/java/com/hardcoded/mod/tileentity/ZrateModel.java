package com.hardcoded.mod.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ZrateModel extends EntityModel<ZrateEntity> {
	private final ModelRenderer body;
	
	public ZrateModel() {
		body = new ModelRenderer(16, 16, 0, 0);
		body.setRotationPoint(8, 8, 8);
		body.addBox(0, 0, 0, 16, 16, 16);
	}
	
	public void setRotationAngles(ZrateEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		
	}
	
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		
	}
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) {
		render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, 0, 0, 0, 0);
	}
}
