package com.udacity.gradle.builditbigger.jokeprovider;

public interface JokeProvider {
	Joke getRandomJoke();

	Joke getJoke(String category);
}
