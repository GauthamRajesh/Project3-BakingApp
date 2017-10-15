package com.example.android.bakingapp;

public class Ingredient {
    private float quantity;
    private String measure;
    private String ingredientName;
    public Ingredient(float quantity, String measure, String ingredientName) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredientName = ingredientName;
    }
    public String getIngredientName() {
        return ingredientName;
    }
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    public String getMeasure() {
        return measure;
    }
    public void setMeasure(String measure) {
        this.measure = measure;
    }
    public float getQuantity() {
        return quantity;
    }
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
