package com.dant.database;

import com.dant.entity.Invitation;
import com.dant.entity.InvitationDemandTable;
import com.dant.entity.InvitationRequestTable;
import com.dant.exception.InternalServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitationRequestTableDAO implements DAO<InvitationRequestTable> {

    private final Connection connection;
    private PreparedStatement preparedStatement = null;

    public InvitationRequestTableDAO() { this.connection = Database.connect();    }

    @Override
    public void save(InvitationRequestTable object) {
        try {

            for(Invitation demand : object.getList() ){
                preparedStatement = connection.prepareStatement("INSERT INTO invitation(emitter, receiver) VALUES(?, ?);");
                preparedStatement.setString(1, demand.getEmitterId());
                preparedStatement.setString(2, demand.getRecepterId());
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e){
            throw new InternalServerException(e);
        }
    }

    @Override
    public InvitationRequestTable get(String key) {
        ResultSet result = null;
        ArrayList<Invitation> list = new ArrayList<Invitation>();
        try{
            preparedStatement.setString(1, key);
            result = preparedStatement.executeQuery("select emitter, receiver from invitation where emitter = (?);");
            while(result.next()){
                String emitter = result.getString("emitter");
                String receiver = result.getString("receiver");
                Invitation i = new Invitation(emitter, receiver);
                list.add(i);
            }
            return new InvitationRequestTable(list);

        } catch (SQLException e){
            throw new InternalServerException(e);
        }

    }

    @Override
    public void delete(InvitationRequestTable object) {
        try {

            for(Invitation demand : object.getList() ){
                preparedStatement = connection.prepareStatement("DELETE * from invitation where emitter = (?);");
                preparedStatement.setString(1, demand.getRecepterId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            throw new InternalServerException(e);
        }

    }

    @Override
    public InvitationRequestTable update(InvitationRequestTable object) {
        return null;
    }

    @Override
    public List<InvitationRequestTable> getAll() {
        return null;
    }

}
