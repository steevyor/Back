package com.dant.app;

import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
import com.dant.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        if (org.apache.commons.lang3.StringUtils.isNotBlank(dto.password) && org.apache.commons.lang3.StringUtils.isNotBlank(dto.pseudo)) {
            User user = userService.authenticate(dto);
            return new UserDTO(user);
        }
        //crypt
        System.out.println("Erreur");
        return dto;
    }
}
