package com.filkond.upgrades.controller;

import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.holder.SimpleUpgradeHolder;
import com.filkond.upgrades.holder.dao.UpgradeHolderDAO;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@NoArgsConstructor
public class UpgradeController {
    private UpgradeHolderDAO holdersDAO;
    private final Set<SimpleUpgradeHolder> holders = new HashSet<>();

    public UpgradeController(@NotNull DatabaseCredentials credentials) {
        initialize(credentials);
    }

    @SneakyThrows
    public void initialize(@NotNull DatabaseCredentials credentials) {
        val connectionSource = new JdbcPooledConnectionSource(credentials.getJdbcUrl());
        connectionSource.setUsername(credentials.username());
        connectionSource.setPassword(credentials.password());
        TableUtils.createTableIfNotExists(connectionSource, SimpleUpgradeHolder.class);
        holdersDAO = DaoManager.createDao(connectionSource, SimpleUpgradeHolder.class);
        holders.addAll(holdersDAO.getAll());
    }

    @SneakyThrows
    public void save() {
        for (SimpleUpgradeHolder holder : holders) holdersDAO.createOrUpdate(holder);
    }

    public Optional<SimpleUpgradeHolder> getHolder(final UUID uniqueId) {
        return holders.stream()
                .filter(holder -> holder.getUniqueId().equals(uniqueId))
                .findFirst();
    }

    public SimpleUpgradeHolder getHolderOrDefault(final UUID uniqueId) {
        return getHolder(uniqueId).orElseGet(() -> {
            val defaultHolder = SimpleUpgradeHolder.builder()
                    .uniqueId(uniqueId)
                    .build();
            holders.add(defaultHolder);
            return defaultHolder;
        });
    }
}
