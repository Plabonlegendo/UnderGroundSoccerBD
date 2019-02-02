package com.example.plabon.myapplication;

public class Teamuserform {
    private String teamname;
    private String contact;
    private String location;

    public Teamuserform(String teamname)
    {
        this.teamname = teamname;
    }

    public Teamuserform(String teamname, String contact, String location) {
        this.teamname = teamname;
        this.contact = contact;
        this.location = location;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
