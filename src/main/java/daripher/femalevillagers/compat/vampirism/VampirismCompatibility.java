package daripher.femalevillagers.compat.vampirism;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.compat.vampirism.client.render.FemaleConvertedVIllagerRenderer;
import daripher.femalevillagers.compat.vampirism.entity.FemaleConvertedVillager;
import daripher.femalevillagers.init.EntityInit;
import de.teamlapen.vampirism.api.VampirismAPI;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public enum VampirismCompatibility {
	INSTANCE;

	private static final ResourceLocation FEMALE_CONVERTED_VILLAGER_ENTITY_ID = new ResourceLocation(FemaleVillagersMod.MOD_ID, "female_converted_villager");

	public void addCompatibility() {
		var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::createAttributes);
		modEventBus.addListener(this::registerEntityType);
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

	private void registerEntityType(RegisterEvent event) {
		event.register(Registry.ENTITY_TYPE_REGISTRY, FEMALE_CONVERTED_VILLAGER_ENTITY_ID, this::createFemaleConvertedVillagerEntityType);
	}

	private EntityType<FemaleConvertedVillager> createFemaleConvertedVillagerEntityType() {
		return EntityType.Builder.<FemaleConvertedVillager>of(FemaleConvertedVillager::new, MobCategory.MISC).sized(0.6F, 1.95F).setShouldReceiveVelocityUpdates(true).build("female_converted_villager");
	}

	private void registerRenderer(FMLClientSetupEvent event) {
		EntityRenderers.register(getFemaleConvertedVillagerEntityType(), FemaleConvertedVIllagerRenderer::new);
	}

	@SuppressWarnings("unchecked")
	public EntityType<FemaleConvertedVillager> getFemaleConvertedVillagerEntityType() {
		return (EntityType<FemaleConvertedVillager>) ForgeRegistries.ENTITY_TYPES.getValue(FEMALE_CONVERTED_VILLAGER_ENTITY_ID);
	}
}
