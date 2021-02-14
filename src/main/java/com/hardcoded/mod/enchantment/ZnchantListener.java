package com.hardcoded.mod.enchantment;

import java.util.Map;

import com.hardcoded.utility.ModEnchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class ZnchantListener {
	@SubscribeEvent
	public void onRightClick(PlayerInteractEvent e) {
		PlayerEntity p = e.getPlayer();
		
		ItemStack stack = p.inventory.getCurrentItem();
		if(!stack.isEnchanted()) return;
		
		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
		
		Integer level = map.getOrDefault(ModEnchantments.ZNCHANT.get(), 0);
		if(level < 1) return;
		
		System.out.println("Right click with Znchant Enchantment");
	}
}
