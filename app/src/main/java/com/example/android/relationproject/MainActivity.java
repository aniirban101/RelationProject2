package com.example.android.relationproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class MainActivity extends Activity {
    ListView list;
    String[] name = {
            "My Dad",
            "My Mom",
            "My Sister",
            "That's me!!"
    };
    String[] relation = {
            "Father",
            "Mother",
            "Sister",
            "Myself",
    };
    Integer[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        com.example.android.relationproject.NewList adapter = new com.example.android.relationproject.NewList(MainActivity.this, name, relation, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

}
