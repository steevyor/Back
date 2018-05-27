package com.dant.app;

import com.dant.entity.Token;
import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
import com.dant.security.Encripter;
import com.dant.security.tokenGenerator;
import com.dant.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.util.StringUtils;
import org.jboss.resteasy.annotations.Form;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.sound.midi.SysexMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    final GsonBuilder builder = new GsonBuilder();
    final Gson gson = builder.create();

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
    @Path("/auth2")
    public Response authenticate2(UserDTO dto ) {
        System.out.println("UserControler.authenticate2 :");
        System.out.println(dto.pseudo +" :" +dto.password);
        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo)) {
            System.out.println("UserControler.authenticate2 : fields are not blank");
            String json = null;
            try {
                System.out.println("UserControler.authenticate2 : authenticating now");
                if (userService.authenticate2(dto)) {
                    System.out.println("UserControler.authenticate2 : user correctly authenticated");
                    List list = new ArrayList();
                    System.out.println("UserControler.authenticate2 : creating token now ");
                    Token token = new Token();
                    list.add(dto.pseudo);
                    list.add(token);
                    json = gson.toJson(list);
                }
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            System.out.println(json);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
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

    @POST
    @Path("/friendList")
    public List<UserDTO> sendFriendList(UserDTO dto ) {
        ArrayList<UserDTO> friends = new ArrayList<UserDTO>();

        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo)) {

            friends.add(new UserDTO("test1", dto.email, "test3"));
            friends.add(new UserDTO("test3", dto.email,  "tes4"));
            //try {
            //User user = userService.sendFriendList(dto);
            //}catch(SQLException e) {
            //return new UserDTO(dto.getPseudo(), dto.getEmail(), dto.getPassword());
            //}
        }
        return friends;
    }


    @POST
    @Path("/positions")
    public List<UserDTO> sendFriendsPositionsList(UserDTO dto ) {
        ArrayList<UserDTO> friends = new ArrayList<UserDTO>();

        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo)) {

            friends.add(new UserDTO("test1", dto.email, "test3"));
            friends.add(new UserDTO("test3", dto.email,  "tes4"));
            //try {
            //User user = userService.sendFriendPositionList(dto);
            //}catch(SQLException e) {
            //return new UserDTO(dto.getPseudo(), dto.getEmail(), dto.getPassword());
            //}
        }
        return friends;
    }

}
