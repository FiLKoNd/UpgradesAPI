package com.filkond.upgrades_example.listener;

import com.filkond.upgrades_example.UpgradesExample;
import com.filkond.upgrades.configuration.buff.DoubleBuff;
import com.filkond.upgrades.configuration.buff.IntegerBuff;
import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import lombok.val;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        val controller = UpgradesExample.getInstance().getController();
        Player player = event.getPlayer();
        int buff = controller.getAllBuffs(player, "increase_hp", IntegerBuff.class)
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
        val controller = UpgradesExample.getInstance().getController();
        double buff = controller.getAllBuffs(player, "increase_damage", DoubleBuff.class)
                .stream()
                .mapToDouble(UpgradeBuff::getValue)
                .reduce(1, (v, v1) -> v * v1);
        event.setDamage(event.getDamage() * buff);
    }
}
