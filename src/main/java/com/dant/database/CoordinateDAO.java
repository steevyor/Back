package com.dant.database;

import com.dant.entity.Coordinate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CoordinateDAO implements DAO<Coordinate> {
    private final Connection connection;

    public CoordinateDAO() {
        this.connection = Database.connect();
    }

    @Override
    public void save(Coordinate object) {

    }

    @Override
    public Coordinate get(String key) {

        try (Statement st = connection.createStatement()) {
            ResultSet result = st.executeQuery("SELECT xCoordinates, yCoordinates FROM user WHERE pseudo =" +key +";");
            double x = result.getDouble("xCoordinates");
            double y = result.getDouble("yCoordinates");
            return new Coordinate(x,y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Coordinate();
    }

    @Override
    public void delete(Coordinate object) {

    }

    @Override
    public Coordinate update(Coordinate object) {
        return null;
    }

    @Override
    public List<Coordinate> getAll() {
        return null;
    }
}
