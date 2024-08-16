package com.filkond.upgrades.controller;

import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.holder.UpgradeHolder;
import com.filkond.upgrades.holder.dao.UpgradeHolderDAO;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface UpgradeController<H extends UpgradeHolder> {
    @SneakyThrows
    void initialize(@NotNull DatabaseCredentials credentials);

    @SneakyThrows
    void save();

    UpgradeHolderDAO<H> getHoldersDAO();

    Set<H> getHolders();

    Class<H> getHolderClass();
}
