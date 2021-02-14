package com.hardcoded.mod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ZebonSword extends SwordItem {
	public ZebonSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if(entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			int i = this.getUseDuration(stack) - timeLeft;
			
			if(i >= 10) {
				setCharged(playerentity.getHeldItem(Hand.MAIN_HAND), true);
			}
		}
	}
	
	public static void setCharged(ItemStack stack, boolean chargedIn) {
		CompoundNBT compoundnbt = stack.getOrCreateTag();
		compoundnbt.putBoolean("zebon_charged", chargedIn);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return ActionResult.resultConsume(itemstack);
	}
	
	public static boolean isCharged(ItemStack stack) {
		return stack.getTag().getBoolean("zebon_charged");
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//		stack.damageItem(1, attacker, (entity) -> {
//			entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
//		});
		
		setCharged(stack, false);
		return true;
	}
	
	public int getItemEnchantability() {
		return 0;
	}
}
