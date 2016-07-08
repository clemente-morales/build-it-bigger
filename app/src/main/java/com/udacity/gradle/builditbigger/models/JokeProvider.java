package com.udacity.gradle.builditbigger.models;

import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;

/**
 * Created by clerks on 7/7/16.
 */

public interface JokeProvider {
    Joke getJokeByCategory(String category);
}
