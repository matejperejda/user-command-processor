package com.example.processor.adapter.persistence;

import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class H2DbInitializer {

    private static final Logger log = LoggerFactory.getLogger(H2DbInitializer.class);

    private final Connection connection;

    public H2DbInitializer(Connection connection) {
        this.connection = connection;
    }

    public void runScript(String name) {
        InputStream is = H2DbInitializer.class.getClassLoader().getResourceAsStream(name);
        if (is == null) {
            log.error("SQL script file not found.");
            throw new RuntimeException("SQL script file not found.");
        }
        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            RunScript.execute(connection, reader);
            log.info("Database script executed successfully.");
        } catch (SQLException e) {
            log.error("Error executing script to initialize database", e);
        } catch (Exception e) {
            log.error("Unexpected error during script execution", e);
        }
    }
}
