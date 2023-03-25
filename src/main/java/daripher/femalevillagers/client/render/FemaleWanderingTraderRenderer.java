package daripher.femalevillagers.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleVillagerModel;
import daripher.femalevillagers.client.render.layer.ItemInVillagerHandLayer;
import daripher.femalevillagers.entity.FemaleWanderingTrader;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.resources.ResourceLocation;

public class FemaleWanderingTraderRenderer extends MobRenderer<FemaleWanderingTrader, FemaleVillagerModel<FemaleWanderingTrader>> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_wandering_trader.png");

	public FemaleWanderingTraderRenderer(EntityRendererProvider.Context context) {
		super(context, new FemaleVillagerModel<>(context.bakeLayer(FemaleVillagerModel.MAIN_LAYER_LOCATION)), 0.5F);
		addLayer(new CustomHeadLayer<>(this, context.getModelSet()));
		addLayer(new ItemInVillagerHandLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleWanderingTrader villager) {
		return SKIN_LOCATION;
	}

	@Override
	protected void scale(FemaleWanderingTrader villager, PoseStack poseStack, float partialTicks) {
		var defaultScale = 0.9375F;
		shadowRadius = 0.5F;

		if (villager.isBaby()) {
			defaultScale *= 0.5F;
			shadowRadius *= 0.5F;
		}

		poseStack.scale(defaultScale, defaultScale, defaultScale);
	}
}
