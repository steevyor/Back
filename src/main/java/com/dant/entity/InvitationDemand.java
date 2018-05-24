package com.dant.entity;

public class InvitationDemand {

    private String emitterId;
    private String recepterId;

    public InvitationDemand(String emitterId, String recepterId){

        this.emitterId = emitterId;
        this.recepterId = recepterId;

    }

    public String getEmitterId() {
        return emitterId;
    }

    public String getRecepterId() {
        return recepterId;
    }

    public void setEmitterId(String emitterId) {
        this.emitterId = emitterId;
    }

    public void setRecepteurId(String recepterId) {
        this.recepterId = recepterId;
    }

}
