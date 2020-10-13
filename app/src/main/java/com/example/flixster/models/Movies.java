package com.example.flixster.models;

import android.graphics.Movie;

import com.example.flixster.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movies {
    String posterPath;
    String title;
    String overview;
    String backdropPath;


    //constructor that takes in Json Object
    public Movies(JSONObject json_obj) throws JSONException {
        posterPath = json_obj.getString("poster_path");
        title= json_obj.getString("title");
        overview =json_obj.getString("overview");
        backdropPath=json_obj.getString(("backdrop_path"));
    }


    //making array of array?
    public static List<Movies> convertedJsonArray (JSONArray origJsonArray) throws JSONException {
        List<Movies> items = new ArrayList<>();
        for(int i=0;i<origJsonArray.length();i++){
            items.add(new Movies(origJsonArray.getJSONObject(i)));
        }
        return items;
    }


    //allow accessing the movie object after constructing it.
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342%s",backdropPath);
    }
}
