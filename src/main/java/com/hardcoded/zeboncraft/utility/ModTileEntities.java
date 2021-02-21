package com.hardcoded.zeboncraft.utility;

import java.util.function.Supplier;

import com.hardcoded.zeboncraft.tileentity.ZebonWorkbenchTileEntity;
import com.hardcoded.zeboncraft.tileentity.ZrateTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {
	public static final RegistryObject<TileEntityType<ZrateTileEntity>> ZRATE = register("zrate_tile_entity",
			() -> TileEntityType.Builder.create(ZrateTileEntity::new, ModBlocks.ZRATE_BLOCK.get()).build(null));
	
	public static final RegistryObject<TileEntityType<ZebonWorkbenchTileEntity>> ZEBON_WORKBENCH = register("zebon_workbench",
			() -> TileEntityType.Builder.create(ZebonWorkbenchTileEntity::new, ModBlocks.ZEBON_WORKBENCH.get()).build(null));
	
	
	static void register() {}
	
	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> supplier) {
		return Registration.TILE_ENTITIES.register(name, supplier);
	}
	
//	@SubscribeEvent
//	public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
//		zrate_block = new ZrateBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.CLOTH));
//		zrate_block.setRegistryName(HardcodedMod.MOD_ID, "zrate_tile_entity");
//		blockRegisterEvent.getRegistry().register(zrate_block);
//	}
//	
//	@SubscribeEvent
//	public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
//		zrate_block_item = new BlockItem(zrate_block, new Item.Properties().group(ModItemGroups.ZEBON));
//		zrate_block_item.setRegistryName(zrate_block.getRegistryName());
//		itemRegisterEvent.getRegistry().register(zrate_block_item);
//	}
//	
//	@SubscribeEvent
//	public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {
//		ZRATE = TileEntityType.Builder.create(ZrateTileEntity::new, zrate_block).build(null);
//		ZRATE.setRegistryName(HardcodedMod.MOD_ID, "zrate_tile_entity_type");
//		event.getRegistry().register(ZRATE);
//		
//		ZEBON_WORKBENCH = TileEntityType.Builder.create(ZebonWorkbenchTileEntity::new, ModBlocks.ZEBON_WORKBENCH.get()).build(null);
//		ZEBON_WORKBENCH.setRegistryName(HardcodedMod.MOD_ID, "zebon_workbench");
//		event.getRegistry().register(ZEBON_WORKBENCH);
//	}
}
