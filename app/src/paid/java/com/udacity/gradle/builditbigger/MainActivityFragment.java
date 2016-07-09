package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.udacity.gradle.builditbigger.jokedisplayer.views.JokeDisplayerActivity;
import com.udacity.gradle.builditbigger.jokeservice.jokes.model.Joke;
import com.udacity.gradle.builditbigger.models.DialogData;
import com.udacity.gradle.builditbigger.models.JokeProvider;
import com.udacity.gradle.builditbigger.models.ServerJokeProvider;
import com.udacity.gradle.builditbigger.services.JokeRequestorAsyncTask;
import com.udacity.gradle.builditbigger.views.IndeterminateProgressDialog;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
        implements JokeRequestorAsyncTask.OnJokeFetchListener {

    public static final String PROGRESS_DIALOG_TAG = "LoadingData";

    private IndeterminateProgressDialog dialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        loadActions(root);

        return root;
    }

    @Override
    public void onPreExecute() {
        dialog = IndeterminateProgressDialog.newInstance(buildDialogData());
        dialog.show(getActivity().getSupportFragmentManager(), PROGRESS_DIALOG_TAG);
    }

    @Override
    public void onComplete(Joke joke) {
        dialog.dismiss();

        if (joke != null) {
            Intent intent = new Intent(getActivity(), JokeDisplayerActivity.class);
            intent.putExtra(JokeDisplayerActivity.JOKE_EXTRA_KEY, joke.getDescription());
            this.startActivity(intent);
        }
    }

    private DialogData buildDialogData() {
        return new DialogData(R.string.app_name, R.string.fetchingJokesMessage, true, android.R.drawable.ic_dialog_info);
    }

    private void loadActions(View root) {
        Button button = (Button) root.findViewById(R.id.getJokeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveJoke();
            }
        });
    }

    private void retrieveJoke() {
        JokeProvider jokeProvider = new ServerJokeProvider(getActivity());
        Spinner categorySpinner = (Spinner) getActivity().findViewById(R.id.categorySpinner);
        String category = categorySpinner.getSelectedItem().toString();
        new JokeRequestorAsyncTask(this, jokeProvider).execute(category);
    }
}
