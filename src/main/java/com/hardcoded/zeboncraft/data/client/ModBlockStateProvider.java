package com.hardcoded.zeboncraft.data.client;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.utility.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

	public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ZebonCraft.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(ModBlocks.ZEBON_BLOCK.get());
		simpleBlock(ModBlocks.ZEBON_ORE.get());
		simpleBlock(ModBlocks.ZRASS_BLOCK.get(), modLoc("block/zrirt"), modLoc("block/zrass_block_side"), modLoc("block/zrass_block_top"), modLoc("block/zrass_block_side"));
		simpleBlock(ModBlocks.ZTEM_BLOCK.get(), modLoc("block/ztem_top"), modLoc("block/ztem_side"), modLoc("block/ztem_top"), modLoc("block/ztem_side"));
		simpleBlock(ModBlocks.ZEBON_WORKBENCH.get(), modLoc("block/zebon_workbench_top"), modLoc("block/zebon_workbench_front"), modLoc("block/zebon_workbench_top"), modLoc("block/zebon_workbench_side"));
		simpleBlock(ModBlocks.ZRIRT.get());
		simpleBlock(ModBlocks.DRIED_ZRIRT.get());
		simpleBlock(ModBlocks.DRIED_POLISHED_ZRIRT.get());
		simpleBlock(ModBlocks.ZEVES.get());
		
		// Plants
		simpleBush(ModBlocks.ZRASS.get());
		simpleBush(ModBlocks.SHORT_ZRASS.get());
		simpleBush(ModBlocks.ZAPPLING.get());
		
		// Mushrooms
		simpleBush(ModBlocks.ORANGE_CAP.get());
		simpleBush(ModBlocks.GREEN_CAP.get());
		simpleBush(ModBlocks.TALL_MUSHROOM.get());
		simpleBush(ModBlocks.GLOWING_YELLOW_CAP.get());
		
		getRailBuilder(ModBlocks.ZEBON_POWERED_RAIL.get())
			.add(false, RailShape.ASCENDING_EAST, "zebon_powered_rail_raised_ne", 90)
			.add(false, RailShape.ASCENDING_NORTH, "zebon_powered_rail_raised_ne")
			.add(false, RailShape.ASCENDING_SOUTH, "zebon_powered_rail_raised_sw")
			.add(false, RailShape.ASCENDING_WEST, "zebon_powered_rail_raised_sw", 90)
			.add(false, RailShape.EAST_WEST, "zebon_powered_rail", 90)
			.add(false, RailShape.NORTH_SOUTH, "zebon_powered_rail")
			
			.add(true, RailShape.ASCENDING_EAST, "zebon_powered_rail_on_raised_ne", 90)
			.add(true, RailShape.ASCENDING_NORTH, "zebon_powered_rail_on_raised_ne")
			.add(true, RailShape.ASCENDING_SOUTH, "zebon_powered_rail_on_raised_sw")
			.add(true, RailShape.ASCENDING_WEST, "zebon_powered_rail_on_raised_sw", 90)
			.add(true, RailShape.EAST_WEST, "zebon_powered_rail_on", 90)
			.add(true, RailShape.NORTH_SOUTH, "zebon_powered_rail_on")
			.build();
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
	
	private ZebonRailBuilder getRailBuilder(Block block) {
		return new ZebonRailBuilder(block);
	}
	
	private class ZebonRailBuilder {
		private VariantBlockStateBuilder builder;
		
		public ZebonRailBuilder(Block block) {
			this.builder = getVariantBuilder(block);
		}
		
		public ZebonRailBuilder add(boolean powered, RailShape shape, String model) {
			builder.partialState().with(PoweredRailBlock.POWERED, powered).with(PoweredRailBlock.SHAPE, shape)
				.modelForState().modelFile(models().getExistingFile(modLoc("block/" + model))).addModel();
			
			return this;
		}
		
		public ZebonRailBuilder add(boolean powered, RailShape shape, String model, int rotationY) {
			builder.partialState().with(PoweredRailBlock.POWERED, powered).with(PoweredRailBlock.SHAPE, shape)
				.modelForState().modelFile(models().getExistingFile(modLoc("block/" + model))).rotationY(rotationY).addModel();
			
			return this;
		}
		
		public VariantBlockStateBuilder build() {
			return builder;
		}
	}
	
}
