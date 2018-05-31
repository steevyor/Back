package com.dant.request;


import com.dant.entity.dto.TokenDTO;
import com.dant.entity.dto.UserDTO;

public class UserRequest {

        String pseudo;
        String tokenKey;

        public String getPseudo() {
            return pseudo;
        }

        public String getTokenKey() {
            return tokenKey;
        }

        public UserRequest(String udtoPseudo, String tdtoKey){
            this.tokenKey = tdtoKey;
            this.pseudo = udtoPseudo;
        }

        public UserDTO getUserDTO(){ return new UserDTO(pseudo); }

        public TokenDTO getTokenDTO(){ return new TokenDTO(tokenKey); }

}
