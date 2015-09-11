package ca.nbmaltais.popularmovies.provider.video;

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
 * Video
 */
public class VideoColumns implements BaseColumns {
    public static final String TABLE_NAME = "video";
    public static final Uri CONTENT_URI = Uri.parse(PopularMoviesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    public static final String STRING_ID = "string_id";

    public static final String KEY = "key";

    public static final String NAME = "name";

    public static final String SITE = "site";

    public static final String TYPE = "type";

    public static final String SIZE = "size";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            STRING_ID,
            KEY,
            NAME,
            SITE,
            TYPE,
            SIZE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(STRING_ID) || c.contains("." + STRING_ID)) return true;
            if (c.equals(KEY) || c.contains("." + KEY)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(SITE) || c.contains("." + SITE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(SIZE) || c.contains("." + SIZE)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
