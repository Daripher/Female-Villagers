package daripher.femalevillagers.client.render;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import daripher.femalevillagers.entity.FemaleIllusioner;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class FemaleIllusionerRenderer extends IllagerRenderer<FemaleIllusioner> {
	private static final ResourceLocation SKIN_LOCATION = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/female_illusioner.png");

	public FemaleIllusionerRenderer(EntityRendererProvider.Context context) {
		super(context, new IllagerModel<>(context.bakeLayer(FemaleIllagerModel.LAYER_LOCATION)), 0.5F);
		addLayer(new ItemInHandLayer<>(this));
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(FemaleIllusioner villager) {
		return SKIN_LOCATION;
	}
}
