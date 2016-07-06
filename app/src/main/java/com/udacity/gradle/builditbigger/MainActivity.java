package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lania.android.products.joker.InMemoryJokeProvider;
import com.lania.android.products.joker.JokeProvider;
import com.udacity.gradle.builditbigger.jokedisplayer.views.JokeDisplayerActivity;


public class MainActivity extends ActionBarActivity {
    private JokeProvider jokeProvider = new InMemoryJokeProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        String joke = jokeProvider.getRandomJoke();
        // Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(JokeDisplayerActivity.DISPLAY_JOKE_ACTION);
        intent.putExtra(JokeDisplayerActivity.JOKE_EXTRA_KEY, joke);
        startActivity(intent);
    }
}
