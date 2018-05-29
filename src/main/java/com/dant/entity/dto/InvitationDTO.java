package com.dant.entity.dto;

public class InvitationDTO  {

    public String emitterId;
    public String recepterId;

    public InvitationDTO(String emitterId, String recepterId){

        this.emitterId = emitterId;
        this.recepterId = recepterId;

    }
    public InvitationDTO(){

        this.emitterId = "";
        this.recepterId = "";

    }

    public String getEmitterId() {
        return emitterId;
    }

    public void setEmitterId(String emitterId) {
        this.emitterId = emitterId;
    }

    public String getRecepterId() {
        return recepterId;
    }

    public void setRecepterId(String recepterId) {
        this.recepterId = recepterId;
    }
}
