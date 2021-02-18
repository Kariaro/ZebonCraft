package com.hardcoded.utility;

import java.util.function.Supplier;

import com.hardcoded.mod.container.ZebonWorkbenchContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {
	public static final RegistryObject<ContainerType<ZebonWorkbenchContainer>> ZEBON_WORKBENCH = register("zebon_workbench", () ->
			new ContainerType<>(ZebonWorkbenchContainer::new));
	
	private static <T extends ContainerType<?>> RegistryObject<T> register(String name, Supplier<T> supplier) {
		return Registration.CONTAINERS.register(name, supplier);
	}
	
	static void register() {}
}
