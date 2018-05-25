package com.dant.app;

import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
import com.dant.service.UserService;
import com.mysql.cj.util.StringUtils;
import org.jboss.resteasy.annotations.Form;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

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

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String helloWorld() {
        return "Hello World";
    }

    @POST
    @Path("/auth")
    public UserDTO authenticate(UserDTO dto ) {
        System.out.println(""+dto.pseudo +" : " +dto.password );
        //verif
        //crypt
        //Commons lang = > StringUtils => is blank
        User user = userService.authenticate(dto);
        return new UserDTO(user);
    }

}
