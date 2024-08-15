package com.filkond.upgrades_example;

import com.filkond.upgrades.configuration.UpgradeLevel;
import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.DoubleBuff;
import com.filkond.upgrades.configuration.buff.IntegerBuff;
import com.filkond.upgrades.controller.AdvancedController;
import com.filkond.upgrades.db.DatabaseCredentials;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class PlayerUpgradesController extends AdvancedController<Player> {
    private final Set<UpgradeType> types = Set.of(UpgradeType.builder()
                    .id("test")
                    .levels(Set.of(
                            UpgradeLevel.builder()
                                    .intValue(1)
                                    .buffs(Set.of(new DoubleBuff("increase_damage", 1.2D), new IntegerBuff("increase_hp", 4))).build(),
                            UpgradeLevel.builder()
                                    .intValue(2)
                                    .buffs(Set.of(new DoubleBuff("increase_damage", 2D), new IntegerBuff("increase_hp", 8))).build()
                    )).build(),
            UpgradeType.builder()
                    .id("test2")
                    .levels(Set.of(
                            UpgradeLevel.builder()
                                    .intValue(1)
                                    .buffs(Set.of(new DoubleBuff("increase_damage", 1.2D), new IntegerBuff("increase_hp", 5))).build(),
                            UpgradeLevel.builder()
                                    .intValue(2)
                                    .buffs(Set.of(new DoubleBuff("increase_damage", 5D), new IntegerBuff("increase_hp", 5))).build()
                    )
                    ).build()
    );

    public PlayerUpgradesController(DatabaseCredentials credentials) {
        super(credentials);
    }

    @Override
    public UUID getUniqueId(Player entity) {
        return entity.getUniqueId();
    }

    @Override
    public Set<UpgradeType> getUpgradeTypes() {
        return types;
    }
}
