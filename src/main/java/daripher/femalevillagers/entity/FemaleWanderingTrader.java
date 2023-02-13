package daripher.femalevillagers.entity;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
public class FemaleWanderingTrader extends WanderingTrader {
	public FemaleWanderingTrader(EntityType<? extends FemaleWanderingTrader> entityType, Level level) {
		super(entityType, level);
	}

	public FemaleWanderingTrader(Level level) {
		super(EntityInit.FEMALE_WANDERING_TRADER.get(), level);
	}

	@Override
	protected Component getTypeName() {
		return EntityType.WANDERING_TRADER.getDescription();
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(Items.WANDERING_TRADER_SPAWN_EGG);
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.4F;
	}

	@SubscribeEvent
	public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
		event.put(EntityInit.FEMALE_WANDERING_TRADER.get(), WanderingTrader.createMobAttributes().build());
	}

	public static EntityType<FemaleWanderingTrader> createEntityType() {
		var builder = EntityType.Builder.<FemaleWanderingTrader>of(FemaleWanderingTrader::new, MobCategory.CREATURE).sized(0.6F, 1.95F).clientTrackingRange(10);
		return builder.build("female_wandering_trader");
	}
}
