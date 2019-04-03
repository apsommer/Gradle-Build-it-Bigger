package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.sommerengineering.androidlib.DisplayJokesActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    // this creates an object of the MyEndpoint class
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {

        if (myApiService == null) {

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)


                    // options for running against a local server
                    // 10.0.2.2 is the localhost IP for Android emulator
                    // turn off compression when running with local server
                    .setRootUrl("http://192.168.200.3:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {

                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            // build service
            myApiService = builder.build();
        }

        // get context and string from passed inputs
        context = pairs[0].first;
        String name = pairs[0].second;

        // run the api method and pass result to onPostExecute()
        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    // display the retrieved result of the asynctask in a toast
    @Override
    protected void onPostExecute(String result) {

        // intent to start activity from android library
        Intent intent = new Intent(context.getApplicationContext(), DisplayJokesActivity.class);

        // add the joke to the intent and start the android library activity
        intent.putExtra(DisplayJokesActivity.JOKE_KEY, result);
        context.startActivity(intent);
    }
}
