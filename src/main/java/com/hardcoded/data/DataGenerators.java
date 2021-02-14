package com.hardcoded.data;

import com.hardcoded.data.client.ModBlockStateProvider;
import com.hardcoded.data.client.ModItemModelProvider;
import com.hardcoded.data.server.ModLootTableProvider;
import com.hardcoded.data.server.ModRecipeProvider;
import com.hardcoded.mod.HardcodedMod;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = HardcodedMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
	private DataGenerators() {}
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper file = event.getExistingFileHelper();
		
		if(event.includeClient()) {
			gen.addProvider(new ModBlockStateProvider(gen, file));
			gen.addProvider(new ModItemModelProvider(gen, file));
		}
		
		if(event.includeServer()) {
			gen.addProvider(new ModRecipeProvider(gen));
			gen.addProvider(new ModLootTableProvider(gen));
		}
	}
}
