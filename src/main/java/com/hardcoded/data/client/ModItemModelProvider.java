package com.hardcoded.data.client;

import com.hardcoded.mod.HardcodedMod;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, HardcodedMod.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		withExistingParent("zebon_block", modLoc("block/zebon_block"));
		withExistingParent("zebon_ore", modLoc("block/zebon_ore"));
		withExistingParent("zeves", modLoc("block/zeves"));
		withExistingParent("zrass_block", modLoc("block/zrass_block"));
		withExistingParent("ztem_block", modLoc("block/ztem_block"));
		withExistingParent("zrirt", modLoc("block/zrirt"));
		withExistingParent("dried_zrirt", modLoc("block/dried_zrirt"));
		withExistingParent("dried_polished_zrirt", modLoc("block/dried_polished_zrirt"));
		//withExistingParent("zrass", modLoc("block/zrass"));
		
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));
		builder(itemGenerated, "zebon_ingot");
		
		
		// Tools
		builder(itemHandheld, "zebon_zvord");
		builder(itemHandheld, "zebon_axe");
		builder(itemHandheld, "zebon_hoe");
		builder(itemHandheld, "zebon_shovel");
		builder(itemHandheld, "zebon_pickaxe");
		
		// Armor
		builder(itemGenerated, "zebon_helmet");
		builder(itemGenerated, "zebon_chestplate");
		builder(itemGenerated, "zebon_leggings");
		builder(itemGenerated, "zebon_boots");
		
		
		builder(itemGenerated, "zebon_horse_armor");
		
		getBuilder("zrass").parent(itemGenerated).texture("layer0", "block/zrass");
		getBuilder("zappling").parent(itemGenerated).texture("layer0", "block/zappling");
	}
	
	private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
		return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
	}
	
}
