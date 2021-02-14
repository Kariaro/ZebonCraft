package com.hardcoded.utility;

import java.util.function.Supplier;

import com.hardcoded.mod.tileentity.ZrateTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntityTypes {
	public static final RegistryObject<TileEntityType<ZrateTileEntity>> ZRATE = register("zrate_tile_entity",
			() -> TileEntityType.Builder.create(ZrateTileEntity::new, ModBlocks.DRIED_ZRIRT.get()).build(null));
	
	
	static void register() {}
	
	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> supplier) {
		return Registration.TILE_ENTITIES.register(name, supplier);
	}
}
