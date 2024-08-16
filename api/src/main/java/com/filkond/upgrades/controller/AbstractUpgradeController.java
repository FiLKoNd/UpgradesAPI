package com.filkond.upgrades.controller;

import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.holder.UpgradeHolder;
import com.filkond.upgrades.holder.dao.UpgradeHolderDAO;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@RequiredArgsConstructor
public abstract class AbstractUpgradeController<H extends UpgradeHolder> implements UpgradeController<H> {
    private UpgradeHolderDAO<H> holdersDAO;
    protected final Set<H> holders = new HashSet<>();
    private final Class<H> holderClass;

    public AbstractUpgradeController(Class<H> holderClass, @NotNull DatabaseCredentials credentials) {
        this.holderClass = holderClass;
        initialize(credentials);
    }

    @Override
    @SneakyThrows
    public void initialize(@NotNull DatabaseCredentials credentials) {
        val connectionSource = new JdbcPooledConnectionSource(credentials.getJdbcUrl());
        connectionSource.setUsername(credentials.username());
        connectionSource.setPassword(credentials.password());
        TableUtils.createTableIfNotExists(connectionSource, holderClass);
        holdersDAO = DaoManager.createDao(connectionSource, holderClass);
        holders.addAll(holdersDAO.getAll());
    }

    @Override
    @SneakyThrows
    public void save() {
        for (H holder : holders) holdersDAO.createOrUpdate(holder);
    }
}
