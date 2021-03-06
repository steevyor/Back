package com.dant.database;

import com.dant.entity.Coordinate;
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
        System.out.println();
        try(Statement st = connection.createStatement()){
            preparedStatement = connection.prepareStatement("INSERT INTO user(pseudo, email, password, image) VALUES(?, ?, ?, ?);");
            preparedStatement.setString(1, object.getPseudo());
            preparedStatement.setString(2, object.getEmail());
            preparedStatement.setString(3, object.getPassword());
            preparedStatement.setString(4, object.getImage());
            preparedStatement.executeUpdate();
            //st.execute("INSERT INTO user(pseudo, email, password) VALUES(" +object.getPseudo() +"," +object.getEmail() +"," +object.getPassword() +");");
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }

    }

    @Override
    public User get(String key) throws SQLException {
        ResultSet result = null;
        try(Statement st = connection.createStatement()){
            System.out.println("UserDAO.get : executing get query now");
            result = st.executeQuery("SELECT * FROM user WHERE pseudo =\'"+key+"\';");
            System.out.println("UserDAO.get : query executed");
            if(result.next()){
                String pseudo = result.getString("pseudo");
                String email = result.getString("email");
                String password = result.getString("password");
                String image = result.getString("image");

                User user = new User(pseudo,email,password, image);
                user.setCoordinate(new Coordinate(result.getDouble("xCoordinates"), result.getDouble("yCoordinates")));
                user.setFriendList(new ArrayList<User>());
                System.out.println("UserDAO.get : query result User = "+user.getPseudo() +";" +user.getEmail() +";" +user.getPassword() );
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        return new User();
    }

    public boolean doesExist(String key) {
        ResultSet result = null;
        try (Statement st = connection.createStatement()) {
            System.out.println("UserDAO.doesExist : executing get query now");
            result = st.executeQuery("SELECT * FROM user WHERE pseudo =\'" + key + "\';");
            System.out.println("UserDAO.doesExist : query executed");
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                String image = result.getString("image");
                Coordinate coord = new Coordinate(result.getDouble("xCoordinates"),
                        result.getDouble("yCoordinates"));
                User u = new User(pseudo, email);
                u.setImage(image);
                u.setCoordinate(coord);
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return users;
    }

    public Coordinate getCoordinates(String key){
        try (Statement st = connection.createStatement()) {
            ResultSet result = st.executeQuery("SELECT xCoordinates, yCoordinates FROM user WHERE pseudo =" +key +";");
            result.next();
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

    public void updateCoordinates(Coordinate object, String key) throws SQLException {
        System.out.println("UserDAO.updateCoord : ");
        System.out.println("UserDAO.updateCoord : "+object.getXCoordinate());
        System.out.println("UserDAO.updateCoord : "+object.getYCoordinate());

        try (Statement st = connection.createStatement()) {
            st.executeUpdate("UPDATE user SET xCoordinates = \'" +object.getXCoordinate() +"\', yCoordinates = \'" +object.getYCoordinate() + "\' where pseudo = '" +key + "\' ;");

        } catch (SQLException e) {
            throw new SQLException();

        }
    }

    //modifier la requete quand la relation entres amis sera faite
    public List<String> getFriends(String key) throws SQLException {
        List<String> users = new ArrayList<String>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT friendPseudo FROM friendship where userPseudo = '" + key + "' ;");
            while(result.next()){
                String pseudo = result.getString("friendPseudo");
                users.add(pseudo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

        return users;
    }

    //modifier la méthode quand le lien entre amis sera fait en bdd
    public List<User> getFriendsPosition(String key) throws SQLException {
        List<User> users = new ArrayList<User>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("select u.xCoordinates, u.yCoordinates, friendPseudo, image from user u, friendship f where f.userPseudo = '" + key + "' and u.pseudo = f.friendPseudo;");
            while(result.next()){
                Double x = result.getDouble("xCoordinates");
                Double y = result.getDouble("yCoordinates");
                String pseudo = result.getString("friendPseudo");
                String image = result.getString("image");
                User u = new User(pseudo, new Coordinate(x, y));
                u.setImage(image);
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        return users;
    }

    public List<String> getCorrespondingUsers(String key) throws SQLException {
        List<String> list = new ArrayList();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT pseudo FROM user WHERE pseudo LIKE \'" +key +"%\';");
            while(result.next()) {
                list.add(result.getString("pseudo"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }


}
