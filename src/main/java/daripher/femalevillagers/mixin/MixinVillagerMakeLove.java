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

		if (optionalBreedTarget.isPresent()) {
			var breedTarget = optionalBreedTarget.get();

			if (villager instanceof FemaleVillager && breedTarget instanceof FemaleVillager) {
				callbackInfo.setReturnValue(false);
			}

			if (!(villager instanceof FemaleVillager) && !(breedTarget instanceof FemaleVillager)) {
				callbackInfo.setReturnValue(false);
			}
		}
	}
}
