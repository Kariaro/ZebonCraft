package com.hardcoded.zeboncraft.data.client;

import com.hardcoded.zeboncraft.ZebonCraft;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockModelProvider {

	public ModBlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ZebonCraft.MOD_ID, existingFileHelper);
	}
	
	protected void registerModels() {
		doRails();
	}
	
	private void doRails() {
		ModelFile rail_flat = getExistingFile(mcLoc("block/rail_flat"));
		ModelFile raised_ne = getExistingFile(mcLoc("block/template_rail_raised_ne"));
		ModelFile raised_sw = getExistingFile(mcLoc("block/template_rail_raised_sw"));
		
		buildRail(raised_ne, "zebon_powered_rail_on_raised_ne", "zebon_powered_rail_on");
		buildRail(raised_sw, "zebon_powered_rail_on_raised_sw", "zebon_powered_rail_on");
		buildRail(rail_flat, "zebon_powered_rail_on", "zebon_powered_rail_on");
		buildRail(raised_ne, "zebon_powered_rail_raised_ne", "zebon_powered_rail");
		buildRail(raised_sw, "zebon_powered_rail_raised_sw", "zebon_powered_rail");
		buildRail(rail_flat, "zebon_powered_rail", "zebon_powered_rail");
	}
	
	private BlockModelBuilder buildRail(ModelFile model, String name, String texture) {
		return getBuilder(name).parent(model).texture("rail", "block/" + texture);
	}
}
