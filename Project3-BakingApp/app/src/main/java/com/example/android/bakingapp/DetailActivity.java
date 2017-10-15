package com.example.android.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_detail);
        Bundle b = getIntent().getExtras();
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
    }
}
