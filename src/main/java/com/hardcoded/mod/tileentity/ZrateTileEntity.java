package com.hardcoded.mod.tileentity;

import com.hardcoded.utility.ModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

public class ZrateTileEntity extends TileEntity {
	
	public ZrateTileEntity() {
		super(ModTileEntities.ZRATE);
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		return super.write(compound);
	}
	
	
}
