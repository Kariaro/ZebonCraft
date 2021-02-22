package com.hardcoded.zeboncraft.network.client;

import java.util.UUID;
import java.util.function.Supplier;

import com.hardcoded.zeboncraft.capabilities.IFungusData;
import com.hardcoded.zeboncraft.utility.ModCapabilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class FungusDataPacket {
	private UUID id;
	private float infectedHearts;
	
	public FungusDataPacket(UUID id, float infectedHearts) {
		this.id = id;
		this.infectedHearts = infectedHearts;
	}
	
	public static void encode(FungusDataPacket msg, PacketBuffer outBuffer) {
		outBuffer.writeUniqueId(msg.id);
		outBuffer.writeFloat(msg.infectedHearts);
	}
	
	public static FungusDataPacket decode(PacketBuffer inBuffer) {
		UUID id = inBuffer.readUniqueId();
		float infectedHearts = inBuffer.readFloat();
		
		return new FungusDataPacket(id, infectedHearts);
	}
	
	public static void handle(FungusDataPacket msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			@SuppressWarnings("resource")
			PlayerEntity entity = Minecraft.getInstance().player;
			
			if(entity == null) return;
			IFungusData data = entity.getCapability(ModCapabilities.FUNGUS_DATA).orElse(null);
			
			if(data != null) {
				data.setInfectedHearts(msg.infectedHearts);
			}
		});
		ctx.get().setPacketHandled(true);
	}
}
