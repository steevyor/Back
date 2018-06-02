package com.dant.service;

import com.dant.database.FriendshipDAO;
import com.dant.database.InvitationDAO;
import com.dant.entity.Invitation;
import com.dant.entity.dto.InvitationDTO;
import com.dant.entity.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitationService {
    InvitationDAO daoinvitation = new InvitationDAO();
    FriendshipDAO daofriendship = new FriendshipDAO();

    public Invitation save(InvitationDTO dto) {
        Invitation invit = new Invitation(dto.getEmitterId(), dto.getRecepterId());
        daoinvitation.save(invit);
        return invit;
    }

    public Invitation invitationToFriendship(InvitationDTO dto) {
        Invitation invit = new Invitation(dto.getEmitterId(), dto.getRecepterId());
        daoinvitation.delete(invit);

        return invit;
    }

    public List<String> getAllInvitationsFromUser(UserDTO userDTO) throws SQLException{
        return daoinvitation.getAllInvitationsFromUser(userDTO.getPseudo());
    }




}
