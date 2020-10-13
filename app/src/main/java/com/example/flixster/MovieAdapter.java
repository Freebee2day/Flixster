package com.example.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Movie;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.models.Movies;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context adapterContext;
    List<Movies> list;

    public MovieAdapter(Context adapterContext, List<Movies> movies) {
        this.adapterContext = adapterContext;
        this.list = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieData= LayoutInflater.from(adapterContext).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieData);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies individual= list.get(position);
        holder.bind(individual);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }







    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvSummary;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvSummary=itemView.findViewById(R.id.tvSummary);
            ivPoster=itemView.findViewById(R.id.ivPoster);



        }


        public void bind(Movies individual) {
            tvTitle.setText(individual.getTitle());
            tvSummary.setText(individual.getOverview());

            if(adapterContext.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
                //loading the image url into the position of ivPoster.
                //portray view
                Glide.with(adapterContext).load(individual.getPosterPath()).into(ivPoster);
            }else{
                //landscape view
                Glide.with(adapterContext).load(individual.getBackdropPath()).into(ivPoster);
            }
        }
    }




}
