package com.dant.database;

import com.dant.entity.Coordinate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Coordinate object) {
        try (Statement st = connection.createStatement()) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNull(String key){
        try (Statement st = connection.createStatement()) {
            st.execute("UPDATE user SET xCoordinates = 0, yCoordinates = 0 FROM user WHERE pseudo =" +key +";");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Coordinate update(Coordinate object) {
        Coordinate coord = new Coordinate();
        try (Statement st = connection.createStatement()) {
            
        }catch(SQLException e){
        }
        return new Coordinate();
    }

    public Coordinate update(String key){
        try (Statement st = connection.createStatement()) {

        }catch(SQLException e){

        }

        return new Coordinate();
    }

    @Override
    public List<Coordinate> getAll() {
        return null;
    }
}
