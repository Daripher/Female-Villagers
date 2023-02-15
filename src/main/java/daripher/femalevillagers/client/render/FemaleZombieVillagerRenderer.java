package daripher.femalevillagers.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleZombieVillagerModel;
import daripher.femalevillagers.entity.FemaleZombieVillager;
import net.minecraft.client.model.ZombieVillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.VillagerProfessionLayer;
import net.minecraft.resources.ResourceLocation;

public class FemaleZombieVillagerRenderer extends HumanoidMobRenderer<FemaleZombieVillager, ZombieVillagerModel<FemaleZombieVillager>> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_zombie_villager.png");

	public FemaleZombieVillagerRenderer(EntityRendererProvider.Context context) {
		super(context, new ZombieVillagerModel<>(context.bakeLayer(FemaleZombieVillagerModel.LAYER_LOCATION)), 0.5F);
		addLayer(new HumanoidArmorLayer<>(this, new ZombieVillagerModel<>(context.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_INNER_ARMOR)),
				new ZombieVillagerModel<>(context.bakeLayer(ModelLayers.ZOMBIE_VILLAGER_OUTER_ARMOR))));
		addLayer(new VillagerProfessionLayer<>(this, context.getResourceManager(), "zombie_villager"));
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleZombieVillager entity) {
		return SKIN_LOCATION;
	}

	@Override
	protected boolean isShaking(FemaleZombieVillager entity) {
		return super.isShaking(entity) || entity.isConverting();
	}

	@Override
	protected void scale(FemaleZombieVillager villager, PoseStack poseStack, float partialTicks) {
		var defaultScale = 0.89F;
		poseStack.scale(defaultScale, defaultScale, defaultScale);
	}
}
