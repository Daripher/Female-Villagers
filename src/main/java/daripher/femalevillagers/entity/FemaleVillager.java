package daripher.femalevillagers.entity;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
public class FemaleVillager extends Villager {
	public FemaleVillager(EntityType<? extends FemaleVillager> entityType, Level level) {
		super(entityType, level);
	}

	public FemaleVillager(Level level) {
		super(EntityInit.FEMALE_VILLAGER.get(), level);
	}

	@Override
	protected Component getTypeName() {
		var professionId = ForgeRegistries.VILLAGER_PROFESSIONS.getKey(getVillagerData().getProfession());
		var professionNamespace = !"minecraft".equals(professionId.getNamespace()) ? professionId.getNamespace() + '.' : "";
		var professionName = professionNamespace + professionId.getPath();
		return Component.translatable(EntityType.VILLAGER.getDescriptionId() + '.' + professionName);
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(Items.VILLAGER_SPAWN_EGG);
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.4F;
	}

	@SubscribeEvent
	public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
		event.put(EntityInit.FEMALE_VILLAGER.get(), Villager.createAttributes().build());
	}

	public static EntityType<FemaleVillager> createEntityType() {
		var builder = EntityType.Builder.<FemaleVillager>of(FemaleVillager::new, MobCategory.MISC).sized(0.6F, 1.95F).clientTrackingRange(10);
		return builder.build("female_villager");
	}
}
