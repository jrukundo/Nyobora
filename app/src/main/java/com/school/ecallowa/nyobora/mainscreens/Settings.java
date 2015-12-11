package com.school.ecallowa.nyobora.mainscreens;
//Todo: Make the checks the same when switching languages
//Todo: Check for the words to match after or save the current checked boxes by id for refresh
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.adapters.PersonAPI;

import java.util.Locale;

/**
 * Written by ecallowa
 */

public class Settings extends AppCompatActivity{

    boolean connect,notify;
    String interests = "";
    CheckBox out,animals,art,food,tech,museums;
    TextView loc;
    Spinner changeLang;
    ToggleButton connectWith,notifications;
    int locationOfLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        loc = (TextView) findViewById(R.id.title);

        Button save = (Button) findViewById(R.id.saveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings(v);

                //TODO:get rid of previous screen so it just goes back to the previous screen
            }
        });

        final ListView locationList = (ListView) findViewById(R.id.locationList);
        ArrayAdapter<String> locList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locationlist));
        locationList.setAdapter(locList);

        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String location = locationList.getItemAtPosition(position).toString();
                locationList.setSelection(position);
                loc.setText(location);
            }
        });

        out = (CheckBox) findViewById(R.id.outdoorsCheckBox);
        animals = (CheckBox) findViewById(R.id.animalsCheckBox);
        art = (CheckBox) findViewById(R.id.artCheckBox);
        food = (CheckBox) findViewById(R.id.foodCheckBox);
        tech = (CheckBox) findViewById(R.id.technologyCheckBox);
        museums = (CheckBox) findViewById(R.id.museumCheckBox);

        connectWith = (ToggleButton) findViewById(R.id.connectButton);
        notifications = (ToggleButton) findViewById(R.id.notifications);

        changeLang = (Spinner) findViewById(R.id.languageSpinner);
        ArrayAdapter<CharSequence> adapterclass = ArrayAdapter.createFromResource(this,R.array.langlist,android.R.layout.simple_spinner_dropdown_item);
        adapterclass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        changeLang.setAdapter(adapterclass);
        resetSettings();
    }
    public void resetSettings(){
        String sets = getSettings();
        if(!sets.contains("true") && !sets.contains("false") || sets.contains("0")){

        }
        else {
            String[] pop = sets.split("/");

            loc.setText(pop[0].split(":")[1]);

            for (int i = 1; i < pop.length-1; i++) {
                if (pop[i].contains(String.valueOf(out.getText()))) {
                    out.setChecked(true);
                }
                if (pop[i].contains(art.getText())) {
                    art.setChecked(true);
                }
                if (pop[i].contains(animals.getText())) {
                    animals.setChecked(true);
                }
                if (pop[i].contains(food.getText())) {
                    food.setChecked(true);
                }
                if (pop[i].contains(tech.getText())) {
                    tech.setChecked(true);
                }
                if (pop[i].contains(museums.getText())) {
                    museums.setChecked(true);
                }
                if (pop[i].contains("connect:true")) {
                    connectWith.setChecked(true);
                }
                if (pop[i].contains("notify:true")) {
                    notifications.setChecked(true);
                }
            }
            changeLang.setSelection(Integer.parseInt(pop[pop.length-1].split(":")[1]));
        }
    }
    public String getInterestsChecks() {
        if (out.isChecked()) {
            interests += "," +String.valueOf(out.getText());
        }
        if (animals.isChecked()) {
            interests += "," + String.valueOf(animals.getText());
        }
        if (art.isChecked()) {
            interests += "," + (String) art.getText();
        }
        if (food.isChecked()) {
            interests += "," + (String) food.getText();
        }
        if (tech.isChecked()) {
            interests += "," + String.valueOf(tech.getText());
        }
        if(museums.isChecked()){
            interests += "," + String.valueOf(museums.getText());
        }
        return interests;
    }

    public void saveSettings(View view){
        String lang = changeLang.getSelectedItem().toString();

        ArrayAdapter tempAdap = (ArrayAdapter) changeLang.getAdapter();
        locationOfLang = tempAdap.getPosition(lang);
        //Change Language
        changeLocale(lang);

        String location = loc.getText().toString();
        String ints = getInterestsChecks();


        connect = connectWith.isChecked();
        notify = notifications.isChecked();

        SharedPreferences sharedP = getSharedPreferences("userSettings", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedP.edit();
        edit.putString("location",location);
        edit.putString("interests", ints);
        edit.putString("applanguage", lang);
        if(connect) {
            edit.putBoolean("connect", true);
        }else{
            edit.putBoolean("connect",false);
        }

        if(notify){
            edit.putBoolean("notify",true);
        }else {
            edit.putBoolean("notify", false);
        }
        edit.putInt("language", locationOfLang);
        edit.apply();


        PersonAPI thisPerson = new PersonAPI();
        thisPerson.newPerson(getApplicationContext());

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public String getSettings(){
        String sets;

        SharedPreferences sharedPrefs = getSharedPreferences("userSettings", Context.MODE_PRIVATE);

        sets ="location:";
        sets+= sharedPrefs.getString("location","");
        sets+="/";
        sets+="interests:";
        sets+= sharedPrefs.getString("interests","");
        sets+="/";
        sets+="language:";
        sets+= sharedPrefs.getString("applanguage","");
        sets+="/";
        sets+="connect:";
        sets+= sharedPrefs.getBoolean("connect",false);
        sets+="/";
        sets+="notify:";
        sets+= sharedPrefs.getBoolean("notify",false);
        sets+="/";
        sets+="language:";
        sets+=sharedPrefs.getInt("language",locationOfLang);

        return sets;
    }
    public void changeLocale(String language){
        Locale myLocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();
        config.locale = myLocale;
        res.updateConfiguration(config, dm);
        Intent refresh = new Intent(this,Settings.class);
        startActivity(refresh);
    }

}
