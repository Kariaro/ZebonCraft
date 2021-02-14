package com.hardcoded.data.server;

import java.util.function.Consumer;

import com.hardcoded.mod.HardcodedMod;
import com.hardcoded.utility.ModBlocks;
import com.hardcoded.utility.ModItems;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class ModRecipeProvider extends RecipeProvider {

	public ModRecipeProvider(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		// With conditions you can exclude recipes depending on what mods are loaded
		
		simpleRecipe("xxx", "xxx", "xxx", 'x', ModBlocks.ZEBON_BLOCK.get(), ModItems.ZEBON_INGOT.get(), consumer);
		simpleRecipe("xxx", "x x", "   ", 'x', ModItems.ZEBON_HELMET.get(), ModItems.ZEBON_INGOT.get(), consumer);
		simpleRecipe("x x", "xxx", "xxx", 'x', ModItems.ZEBON_CHESTPLATE.get(), ModItems.ZEBON_INGOT.get(), consumer);
		simpleRecipe("xxx", "x x", "x x", 'x', ModItems.ZEBON_LEGGINGS.get(), ModItems.ZEBON_INGOT.get(), consumer);
		simpleRecipe("   ", "x x", "x x", 'x', ModItems.ZEBON_BOOTS.get(), ModItems.ZEBON_INGOT.get(), consumer);
		simpleRecipe("xx ", "xx ", "   ", 'x', ModBlocks.DRIED_POLISHED_ZRIRT.get(), ModBlocks.DRIED_ZRIRT.get(), consumer);
		
		ShapelessRecipeBuilder.shapelessRecipe(ModItems.ZEBON_INGOT.get(), 9)
			.addIngredient(ModBlocks.ZEBON_BLOCK.get())
			.setGroup(HardcodedMod.MOD_ID)
			.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModBlocks.ZEBON_BLOCK.get()))
			.build(consumer, new ResourceLocation(HardcodedMod.MOD_ID, "zebon_ingot_shapeless"));
		
		ShapedRecipeBuilder.shapedRecipe(ModItems.ZEBON_ZVORD.get())
			.patternLine("  x")
			.patternLine(" x ")
			.patternLine("a  ")
			.key('x', ModItems.ZEBON_INGOT.get())
			.key('a', Items.STICK)
			.setGroup(HardcodedMod.MOD_ID)
			.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.ZEBON_INGOT.get()))
			.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(ModItems.ZEBON_PICKAXE.get())
			.patternLine("xxx")
			.patternLine(" a ")
			.patternLine(" a ")
			.key('x', ModItems.ZEBON_INGOT.get())
			.key('a', Items.STICK)
			.setGroup(HardcodedMod.MOD_ID)
			.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.ZEBON_INGOT.get()))
			.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(ModItems.ZEBON_AXE.get())
			.patternLine("xx")
			.patternLine("xa")
			.patternLine(" a")
			.key('x', ModItems.ZEBON_INGOT.get())
			.key('a', Items.STICK)
			.setGroup(HardcodedMod.MOD_ID)
			.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.ZEBON_INGOT.get()))
			.build(consumer);

		ShapedRecipeBuilder.shapedRecipe(ModItems.ZEBON_SHOVEL.get())
			.patternLine("x")
			.patternLine("a")
			.patternLine("a")
			.key('x', ModItems.ZEBON_INGOT.get())
			.key('a', Items.STICK)
			.setGroup(HardcodedMod.MOD_ID)
			.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.ZEBON_INGOT.get()))
			.build(consumer);
		
		ShapedRecipeBuilder.shapedRecipe(ModItems.ZEBON_HOE.get())
			.patternLine("xx")
			.patternLine(" a")
			.patternLine(" a")
			.key('x', ModItems.ZEBON_INGOT.get())
			.key('a', Items.STICK)
			.setGroup(HardcodedMod.MOD_ID)
			.addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModItems.ZEBON_INGOT.get()))
			.build(consumer);
		
		CookingRecipeBuilder.smeltingRecipe(
			Ingredient.fromItems(ModBlocks.ZEBON_ORE.get()),
			ModItems.ZEBON_INGOT.get(),
			0.3f,
			20 * 8
		).addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModBlocks.ZEBON_ORE.get()))
		.build(consumer, new ResourceLocation(HardcodedMod.MOD_ID, "zebon_ingot_smelting"));
		
		CookingRecipeBuilder.smeltingRecipe(
			Ingredient.fromItems(ModBlocks.ZRIRT.get()),
			ModBlocks.DRIED_ZRIRT.get(),
			0.3f,
			20 * 8
		).addCriterion("item", InventoryChangeTrigger.Instance.forItems(ModBlocks.ZRIRT.get()))
		.build(consumer, new ResourceLocation(HardcodedMod.MOD_ID, "dried_zrirt_smelting"));
	}
	
	private void simpleRecipe(String a, String b, String c, char key, IItemProvider result, IItemProvider criteria, Consumer<IFinishedRecipe> consumer) {
		ShapedRecipeBuilder.shapedRecipe(result)
			.patternLine(a).patternLine(b).patternLine(c)
			.key(key, criteria)
			.setGroup(HardcodedMod.MOD_ID)
			.addCriterion("item", InventoryChangeTrigger.Instance.forItems(criteria))
			.build(consumer);
	}
}
