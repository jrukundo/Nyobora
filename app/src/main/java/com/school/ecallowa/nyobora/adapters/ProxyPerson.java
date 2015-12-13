package com.school.ecallowa.nyobora.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.school.ecallowa.nyobora.entities.Person;

/**
 * Created by ecalloway on 12/6/2015.
 */
public class ProxyPerson implements CreatePerson{
    private static Person person;


    @Override
    public void newPerson(Context context) {
        person = new Person(context);

        String[] arraySets = getPersonSettings(context).split("/");
        String[] arrayProf = getPersonProfile(context).split("/");

        if(arrayProf.length>=3 && arraySets.length>=3) {
            person.setName(arrayProf[0]);
            person.setAge(Integer.parseInt(arrayProf[1]));
            person.setDescription(arrayProf[2]);
            person.setImage(decodeImage(arrayProf[3]));

            person.setLocation(arraySets[0].split(":")[1]);
            person.setInterests(arraySets[1].split(":")[1].split(","));
            person.setConnect(Boolean.parseBoolean(arraySets[2].split(":")[1]));
            person.setNotifications(Boolean.parseBoolean(arraySets[3].split(":")[1]));
        }
    }

    public String getPersonSettings(Context context){
        String sets;
        SharedPreferences share = context.getSharedPreferences("userSettings", Context.MODE_PRIVATE);

        sets ="location:";
        sets+= share.getString("location","");
        sets+="/";
        sets+="interests:";
        sets+= share.getString("interests","");
        sets+="/";
        sets+="connect:";
        sets+= share.getBoolean("connect",false);
        sets+="/";
        sets+="notify:";
        sets+= share.getBoolean("notify",false);

        return sets;
    }
    public String getPersonProfile(Context context){
        String profile;
        SharedPreferences prof = context.getSharedPreferences("userProfile",Context.MODE_PRIVATE);

        String name = prof.getString("name", "");
        String age = prof.getString("age", "");
        String descript = prof.getString("description", "");
        String imageString = prof.getString("image", "");

        profile = name + "/" + age + "/" + descript + "/" + imageString;
        return profile;
    }

    public static Bitmap decodeImage(String img){
        byte[] decoded = Base64.decode(img, 0);

        return BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
    }
}
