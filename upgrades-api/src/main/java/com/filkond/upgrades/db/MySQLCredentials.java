package com.filkond.upgrades.db;

public record MySQLCredentials(String databaseName, String hostname, String username, String password,
                               String... flags) implements DatabaseCredentials {
    @Override
    public String databaseId() {
        return "mysql";
    }
}
