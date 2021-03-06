package com.hardcoded.zeboncraft.tileentity;

import com.hardcoded.zeboncraft.container.ZebonWorkbenchContainer;
import com.hardcoded.zeboncraft.recipe.ZebonWorkbenchRecipe;
import com.hardcoded.zeboncraft.utility.ModTags;
import com.hardcoded.zeboncraft.utility.ModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

public class ZebonWorkbenchTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {
	protected NonNullList<ItemStack> items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
	protected int burnTime;
	protected int totalBurnTime;
	protected int craftingTime;
	protected int totalCraftingTime;
	
	public static final int BURN_TIME = 0;
	public static final int TOTAL_BURN_TIME = 1;
	public static final int CRAFT_TIME = 2;
	public static final int TOTAL_CRAFT_TIME = 3;
	
	protected final IIntArray data = new IIntArray() {
		public int get(int index) {
			switch(index) {
				case BURN_TIME: return burnTime;
				case TOTAL_BURN_TIME: return totalBurnTime;
				case CRAFT_TIME: return craftingTime;
				case TOTAL_CRAFT_TIME: return totalCraftingTime;
			}
			
			return 0;
		}
		
		public void set(int index, int value) {
			switch(index) {
				case BURN_TIME: burnTime = value; break;
				case TOTAL_BURN_TIME: totalBurnTime = value; break;
				case CRAFT_TIME: craftingTime = value; break;
				case TOTAL_CRAFT_TIME: totalCraftingTime = value; break;
			}
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
	
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = items.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		items.set(index, stack);
		
		if(stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}

		if((index == ZebonWorkbenchContainer.INGREDIENT_1_SLOT || index == ZebonWorkbenchContainer.INGREDIENT_2_SLOT) && !flag) {
			totalCraftingTime = calculateCraftingTime();
			craftingTime = 0;
			markDirty();
		}
	}
	
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(items, index);
	}
	
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch(index) {
			case ZebonWorkbenchContainer.FUEL_SLOT: return FurnaceTileEntity.isFuel(stack);
			case ZebonWorkbenchContainer.INGREDIENT_1_SLOT:
			case ZebonWorkbenchContainer.INGREDIENT_2_SLOT: return true;
		}
		
		return false;
	}
	
	public boolean isUsableByPlayer(PlayerEntity player) {
		return true;
	}
	
	public void clear() {
		items.clear();
	}
	
	public void tick() {
		boolean flag = false;
		
		if(isBurning()) {
			burnTime--;
		}
		
		if(!world.isRemote) {
			ItemStack itemstack = items.get(ZebonWorkbenchContainer.FUEL_SLOT);
			
			if(isBurning() || !itemstack.isEmpty() && (!items.get(ZebonWorkbenchContainer.INGREDIENT_1_SLOT).isEmpty() && !items.get(ZebonWorkbenchContainer.INGREDIENT_2_SLOT).isEmpty())) {
				ZebonWorkbenchRecipe irecipe = (ZebonWorkbenchRecipe)world.getRecipeManager().getRecipe(ModTags.ZEBON_WORKBENCH, this, world).orElse(null);
				
				// If we are not burning and the recipe is valid
				if(!isBurning() && canCraft(irecipe)) {
					burnTime = getBurnTime(itemstack);
					totalBurnTime = burnTime;
					
					if(isBurning()) {
						flag = true;
						
						if(itemstack.hasContainerItem()) {
							items.set(1, itemstack.getContainerItem());
						} else if(!itemstack.isEmpty()) {
							itemstack.shrink(1);
							
							if(itemstack.isEmpty()) {
								items.set(1, itemstack.getContainerItem());
							}
						}
					}
				}
				
				if(isBurning() && canCraft(irecipe)) {
					craftingTime++;
					if(craftingTime == totalCraftingTime) {
						craftingTime = 0;
						totalCraftingTime = irecipe.getCraftTime();
						craft(irecipe);
						flag = true;
					}
				} else {
					craftingTime = 0;
				}
			} else if(!isBurning() && craftingTime > 0) {
				craftingTime = MathHelper.clamp(craftingTime - 2, 0, totalCraftingTime);
			}
		}
		
		if(flag) {
			markDirty();
		}
	}
	
	private void craft(ZebonWorkbenchRecipe recipe) {
		if(recipe != null && canCraft(recipe)) {
			ItemStack stack1 = items.get(ZebonWorkbenchContainer.INGREDIENT_1_SLOT);
			ItemStack stack2 = items.get(ZebonWorkbenchContainer.INGREDIENT_2_SLOT);
			ItemStack result = items.get(ZebonWorkbenchContainer.RESULT_SLOT);
			ItemStack craftingResult = recipe.getRecipeOutput();
			
			if(result.isEmpty()) {
				items.set(2, craftingResult.copy());
			} else if(result.getItem() == craftingResult.getItem()) {
				result.grow(craftingResult.getCount());
			}
			
			stack1.shrink(1);
			stack2.shrink(1);
		}
	}
	
	public boolean canCraft(ZebonWorkbenchRecipe recipe) {
		if(recipe == null) return false;
		
		ItemStack output = recipe.getRecipeOutput();
		if(output.isEmpty()) return false;
		
		ItemStack result = items.get(ZebonWorkbenchContainer.RESULT_SLOT);
		return !(!result.isEmpty() && result.getItem() != output.getItem());
	}
	
	protected int getBurnTime(ItemStack fuel) {
		if(fuel.isEmpty()) return 0;
		return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel);
	}
	
	protected int calculateCraftingTime() {
		return world.getRecipeManager().getRecipe(ModTags.ZEBON_WORKBENCH, this, world).map(ZebonWorkbenchRecipe::getCraftTime).orElse(200);
	}
	
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, items);
		this.burnTime = nbt.getInt("BurnTime");
		this.craftingTime = nbt.getInt("CraftTime");
		this.totalCraftingTime = nbt.getInt("TotalCraftTime");
		this.totalBurnTime = getBurnTime(items.get(ZebonWorkbenchContainer.FUEL_SLOT));
	}
	
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, items);
		compound.putInt("BurnTime", burnTime);
		compound.putInt("CraftTime", craftingTime);
		compound.putInt("TotalCraftTime", totalCraftingTime);
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
		return isItemValidForSlot(index, itemStackIn);
	}
	
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		if(direction == Direction.DOWN && index == ZebonWorkbenchContainer.RESULT_SLOT) {
			return true;
		}
		
		return false;
	}
	
	private LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		if(!removed && facing == null && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if(facing == Direction.UP) {
				return handlers[0].cast();
			} else if(facing == Direction.DOWN) {
				return handlers[1].cast();
			} else {
				return handlers[2].cast();
			}
		}
		
		return super.getCapability(capability, facing);
	}
	
	protected void invalidateCaps() {
		super.invalidateCaps();
		
		for(int x = 0; x < handlers.length; x++) {
			handlers[x].invalidate();
		}
	}
	
	public int getBurnTime() {
		return burnTime;
	}
	
	public int getTotalBurnTime() {
		return totalBurnTime;
	}
	
	public boolean isBurning() {
		return burnTime > 0;
	}
	
	public int getCraftingTime() {
		return craftingTime;
	}
	
	public int getTotalCraftingTime() {
		return totalCraftingTime;
	}
}
