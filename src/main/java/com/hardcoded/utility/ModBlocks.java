package com.hardcoded.utility;

import java.util.function.Supplier;

import com.hardcoded.mod.block.ZapplingBlock;
import com.hardcoded.mod.block.ZrassBlock;
import com.hardcoded.mod.block.ZrassPlantBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModBlocks {
	public static final RegistryObject<Block> ZEBON_ORE = register("zebon_ore", () ->
			new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.SHROOMLIGHT)));
	public static final RegistryObject<Block> ZEBON_BLOCK = register("zebon_block", () ->
			new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(3, 10).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)));
	
	public static final RegistryObject<Block> ZTEM_BLOCK = register("ztem_block", () ->
			new Block(AbstractBlock.Properties.create(Material.NETHER_WOOD).hardnessAndResistance(3, 10).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> ZEVES = register("zeves", () ->
			new Block(AbstractBlock.Properties.create(Material.LEAVES).sound(SoundType.PLANT)));
	
	public static final RegistryObject<Block> ZRASS = register("zrass", () ->
			new ZrassPlantBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().doesNotBlockMovement().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> ZAPPLING = register("zappling", () ->
			new ZapplingBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().doesNotBlockMovement().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> ZRASS_BLOCK = register("zrass_block", () ->
			new ZrassBlock(AbstractBlock.Properties.create(Material.ORGANIC).tickRandomly().harvestTool(ToolType.SHOVEL).slipperiness(0.7f).hardnessAndResistance(0.7f).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> ZRIRT = register("zrirt", () ->
			new Block(AbstractBlock.Properties.create(Material.EARTH).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.7f).sound(SoundType.GROUND)));
	
	public static final RegistryObject<Block> DRIED_ZRIRT = register("dried_zrirt", () ->
			new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(0.7f).sound(SoundType.STONE)));
	public static final RegistryObject<Block> DRIED_POLISHED_ZRIRT = register("dried_polished_zrirt", () ->
			new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).hardnessAndResistance(0.7f).sound(SoundType.STONE)));

	// Zlikcid
	static void register() {
		//PaintingEntity
		
		// BowItem
	}
	
	private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
		return Registration.BLOCKS.register(name, block);
	}
	
	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
		RegistryObject<T>  ret = registerNoItem(name, block);
		Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
		return ret;
	}
}
