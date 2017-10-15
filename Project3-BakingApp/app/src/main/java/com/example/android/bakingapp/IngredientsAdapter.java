package com.example.android.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private List<Ingredient> ingredients;
    private Context c;
    public IngredientsAdapter(List<Ingredient> ingredients, Context c) {
        this.ingredients = ingredients;
        this.c = c;
    }
    class IngredientsViewHolder extends RecyclerView.ViewHolder {
        TextView quantityTextView;
        TextView measureTextView;
        TextView nameTextView;
        public IngredientsViewHolder(View itemView) {
            super(itemView);
            quantityTextView = (TextView) itemView.findViewById(R.id.ingredient_quantity);
            measureTextView = (TextView) itemView.findViewById(R.id.ingredient_measure);
            nameTextView = (TextView) itemView.findViewById(R.id.ingredient_description);
        }
    }
    @Override
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        c = parent.getContext();
        View v = LayoutInflater.from(c).inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientsViewHolder(v);
    }
    @Override
    public void onBindViewHolder(IngredientsAdapter.IngredientsViewHolder holder, int position) {
        Ingredient i = ingredients.get(position);
        holder.quantityTextView.setText(String.valueOf(i.getQuantity()));
        String measure_original = i.getMeasure().toLowerCase();
        holder.measureTextView.setText(String.format("%s%s", Character.toUpperCase(measure_original.charAt(0)), measure_original.substring(1)));
        holder.nameTextView.setText(i.getIngredientName());
    }
    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
