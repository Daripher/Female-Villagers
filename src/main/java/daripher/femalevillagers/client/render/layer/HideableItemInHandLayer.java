package daripher.femalevillagers.client.render.layer;

import java.util.function.Predicate;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.monster.AbstractIllager;

public class HideableItemInHandLayer<T extends AbstractIllager> extends ItemInHandLayer<T, IllagerModel<T>> {
	private final Predicate<T> shouldShowItemPredicate;

	public HideableItemInHandLayer(RenderLayerParent<T, IllagerModel<T>> parent, Predicate<T> shouldShowItemPredicate) {
		super(parent);
		this.shouldShowItemPredicate = shouldShowItemPredicate;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float entityAge, float netHeadYaw, float headPitch) {
		if (shouldShowItemPredicate.test(entity)) {
			super.render(poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, partialTicks, entityAge, netHeadYaw, headPitch);
		}
	}
}