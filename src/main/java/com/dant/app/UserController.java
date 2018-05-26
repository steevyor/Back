package com.dant.app;

import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
import com.dant.security.Encripter;
import com.dant.service.UserService;
import com.mysql.cj.util.StringUtils;
import org.jboss.resteasy.annotations.Form;

import javax.sound.midi.SysexMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import java.sql.SQLException;

import static org.apache.commons.lang3.StringUtils.*;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService = new UserService();

    @POST
    public UserDTO create(UserDTO dto) {
        userService.save(dto);
        return dto;
    }

    @POST
    @Path("/auth")
    public UserDTO authenticate(UserDTO dto ) {
        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo)) {
            //User user = userService.authenticate(dto);
            //return new UserDTO(user);
        }
        //crypt
        return new UserDTO(dto.getPseudo(), dto.getEmail());
    }
    @POST
    @Path("/inscription")
    public UserDTO inscription(UserDTO dto ) {
        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo) && isNotBlank(dto.email)) {
            //try {
                //User user = userService.inscription(dto);
            //}catch(SQLException e) {
                return new UserDTO(dto.getPseudo(), dto.getEmail(), dto.getPassword());
            //}
        }
        return dto;
    }

}
