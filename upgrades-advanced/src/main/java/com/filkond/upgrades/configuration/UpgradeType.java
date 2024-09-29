package com.filkond.upgrades.configuration;

import java.util.Optional;
import java.util.Set;

public interface UpgradeType {
    Optional<UpgradeLevel> getMaxLevel();

    Optional<UpgradeLevel> getLevel(int level);

    String getId();

    Set<UpgradeLevel> getLevels();
}
