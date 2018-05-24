package com.dant.entity;

public class User {

    private String pseudo;
    private String email;
    private FriendList friendList;
    private InvitationDemand invitationDemands;
    private InvitationRequest invitationRequest;
    private Boolean isConnected;
    private Boolean isVisible;
    private Coordinate coordinate;


    public User(String pseudo , String email){

        this.pseudo = pseudo;
        this.email = email;
        this.friendList = new FriendList();
        this.isConnected = false;
        this.isVisible  = false;
    }

    public User() {

    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Coordinate getCoordinate(){return coordinate;}

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public FriendList getFriendList() {
        return this.friendList;
    }

    public void setFriendList(FriendList friendList) {
        this.friendList = friendList;
    }

    public void getInvitationDemands() { this.invitationDemands = invitationDemands;  }

    public void setInvitationDemands(InvitationDemand invitationDemands) { this.invitationDemands = invitationDemands; }

    public void getInvitationRequest() { this.invitationRequest = invitationRequest  ; }

    public void setInvitationRequest(InvitationRequest invitationRequest) {this.invitationRequest = invitationRequest; }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean isConnected) {
        this.isConnected = isConnected;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
}