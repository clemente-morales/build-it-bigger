package com.udacity.gradle.builditbigger.services;

import android.os.AsyncTask;

import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;
import com.udacity.gradle.builditbigger.models.JokeProvider;

/**
 * Created by clerks on 7/6/16.
 */

public class JokeRequestorAsyncTask extends AsyncTask<String, Void, Joke> {
    private OnJokeFetchListener jokeFetchListener;
    private JokeProvider jokeProvider;

    public JokeRequestorAsyncTask(OnJokeFetchListener jokeFetchListener, JokeProvider jokeProvider) {
        this.jokeFetchListener = jokeFetchListener;
        this.jokeProvider = jokeProvider;
    }

    @Override
    protected void onPreExecute() {
        jokeFetchListener.onPreExecute();
    }

    @Override
    protected Joke doInBackground(String... params) {
        String category = params[0];
        return jokeProvider.getJokeByCategory(category);
    }

    @Override
    protected void onPostExecute(Joke joke) {
        jokeFetchListener.onComplete(joke);
    }

    public interface OnJokeFetchListener {

        void onPreExecute();
        void onComplete(Joke joke);
    }

}
