package daripher.femalevillagers.client.event;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.config.Config;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, value = Dist.CLIENT)
public class FemaleVillagersClientEvents {
	@SubscribeEvent
	public static void setVillagersNoseVisibility(RenderLivingEvent.Pre<? extends Villager, EntityModel<? extends Villager>> event) {
		if (!Config.COMMON.hideVillagerNoses.get()) {
			return;
		}

		if (event.getRenderer().getModel() instanceof VillagerModel<?> model) {
			ModelPart nose = ObfuscationReflectionHelper.getPrivateValue(VillagerModel.class, model, "f_104044_");
			nose.visible = false;
		}
	}
}
