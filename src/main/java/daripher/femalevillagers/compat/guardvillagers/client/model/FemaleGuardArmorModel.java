package daripher.femalevillagers.compat.guardvillagers.client.model;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.guardvillagers.entity.FemaleGuard;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class FemaleGuardArmorModel extends HumanoidModel<FemaleGuard> {
	public static final ModelLayerLocation INNER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_guard_armor"), "inner");
	public static final ModelLayerLocation OUTER_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_guard_armor"), "outer");

	public FemaleGuardArmorModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createOuterArmorLayer() {
		return LayerDefinition.create(createMeshDefinition(1F), 64, 32);
	}

	public static LayerDefinition createInnerArmorLayer() {
		return LayerDefinition.create(createMeshDefinition(0.5F), 64, 32);
	}

	private static MeshDefinition createMeshDefinition(float size) {
		var model = HumanoidModel.createMesh(new CubeDeformation(size), 0);
		var root = model.getRoot();

		var body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4, -23.25F, -2, 8, 12, 4, new CubeDeformation(size - 0.15F)),
				PartPose.offset(0, 24, 0));

		var breast = body.addOrReplaceChild("breast", CubeListBuilder.create(), PartPose.offsetAndRotation(0, -22, -2.25F, 0.1309F, 0, 0));

		breast.addOrReplaceChild("breast_bottom_r1",
				CubeListBuilder.create().texOffs(17, 27).mirror().addBox(-4, 4.25F, -2.75F, 8, 1, 3, new CubeDeformation(size - 0.71F)).mirror(false),
				PartPose.offsetAndRotation(0, 0, 0, 0.48F, 3.1416F, 0));

		breast.addOrReplaceChild("breast_r1", CubeListBuilder.create().texOffs(17, 19).addBox(-4, 0.25F, -0.25F, 8, 5, 3, new CubeDeformation(size - 0.7F)),
				PartPose.offsetAndRotation(0, 0, 0, -0.48F, 0, 0));

		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.8F, 0, -2, 4, 12, 4, new CubeDeformation(size - 0.2F)),
				PartPose.offset(-2, 12, 0));

		root.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.2F, 0, -2, 4, 12, 4, new CubeDeformation(size - 0.2F)).mirror(false),
				PartPose.offset(2, 12, 0));

		root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4, -10, -4, 8, 8, 8, new CubeDeformation(size - 0.2F)), PartPose.offset(0, 2, 0));

		root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(41, 16).mirror().addBox(0.25F, -2, -2, 3, 12, 4, new CubeDeformation(size)).mirror(false),
				PartPose.offsetAndRotation(3.5F, 4, 0, 0.2094F, 0, 0));

		root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(41, 16).addBox(-2.25F, -2, -2, 3, 12, 4, new CubeDeformation(size)),
				PartPose.offsetAndRotation(-4.5F, 4, 0, -0.1745F, 0, 0));

		return model;
	}
}
