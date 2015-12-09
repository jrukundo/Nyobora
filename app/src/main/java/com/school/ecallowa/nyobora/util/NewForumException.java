package com.school.ecallowa.nyobora.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ecalloway on 12/8/2015.
 */
public class NewForumException extends Exception{
    public NewForumException(){}
    public NewForumException(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
}
