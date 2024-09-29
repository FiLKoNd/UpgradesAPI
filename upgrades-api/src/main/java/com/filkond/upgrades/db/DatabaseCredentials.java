package com.filkond.upgrades.db;

public interface DatabaseCredentials {
    String username();
    String password();
    String hostname();
    String[] flags();
    String databaseName();
    String databaseId();
    default String getJdbcUrl() {
        return String.format("jdbc:%s://%s/%s?%s", databaseId(), hostname(), databaseName(), String.join("&", flags()));
    }
}
