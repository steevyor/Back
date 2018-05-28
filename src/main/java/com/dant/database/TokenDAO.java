package com.dant.database;

import com.dant.entity.Token;
import com.dant.exception.InternalServerException;

import java.sql.*;
import java.util.List;


public class TokenDAO implements DAO<Token>{

    private final Connection connection;


    public TokenDAO(){ this.connection=Database.connect();}

    @Override
    public void save(Token object) {
        try (Statement st = connection.createStatement()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO token (userPseudo, tokenKey, timer) VALUES(?, ?, ?) ");
            preparedStatement.setString(1, object.getPseudo()); //recup pseudo
            preparedStatement.setString(2, object.getTokenKey());// recup token
            preparedStatement.setString(3, object.getTimer());

        }catch (SQLException e) {

            throw new InternalServerException(e);

        }
    }

    @Override
    public Token get(String pseudo) throws SQLException {
        ResultSet result = null;
        String key = null;
        String timer = null;
        try (Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM token WHERE pseudo =\'" + key + "\';");
            if (result.next()) {
                pseudo = result.getString("userPseudp");
                key = result.getString("tokenKey");
                timer = result.getString("timer");
                Token token = new Token(pseudo, key, timer);
                return token;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }
        return new Token(pseudo, key, timer);
    }

    @Override
    public void delete(Token object) {

    }

    @Override
    public Token update(Token object) {
        return null;
    }

    @Override
    public List<Token> getAll() {
        return null;
    }


}