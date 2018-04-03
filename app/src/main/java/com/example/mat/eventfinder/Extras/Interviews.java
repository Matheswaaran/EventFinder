package com.example.mat.eventfinder.Extras;

/**
 * Created by mat on 21/03/18.
 */

public class Interviews {
    private String interviewId, title, position, organiser, salaryPackage, date, startTime, endTIme, location, description, uid;

    public Interviews(){

    }

    public Interviews(String interviewId, String title, String position, String organiser, String salaryPackage, String date, String startTime, String endTIme, String location, String description, String uid){
        this.interviewId = interviewId;
        this.title = title;
        this.position = position;
        this.organiser = organiser;
        this.salaryPackage = salaryPackage;
        this.date = date;
        this.startTime = startTime;
        this.endTIme = endTIme;
        this.location = location;
        this.description = description;
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public String getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(String salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public String getInterviewDate() {
        return date;
    }

    public void setInterviewDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTIme() {
        return endTIme;
    }

    public void setEndTIme(String endTIme) {
        this.endTIme = endTIme;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }
}