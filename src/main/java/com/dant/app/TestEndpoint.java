package com.dant.app;

//import com.dant.entity.Account;

import com.dant.database.Database;
import com.dant.database.InvitationDAO;
import com.dant.database.UserDAO;
import com.dant.entity.Invitation;
import com.dant.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/api/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestEndpoint {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String helloWorld() {
		return "Hello World";
	}

	@GET
	@Path("/list")
	public List<String> getListInParams(@QueryParam("ids") List<String> ids ) {
		/*ids.add("Sonia");
		ids.add("Henri");
		ids.add("Guillaume");
		ids.add("Alex");
		System.out.println(ids);
		return ids;*/
		UserDAO user = new UserDAO();
		InvitationDAO invit = new InvitationDAO();
		List<Invitation> listInvit = invit.getAll();
		List<User> listUsers = user.getAll();
		/*for(User u : listUsers){
			ids.add(u.getPseudo() + " " + u.getEmail());
		}*/
		for(Invitation i : listInvit){
			ids.add(i.getEmitterId() + " a invité " + i.getRecepterId());
		}
		return ids;
	}

	/*
	@POST
	@Path("/entity")
	public Account getAccount(Account account) {
		System.out.println("Received account " + account);
		account.setUpdated(System.currentTimeMillis());
		return account;
	}


	@GET
	@Path("/exception")
	public Response exception() {
		throw new RuntimeException("Mon erreur");
	}
*/
}
