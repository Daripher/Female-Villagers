package daripher.femalevillagers.event;

import javax.annotation.Nullable;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.guardvillagers.GuardVillagersCompatibility;
import daripher.femalevillagers.compat.theconjurer.TheConjurerCompatibility;
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
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.raid.Raider;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID)
public class FemaleVillagersEvents {
	@SubscribeEvent
	public static void replaceVillagerWithFemaleVersion(EntityJoinLevelEvent event) {
		if (event.getEntity().getTags().contains("do_not_replace")) {
			return;
		}
		if (event.loadedFromDisk()) {
			event.getEntity().getTags().add("do_not_replace");
			return;
		}
		if (event.getEntity().level.isClientSide) {
			return;
		}
		var level = event.getLevel();
		var replaceChance = 0.5;
		if (level.random.nextFloat() >= replaceChance) {
			event.getEntity().getTags().add("do_not_replace");
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
		if (entity instanceof Raider raider && raider.getCurrentRaid() != null) {
			return null;
		}

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
			final var llamaOwner = replacementEntity;
			entity.level.getEntitiesOfClass(TraderLlama.class, entity.getBoundingBox().inflate(10D)).forEach(llama -> llama.setLeashedTo(llamaOwner, true));
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
			var replacedEntity = (Illusioner) entity;
			replacementEntity = new FemaleIllusioner(entity.level);
			replacementEntity.setItemSlot(EquipmentSlot.MAINHAND, replacedEntity.getItemBySlot(EquipmentSlot.MAINHAND));
		}

		if (EntityType.getKey(entity.getType()).toString().equals("guardvillagers:guard")) {
			replacementEntity = GuardVillagersCompatibility.INSTANCE.createFemaleGuard(entity);
		}

		if (EntityType.getKey(entity.getType()).toString().equals("conjurer_illager:conjurer")) {
			replacementEntity = TheConjurerCompatibility.INSTANCE.createFemaleConjurer(entity);
		}

		if (replacementEntity != null) {
			replacementEntity.setPos(entity.position());
		}

		return replacementEntity;
	}
}
