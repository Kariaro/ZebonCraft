package com.hardcoded.zeboncraft.block;

import java.util.Random;

import com.hardcoded.zeboncraft.utility.ModBlocks;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ZrassBlock extends Block implements IGrowable {

	public ZrassBlock(Properties properties) {
		super(properties);
	}
	
	@SuppressWarnings("deprecation")
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return worldIn.getBlockState(pos.up()).isAir();
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}
	
	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
//		tryInfect(worldIn, rand, pos.east());
//		tryInfect(worldIn, rand, pos.west());
//		tryInfect(worldIn, rand, pos.north());
//		tryInfect(worldIn, rand, pos.south());
//		tryInfect(worldIn, rand, pos.up());
//		tryInfect(worldIn, rand, pos.down());
		BlockState zrassstate = ModBlocks.ZRASS.get().getDefaultState();
		
		for(int i = 0; i < 64; i++) {
			if(rand.nextDouble() > 0.3) continue;
			
			BlockPos blockpos = pos.add(rand.nextInt(5) - 2, rand.nextInt(5) - 2, rand.nextInt(5) - 2);
			
			if(worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).isIn(ModBlocks.ZRASS_BLOCK.get())) {
				worldIn.setBlockState(blockpos, zrassstate);
			}
		}
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(worldIn.getBlockState(pos.up()).hasOpaqueCollisionShape(worldIn, pos.up())) {
			worldIn.setBlockState(pos, ModBlocks.ZRIRT.get().getDefaultState());
		} else {
			if(worldIn.getLight(pos.up()) >= 9) {
				for(int i = 0; i < 4; i++) {
					BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if(worldIn.getBlockState(blockpos).isIn(ModBlocks.ZRIRT.get()) && !worldIn.getBlockState(blockpos.up()).hasOpaqueCollisionShape(worldIn, blockpos.up())) {
						worldIn.setBlockState(blockpos, getDefaultState());
					}
				}
			}
		}
		
//		tryInfect(worldIn, random, pos.east());
//		tryInfect(worldIn, random, pos.west());
//		tryInfect(worldIn, random, pos.north());
//		tryInfect(worldIn, random, pos.south());
//		tryInfect(worldIn, random, pos.up());
//		tryInfect(worldIn, random, pos.down());
	}
	
//	private void tryInfect(ServerWorld world, Random rand, BlockPos pos) {
//		if(rand.nextDouble() > 0.3) return;
//		
//		BlockState state = world.getBlockState(pos);
//		Block block = state.getBlock();
//		if(block == ModBlocks.ZRIRT.get()) {
//			world.setBlockState(pos, ModBlocks.ZRASS_BLOCK.get().getDefaultState());
//		}
//	}
}
