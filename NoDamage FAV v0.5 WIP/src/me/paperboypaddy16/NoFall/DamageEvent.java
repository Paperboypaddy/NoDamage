package me.paperboypaddy16.NoFall;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class DamageEvent implements Listener {
	public NoFall plugin;

	public DamageEvent(NoFall pl) {
		plugin = pl;
	}

	@EventHandler
	public void onPlayerFall(EntityDamageEvent e) {
		Player p;
		if (e.getEntityType().toString().equals("PLAYER")) {
			p = (Player) e.getEntity();
		} else {
			return;
		}

		List<String> disworlds = plugin.getConfig().getStringList("Disabled Worlds");
		boolean All = p.hasPermission(plugin.ALL);
		if (disworlds.contains(p.getWorld().getName()))
			return;
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			if (p.hasPermission(plugin.fall) || p.hasPermission(plugin.use) || All) {
				e.setCancelled(true);
			}
			return;
		}
		if (e.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
			if (p.hasPermission(plugin.drowning) || p.hasPermission(plugin.use) || All) {
				e.setCancelled(true);
			}
			return;
		}
		if (e.getCause() == EntityDamageEvent.DamageCause.FIRE) {
			if (p.hasPermission(plugin.fire) || p.hasPermission(plugin.use) || All) {
				e.setCancelled(true);
				p.setFireTicks(0);
			}
			return;
		}
		if (e.getCause() == EntityDamageEvent.DamageCause.LAVA) {
			if (p.hasPermission(plugin.lava) || p.hasPermission(plugin.use) || All) {
				e.setCancelled(true);
				p.setFireTicks(0);
			}
			return;
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (player.isOp()) {
			if (plugin.Old) {
				player.sendMessage(
						ChatColor.GOLD + "There is a new update available at www.spigotmc.org/resources/62260/");
			}
		}
	}
}