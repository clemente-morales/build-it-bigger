package com.udacity.gradle.builditbigger.jokedisplayer.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.jokedisplayer.R;


/**
 *
 */
public class JokeDisplayerActivityFragment extends Fragment {

    private String joke;

    public JokeDisplayerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke_displayer, container, false);
        displayJoke(root);
        return root;
    }

    private void displayJoke(View rootView) {
        String joke = getJoke();
        TextView jokeTextView = (TextView) rootView.findViewById(R.id.jokeTextView);
        jokeTextView.setText(joke);
    }

    public String getJoke() {
        if (getActivity().getIntent().getExtras() == null) return "";

        return getActivity().getIntent().getExtras().getString(JokeDisplayerActivity.JOKE_EXTRA_KEY);
    }
}
