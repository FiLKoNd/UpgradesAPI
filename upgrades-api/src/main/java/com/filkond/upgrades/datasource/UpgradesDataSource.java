package com.filkond.upgrades.datasource;

import com.filkond.upgrades.holder.UpgradeHolder;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UpgradesDataSource<H extends UpgradeHolder> {
    void createOrUpdate(H entity);
    Optional<H> getHolderByUniqueId(UUID uniqueId);
    Collection<H> getHolders();
}
