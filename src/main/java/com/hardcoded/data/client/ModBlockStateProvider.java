package com.hardcoded.data.client;

import com.hardcoded.mod.HardcodedMod;
import com.hardcoded.utility.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, HardcodedMod.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(ModBlocks.ZEBON_BLOCK.get());
		simpleBlock(ModBlocks.ZEBON_ORE.get());
		simpleBlock(ModBlocks.ZRASS_BLOCK.get(), modLoc("block/zrirt"), modLoc("block/zrass_block_side"), modLoc("block/zrass_block_top"), modLoc("block/zrass_block_side"));
		simpleBlock(ModBlocks.ZTEM_BLOCK.get(), modLoc("block/ztem_top"), modLoc("block/ztem_side"), modLoc("block/ztem_top"), modLoc("block/ztem_side"));
		simpleBlock(ModBlocks.ZRIRT.get());
		simpleBlock(ModBlocks.DRIED_ZRIRT.get());
		simpleBlock(ModBlocks.DRIED_POLISHED_ZRIRT.get());
		simpleBlock(ModBlocks.ZEVES.get());
		simpleBush(ModBlocks.ZRASS.get());
		simpleBush(ModBlocks.ZAPPLING.get());
		
	}
	
	private ModelFile cross(Block block) {
		return models().cross(block.getRegistryName().getPath(), blockTexture(block));
	}
	
	private void simpleBush(Block block) {
		simpleBlock(block, cross(block));
	}
	
	private void simpleBlock(Block block, ResourceLocation down, ResourceLocation side, ResourceLocation up, ResourceLocation particle) {
		simpleBlock(block, models().cube(block.getRegistryName().getPath(), down, up, side, side, side, side).texture("particle", particle));
	}
	
}
