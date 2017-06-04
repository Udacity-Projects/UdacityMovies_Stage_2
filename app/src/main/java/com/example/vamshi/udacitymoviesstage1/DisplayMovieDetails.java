package com.example.vamshi.udacitymoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DisplayMovieDetails extends AppCompatActivity {

    private ImageView movie_poster;
    private TextView movie_title;
    private TextView HSynopsis;
    private TextView movie_release;
    private TextView movie_rating;
    private TextView movie_synopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie_details);
        movie_poster = (ImageView)findViewById(R.id.movie_poster);
        movie_title = (TextView)findViewById(R.id.movie_title);
        HSynopsis = (TextView)findViewById(R.id.HSynopsis);
        movie_release = (TextView)findViewById(R.id.movie_release);
        movie_rating = (TextView)findViewById(R.id.movie_rating);
        movie_synopsis = (TextView)findViewById(R.id.movie_synopsis);

        Intent in = getIntent();

        Glide.with(this).load(in.getStringExtra("POSTER_URL").trim()).into(movie_poster);
        //Toast.makeText(this, in.getStringExtra("POSTER_URL"), Toast.LENGTH_SHORT).show();
        movie_title.setText(in.getStringExtra("ORIGINAL_TITLE"));
        movie_release.setText("(" + in.getStringExtra("RELEASE_DATE") + ")");
        movie_rating.setText("Rating: " + in.getStringExtra("RATING"));
        movie_synopsis.setText(in.getStringExtra("SYNOPSIS"));
    }
}
