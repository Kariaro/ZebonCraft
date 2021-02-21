package com.hardcoded.zeboncraft.data;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.data.client.ModBlockModelProvider;
import com.hardcoded.zeboncraft.data.client.ModBlockStateProvider;
import com.hardcoded.zeboncraft.data.client.ModItemModelProvider;
import com.hardcoded.zeboncraft.data.server.*;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ZebonCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
	private DataGenerators() {}
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper file = event.getExistingFileHelper();
	
		if(event.includeClient()) {
			gen.addProvider(new ModBlockModelProvider(gen, file));
			gen.addProvider(new ModBlockStateProvider(gen, file));
			gen.addProvider(new ModItemModelProvider(gen, file));
			
			ModBlockTagsProvider mbtp = new ModBlockTagsProvider(gen, file);
			gen.addProvider(mbtp);
			gen.addProvider(new ModItemTagsProvider(gen, mbtp, file));
		}
		
		if(event.includeServer()) {
			gen.addProvider(new ModRecipeProvider(gen));
			gen.addProvider(new ModLootTableProvider(gen));
		}
	}
}
