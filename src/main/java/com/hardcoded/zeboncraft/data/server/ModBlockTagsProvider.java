package com.hardcoded.zeboncraft.data.server;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.utility.ModBlocks;
import com.hardcoded.zeboncraft.utility.ModTags;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {

	public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, ZebonCraft.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void registerTags() {
		getOrCreateBuilder(Tags.Blocks.ORES).add(ModBlocks.ZEBON_ORE.get());
		getOrCreateBuilder(BlockTags.RAILS).add(ModBlocks.ZEBON_POWERED_RAIL.get());
		
		getOrCreateBuilder(ModTags.Blocks.MUSHROOMS)
			.add(ModBlocks.TALL_MUSHROOM.get())
			.add(ModBlocks.ORANGE_CAP.get())
			.add(ModBlocks.GREEN_CAP.get())
			.add(ModBlocks.GLOWING_YELLOW_CAP.get());
	}
	
	@Override
	public String getName() {
		return "ZebonCraft Block Tags";
	}
}
