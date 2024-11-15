package com.example.processor.adapter.persistence.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DataSource {

    private static final String CONFIG_PROPERTIES = "db/h2_config.properties";

    private static final HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig(CONFIG_PROPERTIES);
        ds = new HikariDataSource(config);
    }

    private DataSource() {}

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception while retrieving a database connection.", e);
        }
    }
}