package com.udacity.gradle.builditbigger.services;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.jokedisplayer.views.JokeDisplayerActivity;
import com.udacity.gradle.builditbigger.jokeservice.jokes.Jokes;
import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by clerks on 7/6/16.
 */

public class JokeRequestorAsyncTask extends AsyncTask<Pair<Context, String>, Void, Joke> {
    private static String TAG = JokeRequestorAsyncTask.class.getSimpleName();
    private static final String URL_FORMAT = "https://%s/_ah/api/";
    private static final String PROPERTIES_PATH = "configuration.properties";
    public static final String GC_PROJECT_ID_PROPERTY = "google_cloud_project_id";

    private static Jokes jokeService = null;
    private Context context;

    @Override
    protected Joke doInBackground(Pair<Context, String>... params) {
        context = params[0].first;
        String category = params[0].second;

        if (jokeService == null) {
            String rootUrl = getRootUrl();
            Jokes.Builder builder = new Jokes.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(rootUrl);
            jokeService = builder.build();
        }

        try {
            return jokeService.getJoke(category).execute();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Joke joke) {
        if (joke != null) {
            Intent intent = new Intent(context, JokeDisplayerActivity.class);
            intent.putExtra(JokeDisplayerActivity.JOKE_EXTRA_KEY, joke.getDescription());
            context.startActivity(intent);
        }
    }

    private String getRootUrl() {
        return String.format(URL_FORMAT, getProjectId());
    }

    @NonNull
    private String getProjectId() {
        Properties properties = getProperties();
        return properties.getProperty(GC_PROJECT_ID_PROPERTY);
    }

    private Properties getProperties() {
        Properties properties = new Properties();

        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(PROPERTIES_PATH);
            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return properties;
    }

}
