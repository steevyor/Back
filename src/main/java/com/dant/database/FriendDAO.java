package com.dant.database;

import com.dant.entity.Friend;
import com.dant.exception.InternalServerException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FriendDAO implements DAO<Friend> {

    private final Connection connection;

    public FriendDAO(){ this.connection = Database.connect(); }

    @Override
    public void save(Friend object) {
        try (Statement st = connection.createStatement()) {
            st.execute("INSERT INTO friendships VALUES ");
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public Friend get(String key) {
        return null;
    }

    @Override
    public void delete(Friend object) {

    }

    @Override
    public Friend update(Friend object) {
        return null;
    }

    @Override
    public List<Friend> getAll() {
        return null;
    }
}
