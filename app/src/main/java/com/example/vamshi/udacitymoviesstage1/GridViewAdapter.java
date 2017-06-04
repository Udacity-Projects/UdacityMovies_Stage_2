package com.example.vamshi.udacitymoviesstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Vamshi on 6/1/2017.
 */

public class GridViewAdapter extends BaseAdapter {


    private List<MovieObject> Movies;
    private Context context;
    private LayoutInflater inflater;

    public GridViewAdapter(List<MovieObject> movies, Context context) {
        Movies = movies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Movies.size();
    }

    @Override
    public Object getItem(int position) {
        Movies.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView = convertView;

        if(convertView==null){
            inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.single_item,null);
        }

        ImageView myImageView = (ImageView)gridView.findViewById(R.id.myImageView);
        TextView myTextView = (TextView)gridView.findViewById(R.id.myMovieTitle);
        MovieObject tempObject = Movies.get(position);
        Glide.with(context).load(tempObject.getMovieURL().trim()).into(myImageView);
        myTextView.setText(tempObject.getMovieTitle());

        return gridView;
    }
}
