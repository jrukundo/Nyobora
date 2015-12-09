package com.school.ecallowa.nyobora.mainscreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.person.Forum;
import com.school.ecallowa.nyobora.person.PersonsAround;
import com.school.ecallowa.nyobora.person.Profile;

public class Person extends AppCompatActivity {

    Button forumButton,peopleAroundButton,myProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        forumButton = (Button) findViewById(R.id.forumButton);
        peopleAroundButton = (Button) findViewById(R.id.peopleAreaButton);
        myProfileButton = (Button) findViewById(R.id.profileButton);

        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(),Forum.class);
                startActivity(i);
            }
        });

        peopleAroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PersonsAround.class);
                startActivity(i);
            }
        });
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getApplicationContext(),Profile.class);
                startActivity(i);
            }
        });

    }

}
