package com.filkond.upgrades.configuration;

import com.filkond.upgrades.configuration.buff.UpgradeBuff;

import java.util.Set;

public interface UpgradeLevel {
    int getIntValue();

    Set<UpgradeBuff<?>> getBuffs();
}
