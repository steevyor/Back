package com.dant.database;

import com.dant.Print;
import com.dant.entity.Invitation;
import com.dant.exception.InternalServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvitationDAO implements  DAO<Invitation>{


    private final Connection connection;
    private PreparedStatement preparedStatement = null;

    public InvitationDAO() { this.connection = Database.connect();    }


    @Override
    public void save(Invitation object) {
        Print.p("InvitationDAO.save : trying to save invitation(emitter, receiver) = "+object.getEmitterId() +" ; "+object.getRecepterId());
            try (Statement st = connection.createStatement()) {
                //Relation dans un sens
                preparedStatement = connection.prepareStatement("insert into invitation values(?, ?);");
                preparedStatement.setString(1, object.getEmitterId());
                preparedStatement.setString(2, object.getRecepterId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Invitation get(String key) {
        ResultSet result = null;
        try{
            result = preparedStatement.executeQuery("select emitter, receiver from invitation where receiver = (?) or emitter = (?);");
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, key);
            String emitter = result.getString("emitter");
            String receiver = result.getString("receiver");
            return new Invitation(emitter, receiver);

        } catch (SQLException e){
            throw new InternalServerException(e);
        }

    }

    @Override
    public void delete(Invitation object) {
        try {
            preparedStatement = connection.prepareStatement("DELETE * from invitation where receiver = (?) and emitter = (?);");
            preparedStatement.setString(1, object.getRecepterId());
            preparedStatement.setString(2, object.getRecepterId());
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new InternalServerException(e);
        }

    }

    @Override
    public Invitation update(Invitation object) {

        try {
            preparedStatement = connection.prepareStatement("update invitation set receiver, emitter where receiver = (?) and emitter = (?);");
            preparedStatement.setString(1, object.getRecepterId());
            preparedStatement.setString(2, object.getRecepterId());
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new InternalServerException(e);
        }

        return object;
    }

    @Override
    public List<Invitation> getAll() {
        ArrayList<Invitation> invitations = new ArrayList<Invitation>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM invitation;");
            while(result.next()){
                String receiver = result.getString("receiver");
                String emitter = result.getString("emitter");
                invitations.add(new Invitation(receiver, emitter));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invitations;
    }

    public List<Invitation> getAll(String userPseudo) {
        ArrayList<Invitation> invitations = new ArrayList<Invitation>();
        ResultSet result = null;
        try(Statement st = connection.createStatement()) {
            result = st.executeQuery("SELECT * FROM invitation WHERE receiver = \'" +userPseudo +"\' ;");
            while(result.next()){
                String receiver = result.getString("receiver");
                String emitter = result.getString("emitter");
                invitations.add(new Invitation(receiver, emitter));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invitations;
    }

}
