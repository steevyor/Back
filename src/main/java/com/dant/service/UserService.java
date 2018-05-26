package com.dant.service;

import com.dant.database.DAO;
import com.dant.database.UserDAO;
import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
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
        System.out.println(user.getPseudo());
        if (user.getPassword() == /*Encripter.encrypt*/(dto.password)) {
            return true;
        } else {
            throw new ForbiddenException();
        }
    }

    public User inscription(UserDTO dto) {
        User user ;
        try {
        user = dao.get(dto.pseudo);
        return new User();
        }catch(SQLException e){

        return this.save(dto);
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
