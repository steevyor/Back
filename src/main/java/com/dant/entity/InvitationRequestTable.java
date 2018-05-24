package com.dant.entity;
import java.util.ArrayList;

public class InvitationRequestTable {
    private ArrayList<Invitation>  list ;

    public InvitationRequestTable(){
       this.list = new ArrayList<Invitation>();
    }

    public InvitationRequestTable(ArrayList<Invitation> list){
        this.list = list;
    }

    public void setList(ArrayList<Invitation> list) {
        this.list = list;
    }

    public ArrayList<Invitation> getList() {

        return list;
    }


}
