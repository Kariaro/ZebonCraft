package com.hardcoded.zeboncraft.block;

import java.util.List;
import java.util.Random;

import com.hardcoded.zeboncraft.capabilities.IFungusData;
import com.hardcoded.zeboncraft.network.ModPacketHandlers;
import com.hardcoded.zeboncraft.network.client.FungusDataPacket;
import com.hardcoded.zeboncraft.utility.*;

import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// TODO: Add config file that can dissable the spores effect
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
	
	@SuppressWarnings("deprecation")
	// TODO: In 1.17.x remove this .isAir and replace it by the new method
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return !worldIn.getBlockState(pos).isAir();
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
	
	// TODO: This should fire every tick....
	// FIXME: Possible bug when the player leave the game and does not get the fungus data back
	// FIXME: When the player moves this gets called more often.
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if(!worldIn.isRemote && harmfull) {
			if(ZebonConfig.hurtfullMushroomsDealDamage) {
				if(entityIn instanceof LivingEntity) {
					((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 30, 2));
				}
			}
			
			if(ZebonConfig.enableInfectedHearts) {
				if(entityIn instanceof PlayerEntity) {
					PlayerEntity entity = (PlayerEntity)entityIn;
					
					if(!isInvulerable(entity)) {
						infectPlayer(entity);
					}
				}
			}
		}
	}
	
	// If the player had absorption and got infected. half a heart of that absorption will get consumed
	private boolean isInvulerable(PlayerEntity e) {
		if(e.isPotionActive(ModEffects.ANTIDOTE.get())) return true;
		
		Iterable<ItemStack> iter = e.getArmorInventoryList();
		if(!(iter instanceof List)) return false;
		
		List<ItemStack> list = (List<ItemStack>)iter;
		if(list.size() < 4) return false;
		
		if(EnchantmentHelper.getEnchantmentLevel(ModEnchantments.AIR_FILTER.get(), list.get(3)) != 0) {
			return true;
		}
		
		return false;
	}
	
	private void infectPlayer(PlayerEntity entity) {
		entity.addPotionEffect(new EffectInstance(ModEffects.SPORES.get(), 50, 0, true, true));
		
		IFungusData data = entity.getCapability(ModCapabilities.FUNGUS_DATA).orElse(null);
		if(data != null) {
			data.setInfectedHearts(data.getInfectedHearts() + 0.05f);
			
			if(data.getInfectedHearts() > 20) {
				data.setInfectedHearts(0);
			}
			
			if(entity instanceof ServerPlayerEntity) {
				ModPacketHandlers.sendToPlayer(new FungusDataPacket(entity.getUniqueID(), data.getInfectedHearts()), (ServerPlayerEntity)entity);
			}
		}
	}
	
}
