package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.ZebonCraft;
import com.hardcoded.zeboncraft.armor.ZebonArmor;
import com.hardcoded.zeboncraft.item.ZebonSword;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
	public static final RegistryObject<Item> ZEBON_INGOT = Registration.ITEMS.register("zebon_ingot", () ->
			new Item(new Item.Properties().group(ModItemGroups.ZEBON)));
	
	public static final RegistryObject<Item> ZEBON_ZVORD = Registration.ITEMS.register("zebon_zvord", () ->
			new ZebonSword(ModToolTiers.ZEBON_TIER, 2, 1, new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> ZEBON_HOE = Registration.ITEMS.register("zebon_hoe", () ->
			new HoeItem(ModToolTiers.ZEBON_TIER, 2, 1, new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> ZEBON_AXE = Registration.ITEMS.register("zebon_axe", () ->
			new AxeItem(ModToolTiers.ZEBON_TIER, 2, 1, new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> ZEBON_SHOVEL = Registration.ITEMS.register("zebon_shovel", () ->
			new ShovelItem(ModToolTiers.ZEBON_TIER, 2, 1, new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> ZEBON_PICKAXE = Registration.ITEMS.register("zebon_pickaxe", () ->
			new PickaxeItem(ModToolTiers.ZEBON_TIER, 2, 1, new Item.Properties().group(ModItemGroups.ZEBON)));
	
	public static final RegistryObject<Item> ZEBON_HELMET = Registration.ITEMS.register("zebon_helmet", () ->
			new ZebonArmor(EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> ZEBON_CHESTPLATE = Registration.ITEMS.register("zebon_chestplate", () ->
			new ZebonArmor(EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> ZEBON_LEGGINGS = Registration.ITEMS.register("zebon_leggings", () ->
			new ZebonArmor(EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> ZEBON_BOOTS = Registration.ITEMS.register("zebon_boots", () ->
			new ZebonArmor(EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.ZEBON)));
	
	public static final RegistryObject<Item> ZEBON_HORSE_ARMOR = Registration.ITEMS.register("zebon_horse_armor", () ->
			new HorseArmorItem(5, new ResourceLocation(ZebonCraft.MOD_ID, "textures/entity/horse/armor/horse_armor_zebon.png"), new Item.Properties().group(ModItemGroups.ZEBON)));
	
	public static final RegistryObject<Item> BLUE_GRIT = Registration.ITEMS.register("blue_grit", () ->
			new Item(new Item.Properties().group(ModItemGroups.ZEBON)));
	public static final RegistryObject<Item> PURPLE_DUST = Registration.ITEMS.register("purple_dust", () ->
			new Item(new Item.Properties().group(ModItemGroups.ZEBON)));
	

	public static final RegistryObject<Item> PILE_OF_MUSHROOMS = Registration.ITEMS.register("pile_of_mushrooms", () ->
			new Item(new Item.Properties().group(ModItemGroups.ZEBON)));
	
	
	static void register() {
		
	}
}
