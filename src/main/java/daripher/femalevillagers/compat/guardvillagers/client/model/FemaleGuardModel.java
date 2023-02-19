package daripher.femalevillagers.compat.guardvillagers.client.model;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.guardvillagers.entity.FemaleGuard;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;

public class FemaleGuardModel extends HumanoidModel<FemaleGuard> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_guard"), "main");
	public ModelPart quiver = body.getChild("quiver");
	public ModelPart leftShoulderPad = rightArm.getChild("shoulder_pad_left");
	public ModelPart rightShoulderPad = leftArm.getChild("shoulder_pad_right");

	public FemaleGuardModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		var model = new MeshDefinition();
		var root = model.getRoot();

		var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(52, 50).addBox(-4.0F, -23.25F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(-0.15F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		var breast = body.addOrReplaceChild("breast", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, -2.25F, 0.1309F, 0.0F, 0.0F));

		breast.addOrReplaceChild("breast_bottom_r1", CubeListBuilder.create().texOffs(52, 57).addBox(-4.0F, 4.25F, -0.25F, 8.0F, 1.0F, 4.0F, new CubeDeformation(-0.51F))
				.texOffs(53, 53).addBox(-4.0F, 0.25F, -0.25F, 8.0F, 5.0F, 3.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		var quiver = body.addOrReplaceChild("quiver", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		quiver.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(100, 0).addBox(-5.5F, -21.75F, 1.75F, 5.0F, 10.0F, 5.0F, new CubeDeformation(-0.25F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.8F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		root.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(16, 28).mirror().addBox(-2.2F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)).mirror(false),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		var head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(49, 99).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(0.0F, 2.0F, 0.0F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(54, 0).addBox(-1.0F, -1.0F, -5.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)),
				PartPose.offset(0.0F, -2.0F, 0.0F));

		root.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.275F)),
				PartPose.offset(0.0F, 2.0F, 0.0F));

		var left_arm = root.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(34, 48).mirror().addBox(0.25F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 4.0F, 0.0F, 0.2094F, 0.0F, 0.0F));

		left_arm.addOrReplaceChild("shoulder_pad_right",
				CubeListBuilder.create().texOffs(72, 33).addBox(-1.5F, -4.25F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		var right_arm = root.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(33, 75).addBox(-2.25F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 4.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		right_arm.addOrReplaceChild("shoulder_pad_left",
				CubeListBuilder.create().texOffs(40, 20).addBox(-3.5328F, -4.25F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		return LayerDefinition.create(model, 128, 128);
	}

	@Override
	public void setupAnim(FemaleGuard entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netbipedHeadYaw, float bipedHeadPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netbipedHeadYaw, bipedHeadPitch);
		var itemInHand = entityIn.getItemInHand(InteractionHand.MAIN_HAND);
		var isHoldingShootable = itemInHand.getItem() instanceof ProjectileWeaponItem;
		quiver.visible = isHoldingShootable;
		var hasChestplate = entityIn.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ArmorItem;
		leftShoulderPad.visible = !hasChestplate;
		rightShoulderPad.visible = !hasChestplate;
		leftArm.x = 3.5F;
		leftArm.y = 4;
		leftArm.z = 0;
		rightArm.x = -4.5F;
		rightArm.y = 4;
		rightArm.z = 0;
		body.x = 0;
		body.y = 24;
		body.z = 0;
		head.x = 0;
		head.y = 2;
		head.z = 0;
		hat.x = 0;
		hat.y = 2;
		hat.z = 0;

		if (entityIn.getKickTicks() > 0) {
			var kickAnimationProgress = 1F - Mth.abs(10 - 2 * entityIn.getKickTicks()) / 10F;
			rightLeg.xRot = Mth.lerp(kickAnimationProgress, rightLeg.xRot, -1.4F);
		}

		if (attackTime == 0 && entityIn.isAggressive() && !isHoldingShootable && entityIn.getDeltaMovement().horizontalDistanceSqr() > 0.005D
				&& !entityIn.getMainHandItem().isEmpty() && !entityIn.isBlocking()) {
			AnimationUtils.swingWeaponDown(rightArm, leftArm, entityIn, attackTime, ageInTicks);
		}

		if (entityIn.getMainArm() == HumanoidArm.RIGHT) {
			eatingAnimationRightHand(InteractionHand.MAIN_HAND, entityIn, ageInTicks);
			eatingAnimationLeftHand(InteractionHand.OFF_HAND, entityIn, ageInTicks);
		} else {
			eatingAnimationRightHand(InteractionHand.OFF_HAND, entityIn, ageInTicks);
			eatingAnimationLeftHand(InteractionHand.MAIN_HAND, entityIn, ageInTicks);
		}
	}

	public void eatingAnimationRightHand(InteractionHand hand, FemaleGuard entity, float ageInTicks) {
		var itemInHand = entity.getItemInHand(hand);
		var isDrinkingOrEating = itemInHand.getUseAnimation() == UseAnim.EAT || itemInHand.getUseAnimation() == UseAnim.DRINK;

		if (entity.isEating() && isDrinkingOrEating) {
			rightArm.yRot = -0.5F;
			rightArm.xRot = -1.3F;
			rightArm.zRot = Mth.cos(ageInTicks) * 0.1F;
			head.xRot = Mth.cos(ageInTicks) * 0.2F;
			head.yRot = 0F;
			hat.copyFrom(head);
		}
	}

	public void eatingAnimationLeftHand(InteractionHand hand, FemaleGuard entity, float ageInTicks) {
		var itemInHand = entity.getItemInHand(hand);
		var isDrinkingOrEating = itemInHand.getUseAnimation() == UseAnim.EAT || itemInHand.getUseAnimation() == UseAnim.DRINK;

		if (entity.isEating() && isDrinkingOrEating) {
			leftArm.yRot = 0.5F;
			leftArm.xRot = -1.3F;
			leftArm.zRot = Mth.cos(ageInTicks) * 0.1F;
			head.xRot = Mth.cos(ageInTicks) * 0.2F;
			head.yRot = 0F;
			hat.copyFrom(head);
		}
	}
}
