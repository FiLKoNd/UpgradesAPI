package com.filkond.upgrades.configuration;

import com.filkond.upgrades.configuration.buff.UpgradeBuff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
public class UpgradeLevel {
    private final int level;
    private final Set<UpgradeBuff<?>> buffs;
}
