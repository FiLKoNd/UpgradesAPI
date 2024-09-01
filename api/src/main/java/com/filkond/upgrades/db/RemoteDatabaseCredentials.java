package com.filkond.upgrades.db;

public interface RemoteDatabaseCredentials extends DatabaseCredentials {
    String username();
    String password();
    String hostname();
    String[] flags();
    String databaseName();
    String databaseId();
    @Override
    default String getJdbcUrl() {
        return String.format("jdbc:%s://%s/%s?%s", databaseId(), hostname(), databaseName(), String.join("&", flags()));
    }
}
