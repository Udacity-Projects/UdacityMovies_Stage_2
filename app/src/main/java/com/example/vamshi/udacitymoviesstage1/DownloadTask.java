package com.example.vamshi.udacitymoviesstage1;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.vamshi.udacitymoviesstage1.MainActivity.Movies;

/**
 * Created by Vamshi on 5/31/2017.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {

    String result;


    @Override
    protected String doInBackground(String... params) {

        result = "";
        URL link;
        HttpURLConnection myconnection = null;

        try {
            link = new URL(params[0]);
            myconnection = (HttpURLConnection)link.openConnection();
            InputStream in = myconnection.getInputStream();
            InputStreamReader myStreamReader = new InputStreamReader(in);
            int data = myStreamReader.read();
            while(data!= -1){
                char current = (char)data;
                result+= current;
                data = myStreamReader.read();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        MainActivity.myProgress.setVisibility(View.GONE);
        MainActivity.myGridView.setVisibility(View.VISIBLE);


        try {

            JSONObject root = new JSONObject(result);
            JSONArray resultArray = root.getJSONArray("results");
            for(int i=0; i<=resultArray.length(); i++){

                JSONObject tempObject = resultArray.getJSONObject(i);

                String imageUrl = " http://image.tmdb.org/t/p/w185/" + tempObject.getString("poster_path");
                MovieObject newMovie = new MovieObject(tempObject.getString("title"),
                                                        imageUrl,
                                                        tempObject.getString("overview"),
                                                        tempObject.getString("vote_average"),
                                                        tempObject.getString("release_date"),
                                                        tempObject.getString("original_title"));
                Movies.add(newMovie);
                MainActivity.myGridView.setAdapter(MainActivity.myAdapter);
                MainActivity.myAdapter.notifyDataSetChanged();
                //

            }
            //MainActivity.myGridView.setAdapter(MainActivity.myAdapter);




            } catch (JSONException e1) {

            e1.printStackTrace();
        }



        super.onPostExecute(s);
    }
}
