package com.dant.entity;

import java.util.ArrayList;

public class FriendList {


    public ArrayList<User> List ;

    public FriendList(){
        this.List = new ArrayList<User>();
    }

    public void addFriend(User user){
        List.add(user);
    }

    public void removeFriend (User user){
        List.remove(user);
    }

    public ArrayList getList() {
        return List;
    }

}
