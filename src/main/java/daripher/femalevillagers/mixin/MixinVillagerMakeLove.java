package daripher.femalevillagers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import daripher.femalevillagers.entity.FemaleVillager;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.behavior.VillagerMakeLove;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;

@Mixin(VillagerMakeLove.class)
public class MixinVillagerMakeLove {
	@Inject(method = "isBreedingPossible", at = @At("HEAD"), cancellable = true)
	private void inject_isBreedingPossible(Villager villager, CallbackInfoReturnable<Boolean> callbackInfo) {
		var villagerBrain = villager.getBrain();
		var optionalBreedTarget = villagerBrain.getMemory(MemoryModuleType.BREED_TARGET).filter(m -> m.getType() == EntityType.VILLAGER);

		optionalBreedTarget.ifPresent(breedTarget -> {
			var isVillagerFemale = villager instanceof FemaleVillager;
			var isTargetFemale = breedTarget instanceof FemaleVillager;

			// can't breed if both female or if both male
			if (isVillagerFemale == isTargetFemale) {
				callbackInfo.setReturnValue(false);
			}
		});
	}
}
