package com.school.ecallowa.nyobora.thingstodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.school.ecallowa.nyobora.R;

public class SelectEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do_selection);

        TextView title = (TextView) findViewById(R.id.eventTitle);
        Intent in = getIntent();
        Bundle b = in.getExtras();

        String eventTitle = (String) b.get("title");
        title.setText(eventTitle);
    }
}
