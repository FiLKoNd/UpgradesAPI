package com.filkond.upgrades.controller;

import com.filkond.upgrades.datasource.UpgradesDataSource;
import com.filkond.upgrades.holder.UpgradeHolder;

import java.util.Set;

public interface UpgradeController<H extends UpgradeHolder> {
    void initialize();

    void save();

    UpgradesDataSource<H> getDataSource();

    Set<H> getHolders();

    Class<H> getHolderClass();
}
