package com.hardcoded.zeboncraft.data.server;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.utility.ModBlocks;
import com.hardcoded.zeboncraft.utility.ModItems;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {

	public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, ZebonCraft.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void registerTags() {
		getOrCreateBuilder(Tags.Items.INGOTS).add(ModItems.ZEBON_INGOT.get());
		getOrCreateBuilder(Tags.Items.MUSHROOMS)
			.add(ModBlocks.GLOWING_YELLOW_CAP.get().asItem())
			.add(ModBlocks.TALL_MUSHROOM.get().asItem())
			.add(ModBlocks.ORANGE_CAP.get().asItem())
			.add(ModBlocks.GREEN_CAP.get().asItem())
		;
		
	}
	
	@Override
	public String getName() {
		return "ZebonCraft Item Tags";
	}
}
