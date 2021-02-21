package com.hardcoded.zeboncraft.utility;

import java.util.function.Supplier;

import com.hardcoded.zeboncraft.recipe.ZebonWorkbenchRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;

public class ModRecipeSerializers {
	public static final RegistryObject<IRecipeSerializer<ZebonWorkbenchRecipe>> ZEBON_WORKBENCH = register("zebon_workbench",
			() -> new ZebonWorkbenchRecipe.Serializer());
	
	private static <T extends IRecipeSerializer<?>> RegistryObject<T> register(String name, Supplier<T> block) {
		return Registration.RECIPE_SERIALIZERS.register(name, block);
	}
	
	static void register() {
		
	}
}
