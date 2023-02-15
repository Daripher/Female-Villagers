package daripher.femalevillagers;

import daripher.femalevillagers.compat.VillagerNamesCompatibility;
import daripher.femalevillagers.init.EntityInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FemaleVillagersMod.MOD_ID)
public class FemaleVillagersMod {
	public static final String MOD_ID = "femalevillagers";

	public FemaleVillagersMod() {
		var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		var forgeEventBus = MinecraftForge.EVENT_BUS;
		EntityInit.REGISTRY.register(modEventBus);

		if (ModList.get().isLoaded("villagernames")) {
			modEventBus.addListener(VillagerNamesCompatibility::initCustomFemaleNames);
			forgeEventBus.addListener(VillagerNamesCompatibility::setVillagerName);
		}
	}
}
