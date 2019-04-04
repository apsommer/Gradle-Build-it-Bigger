package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private InterstitialAd mInterstitialAd;
    private ProgressBar mLoadingSpinner;

    public MainActivityFragment() {
    }

    // turn off the progress bar loading indicator
    @Override
    public void onResume() {

        // hide the loading spinner
        mLoadingSpinner.setVisibility(View.INVISIBLE);

        // proceed with standard framework
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate fragment layout
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // get reference to loading spinner
        mLoadingSpinner = root.findViewById(R.id.pb_loading_spinner);

        // initialize the Mobile Ads SDK with this app specific ID
        MobileAds.initialize(getContext(), getString(R.string.admob_app_id));

        // display banner ad
        displayBannerAd(root);

        // setup interstitial ad
        setupInterstitialAd();

        // get reference to single UI button and set a click listener on it
        Button jokeButton = (Button) root.findViewById(R.id.b_tell_joke);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // show the interstitial ad
                mInterstitialAd.show();

                // show the loading spinner
                mLoadingSpinner.setVisibility(View.VISIBLE);

                // start new asynctask that communicates with backend
                MainActivity.retrieveJoke(getContext());

            }
        });

        return root;
    }

    // Udacity provided code for Banner Ad
    private void displayBannerAd(View root) {

        // get reference to ad xml element
        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        // create an ad request
        // check logcat for hashed device ID to test on a physical device
        // ex: AdRequest.Builder.addTestDevice("ABCDEF012345")
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    // setup Interstitial Ad
    private void setupInterstitialAd() {

        // new interstitial ad
        mInterstitialAd = new InterstitialAd(getContext());

        // "test ad unit ID" provided by Google
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        // load test ad
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        // set listener to log when ad is finished loading
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("~~", "ad loaded.");
            }
        });
    }

}
