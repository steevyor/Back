package com.dant.database;

import com.dant.entity.Coordinate;
import com.dant.entity.FriendList;
import com.dant.entity.User;
import com.dant.exception.InternalServerException;

import java.sql.*;
import java.util.ArrayList;
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
    public User get(String key) throws SQLException {
        ResultSet result = null;
        try(Statement st = connection.createStatement()){
            result = st.executeQuery("SELECT * FROM user WHERE pseudo =\'"+key+"\';");
            if(result.next()){
                String pseudo = result.getString("pseudo");
                String email = result.getString("email");
                String password = result.getString("password");
                User user = new User(pseudo,email,password);
                System.out.println(user.getPassword());
                user.setCoordinate(new Coordinate(result.getDouble("xCoordinates"), result.getDouble("yCoordinates")));
                user.setFriendList(new FriendList());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        return new User();
    }

    @Override
    public void delete(User object) {

    }

    @Override
    public User update(User object) {
        return object;
    }

    public void updateCoordinates(User objet){

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
                Coordinate coord = new Coordinate(result.getDouble("xCoordinates"),
                        result.getDouble("yCoordinates"));
                users.add(new User(pseudo, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    public Coordinate getCoordinates(String key){
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

    public void setCoordinatesNull(String key){
        try (Statement st = connection.createStatement()) {
            st.execute("UPDATE user SET xCoordinates = 0, yCoordinates = 0 FROM user WHERE pseudo =" +key +";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCoordinates(Coordinate object, String key){
        try (Statement st = connection.createStatement()) {
            st.execute("UPDATE user SET xCoordinates =" +object.xCoordinate +", yCoordinates =" +object.yCoordinate +" FROM user WHERE pseudo =" +key +";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void authenticate(String l , String p){
        try(Statement st = connection.createStatement()) {

        }catch (SQLException e){

        }
    }

    //modifier la requete quand la relation entres amis sera faite
    public List<User> getFriends(String key) {
        List<User> users = new ArrayList<User>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM user where ;");
            while(result.next()){
                String pseudo = result.getString("pseudo");
                String email = result.getString("email");
                Coordinate coord = new Coordinate(result.getDouble("xCoordinates"),
                        result.getDouble("yCoordinates"));
                users.add(new User(pseudo, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    //modifier la méthode quand le lien entre amis sera fait en bdd
    public List<User> getFriendsPosition(String key) {
        List<User> users = new ArrayList<User>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM user where ;");
            while(result.next()){
                String pseudo = result.getString("pseudo");
                String email = result.getString("email");
                Coordinate coord = new Coordinate(result.getDouble("xCoordinates"),
                        result.getDouble("yCoordinates"));
                users.add(new User(pseudo, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
