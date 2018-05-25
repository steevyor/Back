package com.dant.service;

import com.dant.database.DAO;
import com.dant.database.UserDAO;
import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
import com.dant.security.Encripter;

import javax.ws.rs.ForbiddenException;
import java.sql.SQLException;

public class UserService {

    private final DAO<User> dao = new UserDAO();

    public User save(UserDTO dto) {
        User user = new User(dto.pseudo, dto.email);
        dao.save(user);
        return user;
    }

    public User authenticate(UserDTO dto) {
        User user = dao.get(dto.pseudo);
        if (user == null) {
            throw new ForbiddenException();
        }
        if(user.getPassword() == Encripter.encrypt(dto.password)){

        }
        // SI user.getPassword().equals(dto.password)
        if (false) {
            throw new ForbiddenException();
        }
        return user;
    }

    public User inscription(UserDTO dto) {
        User user = dao.get(dto.pseudo);
        //Si le pseudo existe deja
        if (user == null) {
            throw new ForbiddenException();
        } //else dao.save(user);
        return user;
    }

}
