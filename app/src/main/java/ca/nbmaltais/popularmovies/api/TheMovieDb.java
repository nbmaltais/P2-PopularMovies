package ca.nbmaltais.popularmovies.api;

import ca.nbmaltais.popularmovies.api.models.configuration.Configuration;
import ca.nbmaltais.popularmovies.api.models.movie.Movies;
import ca.nbmaltais.popularmovies.api.models.movie.Reviews;
import ca.nbmaltais.popularmovies.api.models.movie.Videos;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Nicolas on 2015-07-21.
 */
public interface TheMovieDb
{
    @GET("/configuration")
    Configuration getConfiguration();

    @GET("/movie/top_rated")
    Movies getTopRatedMovies(@Query("page") int page);

    /*@GET("/movie/latest")
    Movies getLatestMovies();*/

    @GET("/movie/popular")
    Movies getPopularMovies();

    @GET("/movie/now_playing")
    Movies getNowPlayingMovies();

    @GET("/movie/upcoming")
    Movies getUpcomingMovies(@Query("page") int page);

    /**
     *
     * @param sort_by : popularity, release_date revenue primary_release_date original_title vote_average vote_count . [desc|asc]

     * @param page
     * @return
     */
    @GET("/discover/movie")
    Movies discoverMovies( @Query("sort_by") String sort_by, @Query("page") int page );

    @GET("/discover/movie&sort_by=vote_average.desc")
    Movies discoverTopRatedMoviesMovies( @Query("page") int page, @Query("vote_count") int vote_count );

    @GET("/movie/{id}/videos")
    Videos getVideos(@Path("id") int id);

    @GET("/movie/{id}/reviews")
    Reviews getReviews(@Path("id") int id);
}
