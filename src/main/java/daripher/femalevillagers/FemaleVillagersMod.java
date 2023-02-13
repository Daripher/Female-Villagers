package daripher.femalevillagers;

import daripher.femalevillagers.init.EntityInit;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FemaleVillagersMod.MOD_ID)
public class FemaleVillagersMod {
	public static final String MOD_ID = "femalevillagers";

	public FemaleVillagersMod() {
		var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		EntityInit.REGISTRY.register(modEventBus);
	}
}
