package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movies;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity{

    private static final String UTUBE_API_KEY= "AIzaSyCrktFWohziOVFqt7txXndC2L00enyBSpU";
    private static final String VIDEOS_URL="https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView tvDName;
    TextView tvDSummary;
    RatingBar ratingBar;
    YouTubePlayerView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //why can't move above super???
        //they are instance variable => need to create instance first before initiating the attributes. 
        tvDName = findViewById(R.id.tvDName);
        tvDSummary = findViewById(R.id.tvDSummary);
        ratingBar = findViewById(R.id.ratingBar);
        video = findViewById(R.id.player);

        Movies movieItem = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvDName.setText(movieItem.getTitle());
        tvDSummary.setText(movieItem.getOverview());
        ratingBar.setRating((float) movieItem.getStars());


        AsyncHttpClient client2 =new AsyncHttpClient();
        //get movie id from the Movie obj.
        client2.get(String.format(VIDEOS_URL, movieItem.getMovideID()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    //store the result array (the part needed) into localResultV
                    // fetching the data from the json obj passed back from API.
                    JSONArray localResultV= json.jsonObject.getJSONArray("results");
                    if (localResultV.length()==0){
                        return;
                    }else{
                        //from the localResultV, we want to extract out the data linked with "key".
                       String youtubekey= localResultV.getJSONObject(0).getString("key");
                        Log.d("DetailActivity", youtubekey);
                        //calling and the "initilizeYouTube Function to link the appropriate video with the YT key.
                        initializeYoutube(youtubekey);
                    }
                } catch (JSONException e) {
                    Log.e("DetailActivity", "Fail to fet JSONARRAY",e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });

    }

    private void initializeYoutube(final String youtubekey) {
        video.initialize(UTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "onInitializationSuccess");
                youTubePlayer.cueVideo(youtubekey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "onInitializationFailure");
            }
        });


    }
}