package com.filkond.upgrades.db;

public record MariaDBCredentials(String databaseName, String hostname, String username, String password,
                                 String... flags) implements RemoteDatabaseCredentials {
    @Override
    public String databaseId() {
        return "mariadb";
    }
}
