package com.hardcoded.mod;

import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hardcoded.mod.enchantment.ZnchantListener;
import com.hardcoded.mod.item.ZebonSword;
import com.hardcoded.mod.tileentity.ZrateTileEntityRenderer;
import com.hardcoded.utility.*;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// ZbonCraft
@Mod(HardcodedMod.MOD_ID)
public class HardcodedMod {
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final String MOD_ID = "hardcodedmod";
	
	public HardcodedMod() {
		Registration.register();
		
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
	
	@SuppressWarnings("resource")
	private void doClientStuff(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
		
		RenderTypeLookup.setRenderLayer(ModBlocks.ZRASS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.ZAPPLING.get(), RenderType.getCutout());
		
		MinecraftForge.EVENT_BUS.register(new ZnchantListener());
		
		ItemModelsProperties.registerProperty(ModItems.ZEBON_ZVORD.get(), new ResourceLocation(MOD_ID, "charging"), (itemStack, world, entity) -> {
			return ZebonSword.isCharged(itemStack) ? 1:0;
		});
		
		ItemModelsProperties.registerProperty(ModItems.ZEBON_ZVORD.get(), new ResourceLocation(MOD_ID, "charge"), (itemStack, world, entity) -> {
			return entity != null && entity.isHandActive() && itemStack == entity.getActiveItemStack() ? (itemStack.getUseDuration() - entity.getItemInUseCount()) / 20.0f : 0;
		});
		
		RenderTypeLookup.setRenderLayer(ModTileEntities.zrate_block, RenderType.getCutoutMipped());
		ClientRegistry.bindTileEntityRenderer(ModTileEntities.ZRATE, ZrateTileEntityRenderer::new);
	}
	
	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
		InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}
	
	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}", event.getIMCStream().map(m->m.getMessageSupplier().get()).collect(Collectors.toList()));
	}
	
	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}

	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// register a new block here
			LOGGER.info("HELLO from Register Block");
		}
	}
}
