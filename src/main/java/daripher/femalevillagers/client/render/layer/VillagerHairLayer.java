package daripher.femalevillagers.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleVillagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.AbstractVillager;

public class VillagerHairLayer<T extends AbstractVillager> extends RenderLayer<T, FemaleVillagerModel<T>> {
	private static final ResourceLocation HAIR_TEXTURE = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/hair/villager_hair.png");
	private final FemaleVillagerModel<T> hairModel;

	public VillagerHairLayer(RenderLayerParent<T, FemaleVillagerModel<T>> parent, FemaleVillagerModel<T> hairModel) {
		super(parent);
		this.hairModel = hairModel;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float entityAge, float netHeadYaw, float headPitch) {
		var renderType = hairModel.renderType(HAIR_TEXTURE);
		getParentModel().copyPropertiesTo(hairModel);
		hairModel.renderToBuffer(poseStack, bufferSource.getBuffer(renderType), packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
	}
}
