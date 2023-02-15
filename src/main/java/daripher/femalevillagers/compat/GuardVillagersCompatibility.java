package daripher.femalevillagers.compat;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.model.FemaleGuardArmorModel;
import daripher.femalevillagers.client.model.FemaleGuardModel;
import daripher.femalevillagers.client.render.FemaleGuardRenderer;
import daripher.femalevillagers.entity.FemaleGuard;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import tallestegg.guardvillagers.entities.Guard;

public class GuardVillagersCompatibility {
	private static final ResourceLocation FEMALE_GUARD_ENTITY_ID = new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_guard");

	public static void addCompatibility() {
		var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(GuardVillagersCompatibility::createAttributes);
		modEventBus.addListener(GuardVillagersCompatibility::registerEntityType);
		modEventBus.addListener(GuardVillagersCompatibility::registerRenderer);
	}

	private static void createAttributes(EntityAttributeCreationEvent event) {
		event.put(getFemaleGuardEntityType(), Guard.createAttributes().build());
	}

	private static void registerEntityType(RegisterEvent event) {
		event.register(Registry.ENTITY_TYPE_REGISTRY, FEMALE_GUARD_ENTITY_ID, FemaleGuard::createEntityType);
	}

	@OnlyIn(Dist.CLIENT)
	private static void registerRenderer(FMLClientSetupEvent event) {
		ForgeHooksClient.registerLayerDefinition(FemaleGuardModel.LAYER_LOCATION, FemaleGuardModel::createBodyLayer);
		ForgeHooksClient.registerLayerDefinition(FemaleGuardArmorModel.INNER_LAYER_LOCATION, FemaleGuardArmorModel::createInnerArmorLayer);
		ForgeHooksClient.registerLayerDefinition(FemaleGuardArmorModel.OUTER_LAYER_LOCATION, FemaleGuardArmorModel::createOuterArmorLayer);
		EntityRenderers.register(getFemaleGuardEntityType(), FemaleGuardRenderer::new);
	}

	@SuppressWarnings("unchecked")
	public static EntityType<FemaleGuard> getFemaleGuardEntityType() {
		return (EntityType<FemaleGuard>) ForgeRegistries.ENTITY_TYPES.getValue(FEMALE_GUARD_ENTITY_ID);
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
}
