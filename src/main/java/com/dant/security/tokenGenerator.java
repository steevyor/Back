package com.dant.security;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.SecureRandom;

@Path("/Token")
@Produces(MediaType.APPLICATION_JSON)

public class tokenGenerator {

    protected static SecureRandom random = new SecureRandom();
    protected long timer;


    public tokenGenerator(){
    }

    public String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 64);
        return random;
    }

    public String getToken(String var){
        String preftoken = generateToken();
        return (preftoken+".datetime="+var);

    }

    static tokenGenerator TokenGenerator() {
        tokenGenerator token = new tokenGenerator();
        token.generateToken();
        token.setMillis();
        return token;
    }

    public void setMillis() {
        this.timer = System.currentTimeMillis() % 1000;
    }

    public void json() throws JsonGenerationException,
            JsonMappingException, IOException {

                // create the mapper
                ObjectMapper mapper = new ObjectMapper();

                // enable pretty printing
                mapper.enable(SerializationFeature.INDENT_OUTPUT);

                // serialize the object
                mapper.writeValue(System.out, TokenGenerator());
    }

}
