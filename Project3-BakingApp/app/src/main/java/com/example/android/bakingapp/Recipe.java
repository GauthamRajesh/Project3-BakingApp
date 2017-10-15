package com.example.android.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

class Recipe implements Parcelable {
    private int id;
    private String name;
    private Ingredient[] ingredients;
    private Step[] steps;
    private int servings;
    private String image;
    public Recipe(int id, String name, Ingredient[] ingredients, Step[] steps, int servings, String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }
    private Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        steps = in.createTypedArray(Step.CREATOR);
        servings = in.readInt();
        image = in.readString();
    }
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }
        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    public Step[] getSteps() {
        return steps;
    }
    public void setSteps(Step[] steps) {
        this.steps = steps;
    }
    public int getServings() {
        return servings;
    }
    public void setServings(int servings) {
        this.servings = servings;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Ingredient[] getIngredients() {
        return ingredients;
    }
    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedArray(steps, flags);
        dest.writeInt(servings);
        dest.writeString(image);
    }
}
