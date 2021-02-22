package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.ZebonCraft;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ZebonCraft.MOD_ID, bus = Bus.MOD)
public class ModPotions {
	public static final RegistryObject<Potion> ANTIDOTE_POTION = Registration.POTIONS.register("antidote_potion",
			() -> new Potion(new EffectInstance(ModEffects.ANTIDOTE.get(), 3600)));
	
	
	static void register() {
		
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerPotions(final RegistryEvent.Register<Potion> event) {
		//System.out.println("Register POTIONS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		BrewingRecipeRegistry.addRecipe(
			Ingredient.fromStacks(PotionUtils.addPotionToItemStack(Items.POTION.getDefaultInstance(), Potions.HEALING)),
			Ingredient.fromItems(ModItems.PILE_OF_MUSHROOMS.get()),
			PotionUtils.addPotionToItemStack(Items.POTION.getDefaultInstance(), ModPotions.ANTIDOTE_POTION.get())
		);
	}
}
