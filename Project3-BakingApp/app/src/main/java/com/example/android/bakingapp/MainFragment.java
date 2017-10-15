package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    @BindView(R.id.pieCard)
    CardView pieCard;

    @BindView(R.id.yellowCakeCard)
    CardView yellowCakeCard;

    @BindView(R.id.brownieCard)
    CardView brownieCard;

    @BindView(R.id.cheeseCakeCard)
    CardView cheeseCakeCard;

    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
    }

    public MainFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container);
        ButterKnife.bind(this, rootView);
        pieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), NutellaCake.class));
            }
        });
        yellowCakeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), YellowCake.class));
            }
        });
        brownieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Brownie.class));
            }
        });
        cheeseCakeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), CheeseCake.class));
            }
        });
        return rootView;
    }
}
