package daripher.femalevillagers.entity;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
public class FemaleZombieVillager extends ZombieVillager {
	public FemaleZombieVillager(EntityType<? extends FemaleZombieVillager> entityType, Level level) {
		super(entityType, level);
	}

	public FemaleZombieVillager(Level level) {
		super(EntityInit.FEMALE_ZOMBIE_VILLAGER.get(), level);
	}

	@Override
	protected Component getTypeName() {
		return EntityType.ZOMBIE_VILLAGER.getDescription();
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(Items.ZOMBIE_VILLAGER_SPAWN_EGG);
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.4F;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return EntityType.ZOMBIE_VILLAGER.getDefaultLootTable();
	}

	@SubscribeEvent
	public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
		event.put(EntityInit.FEMALE_ZOMBIE_VILLAGER.get(), ZombieVillager.createAttributes().build());
	}

	public static EntityType<FemaleZombieVillager> createEntityType() {
		var builder = EntityType.Builder.<FemaleZombieVillager>of(FemaleZombieVillager::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8);
		return builder.build("female_villager");
	}
}
