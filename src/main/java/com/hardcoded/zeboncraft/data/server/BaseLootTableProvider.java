package com.hardcoded.zeboncraft.data.server;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

abstract class BaseLootTableProvider extends LootTableProvider {
	protected static final ILootCondition.IBuilder SILK_TOUCH = MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))));
	protected static final ILootCondition.IBuilder NO_SILK_TOUCH = SILK_TOUCH.inverted();
	protected static final ILootCondition.IBuilder SHEARS = MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS));
	protected static final ILootCondition.IBuilder SILK_TOUCH_OR_SHEARS = SHEARS.alternative(SILK_TOUCH);
	protected static final ILootCondition.IBuilder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.inverted();
	
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	private final DataGenerator generator;
	protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
	
	public BaseLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
		this.generator = dataGeneratorIn;
	}
	
	protected abstract void addTables();
	
	protected LootPool.Builder create(String name, IItemProvider drop) {
		return create(name, ItemLootEntry.builder(drop));
	}
	
	protected LootPool.Builder create(String name, LootEntry.Builder<?> entry) {
		LootPool.Builder builder = LootPool.builder()
			.name(name)
			.rolls(ConstantRange.of(1))
			.addEntry(entry);
		
		return builder;
	}
	
	protected LootPool.Builder create(String name, IItemProvider normal_drop, ILootCondition.IBuilder condition, IItemProvider condition_drop) {
		return create(name,
			ItemLootEntry.builder(condition_drop)
				.acceptCondition(condition)
				.alternatively(ItemLootEntry.builder(normal_drop)));
	}
	
	protected LootPool.Builder create(String name, IItemProvider drop, ILootCondition.IBuilder condition) {
		return create(name, ItemLootEntry.builder(drop).acceptCondition(condition));
	}
	
	protected LootTable.Builder register(Block block, LootPool.Builder lootbuilder) {
		LootTable.Builder builder = LootTable.builder().addLootPool(lootbuilder);
		this.lootTables.put(block, builder);
		return builder;
	}
	
	@Override
	public void act(DirectoryCache cache) {
		addTables();
		
		Map<ResourceLocation, LootTable> tables = new HashMap<>();
		for(Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
			tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
		}
		
		writeTables(cache, tables);
	}
	
	private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
		Path outputFolder = this.generator.getOutputFolder();
		
		tables.forEach((key, lootTable) -> {
			Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
			
			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
			} catch(IOException e) {
				LOGGER.error("Couldn't write loot table {}", path, e);
			}
		});
	}
	
	@Override
	public String getName() {
		return "Hardcoded ZebonCraft LootTables";
	}
}
