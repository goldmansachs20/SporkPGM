package io.sporkpgm.util;

import io.sporkpgm.Spork;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private static File configFile;
	private static FileConfiguration config;

	public static void init() {
		configFile = new File(Spork.get().getDataFolder(), "config.yml");
		if (!configFile.exists())
			Spork.get().saveResource("config.yml", true);

		config = YamlConfiguration.loadConfiguration(configFile);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String path) {
		return (T) config.get(path);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String path, Object def) {
		return (T) config.get(path, def);
	}

	public static void set(String path, Object value) {
		config.set(path, value);
		try {
			config.save(configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class General {

		public static final boolean DEBUG = get("general.debug", false);

	}

	public static class Map {

		public static final String DIRECTORY = get("map.directory", "maps");
		public static final String CONFIG = get("map.config", "map.xml");

	}

	public static class Rotation {

		public static final String ROTATION = get("rotation.file", "rotation.txt");

	}

	public static class Match {

		public static final String PREFIX = get("match.prefix", "match-");

	}

}