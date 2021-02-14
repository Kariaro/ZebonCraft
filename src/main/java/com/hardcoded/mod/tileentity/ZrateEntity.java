package com.hardcoded.mod.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

public class ZrateEntity extends Entity {
	
	public ZrateEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	protected void registerData() {
		
	}
	
	protected void readAdditional(CompoundNBT compound) {
		
	}
	
	protected void writeAdditional(CompoundNBT compound) {
		
	}
	
	public IPacket<?> createSpawnPacket() {
		return null;
	}

}
