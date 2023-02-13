package daripher.femalevillagers.entity;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
public class FemaleEvoker extends Evoker {
	public FemaleEvoker(EntityType<? extends FemaleEvoker> entityType, Level level) {
		super(entityType, level);
	}

	public FemaleEvoker(Level level) {
		super(EntityInit.FEMALE_EVOKER.get(), level);
	}

	@Override
	protected Component getTypeName() {
		return EntityType.EVOKER.getDescription();
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(Items.EVOKER_SPAWN_EGG);
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.6F;
	}

	@SubscribeEvent
	public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
		event.put(EntityInit.FEMALE_EVOKER.get(), Evoker.createAttributes().build());
	}

	public static EntityType<FemaleEvoker> createEntityType() {
		var builder = EntityType.Builder.<FemaleEvoker>of(FemaleEvoker::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8);
		return builder.build("female_evoker");
	}
}
