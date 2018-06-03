package com.dant.database;

import com.dant.entity.Coordinate;
import com.dant.entity.Friendship;
import com.dant.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class FriendshipDAO implements DAO<Friendship> {
    private final Connection connection;
    private PreparedStatement preparedStatement = null;

    public FriendshipDAO() {this.connection = Database.connect();  }

    @Override
    public void save(Friendship object) throws SQLException {
        try(Statement st = connection.createStatement()){
            st.executeUpdate("insert into friendship values ('" + object.getUserPseudo() + "', ' "+ object.getFriendPseudo() + "';");
            st.executeUpdate("insert into friendship values ('" + object.getFriendPseudo() + "', '"+ object.getUserPseudo() + "';");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

    }
    @Override
    public Friendship get(String key) throws SQLException {
            try(Statement st = connection.createStatement()){
                ResultSet result = st.executeQuery("SELECT * FROM friendship WHERE userPseudo =\'"+key+"\';");
                if(result.next()){
                    String userPseudo = result.getString("userPseudo");
                    String friendPseudo = result.getString("friendPseudo");
                    Friendship fs = new Friendship(userPseudo,friendPseudo);
                    return fs;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException();
            }
            return new Friendship();

    }

    @Override
    public void delete(Friendship object) throws SQLException {
        System.out.println("FriendshipDAO.delete : deleting friendship (user, friend) = " +object.getUserPseudo() +" ; " +object.getFriendPseudo());
        try(Statement st = connection.createStatement()){
            int result1 = st.executeUpdate("DELETE FROM friendship WHERE userPseudo = " +"\'" +object.getUserPseudo() +"\'" +"AND friendPseudo = " +"\'" +object.getFriendPseudo() +"\';");
            int result2 = st.executeUpdate("DELETE FROM friendship WHERE userPseudo = \'" +object.getFriendPseudo() +"\'" +"AND friendPseudo = " +"\'" +object.getUserPseudo() +"\';");
            System.out.println("FriendshipDAO.delete : result1 ; result2 " +result1 +" ; " +result2);
            System.out.println("FriendshipDAO.delete : friendship deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    @Override
    public Friendship update(Friendship object) {
        return null;
    }

    @Override
    public List<Friendship> getAll() {
        List<Friendship> fs = new ArrayList<Friendship>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM friendship;");
            while(result.next()){
                String userPseudo = result.getString("userPseudo");
                String friendPseudo = result.getString("friendPseudo");
                fs.add(new Friendship(userPseudo, friendPseudo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return fs;
    }
}
