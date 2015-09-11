package ca.nbmaltais.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.DrawableRes;

import ca.nbmaltais.popularmovies.api.TheMovieDb;
import ca.nbmaltais.popularmovies.api.models.movie.Movie;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieContentValues;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

import static ca.nbmaltais.popularmovies.APIKEY.getApiKey;

/**
 * Created by Nicolas on 2015-07-24.
 */
public class Utils
{
    private static final String PREF_MOVIESYNC_TIMESTAMP = "MOVIESYNC_TIMESTAMP";

    public static String makeImageUrl500px( String relativePath )
    {
        return "http://image.tmdb.org/t/p/w500" + relativePath;
    }

    public static String makeImageUrl185px( String relativePath )
    {
        return "http://image.tmdb.org/t/p/w185" + relativePath;
    }


    public static TheMovieDb createTheMovieDbService()
    {
        RequestInterceptor requestInterceptor = new RequestInterceptor()
        {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("api_key", getApiKey());
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org/3")
                .setRequestInterceptor(requestInterceptor)
                .build();

        return restAdapter.create(TheMovieDb.class);
    }


    static public MovieContentValues movieToContentValues(Movie movie, boolean setID)
    {
        MovieContentValues values = new MovieContentValues();

        String originalTitle = movie.getOriginalTitle();
        String title = movie.getTitle();

        if(setID)
        {
            values.values().put(MovieColumns._ID, movie.getId());
        }
        values.putBackdropPath(movie.getBackdropPath());
        values.putOriginalTitle(originalTitle != null ? originalTitle : "");
        values.putOverview(movie.getOverview());
        values.putPopularity(movie.getPopularity());
        //values.putMoviedbId(movie.getId());
        values.putPosterPath(movie.getPosterPath());
        String releaseDate = movie.getReleaseDate();
        values.putReleaseDate( releaseDate!=null ? releaseDate : "");
        values.putTitle(title!=null?title:"");
        values.putVoteAverage(movie.getVoteAverage());
        values.putVoteCount(movie.getVoteCount());

        return values;
    }

    static public Movie cursorToMovie( MovieCursor cursor)
    {
        Movie movie = new Movie();

        movie.setBackdropPath(cursor.getBackdropPath());
        movie.setOriginalTitle(cursor.getOriginalTitle());
        movie.setOverview(cursor.getOverview());
        movie.setPopularity(cursor.getPopularity());
        movie.setId((int) cursor.getId());
        movie.setPosterPath(cursor.getPosterPath());
        movie.setReleaseDate(cursor.getReleaseDate());
        movie.setTitle(cursor.getTitle());
        movie.setVoteAverage(cursor.getVoteAverage());
        movie.setVoteCount(cursor.getVoteCount());

        return movie;
    }

    static public Uri makeYoutubeUrl(Context ctx, String key)
    {
        String url =  ctx.getString(R.string.youtube_view_url,key);
        return Uri.parse(url);
    }

    static public @DrawableRes int getFavoriteIcon( boolean isFavorite)
    {
        if (isFavorite)
            return R.drawable.ic_star_24dp;
        else
            return R.drawable.ic_star_outline_24dp;
    }

    public static boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static double getHoursSinceLastSync(Context ctx)
    {
        long timestamp = PreferenceManager.getDefaultSharedPreferences(ctx).getLong(PREF_MOVIESYNC_TIMESTAMP,Long.MAX_VALUE);
        if(timestamp==Long.MAX_VALUE)
            return Double.MAX_VALUE;

        long dt = System.currentTimeMillis() - timestamp;

        return (double)dt/1000/60/60; // in hour
    }

    /**
     * Write the current time in shared preference
     * @param ctx
     */
    public static void setSyncMovieTime(Context ctx)
    {
        PreferenceManager.getDefaultSharedPreferences(ctx)
                .edit()
                .putLong(PREF_MOVIESYNC_TIMESTAMP, System.currentTimeMillis())
                .commit();
    }
}
