package daripher.femalevillagers.init;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.entity.FemaleEvoker;
import daripher.femalevillagers.entity.FemalePillager;
import daripher.femalevillagers.entity.FemaleVillager;
import daripher.femalevillagers.entity.FemaleVindicator;
import daripher.femalevillagers.entity.FemaleWanderingTrader;
import daripher.femalevillagers.entity.FemaleZombieVillager;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, FemaleVillagersMod.MOD_ID);

	public static final RegistryObject<EntityType<FemaleVillager>> FEMALE_VILLAGER = REGISTRY.register("female_villager", FemaleVillager::createEntityType);
	public static final RegistryObject<EntityType<FemaleZombieVillager>> FEMALE_ZOMBIE_VILLAGER = REGISTRY.register("female_zombie_villager", FemaleZombieVillager::createEntityType);
	public static final RegistryObject<EntityType<FemaleWanderingTrader>> FEMALE_WANDERING_TRADER = REGISTRY.register("female_wandering_trader", FemaleWanderingTrader::createEntityType);
	public static final RegistryObject<EntityType<FemalePillager>> FEMALE_PILLAGER = REGISTRY.register("female_pillager", FemalePillager::createEntityType);
	public static final RegistryObject<EntityType<FemaleVindicator>> FEMALE_VINDICATOR = REGISTRY.register("female_vindicator", FemaleVindicator::createEntityType);
	public static final RegistryObject<EntityType<FemaleEvoker>> FEMALE_EVOKER = REGISTRY.register("female_evoker", FemaleEvoker::createEntityType);
}
