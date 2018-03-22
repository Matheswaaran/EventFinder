package com.example.mat.eventfinder.Extras;

/**
 * Created by mat on 21/03/18.
 */

public class Users {
    private String uid, firstName, lastName;

    public Users(){

    }

    public Users(String uid, String firstName, String lastName){
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
