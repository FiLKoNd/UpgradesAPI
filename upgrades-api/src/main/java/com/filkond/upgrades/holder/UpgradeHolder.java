package com.filkond.upgrades.holder;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;

public interface UpgradeHolder {
    @NotNull UUID getUniqueId();
    @NotNull Map<String, Integer> getProgresses();
}
