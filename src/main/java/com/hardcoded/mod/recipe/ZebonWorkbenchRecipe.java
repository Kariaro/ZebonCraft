package com.hardcoded.mod.recipe;

import com.google.gson.JsonObject;
import com.hardcoded.mod.container.ZebonWorkbenchContainer;
import com.hardcoded.utility.ModItems;
import com.hardcoded.utility.ModRecipeSerializers;
import com.hardcoded.utility.ModTags;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ZebonWorkbenchRecipe implements IRecipe<IInventory> {
	protected final ResourceLocation id;
	protected final Ingredient item1;
	protected final Ingredient item2;
	protected final ItemStack result;
	protected final String group;
	protected final int craftTime;
	
	public ZebonWorkbenchRecipe(ResourceLocation idIn, String groupIn, Ingredient item1In, Ingredient item2In, ItemStack resultIn, int craftTimeIn) {
		this.id = idIn;
		this.group = groupIn;
		this.item1 = item1In;
		this.item2 = item2In;
		this.result = resultIn;
		this.craftTime = craftTimeIn;
	}
	
	public ItemStack getIcon() {
		return new ItemStack(ModItems.ZEBON_INGOT.get());
	}
	
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.from(Ingredient.EMPTY, item1, item2);
	}
	
	public String getGroup() {
		return group;
	}
	
	public int getCraftTime() {
		return craftTime;
	}
	
	public IRecipeType<?> getType() {
		return ModTags.ZEBON_RECIPES;
	}
	
	public boolean matches(IInventory inv, World worldIn) {
		if(inv.getSizeInventory() >= 4) {
			ItemStack stack1 = inv.getStackInSlot(ZebonWorkbenchContainer.INGREDIENT_1_SLOT);
			ItemStack stack2 = inv.getStackInSlot(ZebonWorkbenchContainer.INGREDIENT_2_SLOT);
			
			return (item1.test(stack1) && item2.test(stack2))
				|| (item1.test(stack2) && item2.test(stack1));
		}
		
		return false;
	}
	
	public ItemStack getCraftingResult(IInventory inv) {
		return result.copy();
	}
	
	public boolean canFit(int width, int height) {
		return true;
	}
	
	public ItemStack getRecipeOutput() {
		return result;
	}
	
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ModRecipeSerializers.ZEBON_WORKBENCH.get();
	}
	
	public static class Serializer implements IRecipeSerializer<ZebonWorkbenchRecipe> {
		private ResourceLocation registryName = null;
		
		public IRecipeSerializer<?> setRegistryName(ResourceLocation name) {
			this.registryName = name;
			return Serializer.this;
		}
		
		public ResourceLocation getRegistryName() {
			return registryName;
		}
		
		@SuppressWarnings("unchecked")
		public Class<IRecipeSerializer<?>> getRegistryType() {
			return (Class<IRecipeSerializer<?>>)this.getClass();
		}
		
		public ZebonWorkbenchRecipe read(ResourceLocation recipeId, JsonObject json) {
			String group = JSONUtils.getString(json, "group", "");
			Ingredient item1 = Ingredient.deserialize(json.get("item1"));
			Ingredient item2 = Ingredient.deserialize(json.get("item2"));
			int cookTime = JSONUtils.getInt(json, "craftTime");
			ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			return new ZebonWorkbenchRecipe(recipeId, group, item1, item2, itemstack, cookTime);
		}
		
		public ZebonWorkbenchRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String group = buffer.readString();
			Ingredient item1 = Ingredient.read(buffer);
			Ingredient item2 = Ingredient.read(buffer);
			int craftTime = buffer.readInt();
			ItemStack itemstack = buffer.readItemStack();
			return new ZebonWorkbenchRecipe(recipeId, group, item1, item2, itemstack, craftTime);
		}
		
		public void write(PacketBuffer buffer, ZebonWorkbenchRecipe recipe) {
			buffer.writeString(recipe.group);
			recipe.item1.write(buffer);
			recipe.item2.write(buffer);
			buffer.writeItemStack(recipe.result);
			buffer.writeVarInt(recipe.craftTime);
		}
	}
	
	@Override
	public String toString() {
		return String.format("{ %s, %s } = [ %s ]", item1.serialize(), item2.serialize(), result);
	}
}
