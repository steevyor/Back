package com.dant.request;

import com.dant.entity.dto.InvitationDTO;
import com.dant.entity.dto.TokenDTO;

public class InvitationRequest {

    public String emitterId;
    public String recepterId;
    public String tokenKey;

    public InvitationRequest(){
    }

    public InvitationRequest(String emitterId, String recepterId, String tokenKey) {
        this.emitterId = emitterId;
        this.recepterId = recepterId;
        this.tokenKey = tokenKey;
    }

    public InvitationDTO getInvitationDTO() {
        return new InvitationDTO(this.emitterId, this.recepterId);
    }

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }
}
