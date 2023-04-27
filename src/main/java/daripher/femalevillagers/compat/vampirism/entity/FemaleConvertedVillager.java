package daripher.femalevillagers.compat.vampirism.entity;

import java.util.UUID;

import daripher.femalevillagers.compat.vampirism.VampirismCompatibility;
import daripher.femalevillagers.entity.FemaleVillager;
import de.teamlapen.vampirism.api.VampirismAPI;
import de.teamlapen.vampirism.api.entity.convertible.IConvertedCreature;
import de.teamlapen.vampirism.api.entity.convertible.IConvertingHandler;
import de.teamlapen.vampirism.core.ModAdvancements;
import de.teamlapen.vampirism.entity.converted.ConvertedVillagerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import tallestegg.guardvillagers.GuardEntityType;
import tallestegg.guardvillagers.GuardItems;

public class FemaleConvertedVillager extends ConvertedVillagerEntity {
	public FemaleConvertedVillager(EntityType<? extends ConvertedVillagerEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public FemaleConvertedVillager(Level level) {
		super(VampirismCompatibility.INSTANCE.getFemaleConvertedVillagerEntityType(), level);
	}

	@Override
	public FemaleVillager cureEntity(ServerLevel world, PathfinderMob entity, EntityType<Villager> newType) {
		var newEntity = new FemaleVillager(world);
		assert newEntity != null;
		newEntity.load(entity.saveWithoutId(new CompoundTag()));
		newEntity.yBodyRot = entity.yBodyRot;
		newEntity.yHeadRot = entity.yHeadRot;
		newEntity.setUUID(UUID.randomUUID());
		entity.remove(Entity.RemovalReason.DISCARDED);
		entity.level.addFreshEntity(newEntity);
		newEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));

		if (!entity.isSilent()) {
			world.levelEvent(null, 1027, entity.blockPosition(), 0);
		}

		VampirismAPI.getExtendedCreatureVampirism(newEntity).ifPresent(creature -> creature.setBlood(1));
		net.minecraftforge.event.ForgeEventFactory.onLivingConvert(entity, newEntity);
		newEntity.setVillagerData(getVillagerData());
		newEntity.setGossips(getGossips().store(NbtOps.INSTANCE));
		newEntity.setOffers(getOffers());
		newEntity.setVillagerXp(getVillagerXp());
		UUID conversationStarter = ObfuscationReflectionHelper.getPrivateValue(ConvertedVillagerEntity.class, this, "conversationStarter");

		if (conversationStarter != null) {
			var player = world.getPlayerByUUID(conversationStarter);

			if (player instanceof ServerPlayer serverPlayer) {
				ModAdvancements.TRIGGER_CURED_VAMPIRE_VILLAGER.trigger(serverPlayer, this, newEntity);
				world.onReputationEvent(ReputationEventType.ZOMBIE_VILLAGER_CURED, player, newEntity);
			}
		}

		return newEntity;
	}

	@Override
	protected Component getTypeName() {
		return GuardEntityType.GUARD.get().getDescription();
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(GuardItems.GUARD_SPAWN_EGG.get());
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.4F;
	}

	public static class ConvertingHandler implements IConvertingHandler<Villager> {
		@Override
		public IConvertedCreature<Villager> createFrom(Villager entity) {
			var nbt = new CompoundTag();
			entity.saveWithoutId(nbt);
			var converted = VampirismCompatibility.INSTANCE.getFemaleConvertedVillagerEntityType().create(entity.level);
			converted.load(nbt);
			converted.setUUID(Mth.createInsecureUUID(converted.random));
			converted.yBodyRot = entity.yBodyRot;
			converted.yHeadRot = entity.yHeadRot;
			return converted;
		}
	}
}
