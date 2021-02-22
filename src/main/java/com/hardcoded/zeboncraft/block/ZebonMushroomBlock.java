package com.hardcoded.zeboncraft.block;

import java.util.List;
import java.util.Random;

import com.hardcoded.zeboncraft.utility.ModEffects;
import com.hardcoded.zeboncraft.utility.ModEnchantments;
import com.hardcoded.zeboncraft.utility.ModParticles;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZebonMushroomBlock extends BushBlock implements IGrowable {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	private final boolean harmfull;
	
	public ZebonMushroomBlock(Properties properties, boolean harmfull) {
		super(properties);
		this.harmfull = harmfull;
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if(harmfull) {
			if(rand.nextFloat() < 0.3) {
				double x = pos.getX() + 0.5;
				double y = pos.getY() + 0.5;
				double z = pos.getZ() + 0.5;
				worldIn.addParticle(ModParticles.ZEBON_MUSHROOM_PARTICLE.get(), x, y, z, 0.0, 0.0, 0.0);
			}
		}
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return true;
	}
	
	public boolean isHarmfull() {
		return harmfull;
	}
	
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return false;
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return false;
	}
	
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
	}
	
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(!worldIn.isRemote) {
			List<PlayerEntity> entity = worldIn.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB(pos).grow(4));
			
			for(PlayerEntity e : entity) {
				tryInfectPlayer(e);
			}
		}
	}
	
	
	// If the player had absorption and got infected. half a heart of that absorption will get consumed
	private void tryInfectPlayer(PlayerEntity e) {
		if(e.isPotionActive(ModEffects.ANTIDOTE.get())) return;
		
		Iterable<ItemStack> iter = e.getArmorInventoryList();
		if(!(iter instanceof List)) return;
		
		List<ItemStack> list = (List<ItemStack>)iter;
		if(list.size() < 4) return;
		
		if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.AIR_FILTER.get(), list.get(3)) == 0) {
			infectPlayer(e);
		}
	}
	
	private void infectPlayer(PlayerEntity e) {
		e.addPotionEffect(new EffectInstance(ModEffects.SPORES.get(), 200, 0, true, true));
	}
}
