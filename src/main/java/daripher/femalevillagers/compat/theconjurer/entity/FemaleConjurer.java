package daripher.femalevillagers.compat.theconjurer.entity;

import com.legacy.conjurer_illager.IllagerRegistry;
import com.legacy.conjurer_illager.entity.ConjurerEntity;
import com.legacy.conjurer_illager.registry.IllagerEntityTypes;

import daripher.femalevillagers.compat.Compatibilities;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FemaleConjurer extends ConjurerEntity {
	public FemaleConjurer(EntityType<? extends ConjurerEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public FemaleConjurer(Level level) {
		super(Compatibilities.THE_CONJURER.getFemaleConjurerEntityType(), level);
	}

	@Override
	protected Component getTypeName() {
		return IllagerEntityTypes.CONJURER.getDescription();
	}

	@Override
	public ItemStack getPickResult() {
		return new ItemStack(IllagerRegistry.CONJURER_SPAWN_EGG.get());
	}

	@Override
	public float getVoicePitch() {
		return super.getVoicePitch() + 0.4F;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
		return IllagerEntityTypes.CONJURER.getDefaultLootTable();
	}

	public static EntityType<FemaleConjurer> createEntityType() {
		var builder = EntityType.Builder.<FemaleConjurer>of(FemaleConjurer::new, MobCategory.MONSTER).sized(0.6F, 1.95F);
		return builder.build("female_conjurer");
	}
}
