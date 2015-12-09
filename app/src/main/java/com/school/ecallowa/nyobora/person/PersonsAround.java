package com.school.ecallowa.nyobora.person;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.entities.Person;

import java.util.ArrayList;

public class PersonsAround extends AppCompatActivity {

    private ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_around);

        String location;
        SharedPreferences settings = getSharedPreferences("userSettings", Context.MODE_PRIVATE);

        location = settings.getString("location", "");
        persons = getPersons(location);
        ArrayList<String> personArray = new ArrayList<String>();
        for(int i=0;i<persons.size();i++) {
            personArray.add(i,persons.get(i).toString());
        }

        PersonAdapter adapt = new PersonAdapter(this,personArray);

        ListView listV = (ListView) findViewById(R.id.listV);
        listV.setAdapter(adapt);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String name = persons.get(position).getName();

                Toast.makeText(PersonsAround.this,name + " was sent a notification",Toast.LENGTH_LONG).show();

                //TODO:Connect with this person, pass the person or NAME to the servlet
                final AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());

                alert.setTitle("Connect");
                alert.setMessage("Would you like to connect with " + name);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                connectWith(persons.get(position));
                            }
                        }
                ).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO:Close the alert
                    }
                });

                alert.show();
            }
        });

    }

    public ArrayList<Person> getPersons(String location){
        ArrayList<Person> p = new ArrayList<Person>();
        //TODO:get people based on location put them in p using servlet
        Person person = new Person(getApplicationContext());

        person.setAge(55);
        person.setDescription("Cool Dude");
        person.setName("Mandy");
        //TODO:Get the image and set the image
       // person.setImage(R.drawable.settingsmenu);

        p.add(0, person);

        person = new Person(getApplicationContext());
        person.setAge(25);
        person.setDescription("Cool Dudette");
        person.setName("Sam");

        p.add(1, person);

        return p;
    }

    public void connectWith(Person person){
        String loc = person.getLocation();
        String name = person.getName();

        //TODO: Send a notification to that person
    }

}
