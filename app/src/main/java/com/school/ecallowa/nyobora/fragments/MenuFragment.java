package com.school.ecallowa.nyobora.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.mainscreens.Language;
import com.school.ecallowa.nyobora.mainscreens.MainScreen;
import com.school.ecallowa.nyobora.mainscreens.Person;
import com.school.ecallowa.nyobora.mainscreens.Settings;
import com.school.ecallowa.nyobora.mainscreens.ThingsToDo;

public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.fragment_menu, container, false);

            ImageButton b1 = (ImageButton) view.findViewById(R.id.homeButton);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getHomePage(v);
                }
            });

            ImageButton b2 = (ImageButton) view.findViewById(R.id.settingsMenuButton);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSettingsPage(v);
                }
            });

            Button b3 = (Button) view.findViewById(R.id.peopleButton);
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPeoplePage(v);
                }
            });

            Button b4 = (Button) view.findViewById(R.id.ttdButton);
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getThingsToDoPage(v);
                }
            });

            Button b5 = (Button) view.findViewById(R.id.langButton);
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getLanguagePage(v);
                }
            });

            return view;
        }

    public void getPeoplePage(View v){
        Intent intent = new Intent(getActivity(), Person.class);
        startActivity(intent);

    }
    public void getThingsToDoPage(View v){

        Intent intent = new Intent(getActivity(), ThingsToDo.class);
        startActivity(intent);
    }

    public void getLanguagePage(View v){

        Intent intent = new Intent(getActivity(), Language.class);
        startActivity(intent);
    }

    public void getHomePage(View v){

        Intent intent = new Intent(getActivity(), MainScreen.class);
        startActivity(intent);
    }
    public void getSettingsPage(View v){
        Intent intent = new Intent(getActivity(),Settings.class);
        startActivity(intent);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}

