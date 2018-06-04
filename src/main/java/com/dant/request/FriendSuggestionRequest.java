package com.dant.request;

import com.dant.entity.dto.TokenDTO;

public class FriendSuggestionRequest {

    private String userPseudo;
    private String tokenKey;

    public String getUserPseudo() {
        return userPseudo;
    }

    public void setUserPseudo(String userPseudo) {
        this.userPseudo = userPseudo;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }

    public FriendSuggestionRequest(String userPseudo, String tokenKey) {
        this.userPseudo = userPseudo;
        this.tokenKey = tokenKey;
    }
}
