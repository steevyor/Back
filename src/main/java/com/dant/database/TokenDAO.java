package com.dant.database;

import com.dant.Print;
import com.dant.entity.Token;
import com.dant.entity.User;
import com.dant.exception.InternalServerException;

import java.sql.*;
import java.util.List;


public class TokenDAO implements DAO<Token>{

    private final Connection connection;
    public TokenDAO(){ this.connection=Database.connect();}


    @Override
    public void save(Token object) {
    }

    public void save(Token object, String userPseudo) {
        System.out.println("TokenDAO.save : creating statement");
        try (Statement st = connection.createStatement()) {
            System.out.println("TokenDAO.save : preparing statement");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO token (userPseudo, tokenKey, timer) VALUES(?, ?, ?) ");
            preparedStatement.setString(1, userPseudo); //recup pseudo
            preparedStatement.setString(2, object.getTokenKey());// recup token
            preparedStatement.setString(3, object.getTimer());
            System.out.println("TokenDAO.save : saving token");
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }


    @Override
    public Token get(String pseudo) throws SQLException {

        try (Statement st = connection.createStatement()) {
            ResultSet result = st.executeQuery("SELECT tokenKey, timer FROM token WHERE userPseudo = '" +pseudo + "' ;");
            if(result.next()){
                String timer = result.getString("timer");
                String key = result.getString("tokenKey");
                return new Token(key, timer);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Token getByKey(String key) throws SQLException {
        ResultSet result = null;
        String timer = null;
        String pseudo = null;
        try (Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM token WHERE tokenKey =\'" + key + "\';");
            if (result.next()) {
                //pseudo = result.getString("userPseudp");
                key = result.getString("tokenKey");
                Print.p("Token.getByKey : key : "+key);
                timer = result.getString("timer");
                Print.p("Token.getByKey : returning Token with key = "+key +"; and timer = "+timer);
                return new Token(key, timer);
            }
            else{
                throw new NullPointerException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
    }

    @Override
    public void delete(Token object) {
        try(Statement st = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM token WHERE tokenKey = (?) ;");
            preparedStatement.setString(1, object.getTokenKey());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Token update(Token object){
        try(Statement st = connection.createStatement()) {
            System.out.println("TokenDAO.update : new timer value = "+object.getTimer());
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE token SET timer = (?) WHERE tokenKey = (?) ;");
            preparedStatement.setString(1, object.getTimer());
            preparedStatement.setString(2, object.getTokenKey());
            System.out.println("TokenDAO.update : Executing Update with TokenService.updateTokenTimer");
            preparedStatement.executeUpdate();
            System.out.println("TokenDAO.update : Update Executed ");
            System.out.println("TokenDAO.update : Returning Token");
            return object;
        }catch(SQLException e){
            e.printStackTrace();
            return object;
        }
    }


    @Override
    public List<Token> getAll() {
        return null;
    }


}