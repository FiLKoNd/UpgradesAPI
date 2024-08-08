package com.filkond.upgrades.holder.dao;

import com.filkond.upgrades.holder.SimpleUpgradeHolder;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UpgradeHolderDAOImpl extends BaseDaoImpl<SimpleUpgradeHolder, Long> implements UpgradeHolderDAO {
    public UpgradeHolderDAOImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, SimpleUpgradeHolder.class);
    }

    @Override
    @SneakyThrows
    public @NotNull Optional<SimpleUpgradeHolder> getByUniqueId(@NotNull UUID uniqueId) {
        var holders = queryForEq("uuid", uniqueId);
        return holders.stream().findFirst();
    }

    @Override
    @SneakyThrows
    public @NotNull Set<SimpleUpgradeHolder> getAll() {
        return new HashSet<>(queryForAll());
    }
}
