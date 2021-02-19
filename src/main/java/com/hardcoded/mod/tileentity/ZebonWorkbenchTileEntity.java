package com.hardcoded.mod.tileentity;

import com.hardcoded.mod.container.ZebonWorkbenchContainer;
import com.hardcoded.utility.ModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ZebonWorkbenchTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
	protected NonNullList<ItemStack> items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
	protected int burningTime;
	
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
		super(ModTileEntities.ZEBON_WORKBENCH.get());
	}
	
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.zebon_workbench");
	}
	
	protected Container createMenu(int id, PlayerInventory player) {
		return new ZebonWorkbenchContainer(id, player, this, data);
	}
	
	public int getSizeInventory() {
		return 4;
	}
	
	public boolean isEmpty() {
		for(ItemStack itemstack : items) {
			if(!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}
	
	public ItemStack getStackInSlot(int index) {
		return items.get(index);
	}
	
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(items, index, count);
	}
	
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(items, index);
	}
	
	public void setInventorySlotContents(int index, ItemStack stack) {
		items.set(index, stack);
	}
	
	public boolean isUsableByPlayer(PlayerEntity player) {
		return true;
	}
	
	public void clear() {
		items.clear();
	}
	
	public void tick() {
		// FIXME: Add burning timer
		// FIXME: Link with the screen renderer
		
		burningTime ++;
		burningTime %= 20;
	}
	
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, items);
		this.burningTime = nbt.getInt("burningTime");
	}
	
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, items);
		compound.putInt("burningTime", burningTime);
		return compound;
	}
	
	private static final int[] SLOT_DOWN = { ZebonWorkbenchContainer.RESULT_SLOT };
	private static final int[] SLOT_TOP  = { ZebonWorkbenchContainer.FUEL_SLOT };
	private static final int[] SLOT_SIDE = { ZebonWorkbenchContainer.INGREDIENT_1_SLOT, ZebonWorkbenchContainer.INGREDIENT_2_SLOT };
	public int[] getSlotsForFace(Direction side) {
		switch(side) {
			case DOWN: return SLOT_DOWN; 
			case UP: return SLOT_TOP;
			default: return SLOT_SIDE;
		}
	}
	
	public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
		switch(direction) {
			case DOWN: return false;
			case UP: return index == ZebonWorkbenchContainer.RESULT_SLOT;
			default:
		}
		
		return false;
	}
	
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		if(direction != Direction.DOWN) return true;
		return false;
	}
	
	public int getBurningTime() {
		return burningTime;
	}
}
