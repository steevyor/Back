package com.dant.request;

import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;

public class InvitationListRequest {
    private String userPseudo;
    private String tokenKey;

    public InvitationListRequest(String userPseudo, String tokenKey) {
        this.userPseudo = userPseudo;
        this.tokenKey = tokenKey;
    }

    public UserDTO getUserDTO(){ return new UserDTO(userPseudo); }

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }
}
