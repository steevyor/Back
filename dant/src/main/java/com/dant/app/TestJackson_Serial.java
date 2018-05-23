package com.dant.app;
import java.io.IOException;

import com.dant.entity.Account;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestJackson_Serial {

    public static void main(String[] args) throws JsonGenerationException,
            JsonMappingException, IOException {

        // create the mapper
        ObjectMapper mapper = new ObjectMapper();

        // enable pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // serialize the object
        mapper.writeValue(System.out, getAccount());
    }

    static Account getAccount() {
        Account user = new Account();
        user.setEmail("krillin@gmail.com");
        user.setPosition(false);
        return user;
    }
}