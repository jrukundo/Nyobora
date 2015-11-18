package com.school.ecallowa.nyobora.mainscreens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.fragments.MenuFragment;

public class MainScreen extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void getPeoplePage(View v){
        Intent intent = new Intent(this, People.class);
        startActivity(intent);

    }
    public void getThingsToDoPage(View v){

        Intent intent = new Intent(this, ThingsToDo.class);
        startActivity(intent);
    }

    public void getLanguagePage(View v){

        Intent intent = new Intent(this, Language.class);
        startActivity(intent);
    }
    public void getSettingsPage(View v){

        Intent intent = new Intent(this, Language.class);
        startActivity(intent);
    }
}
