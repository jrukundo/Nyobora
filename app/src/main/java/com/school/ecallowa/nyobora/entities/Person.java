package com.school.ecallowa.nyobora.entities;

/**
 * Created by ecalloway on 11/22/2015.
 */
public class Person {

    private int _id;
    private String name;
    private String location;

    public Person(int age, boolean connect, String[] interests, String location, String name, boolean notifications) {
        this.age = age;
        this.connect = connect;
        this.interests = interests;
        this.location = location;
        this.name = name;
        this.notifications = notifications;
    }

    private String[] interests;
    private boolean connect;
    private boolean notifications;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isConnect() {
        return connect;
    }

    public void setConnect(boolean connect) {
        this.connect = connect;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }
}
