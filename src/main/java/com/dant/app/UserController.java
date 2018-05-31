package com.dant.app;

import com.dant.entity.Token;
import com.dant.entity.dto.InvitationDTO;
import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;
import com.dant.exception.InternalServerException;
import com.dant.request.InvitationRequest;
import com.dant.request.SaveRequest;
import com.dant.request.UserRequest;
import com.dant.service.TokenService;
import com.dant.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import jdk.jfr.ContentType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    final GsonBuilder builder = new GsonBuilder();
    final Gson gson = builder.create();

    private final UserService userService = new UserService();
    private final TokenService tokenService = new TokenService();

    @POST
    @Path("/tests")
    public Response create(SaveRequest saveRequest) {
        System.out.println("in tests");
        TokenDTO tokenDTO = saveRequest.getTokenDTO();
        UserDTO userDTO = saveRequest.getUserDTO();
        System.out.println(userDTO.getPseudo());
        String a = saveRequest.getTokenDTO().getKey();
        System.out.println(a);
        System.out.println(saveRequest.getUserDTO().getPseudo());
        Token t = new Token(saveRequest.getTokenDTO().getKey());
        System.out.println("tioken bviou");
        try {
            if(tokenService.canUseService(tokenDTO)){
                userService.save(saveRequest.getUserDTO());
                System.out.println("acepted");
                String json = null;
                HashMap map = new HashMap();
                tokenService.updateTokenTimer(tokenDTO);
                tokenService.save(tokenDTO, userDTO.getPseudo());
                map.put("token", (tokenDTO));
                json = gson.toJson(map);
                Response.status(Response.Status.ACCEPTED);
                return Response.ok(json, MediaType.APPLICATION_JSON).build();
            }else{
                System.out.println("nucepted");
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/auth")
    public Response authenticate(UserDTO dto ) {
        System.out.println("UserControler.authenticate :");
        System.out.println(dto.pseudo +" :" +dto.password);
        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo)) {
            System.out.println("UserControler.authenticate : fields are not blank");
            String json = null;
            try {
                System.out.println("UserControler.authenticate : authenticating now");
                if (userService.authenticate(dto)) {
                    System.out.println("UserControler.authenticate : user correctly authenticated !");
                    List list = new ArrayList();
                    HashMap map = new HashMap();
                    System.out.println("UserControler.authenticate : creating token now ");
                    Token token = new Token();
                    TokenDTO tokenDTO = new TokenDTO(token.getTokenKey(), token.getTimer(), dto.getPseudo());
                    System.out.println(dto.pseudo);
                    System.out.println(tokenDTO.getPseudo());

                    System.out.println("UserControler.authenticate : token successfully created !");
                    list.add(dto);
                    list.add(token);
                    map.put("user", dto);
                    map.put("token", tokenDTO);
                    System.out.println("UserControler.authenticate : adding data to json ");
                    json = gson.toJson(map);
                    System.out.println("UserControler.authenticate : json successfully created ! ");
                    System.out.println(json);
                    System.out.println("UserControler.authenticate : saving Token");
                    System.out.println("1//////////////////////PSEUDO;KEY : " + tokenDTO.getPseudo() + ";" + tokenDTO.getKey());
                    if (tokenService.doesTokenExists(tokenDTO)) {
                        System.out.print("Suppression et enregistrement");
                        tokenService.deleteToken(tokenDTO);
                        tokenService.save(tokenDTO, dto.getPseudo());

                    } else {
                        System.out.print("Enregistrement");

                        tokenService.save(tokenDTO, dto.getPseudo());
                    }
                }
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            System.out.println("UserControler.authenticate : now returning response to auth request ");
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
    public Response sendFriendList(UserRequest userRequest ) {
        System.out.println("UserControler.sendFriendlist :");
        UserDTO user = userRequest.getUserDTO();
        TokenDTO token = userRequest.getTokenDTO();
        System.out.println(user.getPseudo() +" :" +token.getKey());
        if (isNotBlank(user.getPseudo()) && isNotBlank(token.getKey())) {
            System.out.println("UserControler.sendFriendlist : fields are not blank");
            String json = null;
            try {
                System.out.println("UserControler.sendFriendlist : sending now");
                System.out.println("UserControler.sendFriendlist : adding data to json ");
                json = gson.toJson(userService.sendFriendList(user));
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
    @Path("/friendPositions")
    public Response sendFriendsPositionsList(UserRequest request) {
        System.out.println("UserControler.sendFriendsPositions :");
        UserDTO user = request.getUserDTO();
        TokenDTO token = request.getTokenDTO();
        System.out.println(user.getPseudo() +" :" +token.getKey());
        System.out.println(request.getPseudo() +" :" +request.getTokenKey());
        if (isNotBlank(user.getPseudo()) && isNotBlank(token.getKey()) ){
            System.out.println("UserControler.sendFriendsPositions : fields are not blank");
            String json = null;
            try {
                System.out.println("UserControler.sendFriendsPositions : sending now");
                System.out.println("UserControler.sendFriendsPositions : adding data to json ");
                json = gson.toJson(userService.sendFriendsPositionList(user));
                System.out.println("UserControler.sendFriendsPositions : json successfully created ! ");
                System.out.println(json);
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            System.out.println("UserControler.sendFriendsPositions : now returning response to friendList request ");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }


    @POST
    @Path("/sendInvitation")
    public Response sendInvitation(InvitationRequest invitationRequest) {
        InvitationDTO invitationDTO = invitationRequest.getInvitationDTO();
        TokenDTO tokenDTO = invitationRequest.getTokenDTO();
        System.out.println("UserControler.sendInvitation :");
        System.out.println(invitationDTO.getEmitterId() +" :" + tokenDTO.getKey());
        if (isNotBlank(invitationDTO.getEmitterId()) && isNotBlank(tokenDTO.getKey())) {
            System.out.println("UserControler.sendInvitation : fields are not blank");
            String json = null;
            try {
                if(tokenService.canUseService(tokenDTO)) {
                    System.out.println("UserControler.sendInvitation : updating ");
                    userService.sendInvitation(invitationDTO, tokenDTO);
                    System.out.println("UserControler.sendInvitation : update successfully done ! ");
                    HashMap map = new HashMap();
                    tokenService.updateTokenTimer(tokenDTO);
                    map.put("token", (tokenDTO));
                    json = gson.toJson(map);
                    Response.status(Response.Status.ACCEPTED);
                }else{
                    System.out.println("nucepted");
                    return Response.status(Response.Status.UNAUTHORIZED).build();
                }
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            System.out.println("UserControler.sendInvitation : now returning response to sendInvitation request ");
            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

}
