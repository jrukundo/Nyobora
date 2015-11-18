package com.school.ecallowa.nyobora.mainscreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.people.Forum;
import com.school.ecallowa.nyobora.people.PeopleAround;
import com.school.ecallowa.nyobora.people.Profile;

public class People extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
    }
    public void getForumPage(View v){

        Intent intent = new Intent(this, Forum.class);
        startActivity(intent);
    }

    public void getPeopleAroundPage(View v){

        Intent intent = new Intent(this, PeopleAround.class);
        startActivity(intent);
    }

    public void getProfilePage(View v){

        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
