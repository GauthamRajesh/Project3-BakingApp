package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class NutellaCake extends AppCompatActivity implements FragmentClickInterface {
    NutellaFragment fragment;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutella_cake);
        fragment = NutellaFragment.newInstance(this);
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.nutella_container, fragment).commit();
    }
    @Override
    public void onClick(Step s, String description, String videoURL, int position, Context c, ArrayList<Step> steps) {
        launchDetailActivity(s, description, videoURL, position, c, steps);
    }
    private void launchDetailActivity(Step s, String description, String videoURL, int position, Context c, ArrayList<Step> steps) {
        b = new Bundle();
        b.putParcelable("step", s);
        b.putString("description", description);
        b.putString("videoURL", videoURL);
        b.putInt("position", position);
        b.putParcelableArrayList("steps", steps);
        Intent userClick = new Intent(c, DetailActivity.class);
        userClick.putExtras(b);
        c.startActivity(userClick);
    }
}
