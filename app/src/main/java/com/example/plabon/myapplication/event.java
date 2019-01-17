package com.example.plabon.myapplication;

import android.widget.EditText;

public class event {
    String  eventname;
    String eventEmail;
    String eventlocation;
    String phonenumber;
    String startingtime;
    String startingdate;
    String endingtime;
    String endingdate;
    String bestplayer;
    String bestattacker;
    String bestgoalkeeper;
    String bestdefender;
    String bestmidfielder;

    public event(){

    }

    public event(String eventname, String eventEmail, String eventlocation, String phonenumber, String startingtime, String startingdate, String endingtime, String endingdate, String bestplayer, String bestattacker, String bestgoalkeeper, String bestdefender, String bestmidfielder) {
        this.eventname = eventname;
        this.eventEmail = eventEmail;
        this.eventlocation = eventlocation;
        this.phonenumber = phonenumber;
        this.startingtime = startingtime;
        this.startingdate = startingdate;
        this.endingtime = endingtime;
        this.endingdate = endingdate;
        this.bestplayer = bestplayer;
        this.bestattacker = bestattacker;
        this.bestgoalkeeper = bestgoalkeeper;
        this.bestdefender = bestdefender;
        this.bestmidfielder = bestmidfielder;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventEmail() {
        return eventEmail;
    }

    public void setEventEmail(String eventEmail) {
        this.eventEmail = eventEmail;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) {
        this.eventlocation = eventlocation;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStartingtime() {
        return startingtime;
    }

    public void setStartingtime(String startingtime) {
        this.startingtime = startingtime;
    }

    public String getStartingdate() {
        return startingdate;
    }

    public void setStartingdate(String startingdate) {
        this.startingdate = startingdate;
    }

    public String getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(String endingtime) {
        this.endingtime = endingtime;
    }

    public String getEndingdate() {
        return endingdate;
    }

    public void setEndingdate(String endingdate) {
        this.endingdate = endingdate;
    }

    public String getBestplayer() {
        return bestplayer;
    }

    public void setBestplayer(String bestplayer) {
        this.bestplayer = bestplayer;
    }

    public String getBestattacker() {
        return bestattacker;
    }

    public void setBestattacker(String bestattacker) {
        this.bestattacker = bestattacker;
    }

    public String getBestgoalkeeper() {
        return bestgoalkeeper;
    }

    public void setBestgoalkeeper(String bestgoalkeeper) {
        this.bestgoalkeeper = bestgoalkeeper;
    }

    public String getBestdefender() {
        return bestdefender;
    }

    public void setBestdefender(String bestdefender) {
        this.bestdefender = bestdefender;
    }

    public String getBestmidfielder() {
        return bestmidfielder;
    }

    public void setBestmidfielder(String bestmidfielder) {
        this.bestmidfielder = bestmidfielder;
    }
}
