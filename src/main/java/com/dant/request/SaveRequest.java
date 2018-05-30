package com.dant.request;

import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;

public class SaveRequest {
    String udtoPseudo;
    String udtoPassword;
    String tdtoKey;

    public SaveRequest(String udtoPseudo, String udtoPassword, String tdtoKey){
        this.tdtoKey = tdtoKey;
        this.udtoPassword = udtoPassword;
        this.udtoPseudo = udtoPseudo;
    }

    public UserDTO getUserDTO(){ return new UserDTO(udtoPseudo, udtoPassword); }

    public TokenDTO getTokenDTO(){ return new TokenDTO(tdtoKey); }
}
