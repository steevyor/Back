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

    public User authenticate(UserDTO dto) {
        User user;
        try {
            user = dao.get(dto.pseudo);
            if (user.getPassword() == Encripter.encrypt(dto.password)) {

            }
            // SI user.getPassword().equals(dto.password)
            if (false) {
                throw new ForbiddenException();
            }
        }
        catch(SQLException e){
            throw new ForbiddenException();
        }
        return user;
    }

    public boolean authenticate2(UserDTO dto) throws SQLException {
        User user;
        user = dao.get(dto.pseudo);
        System.out.println("UserService.authenticate2 : UserPseudo =" +user.getPseudo());
        System.out.println("UserService.authenticate2 : UserPassword =" +user.getPassword());
        System.out.println("UserService.authenticate2 : UserDTOPassword =" +dto.password);
        System.out.println("UserService.authenticate2 : UserDTOPassword Encrypted =" +Encripter.encrypt(dto.password));
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
            dao.save(new User(dto.pseudo, dto.email,Encripter.encrypt( dto.password) ));
            return true;
        }
    }


    public List<User> sendFriendList(UserDTO dto) {
        ArrayList<User> friends = new ArrayList<User>();
        //try {
        for(User u : this.dao.getFriends(dto.pseudo)){
            friends.add(u);
        }

        //}catch(SQLException e){
        return friends;
        //}
        //return friends;
    }

    public List<User> sendFriendsPositionList(UserDTO dto) {
        ArrayList<User> friends = new ArrayList<User>();
        //try {
        for(User u : this.dao.getFriends(dto.pseudo)){
            friends.add(u);
        }
        //
        //}catch(SQLException e){
        //dao.save(user);
        //}
        return friends;
    }


}
