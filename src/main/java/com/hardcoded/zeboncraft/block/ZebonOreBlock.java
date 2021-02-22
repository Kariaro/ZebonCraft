package com.hardcoded.zeboncraft.block;

import java.util.Random;

import com.hardcoded.zeboncraft.utility.ModParticles;

import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZebonOreBlock extends OreBlock {

	public ZebonOreBlock(Properties properties) {
		super(properties);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(rand.nextFloat() < 0.1) {
			double x = pos.getX() + 0.5;
			double y = pos.getY() + 0.5;
			double z = pos.getZ() + 0.5;
			worldIn.addParticle(ModParticles.ZEBON_ORE_PARTICLE.get(), x, y, z, 0.0, 0.0, 0.0);
		}
	}
	
	protected int getExperience(Random rand) {
		return 0;
	}
}
