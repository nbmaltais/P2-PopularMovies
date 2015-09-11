package ca.nbmaltais.popularmovies.provider.upcomingmovies;

import android.net.Uri;
import android.provider.BaseColumns;

import ca.nbmaltais.popularmovies.provider.PopularMoviesProvider;
import ca.nbmaltais.popularmovies.provider.comment.CommentColumns;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesColumns;
import ca.nbmaltais.popularmovies.provider.topratedmovies.TopratedMoviesColumns;
import ca.nbmaltais.popularmovies.provider.upcomingmovies.UpcomingMoviesColumns;
import ca.nbmaltais.popularmovies.provider.video.VideoColumns;

/**
 * List of upcoming movies
 */
public class UpcomingMoviesColumns implements BaseColumns {
    public static final String TABLE_NAME = "upcoming_movies";
    public static final Uri CONTENT_URI = Uri.parse(PopularMoviesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
