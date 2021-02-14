package com.hardcoded.utility;

import com.hardcoded.mod.tileentity.ZrateModel;
import com.hardcoded.mod.tileentity.ZrateTileEntityRenderer;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModTileEntities {
	
	static void register() {
		ClientRegistry.bindTileEntityRenderer(ModTileEntityTypes.ZRATE.get(), (a) -> new ZrateTileEntityRenderer(a, new ZrateModel()));
	}
}
