package com.example.sisyphus.thepoolparty;

/**
 * Created by mhigh on 7/27/2017.
 */

public class User {

    public String username;
    public String email;

    public User(){
        //Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }
}