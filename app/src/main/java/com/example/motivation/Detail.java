package com.example.motivation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.motivation.api.API;
import com.example.motivation.api.MovieAPI;
import com.example.motivation.model.Movie;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    TextView release_year,  plot;
    ImageView poster;

    TextView director;
    TextView writers;

    ProgressBar detail_progressbar;
    LinearLayout detail_info_layout;
    boolean fav_button_status = false;
    boolean show_snack_bar_add = true;


    Movie detailed_movie = new Movie ( );

    public static String getFormattedList(List<String> names) {
        StringBuilder namesStr = new StringBuilder ( );
        for (String name : names) {
            namesStr = namesStr.length ( ) > 0 ? namesStr.append (", ").append (name) : namesStr.append (name);
        }
        return namesStr.toString ( );
    }


    void showDetailProgress() {
        detail_progressbar.setVisibility (View.VISIBLE);
        detail_info_layout.setVisibility (View.GONE);
    }

    void showDetailView() {
        detail_info_layout.setVisibility (View.VISIBLE);
        detail_progressbar.setVisibility (View.GONE);
    }

    // You can use this for the back button form the detail list
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ( )) {
            case android.R.id.home:
                finish ( );
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail);

        getSupportActionBar ( ).setHomeButtonEnabled (true);
        getSupportActionBar ( ).setDisplayHomeAsUpEnabled (true);

        Intent intent = getIntent ( );
        final long detailed = intent.getLongExtra ("detailed", 0);

        detail_info_layout = findViewById (R.id.detail_info_layout);
        detail_progressbar = findViewById (R.id.detail_progress_bar);
        Sprite doubleBounce = new DoubleBounce ( );
        detail_progressbar.setIndeterminateDrawable (doubleBounce);

        showDetailProgress ( );


        writers = findViewById (R.id.detail_writers);
        director = findViewById (R.id.detail_director);
        release_year = findViewById (R.id.detail_year);
        poster = findViewById (R.id.detailed_movie_poster);
        plot = findViewById (R.id.detail_movie_plot);


        MovieAPI service = API.getInstance ( ).getMovieAPI ( );

        service.getMovieDetail (detailed, "a7fc563ba6989aec1e19d62d2d1985c9", "credits").enqueue (new Callback<Movie> ( ) {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                detailed_movie = response.body ( );


                List<String> directors = new ArrayList<> ( );

                release_year.setText (response.body ( ).getRelease_date ( ).substring (0, 4));

                plot.setText (response.body ( ).getOverview ( ));

                Glide.with (getApplicationContext ( )).load ("https://image.tmdb.org/t/p/w154" + response.body ( ).getImg_url ( )).into (poster);



                setTitle (response.body ( ).getTitle ( ));

                Log.d ("all writers", getFormattedList (directors));

                writers.setText (getFormattedList (directors));

                showDetailView ( );

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d ("cancer err", t.getMessage ( ));
            }
        });

    }


}
