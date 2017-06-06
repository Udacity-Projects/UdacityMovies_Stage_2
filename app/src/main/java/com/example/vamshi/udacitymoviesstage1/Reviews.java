package com.example.vamshi.udacitymoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Reviews extends AppCompatActivity {

    static ListView reviews_list;
    static List<String> reviews;
    static ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        reviews_list = (ListView)findViewById(R.id.reviews_list);
        reviews = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reviews);
        Intent in = getIntent();
        DownloadTask newTask = new DownloadTask();
        String reviewLink = "https://api.themoviedb.org/3/movie/" + in.getStringExtra("ID") + "/reviews?api_key=984eb4f6c311eabbe5fd13dc82c16ab7&language=en-US&page=1";
        DownloadTask.condition = "REVIEWS";
        newTask.execute(reviewLink.trim());
        reviews_list.setAdapter(adapter);
    }
}
