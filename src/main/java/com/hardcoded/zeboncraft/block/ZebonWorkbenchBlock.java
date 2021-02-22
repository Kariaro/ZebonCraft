package com.hardcoded.zeboncraft.block;

import com.hardcoded.zeboncraft.container.ZebonWorkbenchContainer;
import com.hardcoded.zeboncraft.tileentity.ZebonWorkbenchTileEntity;
import com.hardcoded.zeboncraft.utility.ModStats;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ZebonWorkbenchBlock extends Block {
	private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.zebon_workbench");
	
	public ZebonWorkbenchBlock(Properties properties) {
		super(properties);
	}
	
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(worldIn.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if(tileentity instanceof ZebonWorkbenchTileEntity) {
				player.openContainer((ZebonWorkbenchTileEntity)tileentity);
				player.addStat(ModStats.INTERACT_WITH_ZEBON_WORKBENCH);
			}
			
			return ActionResultType.CONSUME;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if(!state.isIn(newState.getBlock())) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			
			if(tileentity instanceof ZebonWorkbenchTileEntity) {
				InventoryHelper.dropInventoryItems(worldIn, pos, (ZebonWorkbenchTileEntity)tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
			
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ZebonWorkbenchTileEntity();
	}
	
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		return new SimpleNamedContainerProvider((id, inventory, player) -> {
			return new ZebonWorkbenchContainer(id, inventory);
		}, CONTAINER_NAME);
	}
}
