package com.hardcoded.zeboncraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ZrateBlock extends Block {
	private static final VoxelShape FULL_SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
	
	public ZrateBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return FULL_SHAPE;
	}
}
