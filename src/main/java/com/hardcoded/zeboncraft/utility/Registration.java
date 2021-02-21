package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.ZebonCraft;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ZebonCraft.MOD_ID);
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ZebonCraft.MOD_ID);
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ZebonCraft.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ZebonCraft.MOD_ID);
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ZebonCraft.MOD_ID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ZebonCraft.MOD_ID);
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ZebonCraft.MOD_ID);
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ZebonCraft.MOD_ID);
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ZebonCraft.MOD_ID);
	
	public static void register() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ENCHANTMENTS.register(modEventBus);
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		CONTAINERS.register(modEventBus);
		TILE_ENTITIES.register(modEventBus);
		RECIPE_SERIALIZERS.register(modEventBus);
		PARTICLE_TYPES.register(modEventBus);
		EFFECTS.register(modEventBus);
		
		ModBlocks.register();
		ModItems.register();
		ModEnchantments.register();
		ModBiomes.register();
		ModTileEntities.register();
		ModParticles.register();
		
		ModContainers.register();
		ModTags.register();
		ModRecipeSerializers.register();
		
		ModEffects.register();
	}
}
