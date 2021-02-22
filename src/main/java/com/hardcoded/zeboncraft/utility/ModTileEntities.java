package com.hardcoded.zeboncraft.utility;

import java.util.function.Supplier;

import com.hardcoded.zeboncraft.tileentity.ZebonWorkbenchTileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class ModTileEntities {
	public static final RegistryObject<TileEntityType<ZebonWorkbenchTileEntity>> ZEBON_WORKBENCH = register("zebon_workbench",
			() -> TileEntityType.Builder.create(ZebonWorkbenchTileEntity::new, ModBlocks.ZEBON_WORKBENCH.get()).build(null));
	
	
	static void register() {}
	
	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> supplier) {
		return Registration.TILE_ENTITIES.register(name, supplier);
	}
}
