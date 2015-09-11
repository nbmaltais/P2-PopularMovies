package ca.nbmaltais.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.IntDef;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ca.nbmaltais.popularmovies.api.TheMovieDb;
import ca.nbmaltais.popularmovies.api.models.movie.Movies;
import retrofit.RetrofitError;

import static ca.nbmaltais.popularmovies.APIKEY.getApiKey;

/**
 * Created by Nicolas on 2015-07-21.
 */
public class MovieLoader extends AsyncTaskLoader<MovieLoader.Result>
{
    public class Result
    {
        public Movies movies;
        public Exception error;
    }

    @IntDef({POPULAR_MOVIES, TOPRATED_MOVIES, FAVORITE_MOVIES,UPCOMING_MOVIES})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MoviesType {}

    public static final int POPULAR_MOVIES = 0;
    public static final int TOPRATED_MOVIES = 1;
    public static final int UPCOMING_MOVIES = 2;
    public static final int FAVORITE_MOVIES = 3;


    static final String LOGTAG =  MovieLoader.class.getSimpleName();
    static String API_KEY = getApiKey();
    TheMovieDb mTheMovieDbService;
    @MoviesType int mSearchCriterion;

    public MovieLoader(Context context, @MoviesType int searchCriterion)
    {
        super(context);
        Log.d(LOGTAG, "MovieLoader created");
        mSearchCriterion = searchCriterion;

        mTheMovieDbService = Utils.createTheMovieDbService();

    }

    @Override
    public Result loadInBackground()
    {
        Result result = new Result();
        result.error=null;
        result.movies=null;
        try
        {
            Log.d(LOGTAG, "MovieLoader created");

            switch (mSearchCriterion)
            {
                case POPULAR_MOVIES:
                    //result = mTheMovieDbService.getPopularMovies();
                    result.movies = mTheMovieDbService.discoverMovies("popularity.desc", 1);
                    break;
                case TOPRATED_MOVIES:
                    result.movies = mTheMovieDbService.discoverMovies("vote_average.desc", 1);
                    break;
            /*case RECENT_MOVIES:
                result = mTheMovieDbService.getLatestMovies();
                break;*/
                case UPCOMING_MOVIES:
                    result.movies = mTheMovieDbService.getUpcomingMovies(1);
                    break;
            }

            return result;
        }
        catch(RetrofitError e)
        {
            Log.e(LOGTAG,e.getMessage());
            result.error = e;
        }

        return result;
    }
}
