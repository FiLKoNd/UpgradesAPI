package com.filkond.upgrades_example;

import com.filkond.upgrades.configuration.UpgradeLevel;
import com.filkond.upgrades.configuration.UpgradeType;
import com.filkond.upgrades.configuration.buff.DoubleBuff;
import com.filkond.upgrades.configuration.buff.IntegerBuff;
import com.filkond.upgrades.controller.AdvancedController;
import com.filkond.upgrades.controller.AdvancedControllerImpl;
import com.filkond.upgrades.controller.UpgradeControllerImpl;
import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.holder.SimpleUpgradeHolder;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
public class PlayerUpgradesController extends AdvancedControllerImpl<PlayerHolder, Player> {
    private final Set<UpgradeType> upgradeTypes = Set.of(UpgradeType.builder()
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

    public PlayerUpgradesController(Class<PlayerHolder> holderClass) {
        super(holderClass);
    }

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
