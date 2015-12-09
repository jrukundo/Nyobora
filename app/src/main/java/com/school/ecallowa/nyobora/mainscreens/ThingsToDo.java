package com.school.ecallowa.nyobora.mainscreens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.school.ecallowa.nyobora.R;
import com.school.ecallowa.nyobora.fragments.MenuFragment;
import com.school.ecallowa.nyobora.thingstodo.SelectEvent;

public class ThingsToDo extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener{

    ListView list;
    String[] web = {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
            "Item 7"
    } ;

    Integer[] imageId = {
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);

        CustomList adapter = new
                CustomList(ThingsToDo.this, web, imageId);
        list = (ListView)findViewById(R.id.listView2);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        //Toast.makeText(ThingsToDo.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view,
                                                                int position, long id) {
                                            Intent newActivity = new Intent(view.getContext(), SelectEvent.class);
                                            String title = web[position];
                                            newActivity.putExtra("title",title);
                                            startActivity(newActivity);
                                        }
                                    }

        );

    }

}
