package com.filkond.upgrades.holder.dao;

import com.filkond.upgrades.holder.UpgradeHolder;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UpgradeHolderDAOImpl<H extends UpgradeHolder> extends BaseDaoImpl<H, Long> implements UpgradeHolderDAO<H> {
    public UpgradeHolderDAOImpl(ConnectionSource connectionSource, Class<H> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    @SneakyThrows
    public @NotNull Optional<H> getByUniqueId(@NotNull UUID uniqueId) {
        var holders = queryForEq("uuid", uniqueId);
        return holders.stream().findFirst();
    }

    @Override
    @SneakyThrows
    public @NotNull Set<H> getAll() {
        return new HashSet<>(queryForAll());
    }
}
