package com.filkond.upgrades.holder.dao;

import com.filkond.upgrades.holder.SimpleUpgradeHolder;
import com.j256.ormlite.dao.Dao;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UpgradeHolderDAO extends Dao<SimpleUpgradeHolder, Long> {
    @NotNull Optional<SimpleUpgradeHolder> getByUniqueId(@NotNull UUID uniqueId);
    @NotNull Set<SimpleUpgradeHolder> getAll();
}
