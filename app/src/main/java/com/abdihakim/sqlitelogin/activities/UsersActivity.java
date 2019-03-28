package com.abdihakim.sqlitelogin.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.abdihakim.sqlitelogin.R;

public class UsersActivity extends AppCompatActivity {

    private TextView textViewName;

    @Override
     protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        String nameFromIntent = getIntent().getStringExtra("NAME");
        textViewName.setText("Welcome "+nameFromIntent);

    }
}
