package daripher.femalevillagers.entity;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
public class FemaleIllusioner extends Illusioner {
	public FemaleIllusioner(EntityType<? extends FemaleIllusioner> entityType, Level level) {
		super(entityType, level);
	}

	public FemaleIllusioner(Level level) {
		super(EntityInit.FEMALE_ILLUSIONER.get(), level);
	}

	@Override
	protected Component getTypeName() {
		return EntityType.ILLUSIONER.getDescription();
	}

	@Override
	public ItemStack getPickResult() {
		if (ModList.get().isLoaded("guardvillagers")) {
			var illusionerSpawnEgg = ForgeRegistries.ITEMS.getValue(new ResourceLocation("guardvillagers", "illusioner_spawn_egg"));
			return new ItemStack(illusionerSpawnEgg);
		}

		return super.getPickResult();
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.6F;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return EntityType.ILLUSIONER.getDefaultLootTable();
	}

	@SubscribeEvent
	public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
		event.put(EntityInit.FEMALE_ILLUSIONER.get(), Evoker.createAttributes().build());
	}

	public static EntityType<FemaleIllusioner> createEntityType() {
		var builder = EntityType.Builder.<FemaleIllusioner>of(FemaleIllusioner::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8);
		return builder.build("female_illusioner");
	}
}
