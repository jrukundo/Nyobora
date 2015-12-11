package com.school.ecallowa.nyobora.person;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.school.ecallowa.nyobora.R;

import java.io.ByteArrayOutputStream;

public class Profile extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    String imgString;
    Bitmap bitmap;
    EditText nameInput;
    EditText ageInput;
    EditText descriptionInput;
    ImageView imgV;
    EditText contactInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button sB = (Button) findViewById(R.id.saveButton);
        Button uB = (Button) findViewById(R.id.uploadButton);
        Button tB = (Button) findViewById(R.id.photoButton);

        if(hasCamera()){
            tB.setEnabled(true);
        }

        sB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile(v);

            }
        });

        uB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
        tB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera(v);
            }
        });
        showProfile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null !=data){
                Uri image = data.getData();

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image);

                imgV = (ImageView) findViewById(R.id.profileImg);
                imgV.setImageBitmap(bitmap);
            }else if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap picture = (Bitmap) extras.get("data");
                //TODO:Check for the correct rotation?
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                picture = Bitmap.createBitmap(picture,0,0,picture.getWidth(),picture.getHeight(),matrix,true);
                imgV.setImageBitmap(picture);
             }

        }catch(Exception e){
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }

    public void saveProfile(View v){
        //get whether the user wants to connect or not
        SharedPreferences sharedP = getSharedPreferences("userSettings", Context.MODE_PRIVATE);

        Boolean connect = sharedP.getBoolean("connect",false);
        Boolean notify = sharedP.getBoolean("notify",false);

        nameInput = (EditText) findViewById(R.id.profileName);
        ageInput = (EditText) findViewById(R.id.ageProfile);
        descriptionInput = (EditText) findViewById(R.id.descriptionProfile);
        contactInput = (EditText) findViewById(R.id.contactProfile);

        String userName = nameInput.getText().toString();
        String userAge = ageInput.getText().toString();
        String userDescription = descriptionInput.getText().toString();
        String contact = contactInput.getText().toString();
        //TODO:Send location

        //Save to SharedPreferences
        SharedPreferences sharedPrefs = getSharedPreferences("userProfile",Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPrefs.edit();
        edit.putString("name",userName);
        edit.putString("age",userAge);
        edit.putString("description", userDescription);
        edit.putString("contact",contact);
        imgV = (ImageView) findViewById(R.id.profileImg);
        bitmap = ((BitmapDrawable)imgV.getDrawable()).getBitmap();
       // bitmap = imgV.getBackground().getBit
        edit.putString("image",saveImage(bitmap));
        edit.apply();
//
//        if(connect){
//            //send to server
//            MyPersonDBHandler personDB = new MyPersonDBHandler(this,null,null,1);
//        }else{
//            //do nothing
//        }
        //Todo: When to make a new person/ give an id
        //Todo: Give an id when posting but how to make it static/ not use it again
        //Todo: How to only upload/change the table values once for this user
        //Todo: Don't upload a new person to the table because one thing changed
//        Person user = new Person(Integer.parseInt(userAge),connect,
//                sharedP.getString("interests","").split(","),sharedP.getString("location",""),
//                userName,notify,bitmap);
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public static String saveImage(Bitmap image){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,out);
        byte[] imgarray = out.toByteArray();
        String imgEncoded = Base64.encodeToString(imgarray, Base64.DEFAULT);

        Log.d("Image Log: ",imgEncoded);
        return imgEncoded;
    }
    public static Bitmap decodeImage(String img){
        byte[] decoded = Base64.decode(img, 0);

        return BitmapFactory.decodeByteArray(decoded,0,decoded.length);
    }
    public void showProfile(){
        SharedPreferences profile = getSharedPreferences("userProfile",Context.MODE_PRIVATE);

        String name = profile.getString("name", "");
        String age = profile.getString("age", "");
        String descript = profile.getString("description", "");
        String imageString = profile.getString("image", "");
        String contact = profile.getString("contact","");
        Bitmap imageBit = null;
        if(!imageString.equals("")){
            imageBit = decodeImage(imageString);
        }

        nameInput = (EditText) findViewById(R.id.profileName);
        ageInput = (EditText) findViewById(R.id.ageProfile);
        descriptionInput = (EditText) findViewById(R.id.descriptionProfile);
        contactInput = (EditText) findViewById(R.id.contactProfile);
        imgV = (ImageView) findViewById(R.id.profileImg);

        nameInput.setText(name);
        ageInput.setText(age);
        descriptionInput.setText(descript);
        contactInput.setText(contact);
        imgV.setImageBitmap(imageBit);
    }

    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
    public void launchCamera(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(i,REQUEST_IMAGE_CAPTURE);
    }
}
