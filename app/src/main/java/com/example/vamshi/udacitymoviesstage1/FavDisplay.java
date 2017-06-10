package com.example.vamshi.udacitymoviesstage1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavDisplay extends AppCompatActivity {

    TextView head;
    List<String> tiles;
    ListView favList;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_display);
        head= (TextView)findViewById(R.id.textHead);
        tiles = new ArrayList<>();
        favList = (ListView)findViewById(R.id.listoffavs);
        db = new DatabaseHelper(this);
        List<FavMovies> contacts = db.getAllContacts();

        for (FavMovies cn : contacts) {
            tiles.add(cn.getMovieName());

        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tiles);
        favList.setAdapter(adapter);

    }
}
