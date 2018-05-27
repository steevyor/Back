package com.dant.service;

import com.dant.database.DAO;
import com.dant.database.UserDAO;
import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
import com.dant.exception.InternalServerException;
import com.dant.security.Encripter;

import javax.ws.rs.ForbiddenException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserDAO dao = new UserDAO();

    public User save(UserDTO dto) {
        User user = new User(dto.pseudo, dto.email, dto.password);
        dao.save(user);
        return user;
    }

    public boolean authenticate(UserDTO dto) throws SQLException {
        User user;
        user = dao.get(dto.pseudo);
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
        if (dao.doesExist(dto.pseudo)) {
            System.out.printf("UserService.inscription : user pseudo already exists");
            throw new ForbiddenException();
        } else {
            System.out.println("UserService.inscription : user does not exist");
            System.out.println("UserService.inscription : starting encryption : ");
            String encryptedPassword = Encripter.encrypt(dto.password);
            System.out.println("UserService.inscription : userDTO password = "+dto.password
                    +" ; encrypted password = "+encryptedPassword);
            dao.save(new User(dto.pseudo, dto.email, encryptedPassword));
            System.out.println("UserService.inscription : user saved");
            return true;
        }
    }


    public List<String> sendFriendList(UserDTO dto) throws SQLException {
        if(this.dao.getToken(dto.pseudo).equals(dto.token)){
            return this.dao.getFriends(dto.pseudo);
        } else throw new ForbiddenException();
    }

    public List<User> sendFriendsPositionList(UserDTO dto) {
        ArrayList<User> friends = new ArrayList<User>();
        //try {
        for(User u : this.dao.getFriendsPosition(dto.pseudo)){
            friends.add(u);
        }
        //
        //}catch(SQLException e){
        //dao.save(user);
        //}
        return friends;
    }


}
