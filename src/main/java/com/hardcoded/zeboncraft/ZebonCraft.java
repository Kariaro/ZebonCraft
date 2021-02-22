package com.hardcoded.zeboncraft;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hardcoded.zeboncraft.client.gui.FungusHeartGuiOverlay;
import com.hardcoded.zeboncraft.container.ZebonWorkbenchScreen;
import com.hardcoded.zeboncraft.enchantment.ZnchantListener;
import com.hardcoded.zeboncraft.item.ZebonSword;
import com.hardcoded.zeboncraft.network.ModPacketHandlers;
import com.hardcoded.zeboncraft.utility.*;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ZebonCraft.MOD_ID)
public class ZebonCraft {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();
	
	public static final String MOD_ID = "zeboncraft";
	public static final String MOD_NAME = "Zebon Craft";
	
	/* TODO: Fungus heart debuff.
	 *       When the player gets the spores effect some hearts
	 *       will be consumed by a fungus. These hearts will be
	 *       easy to take so for x hearts and 4 infected hearts
	 *       You can only naturally regen (x - 4) hearts.
	 *           (This will make being inside the zetra biome dangerous)
	 *
	 *       When the spores debuff runs out. Each infected heart
	 *       will slowly turn back to normal but as empty hearts.
	 *       While you stand on Zrass block you have a chance of getting
	 *       the spores debuff.
	 */
	
	/* TODO: The ZetraBiome should have high fog and look like night.
	 *       Torches should be less effective as light becomes darker.
	 */
	
	/* TODO: Add 10 or more mushroome types and use them for potions. */
	
	/* TODO: Make the Zappling usable and create a Zetra Cap Tree? */
	
	
	public static final ItemGroup ZEBON_CRAFT = new ItemGroup("zeboncraft") {
		public ItemStack createIcon() {
			return new ItemStack(ModItems.ZEBON_INGOT.get());
		}
	};

	public ZebonCraft() {
		Registration.register();
		
		ModLoadingContext loadingContext = ModLoadingContext.get();
		loadingContext.registerConfig(ModConfig.Type.COMMON, ZebonConfig.MOD_CONFIG, "zebon-craft.toml");
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		//FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new FungusHeartGuiOverlay());
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		ModCapabilities.register();
		ModPacketHandlers.register();
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModContainers.ZEBON_WORKBENCH.get(), ZebonWorkbenchScreen::new);
		RenderTypeLookup.setRenderLayer(ModBlocks.ZRASS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.SHORT_ZRASS.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.ZAPPLING.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.ZEBON_POWERED_RAIL.get(), RenderType.getCutout());
		
		// Mushrooms
		RenderTypeLookup.setRenderLayer(ModBlocks.GLOWING_YELLOW_CAP.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.TALL_MUSHROOM.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.ORANGE_CAP.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_CAP.get(), RenderType.getCutout());
		
		MinecraftForge.EVENT_BUS.register(new ZnchantListener());
		
		ItemModelsProperties.registerProperty(ModItems.ZEBON_ZVORD.get(), new ResourceLocation(MOD_ID, "charging"), (itemStack, world, entity) -> {
			return ZebonSword.isCharged(itemStack) ? 1:0;
		});
		
		ItemModelsProperties.registerProperty(ModItems.ZEBON_ZVORD.get(), new ResourceLocation(MOD_ID, "charge"), (itemStack, world, entity) -> {
			return entity != null && entity.isHandActive() && itemStack == entity.getActiveItemStack() ? (itemStack.getUseDuration() - entity.getItemInUseCount()) / 20.0f : 0;
		});
		
		RenderTypeLookup.setRenderLayer(ModBlocks.ZRATE_BLOCK.get(), RenderType.getCutoutMipped());
		// ClientRegistry.bindTileEntityRenderer(ModTileEntities.ZRATE.get(), ZrateTileEntityRenderer::new);
	}
	
	/*
	private void enqueueIMC(final InterModEnqueueEvent event) {
		// some example code to dispatch IMC to another mod
		// InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}
	
	private void processIMC(final InterModProcessEvent event) {
		// some example code to receive and process InterModComms from other mods
		// LOGGER.info("Got IMC {}", event.getIMCStream().map(m->m.getMessageSupplier().get()).collect(Collectors.toList()));
	}
	*/
	
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		
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
