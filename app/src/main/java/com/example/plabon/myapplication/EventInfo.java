package com.example.plabon.myapplication;

public class EventInfo {

    private String bestAttacker;
    private String bestDefender;
    private String bestGoalkeeper;
    private String bestMidfielder;
    private String bestPlayer;
    private String endingDate;
    private String endingTime;
    private String eventEmail;
    private String eventLocation;
    private String eventName;
    private String phoneNumber;
    private String startingDate;
    private String startingTime;

    public EventInfo() {
    }


    public EventInfo(String bestAttacker, String bestDefender, String bestGoalkeeper, String bestMidfielder,
                     String bestPlayer, String endingDate, String endingTime, String eventEmail, String eventLocation,
                     String eventName, String phoneNumber, String startingDate, String startingTime) {
        this.bestAttacker = bestAttacker;
        this.bestDefender = bestDefender;
        this.bestGoalkeeper = bestGoalkeeper;
        this.bestMidfielder = bestMidfielder;
        this.bestPlayer = bestPlayer;
        this.startingDate = startingDate;

        this.startingTime = startingTime;
        this.endingDate = endingDate;
        this.endingTime = endingTime;
        this.eventName = eventName;
        this.eventEmail = eventEmail;
        this.eventLocation = eventLocation;
        this.phoneNumber = phoneNumber;
    }

    public String getEventName(){
        return eventName;
    }

    public String getBestAttacker() {
        return bestAttacker;
    }

    public String getBestDefender() {
        return bestDefender;
    }

    public String getBestGoalkeeper() {
        return bestGoalkeeper;
    }

    public String getBestMidfielder() {
        return bestMidfielder;
    }

    public String getBestPlayer() {
        return bestPlayer;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public String getEventEmail() {
        return eventEmail;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getStartingTime() {
        return startingTime;
    }

}
