package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate fragment layout
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // only the free flavor contains the ad
        boolean isPaid = getResources().getBoolean(R.bool.isPaid);
        if (!isPaid) {

            // get reference to ad xml element
            AdView mAdView = (AdView) root.findViewById(R.id.adView);

            // make ad visible
            mAdView.setVisibility(View.VISIBLE);

            // create an ad request
            // check logcat for hashed device ID to test on a physical device
            // ex: AdRequest.Builder.addTestDevice("ABCDEF012345")
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);

        }

        return root;
    }
}
