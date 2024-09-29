package com.filkond.upgrades.configuration;

import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class SimpleUpgradeLevel implements UpgradeLevel {
    private final int intValue;
    private final Set<UpgradeBuff<?>> buffs;
}
