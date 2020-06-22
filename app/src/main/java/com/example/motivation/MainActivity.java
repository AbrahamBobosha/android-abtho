package com.example.motivation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.motivation.adapter.MoviesAdapter;
import com.example.motivation.api.API;
import com.example.motivation.api.MovieAPI;
import com.example.motivation.model.Movie;
import com.example.motivation.model.MovieResponse;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView new_RecyclerView;
    RecyclerView popular_RecyclerView;
    MoviesAdapter moviesAdapter;//for New movies
    MoviesAdapter popularAdapter; //for popular movies
    ArrayList<Movie> retro_results = new ArrayList<> ( ); // New

    ArrayList<Movie> popular_results = new ArrayList<> ( );

    ProgressBar progressBar;
    LinearLayout main_layout;

    int show_flag = 0;

    void showProgressBar() {
        progressBar.setVisibility (View.VISIBLE);
        main_layout.setVisibility (View.GONE);
    }

    void hideProgressBar() {
        if (show_flag == 2) {
            progressBar.setVisibility (View.GONE);
            main_layout.setVisibility (View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        main_layout = findViewById (R.id.main_layout);
        progressBar = findViewById (R.id.progress_bar);
        Sprite doubleBounce = new DoubleBounce ( );
        progressBar.setIndeterminateDrawable (doubleBounce);

        showProgressBar ( );

        popular_RecyclerView = findViewById (R.id.popular_recyclerview);
        popularAdapter = new MoviesAdapter (getApplicationContext ( ), popular_results);
        popular_RecyclerView.setAdapter (popularAdapter);

        MovieAPI service = API.getInstance ( ).getMovieAPI ( );

        service.getUpcomingMovies ("a7fc563ba6989aec1e19d62d2d1985c9").enqueue (new Callback<MovieResponse> ( ) {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                retro_results = response.body ( ).getResults ( );
                moviesAdapter = new MoviesAdapter (getApplicationContext ( ), retro_results);
                new_RecyclerView.setAdapter (moviesAdapter);
                show_flag++;
                hideProgressBar ( );
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d ("Something is worng", t.getMessage ( ));
            }

        });

        service.getPopularMovies ("a7fc563ba6989aec1e19d62d2d1985c9").enqueue (new Callback<MovieResponse> ( ) {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                popular_results = response.body ( ).getResults ( );
                popularAdapter = new MoviesAdapter (getApplicationContext ( ), popular_results);
                popular_RecyclerView.setAdapter (popularAdapter);
                show_flag++;
                hideProgressBar ( );
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d ("Something is wrong", t.getMessage ( ));
            }

        });

        new_RecyclerView = findViewById (R.id.new_recyclerview);
        moviesAdapter = new MoviesAdapter (getApplicationContext ( ), retro_results);
        new_RecyclerView.setAdapter (moviesAdapter);


    }
}
