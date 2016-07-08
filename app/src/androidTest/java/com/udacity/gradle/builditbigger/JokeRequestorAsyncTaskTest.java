package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;
import com.udacity.gradle.builditbigger.models.JokeProvider;
import com.udacity.gradle.builditbigger.services.JokeRequestorAsyncTask;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.CountDownLatch;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by clerks on 7/7/16.
 */

public class JokeRequestorAsyncTaskTest extends ApplicationTestCase<Application> {
    public static final String ANIMAL_CATEGORY = "animals";
    public static final String DUMMY_ANIMAL_JOKE = "When is it bad luck to see a black cat? When you\'re a mouse!";
    private CountDownLatch signal;
    private Joke actualJoke;

    public JokeRequestorAsyncTaskTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testAsyncTaskRetrievesJoke() throws InterruptedException {
        JokeProvider jokeProvider = mock(JokeProvider.class);
        JokeRequestorAsyncTask.OnJokeFetchListener jokeFetchListener =
                mock(JokeRequestorAsyncTask.OnJokeFetchListener.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                actualJoke = (Joke) args[0];
                signal.countDown();
                return null;
            }
        }).when(jokeFetchListener).onComplete(any(Joke.class));
        when(jokeProvider.getJokeByCategory(anyString())).thenReturn(buildDummyJoke());


        JokeRequestorAsyncTask task = new JokeRequestorAsyncTask(jokeFetchListener, jokeProvider);
        task.execute(ANIMAL_CATEGORY);
        signal.await();
        assertNotNull(actualJoke);
        assertTrue(DUMMY_ANIMAL_JOKE.equalsIgnoreCase(actualJoke.getDescription()));

    }

    private Joke buildDummyJoke() {
        Joke joke = new Joke();
        joke.setCategory(ANIMAL_CATEGORY);
        joke.setDescription(DUMMY_ANIMAL_JOKE);
        return joke;
    }
}
