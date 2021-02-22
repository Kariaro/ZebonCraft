package com.hardcoded.zeboncraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Copied from https://github.com/sekwah41/Naruto-Mod
 * 
 * @author Sekwah41 <https://github.com/sekwah41/Naruto-Mod>
 *
 * @param <T>
 * @param <V>
 */
public class NBTCapabilityStorage<T extends INBT, V extends INBTSerializable<T>> implements Capability.IStorage<V> {
	private final Class<T> clazz;
	
	private NBTCapabilityStorage(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public static <V extends INBT, W extends INBTSerializable<V>> NBTCapabilityStorage<V, W> create(Class<V> tClass) {
		return new NBTCapabilityStorage<>(tClass);
	}
	
	public static <V extends INBTSerializable<CompoundNBT>> NBTCapabilityStorage<CompoundNBT, V> create() {
		return new NBTCapabilityStorage<>(CompoundNBT.class);
	}
	
	@Override
	public INBT writeNBT(Capability<V> capability, V instance, Direction side) {
		return instance.serializeNBT();
	}
	
	@Override
	public void readNBT(Capability<V> capability, V instance, Direction side, INBT nbt) {
		if(clazz.isInstance(nbt)) {
			instance.deserializeNBT(clazz.cast(nbt));
		}
	}
}