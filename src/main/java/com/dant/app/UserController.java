package com.dant.app;

import com.dant.Print;
import com.dant.entity.Token;
import com.dant.entity.dto.CoordinateDTO;
import com.dant.entity.dto.InvitationDTO;
import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;
import com.dant.exception.InternalServerException;
import com.dant.request.*;
import com.dant.service.InvitationService;
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
    private final InvitationService invitationService = new InvitationService();


    @POST
    @Path("/tests")
    public Response create(SaveRequest saveRequest) {
        System.out.println("in tests");
        TokenDTO tokenDTO = saveRequest.getTokenDTO();
        UserDTO userDTO = saveRequest.getUserDTO();
        tokenDTO.setPseudo(userDTO.getPseudo());
        System.out.println(userDTO.getPseudo());
        String a = saveRequest.getTokenDTO().getKey();
        System.out.println(a);
        System.out.println(saveRequest.getUserDTO().getPseudo());
        Token t = new Token(saveRequest.getTokenDTO().getKey());
        System.out.println("tioken bviou");
        try {
            if (tokenService.canUseService(tokenDTO)) {
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
            } else {
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
    public Response authenticate(UserDTO dto) {
        System.out.println("UserControler.authenticate :");
        System.out.println(dto.pseudo + " :" + dto.password);
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
    public Response inscription(UserDTO dto) {
        System.out.println("UserControler.inscription : ");
        if (isNotBlank(dto.password) && isNotBlank(dto.pseudo) && isNotBlank(dto.email)) {
            System.out.printf("UserControler.inscription field not blank: pseudo = %s, password = %s, email = %s",
                    dto.pseudo, dto.password, dto.email);
            String json = null;
            try {
                System.out.printf("UserControler.inscription : registering new user now");
                if (userService.inscription(dto)) {
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
            } catch (InternalServerException e) {
                System.out.println(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            } catch (ForbiddenException f) {
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
    @Path("/friends")
    public Response sendFriends(UserRequest request) {
        System.out.println("UserControler.sendFriendsPositions :");
        UserDTO userDTO = request.getUserDTO();
        TokenDTO tokenDTO = request.getTokenDTO();
        tokenDTO.setPseudo(request.getPseudo());
        System.out.println(userDTO.getPseudo() + " :" + tokenDTO.getKey());
        System.out.println(request.getPseudo() + " :" + request.getTokenKey());
        if (isNotBlank(userDTO.getPseudo()) && isNotBlank(tokenDTO.getKey())) {
            System.out.println("UserControler.sendFriendsPositions : fields are not blank");
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    System.out.println("UserControler.sendFriendsPositions : can use service sending now");
                    System.out.println("UserControler.sendFriendsPositions : adding data to json ");
                    HashMap map = new HashMap();
                    map.put("token", tokenDTO.getKey());
                    map.put("friends", userService.sendFriendsPositionList(userDTO));
                    json = gson.toJson(map);
                    Print.p(json);
                    System.out.println("UserControler.sendFriendsPositions : json successfully created ! ");
                    System.out.println(json);
                    System.out.println("UserControler.sendFriendsPositions : now returning response to friendList request ");
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, userDTO.getPseudo());
                    Response.status(Response.Status.ACCEPTED);
                    return Response.ok(json, MediaType.APPLICATION_JSON).build();


                }
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/invitationList")
    public Response sendInvitationList(InvitationListRequest invitationListRequest) {
        UserDTO userDTO = invitationListRequest.getUserDTO();
        TokenDTO tokenDTO = invitationListRequest.getTokenDTO();
        tokenDTO.setPseudo(userDTO.getPseudo());
        System.out.println("UserController.sendInvitationlist : TokenDTO.getPseudo = " + tokenDTO.getPseudo());
        if (isNotBlank(userDTO.getPseudo()) && isNotBlank(tokenDTO.getKey())) {
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    HashMap map = new HashMap();
                    map.put("emitters", invitationService.getAllInvitationsFromUser(userDTO));
                    map.put("token", tokenDTO.getKey());
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, userDTO.getPseudo());
                    json = gson.toJson(map);
                    return Response.ok(json, MediaType.APPLICATION_JSON).build();
                } else {
                    return Response.status(Response.Status.FORBIDDEN).build();
                }
            } catch (SQLException e)     {
                e.printStackTrace();
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/updateUserCoordinates")
    public Response updateUserPosition(PositionRequest pos) {
        UserDTO userdto = pos.getUserDTO();
        CoordinateDTO coordDTO = pos.getCoordinateDTO();
        TokenDTO tokenDTO = pos.getTokenDTO();
        tokenDTO.setPseudo(userdto.getPseudo());
        System.out.println("UserControler.updateUserPosition:");
        System.out.println(userdto.getPseudo() + " :" + coordDTO.getxCoordinate() + " : " + coordDTO.yCoordinate + " : " + tokenDTO.getKey());
        if (isNotBlank(pos.getPseudo()) && isNotBlank(pos.getxCoordinates()) && isNotBlank(pos.getyCoordinates()) && isNotBlank(pos.getToken())) {
            System.out.println("UserControler.updateUserPosition: : fields are not blank");
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    System.out.println("UserControler.updateUserPosition: : can use service ok");
                    userService.updateCoord(userdto, coordDTO);
                    System.out.println("UserControler.sendFriendsPositions : update successfully done ! ");
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, userdto.getPseudo());
                    HashMap map = new HashMap();
                    map.put("token", tokenDTO);
                    json = gson.toJson(map);
                    return Response.ok(json, MediaType.APPLICATION_JSON).build();
                }
            } catch (ForbiddenException e) {
                System.out.println(e);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            } catch (SQLException f) {
                System.out.println(f);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            System.out.println("NO_CONTENT Exception");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/researchFriend")
    public Response researchFriend(ResearchFriendRequest request) {
        TokenDTO tokenDTO = request.getTokenDTO();
        String key = request.getRequestedPseudo();
        String userPseudo = request.getUserPseudo();
        tokenDTO.setPseudo(userPseudo);
        if (isNotBlank(tokenDTO.getKey()) && isNotBlank(key)) {
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    List list = new ArrayList(userService.findCorrespondingUsers(key));
                    HashMap map = new HashMap();
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, userPseudo);
                    map.put("utilisateurs", list);
                    map.put("token", tokenDTO);
                    json = gson.toJson(map);
                    return Response.ok(json, MediaType.APPLICATION_JSON).build();
                } else {
                    return Response.status(Response.Status.FORBIDDEN).build();
                }
            } catch (SQLException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/deleteFriendShip")
    public Response deleteFriendShip(DeleteFriendRequest request) throws SQLException {
        TokenDTO tokenDTO = request.getTokenDTO();
        String userPseudo = request.getUserPseudo();
        String userFriendPseudo = request.getFriendPseudo();
        userService.deleteFriend(userPseudo, userFriendPseudo);
        tokenDTO.setPseudo(userPseudo);
        if (isNotBlank(userPseudo) && isNotBlank(userFriendPseudo)) {
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    HashMap map = new HashMap();
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, userPseudo);
                    map.put("token", tokenDTO);
                    json = gson.toJson(map);
                    return Response.ok(json, MediaType.APPLICATION_JSON).build();
                } else {
                    return Response.status(Response.Status.FORBIDDEN).build();
                }
            } catch (SQLException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @POST
    @Path("/sendInvitation")
    public Response sendInvitation(InvitationRequest invitationRequest) {
        InvitationDTO invitationDTO = invitationRequest.getInvitationDTO();
        TokenDTO tokenDTO = invitationRequest.getTokenDTO();
        tokenDTO.setPseudo(invitationDTO.getEmitterId());
        System.out.println("UserControler.sendInvitation :");
        System.out.println(invitationDTO.getEmitterId() + " :" + tokenDTO.getKey());
        if (isNotBlank(invitationDTO.getEmitterId()) && isNotBlank(tokenDTO.getKey())) {
            System.out.println("UserControler.sendInvitation : fields are not blank");
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    System.out.println("UserControler.sendInvitation : updating ");
                    userService.sendInvitation(invitationDTO, tokenDTO);
                    System.out.println("UserControler.sendInvitation : update successfully done ! ");
                    HashMap map = new HashMap();
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, invitationRequest.getInvitationDTO().getEmitterId());
                    map.put("token", tokenDTO.getKey());
                    json = gson.toJson(map);
                    Response.status(Response.Status.ACCEPTED);
                } else {
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

    @POST
    @Path("/refuseInvitation")
    public Response refuseInvitation(InvitationRequest invitationRequest) {


        System.out.println("UserController.refuseInvitation : invitationRequest ( emitter, recepter ) = "
                + invitationRequest.getInvitationDTO().getEmitterId() + " ; "
                + invitationRequest.getInvitationDTO().getRecepterId());


        InvitationDTO invitationDTO = new InvitationDTO(invitationRequest.getInvitationDTO().getEmitterId(),
                invitationRequest.getInvitationDTO().getRecepterId());
        TokenDTO tokenDTO = invitationRequest.getTokenDTO();
        tokenDTO.setPseudo(invitationDTO.getRecepterId());
        if (isNotBlank(invitationDTO.getRecepterId()) && isNotBlank(tokenDTO.getKey())) {
            System.out.println("UserControler.refuseInvitation : fields are not blank");
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    userService.refuseInvitation(invitationDTO);
                    HashMap map = new HashMap();
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, invitationRequest.getInvitationDTO().getRecepterId());
                    map.put("token", tokenDTO.getKey());
                    json = gson.toJson(map);
                    return Response.ok(json, MediaType.APPLICATION_JSON).build();
                } else return Response.status(Response.Status.FORBIDDEN).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/acceptInvitation")
    public Response acceptInvitation(InvitationRequest invitationRequest) {
        InvitationDTO invitationDTO = invitationRequest.getInvitationDTO();
        TokenDTO tokenDTO = invitationRequest.getTokenDTO();
        tokenDTO.setPseudo(invitationDTO.getRecepterId());
        if (isNotBlank(invitationDTO.getEmitterId()) && isNotBlank(tokenDTO.getKey())) {
            System.out.println("UserControler.acceptInvitation : fields are not blank");
            String json = null;
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    userService.acceptInvitation(invitationDTO);
                    HashMap map = new HashMap();
                    tokenService.updateTokenTimer(tokenDTO);
                    tokenService.save(tokenDTO, invitationRequest.getInvitationDTO().getEmitterId());
                    map.put("token", tokenDTO.getKey());
                    json = gson.toJson(map);
                    return Response.ok(json, MediaType.APPLICATION_JSON).build();
                } else return Response.status(Response.Status.FORBIDDEN).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/disconnect")
    public Response disconnect(DisconnectionRequest disconnectionRequest) {
        if (isNotBlank(disconnectionRequest.getUserPseudo())
                && isNotBlank(disconnectionRequest.getTokenDTO().getKey())) {
            TokenDTO tokenDTO = disconnectionRequest.getTokenDTO();
            tokenDTO.setPseudo(disconnectionRequest.getUserPseudo());
            System.out.println("UserControler.disconnect : fields are not blank");
            try {
                if (tokenService.canUseService(tokenDTO)) {
                    tokenService.deleteToken(tokenDTO);
                    return Response.status(Response.Status.ACCEPTED).build();
                } else return Response.status(Response.Status.FORBIDDEN).build();
            } catch (SQLException e) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else return Response.status(Response.Status.NO_CONTENT).build();
    }

    }
