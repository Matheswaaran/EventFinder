package com.example.mat.eventfinder.Extras;

/**
 * Created by mat on 21/03/18.
 */

public class Interviews {
    private String title, company, salaryPackage, experience, venue, eligibility, uid;

    public Interviews(){

    }

    public Interviews(String company, String salaryPackage, String experience, String venue, String eligibility){
        this.company = company;
        this.salaryPackage = salaryPackage;
        this.experience = experience;
        this.venue = venue;
        this.eligibility = eligibility;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(String salaryPackage) {
        this.salaryPackage = salaryPackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}