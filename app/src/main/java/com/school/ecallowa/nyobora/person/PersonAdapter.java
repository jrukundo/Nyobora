package com.school.ecallowa.nyobora.person;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.ecallowa.nyobora.R;

import java.util.ArrayList;

/**
 * Created by ecalloway on 12/7/2015.
 */
public class PersonAdapter extends ArrayAdapter<String> {
    public PersonAdapter(Context context,ArrayList<String> people){
        super(context, R.layout.custom_row,people);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater personInflater = LayoutInflater.from(getContext());
        View customView = personInflater.inflate(R.layout.custom_row, parent, false);

        String singlePerson = getItem(position);
        TextView personText = (TextView) customView.findViewById(R.id.textView);
        ImageView img = (ImageView) customView.findViewById(R.id.imageView);

        personText.setText(singlePerson);
        img.setImageResource(R.drawable.settingsmenu);
        //img.setImageBitmap();

        return customView;
    }
}
