package daripher.femalevillagers.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.config.Config;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.npc.Villager;

@Mixin(VillagerModel.class)
public class MixinVillagerModel implements ArmedModel {
	protected ModelPart rightArm;
	protected ModelPart leftArm;
	protected ModelPart arms;

	@Inject(method = "<init>(Lnet/minecraft/client/model/geom/ModelPart;)V", at = @At("TAIL"))
	private void inject_constructor(ModelPart root, CallbackInfo callbackInfo) {
		rightArm = root.getChild("right_arm");
		leftArm = root.getChild("left_arm");
		arms = root.getChild("arms");
	}

	@Inject(method = "createBodyModel", at = @At("RETURN"))
	private static void inject_createBodyModel(CallbackInfoReturnable<MeshDefinition> callbackInfo) {
		var model = callbackInfo.getReturnValue();
		var root = model.getRoot();
		var rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(44, 22).addBox(-1.5F, -2.5F, -2F, 4F, 8F, 4F), PartPose.offsetAndRotation(-6.5F, 3F, 0F, -0.9599F, 0F, 0F));
		rightArm.addOrReplaceChild("cube_1", CubeListBuilder.create().texOffs(52, 38).addBox(-1.5F, -2F, -4F, 4F, 4F, 0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.5F, 7F, -2F, 0F, 3.1416F, -1.5708F));
		rightArm.addOrReplaceChild("cube_2",
				CubeListBuilder.create().texOffs(40, 42).addBox(-1F, -1.5F, 0F, 4F, 4F, 0F, new CubeDeformation(0.01F)).texOffs(36, 38).mirror().addBox(-1F, -1.5F, -4F, 0F, 4F, 4F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(1F, 8.5F, -2F, 0F, 3.1416F, 1.5708F));
		rightArm.addOrReplaceChild("cube_3", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-1F, -4F, -2.5F, 4F, 4F, 0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(1F, 8.5F, -2F, 1.5708F, 3.1416F, 1.5708F));
		rightArm.addOrReplaceChild("cube_4", CubeListBuilder.create().texOffs(52, 38).addBox(-3F, -4F, -1.5F, 4F, 4F, 0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1F, 8.5F, -2F, 1.5708F, 3.1416F, -1.5708F));
		var leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(-2F, -2F, -2F, 4F, 8F, 4F).mirror(false), PartPose.offsetAndRotation(6F, 2.5F, 0F, -0.9599F, 0F, 0F));
		leftArm.addOrReplaceChild("cube_5", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-2.5F, -4F, -1F, 4F, 4F, 0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-1F, 7.5F, -2F, -1.5708F, 0F, -1.5708F));
		leftArm.addOrReplaceChild("cube_6", CubeListBuilder.create().texOffs(40, 42).mirror().addBox(-1.5F, -2F, 0F, 4F, 4F, 0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(0F, 7.5F, -2F, 0F, 3.1416F, -1.5708F));
		leftArm.addOrReplaceChild("cube_7", CubeListBuilder.create().texOffs(36, 38).addBox(-1F, -0.5F, 0F, 0F, 4F, 4F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-1.5F, 9F, -2F, 3.1416F, 3.1416F, 1.5708F));
		leftArm.addOrReplaceChild("cube_8", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-1F, -3.5F, -4F, 4F, 4F, 0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-1.5F, 9F, -2F, 0F, 3.1416F, 1.5708F));
		leftArm.addOrReplaceChild("cube_9", CubeListBuilder.create().texOffs(52, 38).mirror().addBox(-1F, 0F, -3.5F, 4F, 4F, 0F, new CubeDeformation(0.01F)).mirror(false),
				PartPose.offsetAndRotation(-1.5F, 9F, -2F, -1.5708F, 3.1416F, 1.5708F));
	}

	@Inject(method = "setupAnim", at = @At("TAIL"))
	private void inject_setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callbackInfo) {
		if (!(entity instanceof Villager)) {
			leftArm.visible = false;
			rightArm.visible = false;
			arms.visible = true;
			return;
		}

		if (Config.COMMON.useDefaultVillagerArms.get()) {
			leftArm.visible = false;
			rightArm.visible = false;
		} else {
			arms.visible = false;
		}

		var villager = (Villager) entity;
		var isFalling = villager.getFallFlyingTicks() > 4;
		var f = 1F;

		if (isFalling) {
			f = (float) entity.getDeltaMovement().lengthSqr();
			f /= 0.2F;
			f *= f * f;
		}

		if (f < 1) {
			f = 1;
		}

		rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 2 * limbSwingAmount * 0.5F / f;
		leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 2 * limbSwingAmount * 0.5F / f;
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
