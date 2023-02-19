package daripher.femalevillagers.compat.theconjurer.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.theconjurer.client.model.FemaleConjurerModel;
import daripher.femalevillagers.compat.theconjurer.entity.FemaleConjurer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class FemaleConjurerRenderer extends IllagerRenderer<FemaleConjurer> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_conjurer.png");

	public FemaleConjurerRenderer(Context context) {
		super(context, new FemaleConjurerModel(context.bakeLayer(FemaleConjurerModel.LAYER_LOCATION)), 0.5F);
//		addLayer(new ConjurerSunglassesLayer(this, new FemaleConjurerModel(context.bakeLayer(IllagerRenderRefs.CONJURER_GLASSES))));
		addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()) {
			@Override
			public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, FemaleConjurer entitylivingbaseIn, float limbSwing,
					float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				if (entitylivingbaseIn.isCastingSpell())
					super.render(matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw,
							headPitch);
			}
		});
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleConjurer villager) {
		return SKIN_LOCATION;
	}
}
