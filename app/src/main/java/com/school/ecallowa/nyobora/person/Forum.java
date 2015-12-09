package com.school.ecallowa.nyobora.person;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.util.ForumResponseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Forum extends AppCompatActivity {

    ForumAdapter listAdapter;
    ExpandableListView expandList;
    EditText newPostInput;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String forumTopic;
    String location;
    String id;
    SharedPreferences prof;
    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        createListData();

        prof = getSharedPreferences("userProfile", Context.MODE_PRIVATE);
        settings = getSharedPreferences("userSettings", Context.MODE_PRIVATE);

        expandList = (ExpandableListView) findViewById(R.id.expandableListView);

        listAdapter = new ForumAdapter(this, listDataHeader,listDataChild);

        expandList.setAdapter(listAdapter);

        expandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " : "
                        + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                forumTopic = listDataHeader.get(groupPosition);
                popupInput(forumTopic);
                return false;
            }
        });
        expandList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Toast.makeText(getApplicationContext(),"Group Clicked: " + listDataHeader.get(groupPosition),Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        id = prof.getString("id","");

        Button newPost = (Button) findViewById(R.id.postButton);
        newPostInput = (EditText) findViewById(R.id.newPostInput);
        location = settings.getString("location","");
        newPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post = newPostInput.getText().toString();
                try {
                    if (post.equals(null)) {
                        throw new ForumResponseException("You have to type something", getApplicationContext());
                    }
                    sendtoServer(post, null, location, id);
                } catch (ForumResponseException e) {
                    //TODO: Close the alert
                }

            }
        });

    }
    private void createListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //Get questions and answers

        listDataHeader.add("What is the cheapest mode of transportation?");
        listDataHeader.add("Where is a good restaurant?");
        listDataHeader.add("Where is a good Forex location?");

        List<String> list1 = new ArrayList<String>();
        list1.add("Moto");
        list1.add("Taxi");
        list1.add("Bus");

        List<String> list2 = new ArrayList<String>();
        list2.add("KTC");
        list2.add("2 km from KTC");
        list2.add("Near the airport");

        List<String> list3 = new ArrayList<String>();
        list3.add("Kacyiru");
        list3.add("Kimironko");
        list3.add("Minagri");

        listDataChild.put(listDataHeader.get(0),list1);
        listDataChild.put(listDataHeader.get(1),list2);
        listDataChild.put(listDataHeader.get(2),list3);
    }
    public void popupInput(final String topic){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Response");
        alert.setMessage("Type your response");

        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = input.getText().toString();
                        try {
                            if (msg.equals(null)) {
                                throw new ForumResponseException("You have to type something", getApplicationContext());
                            }
                            sendtoServer(topic, msg, location,id);
                        } catch (ForumResponseException e) {
                            //TODO: Close the alert
                        }
                    }
                }
        ).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.show();
    }
    private void sendtoServer(String topic, String msg, String location, String id) {
        //TODO:Write this method for all of the activities
    }
}
