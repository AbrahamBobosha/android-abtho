package com.example.motivation.api;

import com.example.motivation.model.Movie;
import com.example.motivation.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// The service Getting End points
public interface MovieAPI {

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);


    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") long movie_id, @Query("api_key") String apiKey, @Query("append_to_response")String word);


}
