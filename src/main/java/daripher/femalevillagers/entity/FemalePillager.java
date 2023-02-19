package daripher.femalevillagers.entity;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
public class FemalePillager extends Pillager {
	public FemalePillager(EntityType<? extends FemalePillager> entityType, Level level) {
		super(entityType, level);
	}

	public FemalePillager(Level level) {
		super(EntityInit.FEMALE_PILLAGER.get(), level);
	}

	@Override
	protected Component getTypeName() {
		return EntityType.PILLAGER.getDescription();
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(Items.PILLAGER_SPAWN_EGG);
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.6F;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return EntityType.PILLAGER.getDefaultLootTable();
	}

	@SubscribeEvent
	public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
		event.put(EntityInit.FEMALE_PILLAGER.get(), Pillager.createAttributes().build());
	}

	public static EntityType<FemalePillager> createEntityType() {
		var builder = EntityType.Builder.<FemalePillager>of(FemalePillager::new, MobCategory.MONSTER).canSpawnFarFromPlayer().sized(0.6F, 1.95F).clientTrackingRange(8);
		return builder.build("female_pillager");
	}
}
