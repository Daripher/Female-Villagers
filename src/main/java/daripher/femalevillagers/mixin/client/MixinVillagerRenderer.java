package daripher.femalevillagers.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.common.base.Predicates;

import daripher.femalevillagers.client.render.layer.ItemInVillagerHandLayer;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.world.entity.npc.Villager;

@Mixin(VillagerRenderer.class)
public abstract class MixinVillagerRenderer extends LivingEntityRenderer<Villager, VillagerModel<Villager>> {
	public MixinVillagerRenderer() {
		super(null, null, 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Inject(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V", at = @At("TAIL"))
	private void replaceArmsLayer(EntityRendererProvider.Context ctx, CallbackInfo callbackInfo) {
		layers.removeIf(Predicates.instanceOf(CrossedArmsItemLayer.class));
		addLayer(new ItemInVillagerHandLayer(this, ctx.getItemInHandRenderer()));
	}
}
