package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.Adapter.MovieAdapter;
import com.example.flixster.models.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";



    //declaration
    List<Movies> movieNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovie = findViewById(R.id.container);
        //instantiation
        movieNames = new ArrayList<>();

        //set adapter and layout manager
        final MovieAdapter adapterObj = new MovieAdapter(this,movieNames);
        rvMovie.setAdapter(adapterObj);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));


        //create an AsyncHttpClient obj to fetch data via internet.
        AsyncHttpClient client = new AsyncHttpClient();
        //create a JsonHttpResponseHandler to be passed in as the parameter.
        JsonHttpResponseHandler jhrh_Object = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                // array (access by iteration on [index]) vs obj (access by key-value pair).
                //json file-> json obj-> json array [result] -> further unboxing in movie class to get the "title", "overview", etc
                // json in the parameter is the entire json file we receive
                //now we are creating an json obj to hold the obj in the file
                JSONObject jsonInstance = json.jsonObject;
                try {
                    //since we only care about the result array in the json object get it by the key of "results" and cast it (store it as) into array
                    //the array will be passed in to movie class for further process.
                    //[further processing can be done in main but for the sake of organization, we will put in the Movies Class]
                    JSONArray localResults= jsonInstance.getJSONArray("results");
                    Log.i(TAG, "Result: "+ localResults.toString());
                    //initiation of the moviewNames array.
                    //the Movies.convertedJsonArray is a static method defined in the Movie class (for static method, we can access function by calling the class. without needing an instance)
                    //we use the method ^^^ to pass in data (local result array) to the Movies Class.
                    movieNames.addAll(Movies.convertedJsonArray(localResults));
                    adapterObj.notifyDataSetChanged();
                    Log.i(TAG, "Movies: "+ movieNames.size());
                } catch (JSONException e) {
                    Log.e(TAG, "Hit Json exception",e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        };

        client.get(API_URL, jhrh_Object);



    }
}