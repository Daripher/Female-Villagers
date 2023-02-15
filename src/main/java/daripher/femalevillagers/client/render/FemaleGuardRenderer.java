package daripher.femalevillagers.client.render;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleGuardArmorModel;
import daripher.femalevillagers.client.model.FemaleGuardModel;
import daripher.femalevillagers.entity.FemaleGuard;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class FemaleGuardRenderer extends HumanoidMobRenderer<FemaleGuard, HumanoidModel<FemaleGuard>> {
	public FemaleGuardRenderer(EntityRendererProvider.Context context) {
		super(context, new FemaleGuardModel(context.bakeLayer(FemaleGuardModel.LAYER_LOCATION)), 0.5F);
		addLayer(new HumanoidArmorLayer<>(this,
				new FemaleGuardArmorModel(context.bakeLayer(FemaleGuardArmorModel.INNER_LAYER_LOCATION)),
				new FemaleGuardArmorModel(context.bakeLayer(FemaleGuardArmorModel.OUTER_LAYER_LOCATION))));
	}

	@Override
	public void render(FemaleGuard entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		setModelVisibilities(entityIn);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	private void setModelVisibilities(FemaleGuard entityIn) {
		var guardModel = getModel();
		var mainHandItem = entityIn.getMainHandItem();
		var offhandItem = entityIn.getOffhandItem();
		guardModel.setAllVisible(true);
		var mainHandPose = getArmPose(entityIn, mainHandItem, offhandItem, InteractionHand.MAIN_HAND);
		var offhandPose = getArmPose(entityIn, mainHandItem, offhandItem, InteractionHand.OFF_HAND);
		guardModel.crouching = entityIn.isCrouching();

		if (entityIn.getMainArm() == HumanoidArm.RIGHT) {
			guardModel.rightArmPose = mainHandPose;
			guardModel.leftArmPose = offhandPose;
		} else {
			guardModel.rightArmPose = offhandPose;
			guardModel.leftArmPose = mainHandPose;
		}
	}

	private HumanoidModel.ArmPose getArmPose(FemaleGuard entity, ItemStack mainHandItem, ItemStack offhandItem, InteractionHand hand) {
		var armPose = HumanoidModel.ArmPose.EMPTY;
		var itemInHand = hand == InteractionHand.MAIN_HAND ? mainHandItem : offhandItem;

		if (!itemInHand.isEmpty()) {
			armPose = HumanoidModel.ArmPose.ITEM;

			if (entity.getUseItemRemainingTicks() > 0) {
				var useAnimation = itemInHand.getUseAnimation();

				switch (useAnimation) {
					case BLOCK:
						armPose = HumanoidModel.ArmPose.BLOCK;
						break;
					case BOW:
						armPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
						break;
					case SPEAR:
						armPose = HumanoidModel.ArmPose.THROW_SPEAR;
						break;
					case CROSSBOW:
						if (hand == entity.getUsedItemHand()) {
							armPose = HumanoidModel.ArmPose.CROSSBOW_CHARGE;
						}
						break;
					default:
						armPose = HumanoidModel.ArmPose.EMPTY;
						break;
				}
			} else {
				var hasCrossbowInMainHand = mainHandItem.getItem() instanceof CrossbowItem;
				var hasCrossbowInOffhand = offhandItem.getItem() instanceof CrossbowItem;

				if (hasCrossbowInMainHand && entity.isAggressive()) {
					armPose = HumanoidModel.ArmPose.CROSSBOW_HOLD;
				}

				if (hasCrossbowInOffhand && mainHandItem.getItem().getUseAnimation(mainHandItem) == UseAnim.NONE && entity.isAggressive()) {
					armPose = HumanoidModel.ArmPose.CROSSBOW_HOLD;
				}
			}
		}

		return armPose;
	}

	@Override
	protected void scale(FemaleGuard entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
		var defaultScale = 0.9375F;
		matrixStackIn.scale(defaultScale, defaultScale, defaultScale);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(FemaleGuard entity) {
		return new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_guard/guard_" + entity.getGuardVariant() + ".png");
	}
}
