package com.school.ecallowa.nyobora.entities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;

/**
 * Created by ecalloway on 11/22/2015.
 */
public class Person {

    private String _id;
    private String name;
    private String location;
    private String[] interests;
    private String description;
    private boolean connect;
    private boolean notifications;
    private int age;
    private Bitmap image;
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Person(int age, boolean connect, String[] interests, String location, String name, boolean notifications, Bitmap image) {
        this.age = age;
        this.connect = connect;
        this.interests = interests;
        this.location = location;
        this.name = name;
        this.notifications = notifications;
        this.image = image;
    }

    @Override
    public String toString() {
        String personString;

        personString = "" + getName() + "\n" + getAge() + "\n" + getDescription();

        return personString;
    }

    public Person(Context context) {
        String address = getMACAdress(context);
        this._id = "" + address ;
        SharedPreferences sharedPrefs = context.getSharedPreferences("userProfile",Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putString("id",_id);
        edit.apply();
    }

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

    public String get_id() {
        return _id;
    }

    public void set_id() {
        
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public String getMACAdress(Context context){
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String ID = manager.getConnectionInfo().getMacAddress();
        return ID;
    }
}
