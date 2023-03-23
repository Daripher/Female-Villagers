package daripher.femalevillagers.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import daripher.femalevillagers.entity.FemaleIllusioner;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FemaleIllusionerRenderer extends IllagerRenderer<FemaleIllusioner> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_illusioner.png");

	public FemaleIllusionerRenderer(EntityRendererProvider.Context context) {
		super(context, new IllagerModel<>(context.bakeLayer(FemaleIllagerModel.LAYER_LOCATION)), 0.5F);
		addLayer(new ItemInHandLayer<FemaleIllusioner, IllagerModel<FemaleIllusioner>>(this, context.getItemInHandRenderer()) {
			public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, FemaleIllusioner entity, float p_116356_, float p_116357_,
					float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
				if (entity.isCastingSpell() || entity.isAggressive()) {
					super.render(p_116352_, p_116353_, p_116354_, entity, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
				}
			}
		});
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleIllusioner villager) {
		return SKIN_LOCATION;
	}

	@Override
	public void render(FemaleIllusioner entity, float p_114953_, float p_114954_, PoseStack poseStack, MultiBufferSource p_114956_, int p_114957_) {
		if (entity.isInvisible()) {
			renderIllusions(entity, p_114953_, p_114954_, poseStack, p_114956_, p_114957_);
		} else {
			super.render(entity, p_114953_, p_114954_, poseStack, p_114956_, p_114957_);
		}
	}

	public void renderIllusions(FemaleIllusioner entity, float p_114953_, float p_114954_, PoseStack poseStack, MultiBufferSource p_114956_, int p_114957_) {
		var illusionsOffsets = entity.getIllusionOffsets(p_114954_);
		var bob = getBob(entity, p_114954_);

		for (var i = 0; i < illusionsOffsets.length; ++i) {
			poseStack.pushPose();
			var illusionPosX = illusionsOffsets[i].x + Mth.cos(i + bob * 0.5F) * 0.025D;
			var illusionPosY = illusionsOffsets[i].y + Mth.cos(i + bob * 0.75F) * 0.0125D;
			var illusionPosZ = illusionsOffsets[i].z + Mth.cos(i + bob * 0.7F) * 0.025D;
			poseStack.translate(illusionPosX, illusionPosY, illusionPosZ);
			super.render(entity, p_114953_, p_114954_, poseStack, p_114956_, p_114957_);
			poseStack.popPose();
		}
	}

	@Override
	protected boolean isBodyVisible(FemaleIllusioner entity) {
		return true;
	}
}
