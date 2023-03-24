package daripher.femalevillagers.client.model;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.config.Config;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.npc.AbstractVillager;

public class FemaleVillagerModel<T extends AbstractVillager> extends VillagerModel<T> implements ArmedModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_villager"), "main");
	protected final ModelPart rightArm;
	protected final ModelPart leftArm;
	protected final ModelPart head;
	protected final ModelPart hat;
	protected final ModelPart hatRim;
	protected final ModelPart rightLeg;
	protected final ModelPart leftLeg;
	protected final ModelPart nose;
	protected final ModelPart body;
	protected final ModelPart arms;

	public FemaleVillagerModel(ModelPart root) {
		super(root);
		rightArm = root.getChild("right_arm");
		leftArm = root.getChild("left_arm");
		head = root.getChild("head");
		hat = head.getChild("hat");
		hatRim = hat.getChild("hat_rim");
		nose = head.getChild("nose");
		rightLeg = root.getChild("right_leg");
		leftLeg = root.getChild("left_leg");
		body = root.getChild("body");
		arms = root.getChild("arms");

		if (Config.COMMON.useDefaultVillagerArms.get()) {
			leftArm.visible = false;
			rightArm.visible = false;
		} else {
			arms.visible = false;
		}
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = VillagerModel.createBodyModel();
		var root = meshDefinition.getRoot();
		root.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(40, 38).addBox(-4F, 4F, -1.5F, 8F, 3F, 4F, new CubeDeformation(-0.01F)).texOffs(44, 22).addBox(-6.5F, -1F, -1.5F, 3F, 8F, 4F).texOffs(44, 22).mirror()
				.addBox(3.5F, -1F, -1.5F, 3F, 8F, 4F), PartPose.offsetAndRotation(0F, 4F, 0F, -0.7854F, 0F, 0F));
		root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.2F, 0F, -2F, 4F, 12F, 4F, new CubeDeformation(-0.2F)), PartPose.offset(2F, 12F, 0F));
		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-1.8F, 0F, -2F, 4F, 12F, 4F, new CubeDeformation(-0.2F)), PartPose.offset(-2F, 12F, 0F));
		var head = root.getChild("head");
		var hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4F, -10F, -4F, 8F, 10F, 8F, new CubeDeformation(0.3F)), PartPose.ZERO);
		hat.addOrReplaceChild("hat_rim", CubeListBuilder.create().texOffs(30, 47).addBox(-8F, -8F, -6F, 16F, 16F, 1F, new CubeDeformation(0.3F)), PartPose.rotation(-1.5708F, 0F, 0F));
		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1F, -1F, -5.25F, 2F, 4F, 2F, new CubeDeformation(-0.4F)), PartPose.offset(0F, -2F, 0F));
		var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4F, 0F, -3F, 8F, 12F, 6F, new CubeDeformation(-0.45F)), PartPose.ZERO);
		body.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(0, 38).addBox(-4F, 0F, -3F, 8F, 20F, 6F, new CubeDeformation(0.05F)), PartPose.ZERO);
		var breast = body.addOrReplaceChild("breast", CubeListBuilder.create(), PartPose.offsetAndRotation(0F, 2F, -3F, 0.1309F, 0F, 0F));
		breast.addOrReplaceChild("breast_overlay", CubeListBuilder.create().texOffs(4, 42).addBox(-4F, 0F, 0F, 8F, 5F, 2F, new CubeDeformation(-0.2F)).texOffs(18, 29).mirror()
				.addBox(-4F, 3.25F, 0F, 8F, 2F, 4F, new CubeDeformation(-0.51F)).texOffs(19, 24).addBox(-4F, 0F, 0F, 8F, 5F, 3F, new CubeDeformation(-0.5F)), PartPose.rotation(-0.48F, 0F, 0F));
		breast.addOrReplaceChild("breast_bottom_overlay", CubeListBuilder.create().texOffs(2, 47).mirror().addBox(-4F, -0.5F, -1.5F, 8F, -1F, 4F, new CubeDeformation(-0.19F)),
				PartPose.offsetAndRotation(0F, 5.2916F, -0.9997F, -0.48F, 0F, 0F));
		var left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(45, 22).mirror().addBox(-1.5F, -2F, -2F, 3F, 8F, 4F), PartPose.offsetAndRotation(5F, 4F, 0F, -1.1781F, 0F, 0F));
		left_arm.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-2.5F, -2F, -4F, 4F, 3F, 0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, 0F, 3.1416F, 1.5708F));
		left_arm.addOrReplaceChild("cube_2", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-2.5F, -4F, -1F, 4F, 4F, 0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, -1.5708F, 0F, -1.5708F));
		left_arm.addOrReplaceChild("cube_3", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-2.5F, 0F, -2F, 4F, 4F, 0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, -1.5708F, 3.1416F, 1.5708F));
		left_arm.addOrReplaceChild("cube_4", CubeListBuilder.create().texOffs(40, 43).mirror().addBox(-1.5F, -1F, 0F, 4F, 3F, 0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, 0F, 3.1416F, -1.5708F));
		left_arm.addOrReplaceChild("cube_5", CubeListBuilder.create().texOffs(36, 38).addBox(-2.5F, -1F, 0F, 0F, 3F, 4F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, 3.1416F, 3.1416F, 1.5708F));
		var right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(45, 22).addBox(-1.5F, -2F, -2F, 3F, 8F, 4F), PartPose.offsetAndRotation(-5F, 4F, 0F, -1.0908F, 0F, 0F));
		right_arm.addOrReplaceChild("cube_6",
				CubeListBuilder.create().texOffs(36, 38).mirror().addBox(-2.5F, -2F, -4F, 0F, 3F, 4F, new CubeDeformation(0.01F)).texOffs(40, 43).addBox(-2.5F, -2F, 0F, 4F, 3F, 0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, 0F, 3.1416F, 1.5708F));
		right_arm.addOrReplaceChild("cube_7", CubeListBuilder.create().texOffs(52, 38).addBox(-1.5F, -1F, -4F, 4F, 3F, 0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, 0F, 3.1416F, -1.5708F));
		right_arm.addOrReplaceChild("cube_8", CubeListBuilder.create().texOffs(52, 38).addBox(-1.5F, -4F, -2F, 4F, 4F, 0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, 1.5708F, 3.1416F, -1.5708F));
		right_arm.addOrReplaceChild("cube_9", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-2.5F, -4F, -1F, 4F, 4F, 0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2F, 1.5708F, 3.1416F, 1.5708F));
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(T villager, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(villager, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		var isFalling = villager.getFallFlyingTicks() > 4;
		var f = 1F;

		if (isFalling) {
			f = (float) villager.getDeltaMovement().lengthSqr();
			f /= 0.2F;
			f *= f * f;
		}

		if (f < 1) {
			f = 1;
		}

		rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2 * limbSwingAmount * 0.5F / f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2 * limbSwingAmount * 0.5F / f;
		head.y = 2F;
	}

	@Override
	public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
		ModelPart modelpart = getArm(arm);
		float f = 0.5F * (float) (arm == HumanoidArm.RIGHT ? 1 : -1);
		modelpart.x += f;
		modelpart.translateAndRotate(poseStack);
		modelpart.x -= f;
	}

	private ModelPart getArm(HumanoidArm arm) {
		return arm == HumanoidArm.LEFT ? leftArm : rightArm;
	}
}
