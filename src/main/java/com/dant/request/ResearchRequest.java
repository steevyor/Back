package com.dant.request;

import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;

public class ResearchRequest {

    private String userPseudo;
    private String find;
    private String tokenKey;





    public ResearchRequest(String userPseudo, String find, String tokenKey) {
        this.userPseudo = userPseudo;
        this.find = find;
        this.tokenKey = tokenKey;
    }

    public String getUserPseudo() {
        return userPseudo;
    }

    public String getFind() {
        return find;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public UserDTO getUserDTO(){return new UserDTO(this.userPseudo);}
    public UserDTO getFindUserDTO(){return new UserDTO(this.find);}
    public TokenDTO getTokenDTO(){return new TokenDTO(this.tokenKey);}

}
