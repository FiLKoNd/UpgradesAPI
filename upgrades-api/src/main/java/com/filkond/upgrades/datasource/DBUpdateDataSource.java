package com.filkond.upgrades.datasource;

import com.filkond.upgrades.db.DatabaseCredentials;
import com.filkond.upgrades.holder.UpgradeHolder;
import com.filkond.upgrades.holder.dao.UpgradeHolderDAO;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.SneakyThrows;
import lombok.val;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class DBUpdateDataSource<H extends UpgradeHolder> implements UpgradesDataSource<H> {
    private final UpgradeHolderDAO<H> dao;

    public DBUpdateDataSource(UpgradeHolderDAO<H> dao) {
        this.dao = dao;
    }

    @SneakyThrows
    public DBUpdateDataSource(String jdbcUrl, String username, String password, Class<H> holderClass) {
        val connectionSource = new JdbcPooledConnectionSource(jdbcUrl, username, password);
        TableUtils.createTableIfNotExists(connectionSource, holderClass);
        this.dao = DaoManager.createDao(connectionSource, holderClass);
    }

    public DBUpdateDataSource(DatabaseCredentials credentials, Class<H> holderClass) {
        this(credentials.getJdbcUrl(), credentials.username(), credentials.password(), holderClass);
    }

    @Override
    @SneakyThrows
    public void createOrUpdate(H entity) {
        dao.createOrUpdate(entity);
    }

    @Override
    public Optional<H> getHolderByUniqueId(UUID uniqueId) {
        return dao.getByUniqueId(uniqueId);
    }

    @Override
    public Collection<H> getHolders() {
        return dao.getAll();
    }
}
