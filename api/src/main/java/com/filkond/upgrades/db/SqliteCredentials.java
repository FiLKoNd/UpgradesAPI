package com.filkond.upgrades.db;

import java.io.File;

public record SqliteCredentials(File file, String username, String password) implements DatabaseCredentials {

    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String getJdbcUrl() {
        return "jdbc:sqlite:"+ file.getAbsolutePath();
    }
}
