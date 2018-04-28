package com.dant.app;

import java.io.IOException;

import com.dant.entity.Account;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJackson_UnSerial {

    public static void main(String[] args) throws JsonGenerationException,
            JsonMappingException, IOException {

        // create the mapper
        ObjectMapper mapper = new ObjectMapper();

        // de-serialize JSON to object
        Account user = mapper.readValue(getAccountJson(), Account.class);

        // print the de-serialized object
        System.out.println(user);
    }

    static String getAccountJson() {
        return "{                                 " +
                "    \"email\" : \"wakanda@gmail.fr\",                 " +
                "    \"updated\" : \"1349333576093\",          " +
                "    \"position\" : true,                  " +
                "}                                 ";
    }
}
