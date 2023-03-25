package daripher.femalevillagers.client.init;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import daripher.femalevillagers.client.model.FemaleVillagerModel;
import daripher.femalevillagers.client.model.FemaleZombieVillagerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModelLayerInit {
	@SubscribeEvent
	public static void registerModelLayers(FMLClientSetupEvent event) {
		ForgeHooksClient.registerLayerDefinition(FemaleVillagerModel.MAIN_LAYER_LOCATION, FemaleVillagerModel::createBodyLayer);
		ForgeHooksClient.registerLayerDefinition(FemaleVillagerModel.HAIR_LAYER_LOCATION, FemaleVillagerModel::createHairLayer);
		ForgeHooksClient.registerLayerDefinition(FemaleZombieVillagerModel.LAYER_LOCATION, FemaleZombieVillagerModel::createBodyLayer);
		ForgeHooksClient.registerLayerDefinition(FemaleIllagerModel.LAYER_LOCATION, FemaleIllagerModel::createBodyLayer);
	}
}
