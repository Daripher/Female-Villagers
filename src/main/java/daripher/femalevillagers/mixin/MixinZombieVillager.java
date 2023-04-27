package daripher.femalevillagers.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import daripher.femalevillagers.entity.FemaleZombieVillager;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraftforge.event.ForgeEventFactory;

@Mixin(ZombieVillager.class)
public abstract class MixinZombieVillager extends Zombie implements VillagerDataHolder {
	private @Shadow Tag gossips;
	private @Shadow CompoundTag tradeOffers;
	private @Shadow int villagerXp;
	private @Shadow UUID conversionStarter;

	public MixinZombieVillager() {
		super(null);
	}

	@Inject(method = "finishConversion", at = @At("HEAD"), cancellable = true)
	private void finishFemaleVillagerConversion(ServerLevel level, CallbackInfo callbackInfo) {
		if ((Object) this instanceof FemaleZombieVillager) {
			var femaleVillager = convertTo(EntityInit.FEMALE_VILLAGER.get(), false);

			for (var slot : EquipmentSlot.values()) {
				var itemInSlot = getItemBySlot(slot);

				if (!itemInSlot.isEmpty()) {
					if (EnchantmentHelper.hasBindingCurse(itemInSlot)) {
						femaleVillager.getSlot(slot.getIndex() + 300).set(itemInSlot);
					} else {
						var dropChance = getEquipmentDropChance(slot);

						if (dropChance > 1) {
							spawnAtLocation(itemInSlot);
						}
					}
				}
			}

			femaleVillager.setVillagerData(getVillagerData());

			if (gossips != null) {
				femaleVillager.setGossips(gossips);
			}

			if (tradeOffers != null) {
				femaleVillager.setOffers(new MerchantOffers(tradeOffers));
			}

			femaleVillager.setVillagerXp(villagerXp);
			femaleVillager.finalizeSpawn(level, level.getCurrentDifficultyAt(femaleVillager.blockPosition()), MobSpawnType.CONVERSION, null, null);

			if (conversionStarter != null) {
				var player = level.getPlayerByUUID(conversionStarter);

				if (player instanceof ServerPlayer) {
					CriteriaTriggers.CURED_ZOMBIE_VILLAGER.trigger((ServerPlayer) player, this, femaleVillager);
					level.onReputationEvent(ReputationEventType.ZOMBIE_VILLAGER_CURED, player, femaleVillager);
				}
			}

			femaleVillager.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));

			if (!isSilent()) {
				level.levelEvent(null, 1027, blockPosition(), 0);
			}

			ForgeEventFactory.onLivingConvert(this, femaleVillager);
			callbackInfo.cancel();
		}
	}

	@Override
	public <T extends Mob> T convertTo(EntityType<T> entityType, boolean canHaveEquipment) {
		if (isRemoved()) {
			return (T) null;
		} else {
			T convertedMob = entityType.create(this.level);
			convertedMob.addTag("do_not_replace");
			convertedMob.copyPosition(this);
			convertedMob.setBaby(isBaby());
			convertedMob.setNoAi(isNoAi());
			convertedMob.setInvulnerable(isInvulnerable());

			if (hasCustomName()) {
				convertedMob.setCustomName(getCustomName());
				convertedMob.setCustomNameVisible(isCustomNameVisible());
			}

			if (isPersistenceRequired()) {
				convertedMob.setPersistenceRequired();
			}

			if (canHaveEquipment) {
				convertedMob.setCanPickUpLoot(this.canPickUpLoot());

				for (var slot : EquipmentSlot.values()) {
					ItemStack itemInSlot = getItemBySlot(slot);

					if (!itemInSlot.isEmpty()) {
						convertedMob.setItemSlot(slot, itemInSlot.copy());
						convertedMob.setDropChance(slot, getEquipmentDropChance(slot));
						itemInSlot.setCount(0);
					}
				}
			}

			level.addFreshEntity(convertedMob);

			if (isPassenger()) {
				var vehicle = getVehicle();
				stopRiding();
				convertedMob.startRiding(vehicle, true);
			}

			discard();
			return convertedMob;
		}
	}
}
