package com.example.vamshi.udacitymoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static GridView myGridView;
    static List<MovieObject> Movies;
    static GridViewAdapter myAdapter;
    static ProgressBar myProgress;
    static String SortBy = "POPULAR";
    static List<MovieObject> favMovies;
    static GridViewAdapter favAdapter;
    public static DatabaseHelper db;
    public static List<String> movieTiles;
    public static String MOVIEDBKEY = "984eb4f6c311eabbe5fd13dc82c16ab7" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        Movies = new ArrayList<>();
        favMovies = new ArrayList<>();
        movieTiles = new ArrayList<>();
        myProgress = (ProgressBar)findViewById(R.id.progressDialog);
        myProgress.setVisibility(View.VISIBLE);
        myGridView = (GridView) findViewById(R.id.myGridLayout);
        myGridView.setVisibility(View.GONE);

        // CHECKING NETWORK CONNECTION
        if(!isNetworkAvailable()){
            Toast.makeText(this, "Please Connect To The Internet!", Toast.LENGTH_SHORT).show();
            myProgress.setVisibility(View.GONE);
            SortBy = "FAVS";
            LoadUI(SortBy);
        }else{
            LoadUI(SortBy);
        }

        myAdapter = new GridViewAdapter(Movies,this);
        favAdapter = new GridViewAdapter(favMovies,this);

        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(SortBy == "FAVS"){

                    MovieObject temo = favMovies.get(position);

                    Intent in = new Intent(MainActivity.this, MovieDetails.class);
                    in.putExtra("POSTER_URL", temo.getMovieURL());
                    in.putExtra("ORIGINAL_TITLE", temo.getMovieOriginalTitle());
                    in.putExtra("SYNOPSIS", temo.getMovieSynopsis());
                    in.putExtra("RATING", temo.getMovieRating());
                    in.putExtra("RELEASE_DATE", temo.getMovieReleaseDate());
                    in.putExtra("ORIGINAL_TITLE", temo.getMovieOriginalTitle());
                    in.putExtra("ID", temo.getMovieID());
                    startActivity(in);
                }
                else{
                    MovieObject temo = Movies.get(position);

                    Intent in = new Intent(MainActivity.this, MovieDetails.class);
                    in.putExtra("POSTER_URL", temo.getMovieURL());
                    in.putExtra("ORIGINAL_TITLE", temo.getMovieOriginalTitle());
                    in.putExtra("SYNOPSIS", temo.getMovieSynopsis());
                    in.putExtra("RATING", temo.getMovieRating());
                    in.putExtra("RELEASE_DATE", temo.getMovieReleaseDate());
                    in.putExtra("ORIGINAL_TITLE", temo.getMovieOriginalTitle());
                    in.putExtra("ID", temo.getMovieID());
                    startActivity(in);
                }




            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    static void LoadUI(String sort) {
        String s= sort;
        DownloadTask newTask = new DownloadTask();
        Movies.clear();
        if(s == "POPULAR"){
            DownloadTask.condition = "REGULAR";
            newTask.execute("https://api.themoviedb.org/3/movie/popular?api_key="+MOVIEDBKEY+"&language=en-US&page=1");
            myGridView.setAdapter(myAdapter);
        }
        if(s == "RATINGS"){
            DownloadTask.condition = "REGULAR";
            newTask.execute("https://api.themoviedb.org/3/movie/top_rated?api_key="+MOVIEDBKEY+"&language=en-US&page=1");
            myGridView.setAdapter(myAdapter);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.Settings: {
                Intent in = new Intent(MainActivity.this, Settings.class);
                startActivity(in);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}


