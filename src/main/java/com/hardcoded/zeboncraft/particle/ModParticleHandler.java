package com.hardcoded.zeboncraft.particle;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.utility.ModParticles;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ZebonCraft.MOD_ID, bus = Bus.MOD)
public class ModParticleHandler {
	
	@SuppressWarnings("resource")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticles(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(ModParticles.ZEBON_ORE_PARTICLE.get(), ZebonParticle.Factory::new);
	}
}
