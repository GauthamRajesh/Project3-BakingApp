package com.example.android.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
    private ArrayList<Step> steps;
    private Context c;
    private FragmentClickInterface listener;
    StepsAdapter(ArrayList<Step> steps, Context c, FragmentClickInterface listener) {
        this.steps = steps;
        this.c = c;
        this.listener = listener;
    }
    class StepsViewHolder extends RecyclerView.ViewHolder {
        TextView shortDescriptionView;
        StepsViewHolder(View itemView) {
            super(itemView);
            shortDescriptionView = (TextView) itemView.findViewById(R.id.recipeStepDescription);
        }
    }
    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        c = parent.getContext();
        return new StepsViewHolder(LayoutInflater.from(c).inflate(R.layout.step_list_item, parent, false));
    }
    @Override
    public void onBindViewHolder(final StepsAdapter.StepsViewHolder holder, int position) {
        final Step s = steps.get(position);
        final String shortDescription = s.getShortDescription();
        final String videoURL = s.getVideoUrl();
        holder.shortDescriptionView.setText(shortDescription);
        holder.shortDescriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(s, s.getDescription(), videoURL, holder.getAdapterPosition(), c, steps);
            }
        });

    }
    @Override
    public int getItemCount() {
        return steps.size();
    }
}
