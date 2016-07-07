package com.udacity.gradle.builditbigger.jokeservice;

/**
 * Created by clerks on 7/6/16.
 */

public class JokeConverter {
    public static Joke toTransferObject(com.udacity.gradle.builditbigger.jokeprovider.Joke joke) {
        Joke jokeTransferObject = new Joke();
        jokeTransferObject.setCategory(joke.getCategory());
        jokeTransferObject.setDescription(joke.getDescription());
        return jokeTransferObject;
    }
}
