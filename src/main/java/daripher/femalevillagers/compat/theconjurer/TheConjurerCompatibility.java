package daripher.femalevillagers.compat.theconjurer;

import com.legacy.conjurer_illager.entity.ConjurerEntity;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.theconjurer.client.model.FemaleConjurerModel;
import daripher.femalevillagers.compat.theconjurer.client.render.FemaleConjurerRenderer;
import daripher.femalevillagers.compat.theconjurer.entity.FemaleConjurer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

public enum TheConjurerCompatibility {
	INSTANCE;

	private static final ResourceLocation FEMALE_CONJURER_ENTITY_ID = new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_conjurer");

	public void addCompatibility() {
		var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::createAttributes);
		modEventBus.addGenericListener(EntityType.class, this::registerEntityType);
		modEventBus.addListener(this::registerRenderer);
	}

	public Entity createFemaleConjurer(Entity entity) {
		return new FemaleConjurer(entity.level);
	}

	@SuppressWarnings("unchecked")
	public EntityType<FemaleConjurer> getFemaleConjurerEntityType() {
		return (EntityType<FemaleConjurer>) ForgeRegistries.ENTITIES.getValue(FEMALE_CONJURER_ENTITY_ID);
	}

	private void createAttributes(EntityAttributeCreationEvent event) {
		event.put(getFemaleConjurerEntityType(), ConjurerEntity.registerAttributes().build());
	}

	private void registerEntityType(RegistryEvent.Register<EntityType<?>> event) {
		var entityType = FemaleConjurer.createEntityType();
		entityType.setRegistryName(FEMALE_CONJURER_ENTITY_ID);
		event.getRegistry().register(entityType);
	}

	private void registerRenderer(FMLClientSetupEvent event) {
		ForgeHooksClient.registerLayerDefinition(FemaleConjurerModel.LAYER_LOCATION, FemaleConjurerModel::createBodyLayer);
		EntityRenderers.register(getFemaleConjurerEntityType(), FemaleConjurerRenderer::new);
	}
}
