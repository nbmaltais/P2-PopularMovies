package ca.nbmaltais.popularmovies.provider.comment;

import android.net.Uri;
import android.provider.BaseColumns;

import ca.nbmaltais.popularmovies.provider.PopularMoviesProvider;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;

/**
 * Comments for a movie
 */
public class CommentColumns implements BaseColumns {
    public static final String TABLE_NAME = "comment";
    public static final Uri CONTENT_URI = Uri.parse(PopularMoviesProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_ID = "movie_id";

    public static final String STRING_ID = "string_id";

    public static final String AUTHOR = "author";

    public static final String CONTENT = "content";

    public static final String URL = "url";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_ID,
            STRING_ID,
            AUTHOR,
            CONTENT,
            URL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
            if (c.equals(STRING_ID) || c.contains("." + STRING_ID)) return true;
            if (c.equals(AUTHOR) || c.contains("." + AUTHOR)) return true;
            if (c.equals(CONTENT) || c.contains("." + CONTENT)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
