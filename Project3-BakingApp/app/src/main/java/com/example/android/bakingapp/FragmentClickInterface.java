package com.example.android.bakingapp;

import android.content.Context;

import java.util.ArrayList;

interface FragmentClickInterface {
    void onClick(Step s, String description, String videoURL, int position, Context c, ArrayList<Step> steps);
}
