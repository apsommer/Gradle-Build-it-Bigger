package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sommerengineering.javalib.JavaJokes;
import com.sommerengineering.androidlib.DisplayJokesActivity;

public class MainActivity extends AppCompatActivity {

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        // create new object of JavaJokes class
        JavaJokes javaJokes = new JavaJokes();

        // get joke string from JavaJokes object
        String jokeFromJavaLibrary = javaJokes.supplyJoke();

        // intent to start activity from android library
        Intent intent = new Intent(getApplicationContext(), DisplayJokesActivity.class);

        // add the joke to the intent and start the android library activity
        intent.putExtra(DisplayJokesActivity.JOKE_KEY, jokeFromJavaLibrary);
        startActivity(intent);
    }
}
