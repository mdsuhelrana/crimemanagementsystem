package com.example.mdsuhelrana.helloandroid.ModelClasses;

/**
 * Created by Md Suhel Rana on 3/5/2018.
 */

public class CrimeModel {
    private int id;
    private String location;
    private String postalcode;
    private String city;
    private String date;
    private String subject;
    private String complain;
    private String complainstaus;

    public CrimeModel() {
    }

    public CrimeModel(String complainstaus) {
        this.complainstaus = complainstaus;
    }

    public CrimeModel(String location, String postalcode, String city, String date, String subject, String complain, String complainstaus) {
        this.location = location;
        this.postalcode = postalcode;
        this.city = city;
        this.date = date;
        this.subject = subject;
        this.complain = complain;
        this.complainstaus = complainstaus;
    }

    public CrimeModel(int id, String location, String postalcode, String city, String date, String subject, String complain, String complainstaus) {
        this.id = id;
        this.location = location;
        this.postalcode = postalcode;
        this.city = city;
        this.date = date;
        this.subject = subject;
        this.complain = complain;
        this.complainstaus = complainstaus;
    }

    public String getComplainstaus() {
        return complainstaus;
    }

    public void setComplainstaus(String complainstaus) {
        this.complainstaus = complainstaus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
