package com.dant.database;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void save(T object) throws SQLException;

    T get(String key) throws SQLException;

    void delete(T object) throws SQLException;

    void delete(String user, String userFriend) throws SQLException;

    T update(T object);

    List<T> getAll();

}
