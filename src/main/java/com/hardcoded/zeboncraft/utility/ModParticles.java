package com.hardcoded.zeboncraft.utility;

import java.util.function.Supplier;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;

public class ModParticles {
	public static final RegistryObject<BasicParticleType> ZEBON_ORE_PARTICLE = register("zebon_ore_particle",
			() -> new BasicParticleType(true));
	
	public static final RegistryObject<BasicParticleType> ZEBON_MUSHROOM_PARTICLE = register("zebon_mushroom_particle",
			() -> new BasicParticleType(true));
	
	static void register() {
		
	}
	
	private static <T extends ParticleType<?>> RegistryObject<T> register(String name, Supplier<T> supplier) {
		return Registration.PARTICLE_TYPES.register(name, supplier);
	}
}
