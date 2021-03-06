package ca.nbmaltais.popularmovies.provider.movie;

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
 * A movie entry in themoviedb.org
 */
public class MovieColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final Uri CONTENT_URI = Uri.parse(PopularMoviesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ORIGINAL_TITLE = "original_title";

    public static final String OVERVIEW = "overview";

    public static final String VOTE_AVERAGE = "vote_average";

    public static final String VOTE_COUNT = "vote_count";

    public static final String RELEASE_DATE = "release_date";

    public static final String BACKDROP_PATH = "backdrop_path";

    public static final String POSTER_PATH = "poster_path";

    public static final String TITLE = "title";

    public static final String POPULARITY = "popularity";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ORIGINAL_TITLE,
            OVERVIEW,
            VOTE_AVERAGE,
            VOTE_COUNT,
            RELEASE_DATE,
            BACKDROP_PATH,
            POSTER_PATH,
            TITLE,
            POPULARITY
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ORIGINAL_TITLE) || c.contains("." + ORIGINAL_TITLE)) return true;
            if (c.equals(OVERVIEW) || c.contains("." + OVERVIEW)) return true;
            if (c.equals(VOTE_AVERAGE) || c.contains("." + VOTE_AVERAGE)) return true;
            if (c.equals(VOTE_COUNT) || c.contains("." + VOTE_COUNT)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("." + RELEASE_DATE)) return true;
            if (c.equals(BACKDROP_PATH) || c.contains("." + BACKDROP_PATH)) return true;
            if (c.equals(POSTER_PATH) || c.contains("." + POSTER_PATH)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(POPULARITY) || c.contains("." + POPULARITY)) return true;
        }
        return false;
    }

}
