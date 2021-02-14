package com.hardcoded.mod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
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
//				if(!worldIn.isRemote) {
//					stack.damageItem(1, playerentity, (player) -> {
//						player.sendBreakAnimation(entityLiving.getActiveHand());
//					});
//					
//					TridentEntity tridententity = new TridentEntity(worldIn, playerentity, stack);
//					tridententity.func_234612_a_(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, 2.5F, 1.0F);
//					if(playerentity.abilities.isCreativeMode) {
//						tridententity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
//					}
//
//					worldIn.addEntity(tridententity);
//					worldIn.playMovingSound(null, tridententity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
//					if(!playerentity.abilities.isCreativeMode) {
//						playerentity.inventory.deleteStack(stack);
//					}
//				}

				// playerentity.addStat(Stats.ITEM_USED.get(this));
			}
		}
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return ActionResult.resultConsume(itemstack);
	}
	
//	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//		stack.damageItem(1, attacker, (entity) -> {
//			entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
//		});
//		return true;
//	}
	
	public int getItemEnchantability() {
		return 0;
	}
}
