package com.dant.entity.dto;

import com.dant.entity.Coordinate;
import com.dant.entity.User;


public class UserDTO {

    public String email;
    public String password;
    public String pseudo;
    public CoordinateDTO coord;
    public String token;

    public UserDTO() {
    }

    public UserDTO(String pseudo, String password){
        this.password = password;
        this.pseudo = pseudo;
        this.token = "test";
    }
    public UserDTO(String pseudo, String email, String password){
        this.password = password;
        this.pseudo = pseudo;
        this.email = email;
        this.token = "test";
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.pseudo = user.getPseudo();
        this.coord = new CoordinateDTO(user.getCoordinate());
        this.token = "test";
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public CoordinateDTO getCoord() {
        return coord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setCoord(CoordinateDTO coord) {
        this.coord = coord;
    }
}
