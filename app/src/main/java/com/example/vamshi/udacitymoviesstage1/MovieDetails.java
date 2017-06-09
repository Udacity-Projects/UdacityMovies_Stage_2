package com.example.vamshi.udacitymoviesstage1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MovieDetails extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static String GOOGLE_DEVELOPER_KEY = "AIzaSyA4Hk9f40CdF69TemQGLlThxieLJbYE6bE";
    private ImageView movie_poster_smallN;
    private TextView movie_titleN;
    private TextView movie_ratingN;
    private TextView synopsisHead;
    private TextView movie_synopsisN;
    private TextView movie_releaseN;
    public String ID;
    static YouTubePlayerView myPlayer;
    static String TrailerCode="";
    static ProgressBar playerProgress;
    public Intent in;
    Realm realm;
    public Boolean faved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        in  = getIntent();
        ID = in.getStringExtra("ID");
        Realm.init(this);
        realm= Realm.getDefaultInstance();
        Toast.makeText(this, ID, Toast.LENGTH_SHORT).show();
        myPlayer = (YouTubePlayerView) findViewById(R.id.youtube_player);
        movie_poster_smallN = (ImageView)findViewById(R.id.movie_poster_smallN);
        movie_titleN = (TextView)findViewById(R.id.movie_titleN);
        movie_ratingN = (TextView)findViewById(R.id.movie_ratingN);
        synopsisHead = (TextView)findViewById(R.id.synopsisHead);
        movie_synopsisN = (TextView)findViewById(R.id.movie_synopsisN);
        movie_releaseN = (TextView)findViewById(R.id.movie_releaseN);
        playerProgress = (ProgressBar)findViewById(R.id.player_load);
        myPlayer.initialize(GOOGLE_DEVELOPER_KEY, this);

        playerProgress.setVisibility(View.VISIBLE);
//        myPlayer.setVisibility(View.GONE);
        getTrailer newTrailer = new getTrailer();
        newTrailer.execute("https://api.themoviedb.org/3/movie/"+ ID +"/videos?api_key=984eb4f6c311eabbe5fd13dc82c16ab7&language=en-US");


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fav_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(faved == true){
                    Toast.makeText(MovieDetails.this, "Booo Deleted from Favs", Toast.LENGTH_SHORT).show();

                }else{
                    fab.setImageResource(R.drawable.fav_selected);
                    realm.beginTransaction();
                    MovieObject faved = realm.createObject(MovieObject.class);
                    faved.setMovieID(in.getStringExtra("ID"));
                    faved.setMovieOriginalTitle(in.getStringExtra("ORIGINAL_TITLE"));
                    faved.setMovieRating(in.getStringExtra("RATING"));
                    faved.setMovieReleaseDate(in.getStringExtra("RELEASE_DATE"));
                    faved.setMovieSynopsis(in.getStringExtra("SYNOPSIS"));
                    faved.setMovieURL(in.getStringExtra("POSTER_URL"));
                    MainActivity.favMovies.add(faved);
                    realm.commitTransaction();
                    Snackbar.make(view, "Added to Favourites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

        FloatingActionButton fabby = (FloatingActionButton) findViewById(R.id.trailer_button);
        fabby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MovieDetails.this, Reviews.class);

                in.putExtra("ID", ID);
                startActivity(in);
            }
        });



        Glide.with(this).load(in.getStringExtra("POSTER_URL").trim()).into(movie_poster_smallN);
        movie_titleN.setText(in.getStringExtra("ORIGINAL_TITLE"));
        movie_ratingN.setText("Rating: " + in.getStringExtra("RATING"));
        movie_synopsisN.setText(in.getStringExtra("SYNOPSIS"));
        movie_releaseN.setText("Released on: " + in.getStringExtra("RELEASE_DATE"));



    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if(null== youTubePlayer) return;

        // Start buffering
        if (!b) {


             youTubePlayer.cueVideo(TrailerCode);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        Toast.makeText(this, "Failed To Initialize", Toast.LENGTH_SHORT).show();

    }
}


