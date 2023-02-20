package daripher.femalevillagers.compat.villagernames;

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

import com.google.gson.JsonSyntaxException;
import com.natamus.collective_common_forge.data.GlobalVariables;
import com.natamus.collective_common_forge.functions.DataFunctions;
import com.natamus.collective_common_forge.functions.EntityFunctions;
import com.natamus.collective_common_forge.functions.JsonFunctions;
import com.natamus.collective_common_forge.functions.StringFunctions;
import com.natamus.villagernames_common_forge.config.ConfigHandler;
import com.natamus.villagernames_common_forge.util.Names;

import daripher.femalevillagers.FemaleVillagersMod;
import daripher.femalevillagers.entity.FemaleVillager;
import daripher.femalevillagers.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

public enum VillagerNamesCompatibility {
	INSTANCE;

	private static List<String> customFemaleNames = null;

	public void addCompatibility() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::initCustomFemaleNames);
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, this::setVillagerName);
		MinecraftForge.EVENT_BUS.addListener(this::setFemaleVillagerNameInGUI);
	}

	private void setVillagerName(EntityJoinWorldEvent event) {
		if (!shouldHaveName(event.getEntity())) {
			return;
		}

		var isFemale = isFemaleEntity(event.getEntity());
		var entityName = getRandomName(isFemale);
		EntityFunctions.nameEntity(event.getEntity(), entityName);
	}

	private void setFemaleVillagerNameInGUI(PlayerInteractEvent.EntityInteract event) {
		if (event.getTarget().getType() != EntityInit.FEMALE_VILLAGER.get()) {
			return;
		}

		if (event.getEntity().level.isClientSide) {
			return;
		}

		if (!event.getTarget().hasCustomName()) {
			return;
		}

		if (event.getEntity().isCrouching()) {
			return;
		}

		if (event.getHand() != InteractionHand.MAIN_HAND) {
			return;
		}

		var villager = (FemaleVillager) event.getTarget();
		var villagerData = villager.getVillagerData();
		var villagerProfession = villagerData.getProfession().toString();

		if (villagerProfession.equals("none") || villagerProfession.equals("nitwit")) {
			return;
		}

		if (villagerProfession.contains(":")) {
			villagerProfession = villagerProfession.split(":")[1];
		}

		if (villagerProfession.contains("-")) {
			villagerProfession = villagerProfession.split("-")[0].trim();
		}

		var villagerName = villager.getName();

		try {
			var nameJson = Component.Serializer.toJson(villagerName);
			var nameComponentMap = JsonFunctions.JsonStringToHashMap(nameJson);
			var prevName = nameComponentMap.get("text");
			var formattedProfession = villagerProfession.substring(0, 1).toUpperCase() + villagerProfession.substring(1);

			if (prevName.contains(formattedProfession)) {
				return;
			}

			nameComponentMap.put("text", prevName + " the " + formattedProfession);
			villager.setCustomName(Component.Serializer.fromJson(JsonFunctions.HashMapToJsonString(nameComponentMap)));

			new Thread(() -> {
				try {
					Thread.sleep(10);
				} catch (InterruptedException exception) {
				}

				nameComponentMap.put("text", prevName.replace(" the ", "").replace(formattedProfession, ""));
				villager.setCustomName(Component.Serializer.fromJson(JsonFunctions.HashMapToJsonString(nameComponentMap)));
			}).start();
		} catch (JsonSyntaxException exception) {
		}
	}

	private void initCustomFemaleNames(FMLLoadCompleteEvent event) {
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

	private boolean shouldHaveName(Entity entity) {
		if (!ModList.get().isLoaded("villagernames")) {
			return false;
		}

		if (entity.level.isClientSide) {
			return false;
		}

		if (entity.hasCustomName()) {
			return false;
		}

		if (EntityType.getKey(entity.getType()).toString().equals("femalevillagers:female_guard")) {
			return true;
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

	private boolean isFemaleEntity(Entity entity) {
		return ForgeRegistries.ENTITIES.getKey(entity.getType()).getNamespace().equals(FemaleVillagersMod.MOD_ID);
	}

	private String getRandomName(boolean isFemale) {
		var usePredefinedNames = isFemale ? ConfigHandler._useFemaleNames : ConfigHandler._useMaleNames;
		var useCustomNames = ConfigHandler._useCustomNames;
		var predefinedNames = isFemale ? GlobalVariables.femalenames : GlobalVariables.malenames;
		var customNames = isFemale ? customFemaleNames : Names.customnames;
		return getRandomName(usePredefinedNames, useCustomNames, predefinedNames, customNames);
	}

	private String getRandomName(boolean usePredefinedNames, boolean useCustomNames, List<String> predefinedNames, List<String> customNames) {
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

	private String[] readCustomNames(String configDirectoryPath) {
		String customNamesString = null;

		try {
			customNamesString = Files.readString(Paths.get(configDirectoryPath + File.separator + "customfemalenames.txt", new String[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}

		customNamesString = customNamesString.replace("\n", "").replace("\r", "");
		return customNamesString.split(",");
	}

	private void writeCustomNames(String configDirectoryPath) {
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
