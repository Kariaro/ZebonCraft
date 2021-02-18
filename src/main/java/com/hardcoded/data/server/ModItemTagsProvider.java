package com.hardcoded.data.server;

import com.hardcoded.mod.HardcodedMod;
import com.hardcoded.utility.ModItems;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {

	public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, HardcodedMod.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void registerTags() {
		getOrCreateBuilder(Tags.Items.INGOTS).add(ModItems.ZEBON_INGOT.get());
		
	}
	
	@Override
	public String getName() {
		return "ZebonCraft Item Tags";
	}
}
