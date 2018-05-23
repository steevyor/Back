package com.dant.database;

import com.dant.entity.User;
import com.dant.entity.dto.CoordinateDTO;
import com.dant.exception.InternalServerException;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO implements DAO<User> {

    private final Connection connection;

    public UserDAO() {
        this.connection = Database.connect();
    }

    PreparedStatement preparedStatement = null;

    @Override
    public void save(User object) {

        try(Statement st = connection.createStatement()){
            preparedStatement = connection.prepareStatement("INSERT INTO user(pseudo, email) VALUES(?, ?);");
            preparedStatement.setString(1, object.getPseudo());
            preparedStatement.setString(2, object.getEmail());
            preparedStatement.executeUpdate();
            st.execute("INSERT INTO user(pseudo, email) VALUES(" +object.getPseudo() +"," +object.getEmail() +");");
            //st = connection.prepareStatement("INSERT INTO user(pseudo, email) VALUES(?, ?);");

        } catch (SQLException e) {
            throw new InternalServerException(e);
        }

    }

    @Override
    public User get(String key){
        try(Statement st = connection.createStatement()){
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User user= new User();
        return new User();
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
        List<User> users = new ArrayList<User>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM user;");
            while(result.next()){
                String pseudo = result.getString("pseudo");
                String email = result.getString("email");
                CoordinateDTO coord = new CoordinateDTO(result.getDouble("xCoordinates"),
                        result.getDouble("yCoordinates"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Collections.emptyList();
    }
}
