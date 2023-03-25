package daripher.femalevillagers.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleVillagerModel;
import daripher.femalevillagers.client.render.layer.ItemInVillagerHandLayer;
import daripher.femalevillagers.client.render.layer.VillagerHairLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;

public class FemaleVillagerRenderer extends MobRenderer<Villager, FemaleVillagerModel<Villager>> {
	private final ResourceLocation textureLocation;

	public FemaleVillagerRenderer(EntityRendererProvider.Context context, String texture) {
		super(context, new FemaleVillagerModel<>(context.bakeLayer(FemaleVillagerModel.MAIN_LAYER_LOCATION)), 0.5F);
		this.textureLocation = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/" + texture + ".png");
		addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
		addLayer(new VillagerProfessionLayer<>(this, context.getResourceManager(), "villager"));
		addLayer(new ItemInVillagerHandLayer<>(this, context.getItemInHandRenderer()));
		addLayer(new VillagerHairLayer<>(this, new FemaleVillagerModel<>(context.bakeLayer(FemaleVillagerModel.HAIR_LAYER_LOCATION))));
	}

	@Override
	public ResourceLocation getTextureLocation(Villager villager) {
		return textureLocation;
	}

	@Override
	protected void scale(Villager villager, PoseStack poseStack, float partialTicks) {
		var defaultScale = 0.9375F;
		shadowRadius = 0.5F;

		if (villager.isBaby()) {
			defaultScale *= 0.5F;
			shadowRadius *= 0.5F;
		}

		poseStack.scale(defaultScale, defaultScale, defaultScale);
	}
}
