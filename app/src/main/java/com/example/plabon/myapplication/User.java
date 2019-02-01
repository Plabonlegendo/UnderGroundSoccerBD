package com.example.plabon.myapplication;

public class User {
    private String email;
    private String name;
    private String position;
    private String jerseynumber;
    private String phoneNumber;
    private String dpURL;
    private String isModerator;
    private String bio;
    private String Location;
    private String foot;
    private String team;


    public User() {
    }

    public User(String email, String name, String position, String jerseynumber, String phoneNumber,String Location,String team,String foot, String dpURL, String isModerator, String bio) {
        this.email = email;
        this.name = name;
        this.position = position;
        this.jerseynumber = jerseynumber;
        this.phoneNumber = phoneNumber;
        this.Location = Location;
        this.team = team;
        this.foot = foot;
        this.dpURL = dpURL;
        this.isModerator = isModerator;
        this.bio = bio;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setLocation(String location){this.Location= location;}
    public String getLocation(){return Location;}

    public void setFoot(String foot)
    {
        this.foot = foot;
    }
    public String getFoot()
    {
        return foot;
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