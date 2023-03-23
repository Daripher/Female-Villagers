package daripher.femalevillagers.client.init;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.render.FemaleIllagerRenderer;
import daripher.femalevillagers.client.render.FemaleIllusionerRenderer;
import daripher.femalevillagers.client.render.FemaleVillagerRenderer;
import daripher.femalevillagers.client.render.FemaleVindicatorRenderer;
import daripher.femalevillagers.client.render.FemaleWanderingTraderRenderer;
import daripher.femalevillagers.client.render.FemaleZombieVillagerRenderer;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class RendererInit {
	@SubscribeEvent
	public static void registerEntityRenderers(FMLClientSetupEvent event) {
		EntityRenderers.register(EntityInit.FEMALE_VILLAGER.get(), FemaleVillagerRenderer::new);
		EntityRenderers.register(EntityInit.FEMALE_ZOMBIE_VILLAGER.get(), FemaleZombieVillagerRenderer::new);
		EntityRenderers.register(EntityInit.FEMALE_WANDERING_TRADER.get(), FemaleWanderingTraderRenderer::new);
		EntityRenderers.register(EntityInit.FEMALE_PILLAGER.get(), ctx -> new FemaleIllagerRenderer<>(ctx, "female_pillager"));
		EntityRenderers.register(EntityInit.FEMALE_VINDICATOR.get(), FemaleVindicatorRenderer::new);
		EntityRenderers.register(EntityInit.FEMALE_EVOKER.get(), ctx -> new FemaleIllagerRenderer<>(ctx, "female_evoker"));
		EntityRenderers.register(EntityInit.FEMALE_ILLUSIONER.get(), FemaleIllusionerRenderer::new);
	}
}
