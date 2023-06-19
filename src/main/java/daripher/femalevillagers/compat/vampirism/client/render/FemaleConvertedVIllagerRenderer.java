package daripher.femalevillagers.compat.vampirism.client.render;

import daripher.femalevillagers.client.render.FemaleVillagerRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

public class FemaleConvertedVIllagerRenderer extends FemaleVillagerRenderer {
	public FemaleConvertedVIllagerRenderer(Context context) {
		super(context, "vampirism/female_villager");
	}
}
