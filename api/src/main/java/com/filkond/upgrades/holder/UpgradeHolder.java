package com.filkond.upgrades.holder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public interface UpgradeHolder {
    @NotNull UUID getUniqueId();
    @NotNull HashMap<String, Integer> getProgresses();
}
