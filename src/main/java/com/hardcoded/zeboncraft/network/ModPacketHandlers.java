package com.hardcoded.zeboncraft.network;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.network.client.FungusDataPacket;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModPacketHandlers {
	private static final String PROTOCOL_VERSION = "1";
	
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
		new ResourceLocation(ZebonCraft.MOD_ID, "fungus_channel"),
		() -> PROTOCOL_VERSION,
		PROTOCOL_VERSION::equals,
		PROTOCOL_VERSION::equals
	);
	
	public static void register() {
		INSTANCE.registerMessage(0, FungusDataPacket.class, FungusDataPacket::encode, FungusDataPacket::decode, FungusDataPacket::handle);
	}
	
	public static void sendToPlayer(Object packet, ServerPlayerEntity entity) {
		if(entity != null) {
			INSTANCE.sendTo(packet, entity.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
}
