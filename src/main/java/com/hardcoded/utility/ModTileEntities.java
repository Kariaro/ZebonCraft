package com.hardcoded.utility;

import com.hardcoded.mod.HardcodedMod;
import com.hardcoded.mod.block.ZrateBlock;
import com.hardcoded.mod.tileentity.ZebonWorkbenchTileEntity;
import com.hardcoded.mod.tileentity.ZrateTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities {
	public static ZrateBlock zrate_block;
	public static BlockItem zrate_block_item;
	public static TileEntityType<ZrateTileEntity> ZRATE;
	
	public static TileEntityType<ZebonWorkbenchTileEntity> ZEBON_WORKBENCH;
//	public static final RegistryObject<TileEntityType<ZrateTileEntity>> ZRATE = register("zrate_tile_entity",
//			() -> TileEntityType.Builder.create(ZrateTileEntity::new, ModBlocks.DRIED_ZRIRT.get()).build(null));
	
	
	static void register() {}
	
//	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> supplier) {
//		return Registration.TILE_ENTITIES.register(name, supplier);
//	}
	
	@SubscribeEvent
	public static void onBlocksRegistration(final RegistryEvent.Register<Block> blockRegisterEvent) {
		zrate_block = new ZrateBlock(AbstractBlock.Properties.create(Material.ROCK).sound(SoundType.CLOTH));
		zrate_block.setRegistryName(HardcodedMod.MOD_ID, "zrate_tile_entity");
		blockRegisterEvent.getRegistry().register(zrate_block);
	}
	
	@SubscribeEvent
	public static void onItemsRegistration(final RegistryEvent.Register<Item> itemRegisterEvent) {
		zrate_block_item = new BlockItem(zrate_block, new Item.Properties().group(ModItemGroups.ZEBON));
		zrate_block_item.setRegistryName(zrate_block.getRegistryName());
		itemRegisterEvent.getRegistry().register(zrate_block_item);
	}
	
	@SubscribeEvent
	public static void onTileEntityTypeRegistration(final RegistryEvent.Register<TileEntityType<?>> event) {
		ZRATE = TileEntityType.Builder.create(ZrateTileEntity::new, zrate_block).build(null);
		ZRATE.setRegistryName(HardcodedMod.MOD_ID, "zrate_tile_entity_type");
		event.getRegistry().register(ZRATE);
		
		ZEBON_WORKBENCH = TileEntityType.Builder.create(ZebonWorkbenchTileEntity::new, ModBlocks.ZEBON_WORKBENCH.get()).build(null);
		ZEBON_WORKBENCH.setRegistryName(HardcodedMod.MOD_ID, "zebon_workbench");
		event.getRegistry().register(ZEBON_WORKBENCH);
	}
}
