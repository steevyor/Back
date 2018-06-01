package com.dant.request;

import com.dant.entity.Coordinate;
import com.dant.entity.dto.CoordinateDTO;
import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;

public class PositionRequest {
    String pseudo;
    String tokenKey;
    String xCoordinates;
    String yCoordinates;

    public String getPseudo() {
        return pseudo;
    }

    public String getToken() {
        return tokenKey;
    }

    public String getxCoordinates() {
        return xCoordinates;
    }

    public String getyCoordinates() {
        return yCoordinates;
    }

    public PositionRequest(String udtoPseudo, String dtotoken, String xCoord, String yCoord){
        this.pseudo = udtoPseudo;
        this.tokenKey = dtotoken;
        this.xCoordinates = xCoord;
        this.yCoordinates = yCoord;
    }

    public UserDTO getUserDTO(){ return new UserDTO(this.pseudo); }
    public TokenDTO getTokenDTO(){return new TokenDTO(this.tokenKey);}
    public CoordinateDTO getCoordinateDTO(){ return new CoordinateDTO(Double.parseDouble(this.xCoordinates), Double.parseDouble(this.yCoordinates)); }

}