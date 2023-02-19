package daripher.femalevillagers;

import daripher.femalevillagers.compat.Compatibilities;
import daripher.femalevillagers.init.EntityInit;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FemaleVillagersMod.MOD_ID)
public class FemaleVillagersMod {
	public static final String MOD_ID = "femalevillagers";

	public FemaleVillagersMod() {
		EntityInit.REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());

		if (ModList.get().isLoaded("villagernames")) {
			Compatibilities.VILLAGER_NAMES.addCompatibility();
		}

		if (ModList.get().isLoaded("guardvillagers")) {
			Compatibilities.GUARD_VILLAGERS.addCompatibility();
		}
	}
}
