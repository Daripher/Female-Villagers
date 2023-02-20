package daripher.femalevillagers.init;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.guardvillagers.GuardVillagersCompatibility;
import net.minecraft.world.entity.raid.Raid.RaiderType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
public class RaiderTypeInit {
	@SubscribeEvent
	public static void register(FMLCommonSetupEvent event) {
		RaiderType.create("FEMALE_VINDICATOR", EntityInit.FEMALE_VINDICATOR.get(), new int[] { 0, 0, 2, 0, 1, 4, 2, 5 });
		RaiderType.create("FEMALE_EVOKER", EntityInit.FEMALE_EVOKER.get(), new int[] { 0, 0, 0, 0, 0, 1, 1, 2 });
		RaiderType.create("FEMALE_PILLAGER", EntityInit.FEMALE_PILLAGER.get(), new int[] { 0, 4, 3, 3, 4, 4, 4, 2 });

		if (ModList.get().isLoaded("guardvillagers")) {
			GuardVillagersCompatibility.INSTANCE.registerFemaleIllusionerRaiderType();
		}
	}
}
