package daripher.femalevillagers.client.render;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import daripher.femalevillagers.entity.FemalePillager;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class FemalePillagerRenderer extends IllagerRenderer<FemalePillager> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_pillager.png");

	public FemalePillagerRenderer(EntityRendererProvider.Context context) {
		super(context, new IllagerModel<>(context.bakeLayer(FemaleIllagerModel.LAYER_LOCATION)), 0.5F);
		addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(FemalePillager villager) {
		return SKIN_LOCATION;
	}
}
