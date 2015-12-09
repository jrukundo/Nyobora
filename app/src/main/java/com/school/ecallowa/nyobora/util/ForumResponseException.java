package com.school.ecallowa.nyobora.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ecalloway on 11/23/2015.
 */
public class ForumResponseException extends Exception{
    public ForumResponseException(){}
    public ForumResponseException(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
}
