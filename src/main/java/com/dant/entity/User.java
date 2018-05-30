package com.dant.entity;

import sun.nio.cs.US_ASCII;

import java.util.ArrayList;

public class User {

    private String pseudo;
    private String email;
    private ArrayList<User> friendList;
    private ArrayList<Invitation> invitations;
    private Boolean isConnected;
    private Boolean isVisible;
    private Coordinate coordinate;
    private String password;


    public User(String pseudo , String email, String password){

        this.pseudo = pseudo;
        this.email = email;
        this.friendList = new ArrayList<User>();
        this.isConnected = false;
        this.isVisible  = false;
        this.invitations = new ArrayList<Invitation>();
        //a encrypter
        this.password = password;
    }


    public User(String pseudo , String email){

        this.pseudo = pseudo;
        this.email = email;
        this.friendList = new ArrayList<User>();
        this.isConnected = false;
        this.isVisible  = false;
        this.invitations = new ArrayList<Invitation>();
        //a encrypter
        this.password = null;
    }


    public User(String pseudo) {
        this.pseudo = pseudo;
        this.email = "";
        this.friendList = new ArrayList<User>();
        this.isConnected = false;
        this.isVisible  = false;
        this.invitations = new ArrayList<Invitation>();
        //a encrypter
        this.password = "";
    }

    public User(String pseudo, Coordinate c) {
        this.pseudo = pseudo;
        this.email = "";
        this.friendList = new ArrayList<User>();
        this.isConnected = false;
        this.isVisible  = false;
        this.invitations = new ArrayList<Invitation>();
        this.coordinate = c;
        //a encrypter
        this.password = "";
    }

    public User() {
        this.pseudo = "";
        this.email = "";
        this.friendList = new ArrayList<User>();
        this.isConnected = false;
        this.isVisible  = false;
        this.invitations = new ArrayList<Invitation>();
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

    public ArrayList<User> getFriendList() {
        return this.friendList;
    }

    public void setFriendList(ArrayList<User> friendList) {
        this.friendList = friendList;
    }

    public ArrayList<Invitation>  getInvitations() { return this.invitations ;  }

    public void setInvitations(ArrayList<Invitation> invitations) { this.invitations = invitations; }

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