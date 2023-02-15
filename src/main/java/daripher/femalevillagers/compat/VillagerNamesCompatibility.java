package daripher.femalevillagers.compat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.natamus.collective_common_forge.data.GlobalVariables;
import com.natamus.collective_common_forge.functions.DataFunctions;
import com.natamus.collective_common_forge.functions.EntityFunctions;
import com.natamus.collective_common_forge.functions.StringFunctions;
import com.natamus.villagernames_common_forge.config.ConfigHandler;
import com.natamus.villagernames_common_forge.util.Names;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID)
public class VillagerNamesCompatibility {
	private static List<String> customFemaleNames = null;

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void setVillagerName(EntityJoinLevelEvent event) {
		if (!shouldHaveName(event.getEntity())) {
			return;
		}

		var isFemale = isFemaleEntity(event.getEntity());
		var entityName = getRandomName(isFemale);
		EntityFunctions.nameEntity(event.getEntity(), entityName);
	}

	private static boolean shouldHaveName(Entity entity) {
		if (!ModList.get().isLoaded("villagernames")) {
			return false;
		}

		if (entity.level.isClientSide) {
			return false;
		}

		if (entity.hasCustomName()) {
			return false;
		}

		if (entity.getType() == EntityInit.FEMALE_WANDERING_TRADER.get()) {
			return true;
		}

		if (!(entity instanceof Villager)) {
			boolean shouldHaveName = false;

			if (ConfigHandler.nameModdedVillagers) {
				if (EntityFunctions.isModdedVillager(entity)) {
					shouldHaveName = true;
				}
			}

			if (!shouldHaveName) {
				return false;
			}
		}

		return true;
	}

	private static boolean isFemaleEntity(Entity entity) {
		return entity.getType() == EntityInit.FEMALE_VILLAGER.get() || entity.getType() == EntityInit.FEMALE_WANDERING_TRADER.get()
				|| entity.getType() == EntityInit.FEMALE_ZOMBIE_VILLAGER.get();
	}

	private static String getRandomName(boolean isFemale) {
		var usePredefinedNames = isFemale ? ConfigHandler._useFemaleNames : ConfigHandler._useMaleNames;
		var useCustomNames = ConfigHandler._useCustomNames;
		var predefinedNames = isFemale ? GlobalVariables.femalenames : GlobalVariables.malenames;
		var customNames = isFemale ? customFemaleNames : Names.customnames;
		return getRandomName(usePredefinedNames, useCustomNames, predefinedNames, customNames);
	}

	private static String getRandomName(boolean usePredefinedNames, boolean useCustomNames, List<String> predefinedNames, List<String> customNames) {
		List<String> names = null;

		if (usePredefinedNames) {
			names = predefinedNames;
		}

		if (useCustomNames && customNames != null) {
			if (names == null) {
				names = customNames;
			} else {
				names = Stream.concat(customNames.stream(), names.stream()).collect(Collectors.toList());
			}
		}

		if (names == null) {
			return "";
		}

		var name = names.get(GlobalVariables.random.nextInt(names.size())).toLowerCase();
		return StringFunctions.capitalizeEveryWord(name);
	}

	@EventBusSubscriber(modid = FemaleVillagersMod.MOD_ID, bus = Bus.MOD)
	public static class ModEvents {
		@SubscribeEvent()
		public static void initCustomFemaleNames(FMLLoadCompleteEvent event) {
			var configDirectoryPath = DataFunctions.getConfigDirectory() + File.separator + "villagernames";
			var configDirectory = new File(configDirectoryPath);
			var configFile = new File(configDirectoryPath + File.separator + "customfemalenames.txt");

			if (configDirectory.isDirectory() && configFile.isFile()) {
				var customNames = readCustomNames(configDirectoryPath);
				customFemaleNames = Arrays.asList(customNames);
			} else {
				configDirectory.mkdirs();
				writeCustomNames(configDirectoryPath);
				customFemaleNames = new ArrayList<>(Arrays.asList("Olivia", "Emma", "Charlotte"));
			}
		}

		private static String[] readCustomNames(String configDirectoryPath) {
			String customNamesString = null;

			try {
				customNamesString = Files.readString(Paths.get(configDirectoryPath + File.separator + "customfemalenames.txt", new String[0]));
			} catch (IOException e) {
				e.printStackTrace();
			}

			customNamesString = customNamesString.replace("\n", "").replace("\r", "");
			return customNamesString.split(",");
		}

		private static void writeCustomNames(String configDirectoryPath) {
			PrintWriter writer = null;

			try {
				writer = new PrintWriter(configDirectoryPath + File.separator + "customfemalenames.txt", StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}

			writer.println("Olivia,");
			writer.println("Emma,");
			writer.println("Charlotte");
			writer.close();
		}
	}
}
