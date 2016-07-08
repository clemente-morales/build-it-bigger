package com.udacity.gradle.builditbigger.models;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.jokeservice.jokes.Jokes;
import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by clerks on 7/7/16.
 */

public class ServerJokeProvider implements JokeProvider {
    private static final String TAG = ServerJokeProvider.class.getSimpleName();
    private static final String URL_FORMAT = "https://%s/_ah/api/";
    private static final String PROPERTIES_PATH = "configuration.properties";
    public static final String GC_PROJECT_ID_PROPERTY = "google_cloud_project_id";

    private static Jokes jokeService = null;
    private final Context context;

    public ServerJokeProvider(Context context) {
        this.context = context;
    }

    @Override
    public Joke getJokeByCategory(String category) {
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
