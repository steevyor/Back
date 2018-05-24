package com.dant.entity;

public class Friend {


    private String pseudo;
    private Boolean isConnected;
    private Boolean isVisible;
    private Coordinate coordinate;

    public Friend(String pseudo, Boolean isConnected, Boolean isVisible, Coordinate coordinate){

        this.pseudo = pseudo;
        this.isConnected = isConnected;
        this.isVisible = isVisible;
        this.coordinate = coordinate;
    }

    public String getPseudo() {
        return pseudo;

    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean isConnected) {
        this.isConnected = isConnected;
    }

}
