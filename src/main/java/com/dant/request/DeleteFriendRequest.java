package com.dant.request;

import com.dant.entity.User;
import com.dant.entity.dto.TokenDTO;

public class DeleteFriendRequest{


    public String userPseudo;
    public String friendPseudo;
    private String tokenKey;


    public DeleteFriendRequest() {}

    public DeleteFriendRequest(String userPseudo, String friendPseudo) {
        this.userPseudo = userPseudo;
        this.friendPseudo = friendPseudo;
    }

    public DeleteFriendRequest getDeletedFriend(){return new DeleteFriendRequest(this.userPseudo, this.friendPseudo);}

    public TokenDTO getTokenDTO(){
        return new TokenDTO(this.tokenKey);
    }

    public String getUserPseudo() {return userPseudo;}

    public String getFriendPseudo() {return friendPseudo;}

    public String getTokenKey() {return tokenKey;}

    public void setUserPseudo(String userPseudo) {this.userPseudo = userPseudo;}

    public void setFriendPseudo(String friendPseudo) {this.friendPseudo = friendPseudo;}

    public void setTokenKey(String tokenKey) {this.tokenKey = tokenKey;}
}
