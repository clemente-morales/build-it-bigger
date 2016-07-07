/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.builditbigger.jokeservice;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.builditbigger.jokeprovider.InMemoryJokeProvider;
import com.udacity.gradle.builditbigger.jokeprovider.JokeProvider;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokes",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokeservice.builditbigger.gradle.udacity.com",
                ownerName = "jokeservice.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class JokesEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "getJoke", httpMethod = ApiMethod.HttpMethod.GET)
    public Joke getJoke(@Named("category") String category) {
        JokeProvider jokeProvider = new InMemoryJokeProvider();
        return JokeConverter.toTransferObject(jokeProvider.getJoke(category));
    }

}
