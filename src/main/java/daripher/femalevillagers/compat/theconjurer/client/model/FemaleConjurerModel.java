package daripher.femalevillagers.compat.theconjurer.client.model;

import com.legacy.conjurer_illager.client.model.ConjurerModel;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.theconjurer.entity.FemaleConjurer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.AbstractIllager;

public class FemaleConjurerModel extends ConjurerModel<FemaleConjurer> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_conjurer"), "main");

	public FemaleConjurerModel(ModelPart model) {
		super(model);
	}

	public static LayerDefinition createBodyLayer() {
		var model = new MeshDefinition();
		var root = model.getRoot();

		var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -23.25F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(-0.45F))
				.texOffs(0, 38).addBox(-4.0F, -22.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		var breast = body.addOrReplaceChild("breast", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, -3.0F, 0.1309F, 0.0F, 0.0F));

		breast.addOrReplaceChild("breast_overlay_r1",
				CubeListBuilder.create().texOffs(4, 43).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)).texOffs(18, 29).mirror()
						.addBox(-4.0F, 3.25F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(-0.51F)).mirror(false).texOffs(19, 24)
						.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 3.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		breast.addOrReplaceChild("breast_bottom_overlay_r1",
				CubeListBuilder.create().texOffs(2, 47).mirror().addBox(-4.0F, -0.5F, -1.5F, 8.0F, 0.0F, 4.0F, new CubeDeformation(-0.19F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 5.2916F, -0.9997F, -0.48F, 0.0F, 0.0F));

		var head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(0, 2, 0));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -5.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)),
				PartPose.offset(0, -2, 0));

		head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.3F)), PartPose.ZERO);

		root.addOrReplaceChild("arms",
				CubeListBuilder.create().texOffs(44, 22).addBox(-7.0F, -1.8284F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(44, 22)
						.addBox(4.0F, -1.8284F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(40, 38)
						.addBox(-4.0F, 3.1716F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		root.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(41, 46).mirror().addBox(0.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 4.0F, 0.0F, 0.2094F, 0.0F, 0.0F));

		root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(41, 46).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 4.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-1.8F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		root.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.2F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)).mirror(false),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		root.addOrReplaceChild("hat_top", CubeListBuilder.create().texOffs(96, 0).addBox(-4.0F, -17F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.1F)),
				PartPose.ZERO);

		root.addOrReplaceChild("hat_bottom", CubeListBuilder.create().texOffs(84, 16).addBox(-5.5F, -9.0F, -5.5F, 11.0F, 2.0F, 11.0F, new CubeDeformation(-0.1F)),
				PartPose.ZERO);

		root.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(72, 0).addBox(-5.0F, -8.5F, 2.0F, 10.0F, 16.0F, 1.0F, new CubeDeformation(-0.95F)),
				PartPose.offsetAndRotation(0.0F, -15.5F, 5.25F, 0.0F, 3.1416F, 0.0F));

		root.addOrReplaceChild("bow_tie", CubeListBuilder.create().texOffs(32, 0).addBox(-3.5F, -2.0F, 0.25F, 7.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -19.8009F, -3.9669F, -0.3491F, 0.0F, 0.0F));

		root.addOrReplaceChild("held_hat_top", CubeListBuilder.create().texOffs(70, 48).addBox(-0.5F, 10.0F, -4.0F, 8.0F, 8.0F, 8.0F),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		root.addOrReplaceChild("held_hat_bottom", CubeListBuilder.create().texOffs(102, 42).addBox(-2.5F, 8.5F, -5.5F, 2.0F, 11.0F, 11.0F),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(model, 128, 64);
	}

	@Override
	public void setupAnim(FemaleConjurer entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.head.setPos(0.0F, 2F, 0.0F);

		boolean spellcasting = entityIn.getArmPose() == AbstractIllager.IllagerArmPose.SPELLCASTING;
		boolean celebrating = entityIn.getArmPose() == AbstractIllager.IllagerArmPose.CELEBRATING;

		this.heldHatBottom.visible = spellcasting || celebrating;
		this.heldHatTop.visible = spellcasting || celebrating;
		this.hatBottom.visible = !spellcasting && !celebrating;
		this.hatTop.visible = !spellcasting && !celebrating;

		this.hatBottom.copyFrom(this.head);
		this.hatTop.copyFrom(this.head);

		this.cape.setPos(0.0F, 9F, 6F);
		this.bowTie.setPos(0, 3.95F, -3.7F);
		this.bowTie.setRotation(-0.3491F, 0.0F, 0.0F);

		this.cape.xRot = -(0.1F + limbSwingAmount * 0.6F + (celebrating ? 0.5F : 0.0F));
		this.cape.z += (celebrating ? -7.0F : 0.0F);
		this.cape.y += (celebrating ? -2.0F : 0.0F);

		if (celebrating) {
			this.body.xRot = 0.5F;
			this.body.z = -6.5F;

			this.head.z -= 7.5F;
			this.head.xRot = 0.7F + Mth.cos(ageInTicks * 0.2F) * 0.1F;

			this.rightArm.z = -5.5F;
			this.rightArm.x = -5.0F;
			this.rightArm.xRot = 0;
			this.rightArm.zRot = 2.0F + Mth.cos(ageInTicks * 0.1F) * 0.2F;
			this.rightArm.yRot = 0.0F;

			this.leftArm.z = -5.5F;
			this.leftArm.x = 5.0F;

			this.leftArm.xRot = 0.6F;
			this.leftArm.zRot = 0.0F;
			this.leftArm.yRot = 0.0F;

			this.heldHatBottom.copyFrom(this.rightArm);
			this.heldHatTop.copyFrom(this.rightArm);
		} else {
			this.heldHatBottom.copyFrom(rightArm);
			this.heldHatTop.copyFrom(rightArm);

			this.body.xRot = 0.0F;
			this.body.z = 0.0F;
		}

		if (entityIn.getArmPose() == AbstractIllager.IllagerArmPose.CROSSBOW_HOLD) {
			this.leftArm.yRot = Mth.cos(ageInTicks * 2.0F) * 0.2F;
		}
	}
}
