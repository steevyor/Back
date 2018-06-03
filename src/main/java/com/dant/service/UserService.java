package com.dant.service;

import com.dant.Print;
import com.dant.database.*;
import com.dant.entity.Coordinate;
import com.dant.entity.Friendship;
import com.dant.entity.Invitation;
import com.dant.entity.User;
import com.dant.entity.dto.CoordinateDTO;
import com.dant.entity.dto.InvitationDTO;
import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;
import com.dant.exception.InternalServerException;
import com.dant.request.DeleteFriendRequest;
import com.dant.security.Encripter;

import javax.ws.rs.ForbiddenException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserDAO userdao = new UserDAO();
    private final InvitationDAO invitationdao = new InvitationDAO();
    private final TokenDAO tokendao = new TokenDAO();
    private final FriendshipDAO friendshipdao = new FriendshipDAO();

    public User save(UserDTO dto) {
        User user = new User(dto.pseudo, dto.email, dto.password);
        userdao.save(user);
        return user;
    }

    public boolean authenticate(UserDTO dto) throws SQLException {
        User user;
        user = userdao.get(dto.pseudo);
        System.out.println("UserService.authenticate : UserPseudo =" +user.getPseudo());
        System.out.println("UserService.authenticate : UserPassword =" +user.getPassword());
        System.out.println("UserService.authenticate : UserDTOPassword =" +dto.password);
        System.out.println("UserService.authenticate : UserDTOPassword Encrypted =" +Encripter.encrypt(dto.password));
        if (user.getPassword().equals(Encripter.encrypt(dto.password))) {
            return true;
        } else {
            throw new ForbiddenException();
        }
    }

    public boolean inscription(UserDTO dto) throws InternalServerException {
        System.out.printf("UserService.inscription : verifying user inexistance");
        if (userdao.doesExist(dto.pseudo)) {
            System.out.printf("UserService.inscription : user pseudo already exists");
            throw new ForbiddenException();
        } else {
            System.out.println("UserService.inscription : user does not exist");
            System.out.println("UserService.inscription : starting encryption : ");
            String encryptedPassword = Encripter.encrypt(dto.password);
            System.out.println("UserService.inscription : userDTO password = "+dto.password
                    +" ; encrypted password = "+encryptedPassword);
            userdao.save(new User(dto.pseudo, dto.email, encryptedPassword));
            System.out.println("UserService.inscription : user saved");
            return true;
        }
    }


    public List<String> sendFriendList(UserDTO dto) throws SQLException {
        if(this.tokendao.get(dto.pseudo).equals(dto.token)){
            return this.userdao.getFriends(dto.pseudo);
        } else throw new ForbiddenException();
    }

    public List<User> sendFriendsPositionList(UserDTO dto) throws SQLException {
            return this.userdao.getFriendsPosition(dto.pseudo);
    }

    public void deleteFriend(String user, String userFriend) throws SQLException{
        Print.p("UserService.deleteFriend :user + friend "+user+userFriend);
        this.friendshipdao.delete(new Friendship(user, userFriend));
    }

    public void sendInvitation(InvitationDTO invitationDto, TokenDTO tokenDTO) throws SQLException {
        Print.p("UserService.sendInvitation : Invitation(emiter, recepter) = "+invitationDto.getEmitterId() +" ; "+invitationDto.getRecepterId());
        Print.p("UserService.sendInvitation : Token(pseudo, key, timer ) = "+tokenDTO.getPseudo()+" ; "+tokenDTO.getKey() +" ; "+tokenDTO.getCurrentTime());
            this.invitationdao.save(new Invitation(invitationDto.getEmitterId(), invitationDto.getRecepterId()));
    }

    public void refuseInvitation(InvitationDTO invitationDTO) throws SQLException {
        this.invitationdao.refuse(new Invitation(invitationDTO.getEmitterId(), invitationDTO.getRecepterId()));
    }

    public void acceptInvitation(InvitationDTO invitationDTO) throws SQLException {
        this.invitationdao.accept(new Invitation(invitationDTO.getEmitterId(), invitationDTO.getRecepterId()));
    }

    public void updateCoord(UserDTO user, CoordinateDTO coord) throws SQLException {
            userdao.updateCoordinates(new Coordinate(coord.getxCoordinate(), coord.getyCoordinate()), user.getPseudo());
            System.out.println("User Service.updtaCoord : update ok");
    }

    public List<String> findCorrespondingUsers(String s) throws  SQLException {
        return userdao.getCorrespondingUsers(s);
    }
}
