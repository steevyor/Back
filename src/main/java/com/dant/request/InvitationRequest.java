package com.dant.request;

import com.dant.entity.dto.InvitationDTO;
import com.dant.entity.dto.TokenDTO;

public class InvitationRequest {

    public String emitterPseudo;
    public String recepterPseudo;
    public String tokenKey;


    public InvitationRequest(){
    }

    public InvitationRequest(String emitterPseudo, String recepterPseudo, String tokenKey) {
        this.emitterPseudo = emitterPseudo;
        this.recepterPseudo = recepterPseudo;
        this.tokenKey = tokenKey;
    }

    public InvitationDTO getInvitationDTO() {
        return new InvitationDTO(this.emitterPseudo, this.recepterPseudo);
    }

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }
}
