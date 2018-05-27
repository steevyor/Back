package com.dant.app;

import com.dant.database.UserDAO;
import com.dant.entity.Token;
import com.dant.entity.User;
import com.dant.entity.dto.UserDTO;
import com.dant.exception.InternalServerException;
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
                    System.out.println("UserControler.authenticate2 : user correctly authenticated !");
                    List list = new ArrayList();
                    System.out.println("UserControler.authenticate2 : creating token now ");
                    Token token = new Token();
                    System.out.println("UserControler.authenticate2 : token successfully created !");
                    list.add(dto.pseudo);
                    list.add(token);
                    System.out.println("UserControler.authenticate2 : adding data to json ");
                    json = gson.toJson(list);
                    System.out.println("UserControler.authenticate2 : json successfully created ! ");
                    System.out.println(json);
                }
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            System.out.println("UserControler.authenticate2 : now returning response to auth2 request ");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }


    @POST
    @Path("/inscription")
    public Response inscription(UserDTO dto ) {
        System.out.println("UserControler.inscription : ");
        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo) && isNotBlank(dto.email)) {
            System.out.printf("UserControler.inscription field not blank: pseudo = %s, password = %s, email = %s",
                    dto.pseudo, dto.password, dto.email);
            String json = null;
            try{
                System.out.printf("UserControler.inscription : registering new user now");
                if(userService.inscription(dto)){
                    System.out.printf("UserControler.inscription : new user successfuly registered");
                    List list = new ArrayList();
                    System.out.println("UserControler.inscription : creating token now ");
                    Token token = new Token();
                    System.out.println("UserControler.inscription : token successfully created");
                    list.add(dto.pseudo);
                    list.add(token);
                    System.out.println("UserControler.inscription : adding Data to json");
                    json = gson.toJson(list);
                    System.out.println("UserControler.inscription : json successfully created");
                }
            }catch (InternalServerException e){
                System.out.println(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }catch (ForbiddenException f){
                System.out.println(f);
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            System.out.println("UserControler.inscription : now returning response to inscription request ");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/friendList")
    public Response sendFriendList(UserDTO dto ) {
        System.out.println("UserControler.sendFriendlist :");
        System.out.println(dto.pseudo +" :" +dto.token);
        if (isNotBlank(dto.pseudo) && isNotBlank(dto.token)) {
            System.out.println("UserControler.sendFriendlist : fields are not blank");
            String json = null;
            try {
                System.out.println("UserControler.sendFriendlist : sending now");
                System.out.println("UserControler.sendFriendlist : adding data to json ");
                json = gson.toJson(userService.sendFriendList(dto));
                System.out.println("UserControler.sendFriendlist : json successfully created ! ");
                System.out.println(json);
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            System.out.println("UserControler.sendFriendlist : now returning response to friendList request ");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
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
