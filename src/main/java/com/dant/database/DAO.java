package com.dant.database;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void save(T object);

    T get(String key) throws SQLException;

    void delete(T object);

    T update(T object);

    List<T> getAll();

}
