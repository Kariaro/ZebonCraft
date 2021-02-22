package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.recipe.ZebonWorkbenchRecipe;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

@SuppressWarnings("unused")
public class ModTags {
	public static final IRecipeType<ZebonWorkbenchRecipe> ZEBON_WORKBENCH = registerRecipeType("zebon_workbench");
	
	public static class Blocks {
		public static final IOptionalNamedTag<Block> MUSHROOMS = tag("mushrooms");
		
		private static IOptionalNamedTag<Block> tag(String name) {
			return BlockTags.createOptional(new ResourceLocation(ZebonCraft.MOD_ID, name));
		}
	}
	
	public static class Items {
		private static IOptionalNamedTag<Item> tag(String name) {
			return ItemTags.createOptional(new ResourceLocation(ZebonCraft.MOD_ID, name));
		}
	}
	
	public static class Fluids {
		private static IOptionalNamedTag<Fluid> tag(String name) {
			return FluidTags.createOptional(new ResourceLocation(ZebonCraft.MOD_ID, name));
		}
	}
	
	
	static void register() {}
	
	@SuppressWarnings("unchecked")
	private static <T extends IRecipe<?>> IRecipeType<T> registerRecipeType(String name) {
		return (IRecipeType<T>)IRecipeType.register(ZebonCraft.MOD_ID + ":" + name);
	}
}
