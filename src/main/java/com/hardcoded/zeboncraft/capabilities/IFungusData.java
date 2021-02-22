package com.hardcoded.zeboncraft.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IFungusData extends INBTSerializable<CompoundNBT> {
	/**
	 * Get the current amount of infected hearts.
	 * @return the current amount of infected hearts
	 */
	float getInfectedHearts();
	
	/**
	 * Change the amount of hearts that are infected.
	 * @param amount
	 */
	void setInfectedHearts(float amount);
}
