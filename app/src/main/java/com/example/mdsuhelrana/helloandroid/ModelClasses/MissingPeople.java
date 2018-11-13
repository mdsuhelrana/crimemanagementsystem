package com.example.mdsuhelrana.helloandroid.ModelClasses;

/**
 * Created by Md Suhel Rana on 3/19/2018.
 */

public class MissingPeople {
    private int id;
    private String name;
    private String age;
    private String gender;
    private String addrdss;
    private String lastseen;
    private String details;
    private byte[] image;
    private String missingStatus;
    private String date;

    public MissingPeople(String missingStatus) {
        this.missingStatus = missingStatus;
    }

    public MissingPeople(String name, String age, String gender, String addrdss, String lastseen, String details, byte[] image, String missingStatus,String date) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.addrdss = addrdss;
        this.lastseen = lastseen;
        this.details = details;
        this.image = image;
        this.missingStatus = missingStatus;
        this.date=date;
    }

    public MissingPeople(int id, String name, String age, String gender, String addrdss, String lastseen, String details, byte[] image, String missingStatus,String date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.addrdss = addrdss;
        this.lastseen = lastseen;
        this.details = details;
        this.image = image;
        this.missingStatus = missingStatus;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrdss() {
        return addrdss;
    }

    public void setAddrdss(String addrdss) {
        this.addrdss = addrdss;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMissingStatus() {
        return missingStatus;
    }

    public void setMissingStatus(String missingStatus) {
        this.missingStatus = missingStatus;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
