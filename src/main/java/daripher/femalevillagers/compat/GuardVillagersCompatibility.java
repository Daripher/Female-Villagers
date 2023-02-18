package daripher.femalevillagers.compat;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleGuardArmorModel;
import daripher.femalevillagers.client.model.FemaleGuardModel;
import daripher.femalevillagers.client.render.FemaleGuardRenderer;
import daripher.femalevillagers.entity.FemaleGuard;
import daripher.femalevillagers.entity.FemaleVillager;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import tallestegg.guardvillagers.GuardEntityType;
import tallestegg.guardvillagers.configuration.GuardConfig;
import tallestegg.guardvillagers.entities.Guard;

public class GuardVillagersCompatibility {
	private static final ResourceLocation FEMALE_GUARD_ENTITY_ID = new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_guard");

	public static void addCompatibility() {
		var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(GuardVillagersCompatibility::createAttributes);
		modEventBus.addGenericListener(EntityType.class, GuardVillagersCompatibility::registerEntityType);
		modEventBus.addListener(GuardVillagersCompatibility::registerRenderer);
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, GuardVillagersCompatibility::attemptConvertingVillager);
	}

	private static void createAttributes(EntityAttributeCreationEvent event) {
		event.put(getFemaleGuardEntityType(), Guard.createAttributes().build());
	}

	private static void registerEntityType(RegistryEvent.Register<EntityType<?>> event) {
		var entityType = FemaleGuard.createEntityType();
		entityType.setRegistryName(FEMALE_GUARD_ENTITY_ID);
		event.getRegistry().register(entityType);
	}

	private static void attemptConvertingVillager(PlayerInteractEvent.EntityInteract event) {
		var player = event.getPlayer();
		var itemInHand = player.getMainHandItem();
		var target = event.getTarget();
		var isHoldingWeapon = itemInHand.getItem() instanceof SwordItem || itemInHand.getItem() instanceof CrossbowItem;

		if (!isHoldingWeapon || !player.isCrouching()) {
			return;
		}

		if (target instanceof Villager villager) {
			if (villager.isBaby()) {
				return;
			}

			var profession = villager.getVillagerData().getProfession();
			var hasProfession = profession != VillagerProfession.NONE && profession != VillagerProfession.NITWIT;

			if (hasProfession) {
				return;
			}

			var canPlayerConvertVillagers = !GuardConfig.ConvertVillagerIfHaveHOTV
					|| player.hasEffect(MobEffects.HERO_OF_THE_VILLAGE) && GuardConfig.ConvertVillagerIfHaveHOTV;

			if (!canPlayerConvertVillagers) {
				return;
			}

			GuardVillagersCompatibility.convertVillager(villager, player);
			event.setCanceled(true);

			if (!player.isCreative()) {
				itemInHand.shrink(1);
			}
		}
	}

	private static void registerRenderer(FMLClientSetupEvent event) {
		ForgeHooksClient.registerLayerDefinition(FemaleGuardModel.LAYER_LOCATION, FemaleGuardModel::createBodyLayer);
		ForgeHooksClient.registerLayerDefinition(FemaleGuardArmorModel.INNER_LAYER_LOCATION, FemaleGuardArmorModel::createInnerArmorLayer);
		ForgeHooksClient.registerLayerDefinition(FemaleGuardArmorModel.OUTER_LAYER_LOCATION, FemaleGuardArmorModel::createOuterArmorLayer);
		EntityRenderers.register(getFemaleGuardEntityType(), FemaleGuardRenderer::new);
	}

	@SuppressWarnings("unchecked")
	public static EntityType<FemaleGuard> getFemaleGuardEntityType() {
		return (EntityType<FemaleGuard>) ForgeRegistries.ENTITIES.getValue(FEMALE_GUARD_ENTITY_ID);
	}

	public static Entity createFemaleGuard(Entity guard) {
		var replacedEntity = (Guard) guard;
		var replacementEntity = new FemaleGuard(guard.level);
		((FemaleGuard) replacementEntity).setGuardVariant(replacedEntity.getGuardVariant());

		for (var slot : EquipmentSlot.values()) {
			replacementEntity.setItemSlot(slot, replacedEntity.getItemBySlot(slot));
		}

		return replacementEntity;
	}

	private static void convertVillager(LivingEntity entity, Player player) {
		player.swing(InteractionHand.MAIN_HAND);
		var itemInHand = player.getItemBySlot(EquipmentSlot.MAINHAND);
		var guard = GuardEntityType.GUARD.get().create(entity.level);

		if (entity instanceof FemaleVillager) {
			guard = new FemaleGuard(entity.level);
		} else {
			guard.addTag("do_not_replace");
		}

		Villager villager = (Villager) entity;

		if (entity.level.isClientSide) {
			var particle = ParticleTypes.HAPPY_VILLAGER;

			for (int i = 0; i < 10; ++i) {
				var particleMotionX = villager.getRandom().nextGaussian() * 0.02D;
				var particleMotionY = villager.getRandom().nextGaussian() * 0.02D;
				var particleMotionZ = villager.getRandom().nextGaussian() * 0.02D;
				var particleX = villager.getX() + villager.getRandom().nextFloat() * villager.getBbWidth() * 2F - villager.getBbWidth();
				var particleY = villager.getY() + 0.5D + villager.getRandom().nextFloat() * villager.getBbHeight();
				var particleZ = villager.getZ() + villager.getRandom().nextFloat() * villager.getBbWidth() * 2F - villager.getBbWidth();
				villager.level.addParticle(particle, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
			}
		}

		guard.copyPosition(villager);
		guard.playSound(SoundEvents.VILLAGER_YES, 1F, 1F);
		guard.setItemSlot(EquipmentSlot.MAINHAND, itemInHand.copy());
		var guardVariant = Guard.getRandomTypeForBiome(guard.level, guard.blockPosition());
		guard.setGuardVariant(guardVariant);
		guard.setPersistenceRequired();
		guard.setCustomName(villager.getCustomName());
		guard.setCustomNameVisible(villager.isCustomNameVisible());
		guard.setDropChance(EquipmentSlot.HEAD, 100F);
		guard.setDropChance(EquipmentSlot.CHEST, 100F);
		guard.setDropChance(EquipmentSlot.FEET, 100F);
		guard.setDropChance(EquipmentSlot.LEGS, 100F);
		guard.setDropChance(EquipmentSlot.MAINHAND, 100F);
		guard.setDropChance(EquipmentSlot.OFFHAND, 100F);
		guard.getGossips().add(player.getUUID(), GossipType.MINOR_POSITIVE, GuardConfig.reputationRequirement);
		villager.level.addFreshEntity(guard);
		villager.releasePoi(MemoryModuleType.HOME);
		villager.releasePoi(MemoryModuleType.JOB_SITE);
		villager.releasePoi(MemoryModuleType.MEETING_POINT);
		villager.discard();
	}
}
