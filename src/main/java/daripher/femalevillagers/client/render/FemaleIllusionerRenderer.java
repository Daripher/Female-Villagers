package daripher.femalevillagers.client.render;

import java.util.function.Predicate;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import daripher.femalevillagers.client.render.layer.HideableItemInHandLayer;
import daripher.femalevillagers.entity.FemaleIllusioner;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FemaleIllusionerRenderer extends IllagerRenderer<FemaleIllusioner> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_illusioner.png");
	private static final Predicate<FemaleIllusioner> SHOULD_SHOW_ITEM_IN_HAND = e -> e.isCastingSpell() || e.isAggressive();

	public FemaleIllusionerRenderer(EntityRendererProvider.Context context) {
		super(context, new IllagerModel<>(context.bakeLayer(FemaleIllagerModel.LAYER_LOCATION)), 0.5F);
		addLayer(new HideableItemInHandLayer<>(this, SHOULD_SHOW_ITEM_IN_HAND));
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
