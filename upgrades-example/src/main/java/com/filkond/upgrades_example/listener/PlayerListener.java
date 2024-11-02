package com.filkond.upgrades_example.listener;

import com.filkond.upgrades_example.PlayerUpgradesController;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Set;

public class PlayerListener implements Listener {

    private final PlayerUpgradesController controller;
    public PlayerListener(PlayerUpgradesController controller) {
        this.controller = controller;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        int buff = controller.getAllBuffs(player, "increase_hp", Integer.class)
                .stream()
                .mapToInt(UpgradeBuff::getValue)
                .sum();
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (attribute == null) return;
        attribute.setBaseValue(20 + buff);
        player.setHealth(attribute.getValue());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (!(damager instanceof Player player)) return;
        Set<UpgradeBuff<Double>> buffs = controller.getAllBuffs(player, "increase_damage", Double.class);
        double buff = buffs
                .stream()
                .mapToDouble(UpgradeBuff::getValue)
                .reduce(1, (v, v1) -> v * v1);
        event.setDamage(event.getDamage() * buff);
    }
}
