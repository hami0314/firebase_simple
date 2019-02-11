package com.example.hami.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    private Button uploadButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //mImageView = (ImageView) findViewById(R.id.imageView);

        uploadButton = (Button) findViewById(R.id.upload);
        


        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mDatabase = FirebaseDatabase.getInstance().getReference();
                Map locMap = new HashMap();
                locMap.put("name", "kajang");
                locMap.put("location", 123.2);
                Map userMap = new HashMap ();
                userMap.put("Hami", "Happy");
                userMap.put("No:", 1.253);
                userMap.putAll(locMap);
                String key = mDatabase.push().getKey(); // generate id for infomations uploaded
                mDatabase.push().setValue(userMap);

                // another method of uploading database 
                //mDatabase.child("users").child("hi2").child("username").setValue("hami");
                //mDatabase.child("users").child("002").child("geoPoint").child("location").setValue(1.23);


            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        StorageReference storageRef;
        storageRef = FirebaseStorage.getInstance().getReference();
        super.onActivityResult(requestCode, resultCode,data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
