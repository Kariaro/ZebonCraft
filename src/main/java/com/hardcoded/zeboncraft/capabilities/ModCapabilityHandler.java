package com.hardcoded.zeboncraft.capabilities;

import com.hardcoded.zeboncraft.ZebonCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ZebonCraft.MOD_ID, bus = Bus.FORGE)
public class ModCapabilityHandler {
	@SubscribeEvent
	public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof PlayerEntity) {
			event.addCapability(new ResourceLocation(ZebonCraft.MOD_ID, "fungus_data"), new ModDataProvider());
		}
	}
}
