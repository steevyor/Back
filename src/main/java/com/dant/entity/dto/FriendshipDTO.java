package com.dant.entity.dto;

public class FriendshipDTO {

    public String userPseudo;
    public String friendPseudo;


    public FriendshipDTO() {
    }

    public FriendshipDTO(String userPseudo, String friendPseudo) {
        this.userPseudo = userPseudo;
        this.friendPseudo = friendPseudo;
    }

    public String getUserPseudo() {
        return userPseudo;
    }

    public String getFriendPseudo() {
        return friendPseudo;
    }

    public void setUserPseudo(String userPseudo) {
        this.userPseudo = userPseudo;
    }

    public void setFriendPseudo(String friendPseudo) {
        this.friendPseudo = friendPseudo;
    }
}
