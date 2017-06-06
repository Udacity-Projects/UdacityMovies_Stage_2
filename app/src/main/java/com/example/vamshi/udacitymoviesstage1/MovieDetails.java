package com.example.vamshi.udacitymoviesstage1;

import android.content.Intent;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {


    private ImageView movie_poster_smallN;
    private TextView movie_titleN;
    private TextView movie_ratingN;
    private TextView synopsisHead;
    private TextView movie_synopsisN;
    private TextView movie_releaseN;
    public String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent in  = getIntent();
        ID = in.getStringExtra("ID");


        movie_poster_smallN = (ImageView)findViewById(R.id.movie_poster_smallN);
        movie_titleN = (TextView)findViewById(R.id.movie_titleN);
        movie_ratingN = (TextView)findViewById(R.id.movie_ratingN);
        synopsisHead = (TextView)findViewById(R.id.synopsisHead);
        movie_synopsisN = (TextView)findViewById(R.id.movie_synopsisN);

        movie_releaseN = (TextView)findViewById(R.id.movie_releaseN);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fav_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageResource(R.drawable.fav_selected);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
}
