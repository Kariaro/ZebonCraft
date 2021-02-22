package com.hardcoded.zeboncraft.capabilities;

import com.hardcoded.zeboncraft.utility.ModCapabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ModDataProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {
	private final IFungusData value = ModCapabilities.FUNGUS_DATA.getDefaultInstance();
	private final LazyOptional<IFungusData> optional = LazyOptional.of(() -> value);
	
	@Override
	public CompoundNBT serializeNBT() {
		return value.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		value.deserializeNBT(nbt);
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == ModCapabilities.FUNGUS_DATA ? optional.cast() : LazyOptional.empty();
	}
}
