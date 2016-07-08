package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.jokedisplayer.views.JokeDisplayerActivity;
import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;
import com.udacity.gradle.builditbigger.models.JokeProvider;
import com.udacity.gradle.builditbigger.models.ServerJokeProvider;
import com.udacity.gradle.builditbigger.services.JokeRequestorAsyncTask;


public class MainActivity extends ActionBarActivity
        implements JokeRequestorAsyncTask.OnJokeFetchListener {

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

    public void tellJoke(View view) {
        JokeProvider jokeProvider = new ServerJokeProvider(this);
        new JokeRequestorAsyncTask(this, jokeProvider).execute("general");
    }

    @Override
    public void onComplete(Joke joke) {
        if (joke != null) {
            Intent intent = new Intent(this, JokeDisplayerActivity.class);
            intent.putExtra(JokeDisplayerActivity.JOKE_EXTRA_KEY, joke.getDescription());
            this.startActivity(intent);
        }
    }
}
