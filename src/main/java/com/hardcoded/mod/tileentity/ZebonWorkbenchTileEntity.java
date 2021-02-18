package com.hardcoded.mod.tileentity;

import com.hardcoded.mod.container.ZebonWorkbenchContainer;
import com.hardcoded.utility.ModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ZebonWorkbenchTileEntity extends LockableTileEntity implements ITickableTileEntity {
	protected NonNullList<ItemStack> items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
	protected final IIntArray data = new IIntArray() {
		public int get(int index) {
			return 0;
		}
		
		public void set(int index, int value) {
			
		}
		
		public int size() {
			return 4;
		}
	};
		
	public ZebonWorkbenchTileEntity() {
		super(ModTileEntities.ZEBON_WORKBENCH);
	}
	
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.zebon_workbench");
	}
	
	protected Container createMenu(int id, PlayerInventory player) {
		return new ZebonWorkbenchContainer(id, player, this, data);
	}

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return items.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack stack = items.get(index);
		return new ItemStack(stack.getItem(), stack.getCount() - count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return items.remove(index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		items.set(index, stack);
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		return true;
	}

	@Override
	public void clear() {
		items.clear();
	}

	@Override
	public void tick() {
		// TODO
		
		// FIXME: Add burning timer
		// FIXME: Link with the screen renderer
	}
	
	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		ItemStackHelper.loadAllItems(nbt, items);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, items);
		return compound;
	}
}
