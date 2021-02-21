package com.hardcoded.zeboncraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hardcoded.zeboncraft.container.ZebonWorkbenchScreen;
import com.hardcoded.zeboncraft.enchantment.ZnchantListener;
import com.hardcoded.zeboncraft.item.ZebonSword;
import com.hardcoded.zeboncraft.tileentity.ZrateTileEntityRenderer;
import com.hardcoded.zeboncraft.utility.*;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// TODO: Make 
@Mod(ZebonCraft.MOD_ID)
public class ZebonCraft {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final String MOD_ID = "zeboncraft";
	public static final String MOD_NAME = "Zebon Craft";
	
	public ZebonCraft() {
		Registration.register();
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		// LOGGER.info("HELLO FROM PREINIT");
		// LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
	}
	
	// Purple dust is a upgraded version of redstone
	
	// @SuppressWarnings("resource")
	private void doClientStuff(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		// LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
		
		ScreenManager.registerFactory(ModContainers.ZEBON_WORKBENCH.get(), ZebonWorkbenchScreen::new);
		RenderTypeLookup.setRenderLayer(ModBlocks.ZRASS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.ZAPPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.ZEBON_POWERED_RAIL.get(), RenderType.getCutout());
		
		MinecraftForge.EVENT_BUS.register(new ZnchantListener());
		
		ItemModelsProperties.registerProperty(ModItems.ZEBON_ZVORD.get(), new ResourceLocation(MOD_ID, "charging"), (itemStack, world, entity) -> {
			return ZebonSword.isCharged(itemStack) ? 1:0;
		});
		
		ItemModelsProperties.registerProperty(ModItems.ZEBON_ZVORD.get(), new ResourceLocation(MOD_ID, "charge"), (itemStack, world, entity) -> {
			return entity != null && entity.isHandActive() && itemStack == entity.getActiveItemStack() ? (itemStack.getUseDuration() - entity.getItemInUseCount()) / 20.0f : 0;
		});
		
		RenderTypeLookup.setRenderLayer(ModBlocks.ZRATE_BLOCK.get(), RenderType.getCutoutMipped());
		ClientRegistry.bindTileEntityRenderer(ModTileEntities.ZRATE.get(), ZrateTileEntityRenderer::new);
	}
	
	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
		// InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}
	
	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		// LOGGER.info("Got IMC {}", event.getIMCStream().map(m->m.getMessageSupplier().get()).collect(Collectors.toList()));
	}
	
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// LOGGER.info("HELLO from server starting");
	}
	
//	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
//	public static class RegistryEvents {
////		@SubscribeEvent
////		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
////			// register a new block here
////			LOGGER.info("HELLO from Register Block");
////		}
//		
//		@SubscribeEvent
//		public static void onMissingMappingBlock(final RegistryEvent.MissingMappings<Block> event) {
//			System.out.println("onMissingMappingBlock?!!!!!!!!: " + event);
//			
//			ImmutableList<Mapping<Block>> list = event.getMappings();
//			for(Mapping<Block> map : list) {
//				ResourceLocation loc = new ResourceLocation(MOD_ID, map.key.getPath());
//				System.out.printf("Map: %s -> %s\n", map.key, loc);
//				
//				Optional<Block> opt = Registration.BLOCKS.getEntries().stream().filter(i -> i.getId().getPath().equals(map.key.getPath())).map(i -> i.get()).findAny();
//				System.out.printf("  --> (%s)\n", opt);
//				
//				if(opt.isPresent()) {
//					map.remap(opt.get());
//				} else {
//					if(loc.getPath().equals("zebronium_ore")) map.remap(ModBlocks.ZEBON_ORE.get());
//					if(loc.getPath().equals("zrass_plant")) map.remap(ModBlocks.ZRASS.get());
//					if(loc.getPath().equals("zebronium_block")) map.remap(ModBlocks.ZEBON_BLOCK.get());
//				}
//			}
//		}
//		
//		@SubscribeEvent
//		public static void onMissingMappingItem(final RegistryEvent.MissingMappings<Item> event) {
//			System.out.println("onMissingMappingItem?!!!!!!!!: " + event);
//			
//			ImmutableList<Mapping<Item>> list = event.getMappings();
//			for(Mapping<Item> map : list) {
//				ResourceLocation loc = new ResourceLocation(MOD_ID, map.key.getPath());
//				System.out.printf("Map: %s -> %s\n", map.key, loc);
//				
//				Optional<Item> opt = Registration.ITEMS.getEntries().stream().filter(i -> i.getId().getPath().equals(map.key.getPath())).map(i -> i.get()).findAny();
//				System.out.printf("  --> (%s)\n", opt);
//				
//				if(opt.isPresent()) {
//					map.remap(opt.get());
//				} else {
//					//if(loc.getPath().equals("zebronium_ore")) map.remap(ModBlocks.ZEBON_ORE.get());
//				}
//			}
//		}
//	}
}
