package com.example.flixster.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movies;

import org.parceler.Parcels;

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

    //create the frame/template/empty box/a View (according to the desired xml layout)
    //then pass the view to viewholder constructor to stuff in the info with the passed in view frame.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //we can choose out xml layout with 'R.layout.xxx'
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
        RelativeLayout container;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvSummary=itemView.findViewById(R.id.tvSummary);
            ivPoster=itemView.findViewById(R.id.ivPoster);
            container=itemView.findViewById(R.id.container);

        }


        public void bind(final Movies individual) {
            tvTitle.setText(individual.getTitle());
            tvSummary.setText(individual.getOverview());


            //change layout in respond to switching (portray -> landscape)
            if(adapterContext.getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
                //loading the image url into the position of ivPoster.
                //portray view
                Glide.with(adapterContext).load(individual.getPosterPath()).into(ivPoster);
            }else{
                //landscape view
                Glide.with(adapterContext).load(individual.getBackdropPath()).into(ivPoster);
            }

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(adapterContext,individual.getTitle(),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(adapterContext, DetailActivity.class);
                    //"ship" the entire movie obj
                    //since movie obj is a complex (non-primitive type), we need to wrap in parcel.
                    i.putExtra("movie", Parcels.wrap(individual));

                    adapterContext.startActivity(i);
                }
            });

        }
    }




}
