package com.hardcoded.zeboncraft.capabilities;

import net.minecraft.nbt.CompoundNBT;

public class FungusData implements IFungusData {
	public float infectedHearts;

	@Override
	public float getInfectedHearts() {
		return infectedHearts;
	}

	@Override
	public void setInfectedHearts(float amount) {
		this.infectedHearts = amount;
	}
	
	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putFloat("infectedHearts", infectedHearts);
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		this.infectedHearts = nbt.getFloat("infectedHearts");
	}

}
