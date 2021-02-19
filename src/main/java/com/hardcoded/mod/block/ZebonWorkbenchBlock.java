package com.hardcoded.mod.block;

import com.hardcoded.mod.container.ZebonWorkbenchContainer;
import com.hardcoded.mod.tileentity.ZebonWorkbenchTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
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
				// FIXME: Add custom statistics
				player.openContainer((ZebonWorkbenchTileEntity)tileentity);
				//player.addStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			}
			
			return ActionResultType.CONSUME;
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
