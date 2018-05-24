package com.dant.entity;

import java.util.ArrayList;

public class InvitationDemandTable {
    public ArrayList<Invitation> list ;

    public InvitationDemandTable(ArrayList<Invitation> list) {

        this.list = list;
    }

    public InvitationDemandTable() {

        this.list = new ArrayList<Invitation>();
    }

    public void setList(ArrayList<Invitation> list) {
        this.list = list;
    }

    public ArrayList<Invitation> getList() {

        return list;
    }


}
