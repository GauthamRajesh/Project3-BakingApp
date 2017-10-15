package com.example.android.bakingapp.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.android.bakingapp.Ingredient;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONFetcher {
    public static ArrayList<Step> getRecipeSteps(String url, final Context c, final String recipeName) {
        final ArrayList<Step> recipeSteps = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request stepRequest = new Request.Builder().url(url).build();
        client.newCall(stepRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
                Toast.makeText(c, R.string.network_error, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonResponse = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(jsonResponse);
                    JSONObject recipeObject = jsonArray.getJSONObject(RecipeNumber.getRecipeId(recipeName));
                    JSONArray steps = recipeObject.getJSONArray(c.getString(R.string.steps));
                    for(int i = 0; i < steps.length(); i++) {
                        JSONObject detailStep = steps.getJSONObject(i);
                        String shortDescription = detailStep.getString("shortDescription");
                        String description = detailStep.getString("description");
                        String videoURL = detailStep.getString("videoURL");
                        String thumbnailURL = detailStep.getString("thumbnailURL");
                        Step step = new Step(shortDescription, description, videoURL, thumbnailURL);
                        recipeSteps.add(step);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return recipeSteps;
    }
    public static ArrayList<Ingredient> getRecipeIngredients(String url, final Context c, final String recipeName) {
        final ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request ingredientRequest = new Request.Builder().url(url).build();
        client.newCall(ingredientRequest).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
                Toast.makeText(c, R.string.network_error, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonResponse = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(jsonResponse);
                    JSONObject recipeObject = jsonArray.getJSONObject(RecipeNumber.getRecipeId(recipeName));
                    JSONArray ingredients = recipeObject.getJSONArray(c.getString(R.string.ingredients));
                    for(int i = 0; i < ingredients.length(); i++) {
                        JSONObject detailIngredient = ingredients.getJSONObject(i);
                        float quantity = (float) detailIngredient.getDouble("quantity");
                        String measure = detailIngredient.getString("measure");
                        String ingredientName = detailIngredient.getString("ingredient");
                        Ingredient ingredient = new Ingredient(quantity, measure, ingredientName);
                        recipeIngredients.add(ingredient);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return recipeIngredients;
    }
}