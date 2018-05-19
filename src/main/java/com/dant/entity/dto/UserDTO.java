package com.dant.entity.dto;

import com.dant.entity.User;

public class UserDTO {

    public String email;
    public String password;
    public String pseudo;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.pseudo = user.getPseudo();
    }
}
