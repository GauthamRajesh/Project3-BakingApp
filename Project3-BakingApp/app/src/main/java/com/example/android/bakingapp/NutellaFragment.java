package com.example.android.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.bakingapp.utils.Connection;
import com.example.android.bakingapp.utils.JSONFetcher;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NutellaFragment extends Fragment {
    @BindView(R.id.nutella_ingredients_rv)
    RecyclerView ingredients;
    @BindView(R.id.nutella_steps_rv)
    RecyclerView steps;
    ArrayList<Step> nutellaSteps = new ArrayList<>();
    ArrayList<Ingredient> nutellaIngredients = new ArrayList<>();
    String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    IngredientsAdapter nutellaIngredientAdapter;
    StepsAdapter nutellaStepsAdapter;
    FragmentClickInterface clickListener;
    public static NutellaFragment newInstance(FragmentClickInterface listener) {
        NutellaFragment fragment = new NutellaFragment();
        fragment.clickListener = listener;
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            clickListener = (FragmentClickInterface) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentClickInterface.");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nutella, container, false);
        ButterKnife.bind(this, v);
        ingredients.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredients.setHasFixedSize(true);
        if(Connection.checkConnection(getContext())) {
            nutellaIngredients = JSONFetcher.getRecipeIngredients(url, getContext(), getString(R.string.nutella_pie));
            nutellaSteps = JSONFetcher.getRecipeSteps(url, getContext(), getString(R.string.nutella_pie));
        } else {
            Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }
        nutellaIngredientAdapter = new IngredientsAdapter(nutellaIngredients, getContext());
        nutellaStepsAdapter = new StepsAdapter(nutellaSteps, getContext(), clickListener);
        ingredients.setAdapter(nutellaIngredientAdapter);
        steps.setLayoutManager(new LinearLayoutManager(getContext()));
        steps.setHasFixedSize(true);
        steps.setAdapter(nutellaStepsAdapter);
        return v;
    }
}
