package com.dant.entity.dto;

import com.dant.entity.Coordinate;
import com.dant.entity.User;

public class UserDTO {

    public String email;
    public String password;
    public String pseudo;
    public CoordinateDTO coord;

    public UserDTO() {
    }

    public UserDTO(String pseudo, String password){
        this.password = password;
        this.pseudo = pseudo;
    }
    public UserDTO(String pseudo, String email, String password){
        this.password = password;
        this.pseudo = pseudo;
        this.email = email;
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.pseudo = user.getPseudo();
        this.coord = new CoordinateDTO(user.getCoordinate());
        this.password = user.getPassword();
    }
}
