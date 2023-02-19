package daripher.femalevillagers.compat.guardvillagers.entity;

import daripher.femalevillagers.compat.Compatibilities;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import tallestegg.guardvillagers.GuardEntityType;
import tallestegg.guardvillagers.GuardItems;
import tallestegg.guardvillagers.entities.Guard;

public class FemaleGuard extends Guard {
	public FemaleGuard(EntityType<? extends Guard> type, Level world) {
		super(type, world);
	}

	public FemaleGuard(Level level) {
		super(Compatibilities.GUARD_VILLAGERS.getFemaleGuardEntityType(), level);
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

	public static EntityType<FemaleGuard> createEntityType() {
		var builder = EntityType.Builder.<FemaleGuard>of(FemaleGuard::new, MobCategory.MISC).sized(0.6F, 1.95F).setShouldReceiveVelocityUpdates(true);
		return builder.build("female_guard");
	}
}
