package com.dant.request;

import com.dant.entity.dto.TokenDTO;

public class ResearchFriendRequest {

    private String tokenKey;
    private String requestedPseudo;
    private String userPseudo;

    public ResearchFriendRequest(String tokenKey, String requestedPseudo, String userPseudo){
        this.requestedPseudo = requestedPseudo;
        this.tokenKey = tokenKey;
        this.userPseudo = userPseudo;
    }

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }
    public String getRequestedPseudo(){
        return this.requestedPseudo;
    }
    public String getUserPseudo(){
        return this.userPseudo;
    }
}
