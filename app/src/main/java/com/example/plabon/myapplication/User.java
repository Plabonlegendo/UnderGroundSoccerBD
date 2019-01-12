package com.example.plabon.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

public class User {
    private String email;
    private String name;
    private String position;
    private String jerseynumber;
    private String phoneNumber;
    private String dpURL;
    private String isModerator;
    private String bio;

    public User() {
    }

    public User(String email, String name, String position, String jerseynumber, String phoneNumber, String dpURL, String isModerator, String bio) {
        this.email = email;
        this.name = name;
        this.position = position;
        this.jerseynumber = jerseynumber;
        this.phoneNumber = phoneNumber;
        this.dpURL = dpURL;
        this.isModerator = isModerator;
        this.bio = bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {

        return email;

    }

    public String getBio() {
        return bio;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getJerseynumber() {
        return jerseynumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getDpURL() {
        return dpURL;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setJerseynumber(String roll) {
        this.jerseynumber = roll;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setDpURL(String dpURL) {
        this.dpURL = dpURL;
    }

    public void setIsModerator(String isModerator) {
        this.isModerator = isModerator;
    }

    public String getIsModerator() {
        return isModerator;
    }

}