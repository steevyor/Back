package com.dant.filter;

import org.codehaus.jackson.map.ObjectMapper;
import javax.ws.rs.Path;
import java.io.IOException;

@Path("Serial")

public class jsonSerial {

    private static ObjectMapper mapper;

    static{
        mapper = new ObjectMapper();
    }

    public static String objTojson(Object object) throws IOException {
        String response = "";
        response = mapper.writeValueAsString(object);
        return response;
    }

}