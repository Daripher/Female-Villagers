package daripher.femalevillagers.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import daripher.femalevillagers.entity.FemaleVindicator;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class FemaleVindicatorRenderer extends IllagerRenderer<FemaleVindicator> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_vindicator.png");

	public FemaleVindicatorRenderer(EntityRendererProvider.Context context) {
		super(context, new IllagerModel<>(context.bakeLayer(FemaleIllagerModel.LAYER_LOCATION)), 0.5F);
		addLayer(new ItemInHandLayer<FemaleVindicator, IllagerModel<FemaleVindicator>>(this) {
			public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, FemaleVindicator entity, float p_116356_, float p_116357_,
					float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
				if (entity.isAggressive()) {
					super.render(p_116352_, p_116353_, p_116354_, entity, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
				}
			}
		});
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleVindicator villager) {
		return SKIN_LOCATION;
	}
}
