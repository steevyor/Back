package com.dant.request;

import com.dant.entity.dto.TokenDTO;

public class ResearchFriendRequest {

    private String tokenKey;
    private String requestedPseudo;
    private String pseudo;

    public ResearchFriendRequest(String tokenKey, String requestedPseudo, String pseudo){
        this.requestedPseudo = requestedPseudo;
        this.tokenKey = tokenKey;
        this.pseudo = pseudo;
    }

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }
    public String getRequestedPseudo(){
        return this.requestedPseudo;
    }
}
