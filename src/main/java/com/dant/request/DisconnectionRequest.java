package com.dant.request;

import com.dant.entity.dto.TokenDTO;

public class DisconnectionRequest {
    private String userPseudo;
    private String tokenKey;

    public DisconnectionRequest(String userPseudo, String tokenKey){
        this.tokenKey = tokenKey;
        this.userPseudo = userPseudo;
    }

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }

    public String getUserPseudo(){
        return this.userPseudo;
    }

}
