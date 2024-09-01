package com.filkond.upgrades.db;

public interface DatabaseCredentials {
    String username();
    String password();
    String getJdbcUrl();
}
