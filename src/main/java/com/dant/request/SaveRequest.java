package com.dant.request;

import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;

public class SaveRequest {
    String udtoPseudo;
    String udtoPassword;
    String udtoEmail;
    String tdtoKey;


    public SaveRequest(String udtoPseudo, String udtoPassword, String udtoEmail, String tdtoKey){
        this.tdtoKey = tdtoKey;
        this.udtoPassword = udtoPassword;
        this.udtoEmail = udtoEmail;
        this.udtoPseudo = udtoPseudo;
    }

    public UserDTO getUserDTO(){ return new UserDTO(udtoPseudo, udtoEmail, udtoPassword); }

    public TokenDTO getTokenDTO(){ return new TokenDTO(tdtoKey); }
}
