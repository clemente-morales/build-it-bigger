import com.udacity.gradle.builditbigger.jokeprovider.InMemoryJokeProvider;
import com.udacity.gradle.builditbigger.jokeprovider.Joke;
import com.udacity.gradle.builditbigger.jokeprovider.JokeProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by clerks on 7/9/16.
 */

public class InMemoryJokeProviderTest {
    public static final String ANIMALS_CATEGORY = "animals";
    public static final String EMPTY_CATEGORY = "";
    public static final String MISSING_CATEGORY = "sports";
    private JokeProvider jokeProvider;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        jokeProvider = new InMemoryJokeProvider();
    }

    @Test
    public void when_category_is_null_provider_inform_that_category_is_required() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Category is required");
        jokeProvider.getJoke(null);
    }

    @Test
    public void when_category_is_empty_provider_inform_that_category_is_required() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Category is required");
        jokeProvider.getJoke(EMPTY_CATEGORY);
    }

    @Test
    public void when_category_missing_in_provider_application_throws_exception() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Category not included in the repository");
        jokeProvider.getJoke(MISSING_CATEGORY);
    }

    @Test
    public void when_valid_category_joke_match_category() {
        Joke joke = jokeProvider.getJoke(ANIMALS_CATEGORY);
        assertThat(joke.getCategory(), is(ANIMALS_CATEGORY));
    }
}
