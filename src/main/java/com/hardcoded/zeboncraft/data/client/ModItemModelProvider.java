package com.hardcoded.zeboncraft.data.client;

import com.hardcoded.zeboncraft.ZebonCraft;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

	public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ZebonCraft.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		withExistingParent("zebon_block", modLoc("block/zebon_block"));
		withExistingParent("zebon_ore", modLoc("block/zebon_ore"));
		withExistingParent("zeves", modLoc("block/zeves"));
		withExistingParent("zrass_block", modLoc("block/zrass_block"));
		withExistingParent("ztem_block", modLoc("block/ztem_block"));
		withExistingParent("zrirt", modLoc("block/zrirt"));
		withExistingParent("zebon_workbench", modLoc("block/zebon_workbench"));
		//withExistingParent("zebon_powered_rail", modLoc("block/zebon_powered_rail"));
		withExistingParent("dried_zrirt", modLoc("block/dried_zrirt"));
		withExistingParent("dried_polished_zrirt", modLoc("block/dried_polished_zrirt"));
		
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));
		builder(itemGenerated, "zebon_ingot");
		
		
		// Tools
		
		builder(itemHandheld, "zebon_axe");
		builder(itemHandheld, "zebon_hoe");
		builder(itemHandheld, "zebon_shovel");
		builder(itemHandheld, "zebon_pickaxe");
		//builder(itemHandheld, "zebon_zvord");
		
		builder(itemHandheld, "charge_sword_0");
		builder(itemHandheld, "charge_sword_1");
		builder(itemHandheld, "charge_sword_2");
		builder(itemHandheld, "charge_sword_3");
		builder(itemHandheld, "charge_sword_4");
		builder(itemHandheld, "charge_sword_5");
		builder(itemHandheld, "charge_sword_6");
		builder(itemHandheld, "charge_sword_7");
		
		getBuilder("zebon_zvord").parent(itemHandheld).texture("layer0", "item/zebon_zvord")
			.override().predicate(modLoc("charging"), 0).predicate(modLoc("charge"), 1 / 8f).model(getExistingFile(modLoc("item/charge_sword_0"))).end()
			.override().predicate(modLoc("charging"), 0).predicate(modLoc("charge"), 2 / 8f).model(getExistingFile(modLoc("item/charge_sword_1"))).end()
			.override().predicate(modLoc("charging"), 0).predicate(modLoc("charge"), 3 / 8f).model(getExistingFile(modLoc("item/charge_sword_2"))).end()
			.override().predicate(modLoc("charging"), 0).predicate(modLoc("charge"), 4 / 8f).model(getExistingFile(modLoc("item/charge_sword_3"))).end()
			.override().predicate(modLoc("charging"), 0).predicate(modLoc("charge"), 5 / 8f).model(getExistingFile(modLoc("item/charge_sword_4"))).end()
			.override().predicate(modLoc("charging"), 0).predicate(modLoc("charge"), 6 / 8f).model(getExistingFile(modLoc("item/charge_sword_5"))).end()
			.override().predicate(modLoc("charging"), 0).predicate(modLoc("charge"), 7 / 8f).model(getExistingFile(modLoc("item/charge_sword_6"))).end()
			.override().predicate(modLoc("charging"), 1).model(getExistingFile(modLoc("item/charge_sword_7"))).end()
		;
		
		// Armor
		builder(itemGenerated, "zebon_helmet");
		builder(itemGenerated, "zebon_chestplate");
		builder(itemGenerated, "zebon_leggings");
		builder(itemGenerated, "zebon_boots");
		
		// Dust
		builder(itemGenerated, "blue_grit");
		builder(itemGenerated, "purple_dust");
		
		//builder(itemGenerated, "zebon_sink");
		//builder(itemGenerated, "zrate_tile_entity");
		
		
		builder(itemGenerated, "zebon_horse_armor");
		
		// getBuilder("zrass").parent(itemGenerated).texture("layer0", "block/zrass");
		// getBuilder("zappling").parent(itemGenerated).texture("layer0", "block/zappling");
		// getBuilder("zebon_powered_rail").parent(itemGenerated).texture("layer0", "block/zebon_powered_rail");
		
		builderBlock(itemGenerated, "zrass");
		builderBlock(itemGenerated, "zappling");
		builderBlock(itemGenerated, "zebon_powered_rail");
		
		// Mushrooms
		builderBlock(itemGenerated, "tall_mushroom");
		builderBlock(itemGenerated, "orange_cap");
		builderBlock(itemGenerated, "glowing_yellow_cap");
		builder(itemGenerated, "pile_of_mushrooms");
	}
	
	private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
		return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
	}
	
	private ItemModelBuilder builderBlock(ModelFile itemGenerated, String name) {
		return getBuilder(name).parent(itemGenerated).texture("layer0", "block/" + name);
	}
}
