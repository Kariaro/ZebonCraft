package com.hardcoded.zeboncraft.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ZebonPoweredRailBlock extends PoweredRailBlock {
	public ZebonPoweredRailBlock(AbstractBlock.Properties builder) {
		super(builder, true);
	}
	
	@Override
	public float getRailMaxSpeed(BlockState state, World world, BlockPos pos, AbstractMinecartEntity cart) {
		return 0.9f;
	}
}
