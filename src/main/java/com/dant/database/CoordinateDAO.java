package com.dant.database;

import com.dant.entity.Coordinate;

import java.util.List;

public class CoordinateDAO implements DAO<Coordinate> {
    @Override
    public void save(Coordinate object) {

    }

    @Override
    public Coordinate get(String key) {
        try {

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
