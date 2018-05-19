package com.dant.database;

import com.dant.entity.User;
import com.dant.exception.InternalServerException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class UserDAO implements DAO<User> {

    private final Connection connection;

    public UserDAO() {
        this.connection = Database.connect();
    }

    @Override
    public void save(User object) {
        try (Statement st = connection.createStatement()) {

        } catch (SQLException e) {
            throw new InternalServerException(e);
        }

    }

    @Override
    public User get(String key) {
        return null;
    }

    @Override
    public void delete(User object) {

    }

    @Override
    public User update(User object) {
        return object;
    }

    @Override
    public List<User> getAll() {
        return Collections.emptyList();
    }
}
