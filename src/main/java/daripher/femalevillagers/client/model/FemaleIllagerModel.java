package daripher.femalevillagers.client.model;

import daripher.femalevillagers.FemaleVillagersMod;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractIllager;

public class FemaleIllagerModel<T extends AbstractIllager> extends IllagerModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_illager"), "main");

	public FemaleIllagerModel(ModelPart rootModelPart) {
		super(rootModelPart);
	}

	public static LayerDefinition createBodyLayer() {
		var model = new MeshDefinition();
		var root = model.getRoot();

		var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, -23.25F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(-0.45F))
				.texOffs(0, 38).addBox(-4.0F, -23.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		var breast = body.addOrReplaceChild("breast", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -22.0F, -3.0F, 0.1309F, 0.0F, 0.0F));

		breast.addOrReplaceChild("breast_overlay_r1",
				CubeListBuilder.create().texOffs(4, 43).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)).texOffs(18, 29).mirror()
						.addBox(-4.0F, 3.25F, 0.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(-0.51F)).mirror(false).texOffs(19, 24)
						.addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 3.0F, new CubeDeformation(-0.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.0F, 0.0F));

		breast.addOrReplaceChild("breast_bottom_overlay_r1",
				CubeListBuilder.create().texOffs(2, 47).mirror().addBox(-4.0F, -0.5F, -1.5F, 8.0F, -1.0F, 4.0F, new CubeDeformation(-0.19F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 5.2916F, -0.9997F, -0.48F, 0.0F, 0.0F));

		root.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.2F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)).mirror(false),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-1.8F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(41, 46).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 4.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

		root.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(41, 46).mirror().addBox(0.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 4.0F, 0.0F, 0.2094F, 0.0F, 0.0F));

		var head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(-0.2F)),
				PartPose.offset(0.0F, 2.0F, 0.0F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -5.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(-0.4F)),
				PartPose.offset(0.0F, -2.0F, 0.0F));

		head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		root.addOrReplaceChild("arms",
				CubeListBuilder.create().texOffs(44, 22).addBox(-7.0F, -1.8284F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(44, 22)
						.addBox(4.0F, -1.8284F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(40, 38)
						.addBox(-4.0F, 3.1716F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 5.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(model, 64, 64);
	}
}
