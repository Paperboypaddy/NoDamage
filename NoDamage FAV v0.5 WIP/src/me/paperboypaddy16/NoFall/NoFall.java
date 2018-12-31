package me.paperboypaddy16.NoFall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoFall extends JavaPlugin {
	public Permission use = new Permission("ndf.use");
	public Permission fall = new Permission("ndf.use.fall");
	public Permission drowning = new Permission("ndf.use.drowning");
	public Permission fire = new Permission("ndf.use.fire");
	public Permission lava = new Permission("ndf.use.lava");
	public Permission ALL = new Permission("ndf.");
	public boolean Old;

	public void onEnable() {
		RegisterEvents();
		VersionChecker();
	}

	public void RegisterEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.addPermission(use);
		pm.addPermission(fall);
		pm.addPermission(drowning);
		pm.addPermission(fire);
		pm.addPermission(lava);
		pm.addPermission(ALL);
		pm.registerEvents(new DamageEvent(this), this);
		registerConfig();
	}

	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	private void VersionChecker() {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(
					"https://api.spigotmc.org/legacy/update.php?resource=62260").openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
			if (!version.equals("v0.5.1")) {
				this.getServer().getConsoleSender().sendMessage(
						ChatColor.YELLOW + "There is a new update available at www.spigotmc.org/resources/62260/");
				Old = true;
			} else {
				this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "NoDamage is upto date");
				Old = false;
			}
		} catch (@SuppressWarnings("unused") IOException e) {
			this.getServer().getConsoleSender()
					.sendMessage("[NoDamage]" + ChatColor.RED + " ERROR: COULD NOT CHECK FOR UPDATE");
			this.getServer().getConsoleSender()
					.sendMessage("[NoDamage]" + ChatColor.RED + " please check your servers internet connection");
		}
	}
}
