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

    public User inscription(UserDTO dto) {
        User user ;
        try {
            user = dao.get(dto.pseudo);
            dao.save(user);
        }catch(SQLException e){
            user = new User(dto.pseudo, dto.email);

        }
        return user;
    }

}
