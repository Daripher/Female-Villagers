package daripher.femalevillagers.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class Config {
	public static final Common COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	public static class Common {
		public final ConfigValue<Boolean> hideVillagerNoses;
		public final ConfigValue<Boolean> useDefaultVillagerArms;

		public Common(ForgeConfigSpec.Builder builder) {
			hideVillagerNoses = builder.define("hide_villager_noses", false);
			useDefaultVillagerArms = builder.define("use_default_villagers_arms", false);
		}
	}

	static {
		Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
