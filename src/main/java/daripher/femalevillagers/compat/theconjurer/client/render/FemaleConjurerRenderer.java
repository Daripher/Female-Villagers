package daripher.femalevillagers.compat.theconjurer.client.render;

import java.util.function.Predicate;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.render.layer.HideableItemInHandLayer;
import daripher.femalevillagers.compat.theconjurer.client.model.FemaleConjurerModel;
import daripher.femalevillagers.compat.theconjurer.entity.FemaleConjurer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.ResourceLocation;

public class FemaleConjurerRenderer extends IllagerRenderer<FemaleConjurer> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_conjurer.png");
	private static final Predicate<FemaleConjurer> SHOULD_SHOW_ITEM_IN_HAND = e -> e.isCastingSpell();

	public FemaleConjurerRenderer(Context context) {
		super(context, new FemaleConjurerModel(context.bakeLayer(FemaleConjurerModel.LAYER_LOCATION)), 0.5F);
//		addLayer(new ConjurerSunglassesLayer(this, new FemaleConjurerModel(context.bakeLayer(IllagerRenderRefs.CONJURER_GLASSES))));
		addLayer(new HideableItemInHandLayer<>(this, context.getItemInHandRenderer(), SHOULD_SHOW_ITEM_IN_HAND));
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleConjurer villager) {
		return SKIN_LOCATION;
	}
}
