package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.jokedisplayer.views.JokeDisplayerActivity;
import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;
import com.udacity.gradle.builditbigger.models.DialogData;
import com.udacity.gradle.builditbigger.models.JokeProvider;
import com.udacity.gradle.builditbigger.models.ServerJokeProvider;
import com.udacity.gradle.builditbigger.services.JokeRequestorAsyncTask;
import com.udacity.gradle.builditbigger.views.IndeterminateProgressDialog;


public class MainActivity extends AppCompatActivity
        implements JokeRequestorAsyncTask.OnJokeFetchListener {

    public static final String PROGRESS_DIALOG_TAG = "LoadingData";

    private IndeterminateProgressDialog dialog;

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
    public void onPreExecute() {
        dialog = IndeterminateProgressDialog.newInstance(buildDialogData());
        dialog.show(getSupportFragmentManager(), PROGRESS_DIALOG_TAG);
    }

    @Override
    public void onComplete(Joke joke) {
        dialog.dismiss();

        if (joke != null) {
            Intent intent = new Intent(this, JokeDisplayerActivity.class);
            intent.putExtra(JokeDisplayerActivity.JOKE_EXTRA_KEY, joke.getDescription());
            this.startActivity(intent);
        }
    }

    private DialogData buildDialogData() {
        return new DialogData(R.string.app_name, R.string.fetchingJokesMessage, true, android.R.drawable.ic_dialog_info);
    }
}
