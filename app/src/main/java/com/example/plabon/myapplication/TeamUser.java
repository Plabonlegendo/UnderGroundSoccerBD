package com.example.plabon.myapplication;

public class TeamUser {
    private String name;
    private String teamname;

    public TeamUser(String name, String teamname) {
        this.name = name;
        this.teamname = teamname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }
}
