package com.dant.entity;
import java.util.ArrayList;

public class InvitationRequestsTable {
    private ArrayList<Invitation>  list ;

    public InvitationRequestsTable(){
       this.list = new ArrayList<Invitation>();
    }

    public void setList(ArrayList<Invitation> list) {
        this.list = list;
    }

    public ArrayList<Invitation> getList() {

        return list;
    }


}
