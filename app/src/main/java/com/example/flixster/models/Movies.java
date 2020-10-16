package com.example.flixster.models;

import android.graphics.Movie;

import com.example.flixster.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

//add the Parcel library
@Parcel

public class Movies {
    //the attributes that we care about in Json object
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    double stars;
    int movideID;

    //empty constructor needed by the Parcel Library.
    public Movies(){}


    //constructor that takes in Json Object (each object in the localResult array passed in from MainActivity).
    // each Json Object is constructed into a movie.
    public Movies(JSONObject json_obj) throws JSONException {
        posterPath = json_obj.getString("poster_path");
        title= json_obj.getString("title");
        overview =json_obj.getString("overview");
        backdropPath=json_obj.getString(("backdrop_path"));
        stars=json_obj.getDouble("vote_average");
        movideID=json_obj.getInt("id");
    }


    //making array of array? YES!!
    //static function: you can call the method by class (in other places)
    //we first take in the JsonArray of localresult from MainActivity to pass in to make the movie collection
    public static List<Movies> convertedJsonArray (JSONArray origJsonArray) throws JSONException {
        //create a collection array list to hold the all the movie objects
        List<Movies> items = new ArrayList<>();
        for(int i=0;i<origJsonArray.length();i++){
            //For each object in Json array, we will make a movie object using the constructor.
            items.add(new Movies(origJsonArray.getJSONObject(i)));
        }
        //return the movie collection to be added to the movie list in MainActivity
        return items;
    }


    //allow accessing the movie object after constructing it.
    //where are these getter methods being used? => in the ViewHolder class in Aadapter!!
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getStars(){
        return stars;
    }

    public int getMovideID() {
        return movideID;
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342%s",backdropPath);
    }
}
