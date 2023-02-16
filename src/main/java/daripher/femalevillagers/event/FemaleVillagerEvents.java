package daripher.femalevillagers.event;

import javax.annotation.Nullable;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.GuardVillagersCompatibility;
import daripher.femalevillagers.entity.FemaleEvoker;
import daripher.femalevillagers.entity.FemaleIllusioner;
import daripher.femalevillagers.entity.FemalePillager;
import daripher.femalevillagers.entity.FemaleVillager;
import daripher.femalevillagers.entity.FemaleVindicator;
import daripher.femalevillagers.entity.FemaleWanderingTrader;
import daripher.femalevillagers.entity.FemaleZombieVillager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID)
public class FemaleVillagerEvents {
	@SubscribeEvent
	public static void replaceVillagerWithFemaleVersion(EntityJoinLevelEvent event) {
		if (event.getEntity().getTags().contains("do_not_replace")) {
			return;
		}

		if (event.getEntity().level.isClientSide || event.loadedFromDisk()) {
			return;
		}

		var level = event.getLevel();
		var replaceChance = 0.5;

		if (level.random.nextFloat() >= replaceChance) {
			return;
		}

		var replacementEntity = createReplacementEntity(event.getEntity());

		if (replacementEntity != null) {
			level.addFreshEntity(replacementEntity);
			event.setCanceled(true);
		}
	}

	@Nullable
	private static Entity createReplacementEntity(Entity entity) {
		Entity replacementEntity = null;

		if (entity.getType() == EntityType.VILLAGER) {
			replacementEntity = new FemaleVillager(entity.level);
			var replacedEntity = (Villager) entity;
			var villagerData = replacedEntity.getVillagerData();
			((FemaleVillager) replacementEntity).setVillagerData(villagerData);
			((FemaleVillager) replacementEntity).setBaby(replacedEntity.isBaby());
		}

		if (entity.getType() == EntityType.ZOMBIE_VILLAGER) {
			replacementEntity = new FemaleZombieVillager(entity.level);
			var replacedEntity = (ZombieVillager) entity;
			var villagerData = replacedEntity.getVillagerData();
			((FemaleZombieVillager) replacementEntity).setVillagerData(villagerData);
			((FemaleZombieVillager) replacementEntity).setBaby(replacedEntity.isBaby());
		}

		if (entity.getType() == EntityType.WANDERING_TRADER) {
			replacementEntity = new FemaleWanderingTrader(entity.level);
		}

		if (entity.getType() == EntityType.PILLAGER) {
			var replacedEntity = (Pillager) entity;
			replacementEntity = new FemalePillager(entity.level);
			replacementEntity.setItemSlot(EquipmentSlot.MAINHAND, replacedEntity.getItemBySlot(EquipmentSlot.MAINHAND));

			for (var slot = 0; slot < 5; slot++) {
				var itemInSlot = replacedEntity.getInventory().getItem(slot);
				((FemalePillager) replacementEntity).getInventory().setItem(slot, itemInSlot);
			}
		}

		if (entity.getType() == EntityType.EVOKER) {
			replacementEntity = new FemaleEvoker(entity.level);
		}

		if (entity.getType() == EntityType.VINDICATOR) {
			var replacedEntity = (Vindicator) entity;
			replacementEntity = new FemaleVindicator(entity.level);
			replacementEntity.setItemSlot(EquipmentSlot.MAINHAND, replacedEntity.getItemBySlot(EquipmentSlot.MAINHAND));
		}

		if (entity.getType() == EntityType.ILLUSIONER) {
			replacementEntity = new FemaleIllusioner(entity.level);
		}

		if (EntityType.getKey(entity.getType()).toString().equals("guardvillagers:guard")) {
			replacementEntity = GuardVillagersCompatibility.createFemaleGuard(entity);
		}

		if (replacementEntity != null) {
			replacementEntity.setPos(entity.position());
		}

		return replacementEntity;
	}
}
