package com.example.vamshi.udacitymoviesstage1;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vamshi on 6/9/2017.
 */
class getTrailer extends AsyncTask<String,Void,String> {

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


        try {

            JSONObject root = new JSONObject(result);
            JSONArray resultArray = root.getJSONArray("results");
            JSONObject tempObject = resultArray.getJSONObject(0);

            MovieDetails.TrailerCode = tempObject.getString("key");
            MovieDetails.playerProgress.setVisibility(View.GONE);



        }catch (Exception E){

        }
        super.onPostExecute(s);
    }
}
