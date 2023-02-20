package daripher.femalevillagers;

import daripher.femalevillagers.compat.guardvillagers.GuardVillagersCompatibility;
import daripher.femalevillagers.compat.theconjurer.TheConjurerCompatibility;
import daripher.femalevillagers.compat.villagernames.VillagerNamesCompatibility;
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
			VillagerNamesCompatibility.INSTANCE.addCompatibility();
		}

		if (ModList.get().isLoaded("guardvillagers")) {
			GuardVillagersCompatibility.INSTANCE.addCompatibility();
		}

		if (ModList.get().isLoaded("conjurer_illager")) {
			TheConjurerCompatibility.INSTANCE.addCompatibility();
		}
	}
}
