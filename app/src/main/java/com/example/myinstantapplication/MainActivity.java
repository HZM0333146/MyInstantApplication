package com.example.myinstantapplication;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.widget.Toolbar;

import com.example.myinstantapplication.view.TranslationActivity;


public class MainActivity extends BasicActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startActivity(new Intent(this, TranslationActivity.class));
    }
}
