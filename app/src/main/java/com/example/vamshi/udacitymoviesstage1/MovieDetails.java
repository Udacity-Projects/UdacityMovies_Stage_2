package com.example.vamshi.udacitymoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieDetails extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static String GOOGLE_DEVELOPER_KEY = "";
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
    public DatabaseHelper db;
    public Boolean favedyea = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        in  = getIntent();
        ID = in.getStringExtra("ID");
        db = new DatabaseHelper(this);
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
        newTrailer.execute("https://api.themoviedb.org/3/movie/"+ ID +"/videos?api_key=<<INSERT API KEY>>&language=en-US");


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fav_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favedyea == true){
                    Toast.makeText(MovieDetails.this, "Deleted", Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.fav_border);

                }else{
                    fab.setImageResource(R.drawable.fav_selected);
                    db.addContact(new FavMovies(Integer.parseInt(in.getStringExtra("ID")), in.getStringExtra("ORIGINAL_TITLE")));

                    Snackbar.make(view, "Added to Favourites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    favedyea =true;
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


