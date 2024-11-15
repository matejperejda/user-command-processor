package com.example.processor.adapter.persistence.dao.user;

import com.example.processor.adapter.persistence.config.DataSource;
import com.example.processor.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.processor.adapter.persistence.mapper.UserDaoMapper.map;

public class UserDaoImpl implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    private static final String USER_ID_COLUMN = "USER_ID";
    private static final String USER_GUID_COLUMN = "USER_GUID";
    private static final String USER_NAME_COLUMN = "USER_NAME";

    @Override
    public void insert(User user) {
        final String query = "INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?, ?, ?)";
        log.info("Applying: {}", query);

        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, user.id());
            statement.setString(2, user.guid());
            statement.setString(3, user.name());
            statement.executeUpdate();
            log.info("User with id '{}' inserted successfully.", user.id());
        } catch (SQLException e) {
            log.error("Unexpected exception while inserting a user with id '{}'", user.id(), e);
            throw new RuntimeException("Unexpected exception while inserting a user.", e);
        }
    }

    @Override
    public void deleteAll() {
        final String query = "DELETE FROM SUSERS";
        log.info("Applying: {}", query);

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement()) {
            int rowsDeleted = statement.executeUpdate(query);
            log.info("Deleted {} user(s).", rowsDeleted);
        } catch (SQLException e) {
            log.error("Exception while deleting all users.", e);
            throw new RuntimeException("Unexpected exception while deleting all users.", e);
        }
    }

    @Override
    public List<User> listAll() {
        final String sql = "SELECT * FROM SUSERS";
        log.info("Applying: {}", sql);

        List<User> users = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                final User user = map(
                        rs.getObject(USER_ID_COLUMN, UUID.class),
                        rs.getString(USER_GUID_COLUMN),
                        rs.getString(USER_NAME_COLUMN));
                users.add(user);
            }
        } catch (Exception e) {
            log.error("Unexpected exception while retrieving a list of users.", e);
            throw new RuntimeException("Unexpected exception while retrieving a list of users.", e);
        }
        return users;
    }
}
