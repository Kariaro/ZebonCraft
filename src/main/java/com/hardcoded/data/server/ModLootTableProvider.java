package com.hardcoded.data.server;

import com.hardcoded.utility.ModBlocks;

import net.minecraft.data.DataGenerator;

public class ModLootTableProvider extends BaseLootTableProvider {
	public ModLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		register(ModBlocks.ZRASS_BLOCK.get(), create("zrass_block", ModBlocks.ZRIRT.get(), SILK_TOUCH, ModBlocks.ZRASS_BLOCK.get()));
		register(ModBlocks.ZRIRT.get(), create("zrirt", ModBlocks.ZRIRT.get()));
		register(ModBlocks.DRIED_ZRIRT.get(), create("dried_zrirt", ModBlocks.DRIED_ZRIRT.get()));
		register(ModBlocks.DRIED_POLISHED_ZRIRT.get(), create("dried_polished_zrirt", ModBlocks.DRIED_POLISHED_ZRIRT.get()));
		register(ModBlocks.ZRASS.get(), create("zrass", ModBlocks.ZRASS.get(), SHEARS));
		register(ModBlocks.ZAPPLING.get(), create("zappling", ModBlocks.ZAPPLING.get()));
	}
}
