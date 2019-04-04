package com.sommerengineering.androidlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_jokes);

        // extract joke string from intent
        Intent intent = getIntent();
        String joke = intent.getStringExtra(getString(R.string.JOKE_KEY));

        // get reference to textview and display joke
        TextView textView = findViewById(R.id.tv_display_joke);
        textView.setText(joke);
    }
}
