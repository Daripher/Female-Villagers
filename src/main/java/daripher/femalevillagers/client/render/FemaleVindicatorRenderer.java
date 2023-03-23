package daripher.femalevillagers.client.render;

import java.util.function.Predicate;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import daripher.femalevillagers.client.render.layer.HideableItemInHandLayer;
import daripher.femalevillagers.entity.FemaleVindicator;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.ResourceLocation;

public class FemaleVindicatorRenderer extends IllagerRenderer<FemaleVindicator> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_vindicator.png");
	private static final Predicate<FemaleVindicator> SHOULD_SHOW_ITEM_IN_HAND = e -> e.isAggressive();

	public FemaleVindicatorRenderer(EntityRendererProvider.Context context) {
		super(context, new IllagerModel<>(context.bakeLayer(FemaleIllagerModel.LAYER_LOCATION)), 0.5F);
		addLayer(new HideableItemInHandLayer<>(this, context.getItemInHandRenderer(), SHOULD_SHOW_ITEM_IN_HAND));
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleVindicator villager) {
		return SKIN_LOCATION;
	}
}
