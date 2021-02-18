package com.hardcoded.data.server;

import com.hardcoded.mod.HardcodedMod;
import com.hardcoded.utility.ModBlocks;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, HardcodedMod.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void registerTags() {
		getOrCreateBuilder(Tags.Blocks.ORES).add(ModBlocks.ZEBON_ORE.get());
		getOrCreateBuilder(BlockTags.RAILS).add(ModBlocks.ZEBON_POWERED_RAIL.get());
	}
	
	@Override
	public String getName() {
		return "ZebonCraft Block Tags";
	}
}
