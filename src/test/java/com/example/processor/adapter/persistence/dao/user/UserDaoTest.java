package com.example.processor.adapter.persistence.dao.user;

import com.example.processor.adapter.persistence.H2DbInitializer;
import com.example.processor.adapter.persistence.config.DataSource;
import com.example.processor.adapter.persistence.dao.DaoFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class UserDaoTest {
    private static final String TEST_BEFORE_DB_SCRIPT_RESOURCE = "v1_create_susers_table.sql";
    private static final String TEST_AFTER_DB_SCRIPT_RESOURCE = "u1_create_susers_table.sql";

    public UserDao userDao;

    private H2DbInitializer initializer;

    @BeforeEach
    public void setup() {
        userDao = DaoFactory.getDefaultDaoFactory().getUserDao();
        initializer = new H2DbInitializer(DataSource.getConnection());
        initializer.runScript(TEST_BEFORE_DB_SCRIPT_RESOURCE);
    }

    @AfterEach
    public void cleanAndClose() {
        initializer.runScript(TEST_AFTER_DB_SCRIPT_RESOURCE);
    }
}
