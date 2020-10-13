package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
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

    List<Movies> movieNames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovie = findViewById(R.id.rvMovie);
        movieNames = new ArrayList<>();

        final MovieAdapter adapterObj = new MovieAdapter(this,movieNames);
        rvMovie.setAdapter(adapterObj);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));



        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonInstance = json.jsonObject;
                try {
                    JSONArray localResults= jsonInstance.getJSONArray("results");
                    Log.i(TAG, "Result: "+ localResults.toString());
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
        });


    }
}