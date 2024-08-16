package com.filkond.upgrades.holder.dao;

import com.filkond.upgrades.holder.UpgradeHolder;
import com.j256.ormlite.dao.Dao;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UpgradeHolderDAO<H extends UpgradeHolder> extends Dao<H, Long> {
    @NotNull Optional<H> getByUniqueId(@NotNull UUID uniqueId);
    @NotNull Set<H> getAll();
}
