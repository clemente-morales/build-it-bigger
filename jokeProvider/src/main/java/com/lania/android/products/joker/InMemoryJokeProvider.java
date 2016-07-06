package com.lania.android.products.joker;

import java.util.Random;

/**
 * Created by clerks on 7/5/16.
 */

public class InMemoryJokeProvider implements JokeProvider {
    private static final String[] JOKES = new String[] {
            "Can a kangaroo jump higher than a house? Of course, a house doesn\'t jump at all.",
            "It is so cold outside I saw a politician with his hands in his own pockets.",
            "I wanted to grow my own food but I couldn\'t get bacon seeds anywhere.",
            "I can\'t believe I forgot to go to the gym today. That\'s 7 years in a row now.",
            "What goes up and down but never moves? The stairs"
    };

    @Override
    public String getRandomJoke() {
        Random random = new Random();
        int jokeIndex = random.nextInt(JOKES.length);
        return JOKES[jokeIndex];
    }
}
