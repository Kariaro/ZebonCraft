package com.hardcoded.zeboncraft.data.server;

import com.hardcoded.zeboncraft.utility.ModBlocks;

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
		register(ModBlocks.ZEBON_BLOCK.get(), create("zebon_block", ModBlocks.ZEBON_BLOCK.get()));
		register(ModBlocks.ZEBON_ORE.get(), create("zebon_ore", ModBlocks.ZEBON_ORE.get()));
		
		register(ModBlocks.ZEBON_POWERED_RAIL.get(), create("zebon_powered_rail", ModBlocks.ZEBON_POWERED_RAIL.get()));
		register(ModBlocks.ZEBON_WORKBENCH.get(), create("zebon_workbench", ModBlocks.ZEBON_WORKBENCH.get()));
		
		
		register(ModBlocks.ORANGE_CAP.get(), create("orange_cap", ModBlocks.ORANGE_CAP.get()));
		register(ModBlocks.TALL_MUSHROOM.get(), create("tall_mushroom", ModBlocks.TALL_MUSHROOM.get()));
		register(ModBlocks.GLOWING_YELLOW_CAP.get(), create("glowing_yellow_cap", ModBlocks.GLOWING_YELLOW_CAP.get()));
	}
}
