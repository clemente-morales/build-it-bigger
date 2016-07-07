package com.udacity.gradle.builditbigger.jokeprovider;

/**
 * Created by clerks on 7/6/16.
 */

public class Joke {
    private final String category;
    private final String description;

    public Joke(String category, String description) {
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
