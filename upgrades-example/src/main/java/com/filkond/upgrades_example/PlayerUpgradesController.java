package com.filkond.upgrades_example;

import com.filkond.upgrades.configuration.SimpleUpgradeLevel;
import com.filkond.upgrades.configuration.SimpleUpgradeType;
import com.filkond.upgrades.configuration.buff.DoubleBuff;
import com.filkond.upgrades.configuration.buff.IntegerBuff;
import com.filkond.upgrades.controller.AbstractAdvancedController;
import com.filkond.upgrades.db.DatabaseCredentials;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
public class PlayerUpgradesController extends AbstractAdvancedController<PlayerHolder, Player> {
    private final Set<SimpleUpgradeType> upgradeTypes = Set.of(SimpleUpgradeType.builder()
                    .id("test")
                    .levels(Set.of(
                            SimpleUpgradeLevel.builder()
                                    .intValue(1)
                                    .buffs(Set.of(new DoubleBuff("increase_damage", 1.2D), new IntegerBuff("increase_hp", 4))).build(),
                            SimpleUpgradeLevel.builder()
                                    .intValue(2)
                                    .buffs(Set.of(new DoubleBuff("increase_damage", 2D), new IntegerBuff("increase_hp", 8))).build()
                    )).build(),
            SimpleUpgradeType.builder()
                    .id("test2")
                    .levels(Set.of(
                            SimpleUpgradeLevel.builder()
                                            .intValue(1)
                                            .buffs(Set.of(new DoubleBuff("increase_damage", 1.2D), new IntegerBuff("increase_hp", 5))).build(),
                            SimpleUpgradeLevel.builder()
                                            .intValue(2)
                                            .buffs(Set.of(new DoubleBuff("increase_damage", 5D), new IntegerBuff("increase_hp", 5))).build()
                            )
                    ).build()
    );

    public PlayerUpgradesController(Class<PlayerHolder> holderClass, @NotNull DatabaseCredentials credentials) {
        super(holderClass, credentials);
    }

    @Override
    public PlayerHolder getDefaultHolder(Player entity) {
        return PlayerHolder.builder()
                .uniqueId(entity.getUniqueId())
                .build();
    }
}
