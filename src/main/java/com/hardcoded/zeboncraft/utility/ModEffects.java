package com.hardcoded.zeboncraft.utility;

import com.hardcoded.zeboncraft.effect.ZebonSporesEffect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;

public class ModEffects {
	public static final RegistryObject<Effect> SPORES = Registration.EFFECTS.register("spores",
			() -> new ZebonSporesEffect(EffectType.HARMFUL, 0xaa88ff7f));
	
	public static final RegistryObject<Effect> ANTIDOTE = Registration.EFFECTS.register("antidote",
			() -> new ZebonSporesEffect(EffectType.BENEFICIAL, 0xf3a0e2));
	
	static void register() {
	}
}
