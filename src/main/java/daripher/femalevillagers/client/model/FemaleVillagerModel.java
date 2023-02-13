package daripher.femalevillagers.client.model;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
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
	private final ModelPart rightArm;
	private final ModelPart leftArm;

	public FemaleVillagerModel(ModelPart rootModelPart) {
		super(rootModelPart);
		rightArm = rootModelPart.getChild("right_arm");
		leftArm = rootModelPart.getChild("left_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		var root = meshdefinition.getRoot();

		root.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.2F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)).mirror(false),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-1.8F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		var head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(0.0F, 2.0F, 0.0F));

		var hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		hat.addOrReplaceChild("hat_rim", CubeListBuilder.create().texOffs(30, 47).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -5.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)),
				PartPose.offset(0.0F, -2.0F, 0.0F));

		var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -23.25F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(-0.45F))
				.texOffs(0, 38).addBox(-4.0F, -23.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		var breast = body.addOrReplaceChild("breast", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, -3.0F, 0.1309F, 0.0F, 0.0F));

		breast.addOrReplaceChild("breast_overlay_r1",
				CubeListBuilder.create().texOffs(4, 43).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)).texOffs(18, 29).mirror()
						.addBox(-4.0F, 3.25F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(-0.51F)).mirror(false).texOffs(19, 24)
						.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 3.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		breast.addOrReplaceChild("breast_bottom_overlay_r1",
				CubeListBuilder.create().texOffs(2, 46).mirror().addBox(-4.0F, -0.5F, -1.5F, 8.0F, -1.0F, 4.0F, new CubeDeformation(-0.19F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 5.2916F, -0.9997F, -0.48F, 0.0F, 0.0F));

		var left_arm = root.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(45, 22).mirror().addBox(-1.5F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(5.0F, 4.0F, 0.0F, -1.1781F, 0.0F, 0.0F));

		left_arm.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(56, 42).mirror().addBox(-2.5F, -2.0F, -4.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, 0.0F, 3.1416F, 1.5708F));

		left_arm.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(53, 38).mirror().addBox(-2.5F, -4.0F, -1.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, -1.5708F, 0.0F, -1.5708F));

		left_arm.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-2.5F, 0.0F, -2.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, -1.5708F, 3.1416F, 1.5708F));

		left_arm.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(41, 42).mirror().addBox(-1.5F, -1.0F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, 0.0F, 3.1416F, -1.5708F));

		left_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(45, 38).addBox(-2.5F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, 3.1416F, 3.1416F, 1.5708F));

		var right_arm = root.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(45, 22).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, 4.0F, 0.0F, -1.0908F, 0.0F, 0.0F));

		right_arm.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(41, 38).mirror().addBox(-2.5F, -2.0F, -4.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.01F)).mirror(false).texOffs(41, 42)
						.addBox(-2.5F, -2.0F, 0.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, 0.0F, 3.1416F, 1.5708F));

		right_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(56, 42).addBox(-1.5F, -1.0F, -4.0F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, 0.0F, 3.1416F, -1.5708F));

		right_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(53, 38).addBox(-1.5F, -4.0F, -2.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.01F)),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, 1.5708F, 3.1416F, -1.5708F));

		right_arm.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-2.5F, -4.0F, -1.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 7.5F, -2.0F, 1.5708F, 3.1416F, 1.5708F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T villager, float p_104054_, float p_104055_, float p_104056_, float p_104057_, float p_104058_) {
		super.setupAnim(villager, p_104054_, p_104055_, p_104056_, p_104057_, p_104058_);
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

		rightArm.xRot = Mth.cos(p_104054_ * 0.6662F + (float) Math.PI) * 2 * p_104055_ * 0.5F / f;
		leftArm.xRot = Mth.cos(p_104054_ * 0.6662F) * 2 * p_104055_ * 0.5F / f;
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
