package com.dant.entity;

public class User {

    private String pseudo;
    private String email;
    private FriendList friendList;
    private InvitationDemandTable invitationDemands;
    private InvitationRequestTable invitationRequest;
    private Boolean isConnected;
    private Boolean isVisible;
    private Coordinate coordinate;
    private String password;


    public User(String pseudo , String email, String password){

        this.pseudo = pseudo;
        this.email = email;
        this.friendList = new FriendList();
        this.isConnected = false;
        this.isVisible  = false;
        //a encrypter
        this.password = password;
    }

    public User(String pseudo , String email){

        this.pseudo = pseudo;
        this.email = email;
        this.friendList = new FriendList();
        this.isConnected = false;
        this.isVisible  = false;
        //a encrypter
        this.password = null;
    }


    public User(String pseudo) {
        this.pseudo = pseudo;
        this.email = "";
        this.friendList = new FriendList();
        this.isConnected = false;
        this.isVisible  = false;
        //a encrypter
        this.password = "";
    }

    public User() {
        this.pseudo = "";
        this.email = "";
        this.friendList = new FriendList();
        this.isConnected = false;
        this.isVisible  = false;
        //a encrypter
        this.password = "";
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

    public void setInvitationDemands(InvitationDemandTable invitationDemands) { this.invitationDemands = invitationDemands; }

    public void getInvitationRequest() { this.invitationRequest = invitationRequest  ; }

    public void setInvitationRequest(InvitationRequestTable invitationRequest) {this.invitationRequest = invitationRequest; }

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

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }
}