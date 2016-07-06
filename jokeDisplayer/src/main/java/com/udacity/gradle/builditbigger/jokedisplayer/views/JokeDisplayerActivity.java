package com.udacity.gradle.builditbigger.jokedisplayer.views;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.udacity.gradle.builditbigger.jokedisplayer.R;


public class JokeDisplayerActivity extends ActionBarActivity {
    public static String JOKE_EXTRA_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);
    }

}
