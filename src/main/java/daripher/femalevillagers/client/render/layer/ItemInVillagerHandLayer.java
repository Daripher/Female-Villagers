package daripher.femalevillagers.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;

import daripher.femalevillagers.config.Config;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;

public class ItemInVillagerHandLayer<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
	private final ItemInHandLayer<T, M> itemInNormalHandsLayer;
	private final CrossedArmsItemLayer<T, M> itemInCrossedHandsLayer;

	public ItemInVillagerHandLayer(RenderLayerParent<T, M> parent, ItemInHandRenderer itemInHandRenderer) {
		super(parent);
		itemInNormalHandsLayer = new ItemInHandLayer<>(parent, itemInHandRenderer);
		itemInCrossedHandsLayer = new CrossedArmsItemLayer<>(parent, itemInHandRenderer);
	}

	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float entityAge, float netHeadYaw, float headPitch) {
		if (Config.COMMON.useDefaultVillagerArms.get()) {
			itemInCrossedHandsLayer.render(poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, partialTicks, entityAge, netHeadYaw, headPitch);
		} else {
			itemInNormalHandsLayer.render(poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, partialTicks, entityAge, netHeadYaw, headPitch);
		}
	}
}
