package com.dant.entity;

public class Friendship {
    String userPseudo;
    String friendPseudo;

    public Friendship(String userPseudo, String friendPseudo) {
        this.userPseudo = userPseudo;
        this.friendPseudo = friendPseudo;
    }

    public Friendship() {
        this.userPseudo = "";
        this.friendPseudo = "";
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
