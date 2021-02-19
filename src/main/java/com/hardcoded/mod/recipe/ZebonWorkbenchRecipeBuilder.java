package com.hardcoded.mod.recipe;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import com.hardcoded.utility.ModRecipeSerializers;

import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ZebonWorkbenchRecipeBuilder {
	private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
	private final Item result;
	private final int count;
	private Ingredient item1;
	private Ingredient item2;
	private final float experience;
	private final int cookTime;
	private String group;
	
	private ZebonWorkbenchRecipeBuilder(IItemProvider resultIn, int countIn, float experienceIn, int cookTimeIn) {
		this.result = resultIn.asItem();
		this.count = countIn;
		this.experience = experienceIn;
		this.cookTime = cookTimeIn;
	}
	
	public static ZebonWorkbenchRecipeBuilder createRecipe(IItemProvider resultIn, int cookTimeIn) {
		return new ZebonWorkbenchRecipeBuilder(resultIn, 1, 0, cookTimeIn);
	}
	
	public static ZebonWorkbenchRecipeBuilder createRecipe(IItemProvider resultIn, float experienceIn, int cookTimeIn) {
		return new ZebonWorkbenchRecipeBuilder(resultIn, 1, experienceIn, cookTimeIn);
	}
	
	public static ZebonWorkbenchRecipeBuilder createRecipe(IItemProvider resultIn, int countIn, int cookTimeIn) {
		return new ZebonWorkbenchRecipeBuilder(resultIn, countIn, 0, cookTimeIn);
	}
	
	public static ZebonWorkbenchRecipeBuilder createRecipe(IItemProvider resultIn, int countIn, float experienceIn, int cookTimeIn) {
		return new ZebonWorkbenchRecipeBuilder(resultIn, countIn, experienceIn, cookTimeIn);
	}
	
	public ZebonWorkbenchRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
		advancementBuilder.withCriterion(name, criterionIn);
		return this;
	}
	
	public ZebonWorkbenchRecipeBuilder setIngredients(IItemProvider item1In, IItemProvider item2In) {
		this.item1 = Ingredient.fromItems(item1In);
		this.item2 = Ingredient.fromItems(item2In);
		return this;
	}
	
	public ZebonWorkbenchRecipeBuilder setIngredients(Ingredient item1In, Ingredient item2In) {
		this.item1 = item1In;
		this.item2 = item2In;
		return this;
	}
	
	public ZebonWorkbenchRecipeBuilder setGroup(String groupIn) {
		this.group = groupIn;
		return this;
	}
	
	public void build(Consumer<IFinishedRecipe> consumerIn, ResourceLocation id) {
		if(item1 == null || item2 == null) {
			throw new IllegalArgumentException("No ingredients where provided. Try call setIngredients()");
		}
		
		//this.validate(id);
		advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", RecipeUnlockedTrigger.create(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
		consumerIn.accept(new Result(id, result, count, group == null ? "" : group, item1, item2, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + result.getGroup().getPath() + "/" + id.getPath()), experience, cookTime));
	}
	
	public static class Result implements IFinishedRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final Ingredient item1;
		private final Ingredient item2;
		private final Advancement.Builder advancementBuilder;
		private final ResourceLocation advancementId;
		private final float experience;
		private final int cookTime;
		
		public Result(ResourceLocation idIn, Item resultIn, int countIn, String groupIn, Ingredient item1In, Ingredient item2In, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn, float experienceIn, int cookTimeIn) {
			this.id = idIn;
			this.result = resultIn;
			this.count = countIn;
			this.group = groupIn;
			this.item1 = item1In;
			this.item2 = item2In;
			this.advancementBuilder = advancementBuilderIn;
			this.advancementId = advancementIdIn;
			this.experience = experienceIn;
			this.cookTime = cookTimeIn;
		}
		
		@SuppressWarnings("deprecation")
		public void serialize(JsonObject json) {
			if(!group.isEmpty()) {
				json.addProperty("group", this.group);
			}
			
			json.add("item1", item1.serialize());
			json.add("item2", item2.serialize());
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
			if(this.count > 1) {
				jsonobject.addProperty("count", this.count);
			}
			
			json.add("result", jsonobject);
			json.addProperty("experience", experience);
			json.addProperty("cookTime", cookTime);
		}
		
		public IRecipeSerializer<?> getSerializer() {
			return ModRecipeSerializers.ZEBON_WORKBENCH_SHAPELESS.get();
		}
		
		public ResourceLocation getID() { return id; }
		public JsonObject getAdvancementJson() { return advancementBuilder.serialize(); }
		public ResourceLocation getAdvancementID() { return advancementId; }
	}
}
