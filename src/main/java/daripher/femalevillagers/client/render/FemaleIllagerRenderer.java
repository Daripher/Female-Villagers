package daripher.femalevillagers.client.render;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleIllagerModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractIllager;

public class FemaleIllagerRenderer<T extends AbstractIllager> extends IllagerRenderer<T> {
	private final ResourceLocation skinLocation;

	public FemaleIllagerRenderer(EntityRendererProvider.Context context, String texture) {
		super(context, new IllagerModel<>(context.bakeLayer(FemaleIllagerModel.LAYER_LOCATION)), 0.5F);
		skinLocation = new ResourceLocation(FemaleVillagersMod.MOD_ID, "textures/entity/" + texture + ".png");
		addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
		model.getHat().visible = true;
	}

	@Override
	public ResourceLocation getTextureLocation(T illager) {
		return skinLocation;
	}
}
