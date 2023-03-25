package daripher.femalevillagers.compat.vampirism;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.client.render.FemaleVillagerRenderer;
import daripher.femalevillagers.compat.vampirism.entity.FemaleConvertedVillager;
import daripher.femalevillagers.init.EntityInit;
import de.teamlapen.vampirism.api.VampirismAPI;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

public enum VampirismCompatibility {
	INSTANCE;

	private static final ResourceLocation FEMALE_CONVERTED_VILLAGER_ENTITY_ID = new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_converted_villager");

	public void addCompatibility() {
		var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::createAttributes);
		modEventBus.addGenericListener(EntityType.class, this::registerEntityType);
		modEventBus.addListener(this::registerRenderer);
		modEventBus.addListener(this::registerConvertionHandler);
	}

	private void registerConvertionHandler(FMLCommonSetupEvent event) {
		var entityType = EntityInit.FEMALE_VILLAGER.get();
		var overlayTexture = new ResourceLocation("femalevillagers", "textures/vampirism/female_villager_overlay.png");
		var handler = new FemaleConvertedVillager.ConvertingHandler();
		VampirismAPI.entityRegistry().addConvertible(entityType, overlayTexture, handler);
	}

	private void createAttributes(EntityAttributeCreationEvent event) {
		event.put(getFemaleConvertedVillagerEntityType(), FemaleConvertedVillager.getAttributeBuilder().build());
	}

	private void registerEntityType(RegistryEvent.Register<EntityType<?>> event) {
		var entityType = createFemaleConvertedVillagerEntityType();
		entityType.setRegistryName(FEMALE_CONVERTED_VILLAGER_ENTITY_ID);
		event.getRegistry().register(entityType);
	}

	private EntityType<FemaleConvertedVillager> createFemaleConvertedVillagerEntityType() {
		return EntityType.Builder.<FemaleConvertedVillager>of(FemaleConvertedVillager::new, MobCategory.MISC).sized(0.6F, 1.95F).setShouldReceiveVelocityUpdates(true).build("female_converted_villager");
	}

	private void registerRenderer(FMLClientSetupEvent event) {
		EntityRenderers.register(getFemaleConvertedVillagerEntityType(), ctx -> new FemaleVillagerRenderer(ctx, "vampirism/female_villager"));
	}

	@SuppressWarnings("unchecked")
	public EntityType<FemaleConvertedVillager> getFemaleConvertedVillagerEntityType() {
		return (EntityType<FemaleConvertedVillager>) ForgeRegistries.ENTITIES.getValue(FEMALE_CONVERTED_VILLAGER_ENTITY_ID);
	}
}
