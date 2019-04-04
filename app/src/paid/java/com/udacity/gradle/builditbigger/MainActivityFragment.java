package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ProgressBar mLoadingSpinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate fragment layout
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // get reference to loading spinner
        mLoadingSpinner = root.findViewById(R.id.pb_loading_spinner);

        // paid flavor does not contain the ad
        // get reference to single UI button and set a click listener on it
        Button jokeButton = (Button) root.findViewById(R.id.b_tell_joke);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // show the loading spinner
                mLoadingSpinner.setVisibility(View.VISIBLE);

                // start new asynctask that communicates with backend
                MainActivity.retrieveJoke(getContext());

                // hide the loading spinner
                mLoadingSpinner.setVisibility(View.INVISIBLE);

            }
        });

        return root;
    }
}
