package com.filkond.upgrades.configuration;

import java.util.Optional;

public interface UpgradeType {
    Optional<UpgradeLevel> getMaxLevel();

    Optional<UpgradeLevel> getLevel(int level);

    String getId();

    java.util.Set<UpgradeLevel> getLevels();
}
