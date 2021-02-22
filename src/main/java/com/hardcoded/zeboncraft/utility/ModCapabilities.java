package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.capabilities.FungusData;
import com.hardcoded.zeboncraft.capabilities.IFungusData;
import com.hardcoded.zeboncraft.capabilities.NBTCapabilityStorage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {
	@CapabilityInject(IFungusData.class)
	public static final Capability<IFungusData> FUNGUS_DATA = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IFungusData.class, NBTCapabilityStorage.create(CompoundNBT.class), FungusData::new);
	}
}
