package com.example.vamshi.udacitymoviesstage1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Button sortByButton;
    private CharSequence[] items = {"Popular", "Ratings", "Favourite"};
    private TextView SortedAs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sortByButton = (Button)findViewById(R.id.sortButton);
        SortedAs = (TextView)findViewById(R.id.Sorted_AS);
        ActionBar action = this.getSupportActionBar();
        if(action!=null){
            action.setDisplayHomeAsUpEnabled(true);
        }


        sortByButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings.this);
                mBuilder.setTitle("Choose a Sorting Option").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                dialog.dismiss();
                                MainActivity.SortBy = "POPULAR";
                                MainActivity.LoadUI(MainActivity.SortBy);
                                SortedAs.setText("Sorted As Per : - Popularity");
                                Toast.makeText(Settings.this, "Sorted As Popularity", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                dialog.dismiss();
                                MainActivity.SortBy = "RATINGS";
                                MainActivity.LoadUI(MainActivity.SortBy);
                                SortedAs.setText("Sorted As Per : - Ratings");
                                Toast.makeText(Settings.this, "Sorted As Ratings", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                dialog.dismiss();
                                Toast.makeText(Settings.this, "Sorted As Favorites", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    }
                });

                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }
}
