package ca.nbmaltais.popularmovies.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import ca.nbmaltais.popularmovies.BuildConfig;
import ca.nbmaltais.popularmovies.provider.base.BaseContentProvider;
import ca.nbmaltais.popularmovies.provider.comment.CommentColumns;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesColumns;
import ca.nbmaltais.popularmovies.provider.topratedmovies.TopratedMoviesColumns;
import ca.nbmaltais.popularmovies.provider.upcomingmovies.UpcomingMoviesColumns;
import ca.nbmaltais.popularmovies.provider.video.VideoColumns;

public class PopularMoviesProvider extends BaseContentProvider {
    private static final String TAG = PopularMoviesProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "ca.nbmaltais.popularmovies.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_COMMENT = 0;
    private static final int URI_TYPE_COMMENT_ID = 1;

    private static final int URI_TYPE_FAVORITE_MOVIES = 2;
    private static final int URI_TYPE_FAVORITE_MOVIES_ID = 3;

    private static final int URI_TYPE_MOVIE = 4;
    private static final int URI_TYPE_MOVIE_ID = 5;

    private static final int URI_TYPE_POPULAR_MOVIES = 6;
    private static final int URI_TYPE_POPULAR_MOVIES_ID = 7;

    private static final int URI_TYPE_TOPRATED_MOVIES = 8;
    private static final int URI_TYPE_TOPRATED_MOVIES_ID = 9;

    private static final int URI_TYPE_UPCOMING_MOVIES = 10;
    private static final int URI_TYPE_UPCOMING_MOVIES_ID = 11;

    private static final int URI_TYPE_VIDEO = 12;
    private static final int URI_TYPE_VIDEO_ID = 13;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, CommentColumns.TABLE_NAME, URI_TYPE_COMMENT);
        URI_MATCHER.addURI(AUTHORITY, CommentColumns.TABLE_NAME + "/#", URI_TYPE_COMMENT_ID);
        URI_MATCHER.addURI(AUTHORITY, FavoriteMoviesColumns.TABLE_NAME, URI_TYPE_FAVORITE_MOVIES);
        URI_MATCHER.addURI(AUTHORITY, FavoriteMoviesColumns.TABLE_NAME + "/#", URI_TYPE_FAVORITE_MOVIES_ID);
        URI_MATCHER.addURI(AUTHORITY, MovieColumns.TABLE_NAME, URI_TYPE_MOVIE);
        URI_MATCHER.addURI(AUTHORITY, MovieColumns.TABLE_NAME + "/#", URI_TYPE_MOVIE_ID);
        URI_MATCHER.addURI(AUTHORITY, PopularMoviesColumns.TABLE_NAME, URI_TYPE_POPULAR_MOVIES);
        URI_MATCHER.addURI(AUTHORITY, PopularMoviesColumns.TABLE_NAME + "/#", URI_TYPE_POPULAR_MOVIES_ID);
        URI_MATCHER.addURI(AUTHORITY, TopratedMoviesColumns.TABLE_NAME, URI_TYPE_TOPRATED_MOVIES);
        URI_MATCHER.addURI(AUTHORITY, TopratedMoviesColumns.TABLE_NAME + "/#", URI_TYPE_TOPRATED_MOVIES_ID);
        URI_MATCHER.addURI(AUTHORITY, UpcomingMoviesColumns.TABLE_NAME, URI_TYPE_UPCOMING_MOVIES);
        URI_MATCHER.addURI(AUTHORITY, UpcomingMoviesColumns.TABLE_NAME + "/#", URI_TYPE_UPCOMING_MOVIES_ID);
        URI_MATCHER.addURI(AUTHORITY, VideoColumns.TABLE_NAME, URI_TYPE_VIDEO);
        URI_MATCHER.addURI(AUTHORITY, VideoColumns.TABLE_NAME + "/#", URI_TYPE_VIDEO_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return PopularMoviesDBHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_COMMENT:
                return TYPE_CURSOR_DIR + CommentColumns.TABLE_NAME;
            case URI_TYPE_COMMENT_ID:
                return TYPE_CURSOR_ITEM + CommentColumns.TABLE_NAME;

            case URI_TYPE_FAVORITE_MOVIES:
                return TYPE_CURSOR_DIR + FavoriteMoviesColumns.TABLE_NAME;
            case URI_TYPE_FAVORITE_MOVIES_ID:
                return TYPE_CURSOR_ITEM + FavoriteMoviesColumns.TABLE_NAME;

            case URI_TYPE_MOVIE:
                return TYPE_CURSOR_DIR + MovieColumns.TABLE_NAME;
            case URI_TYPE_MOVIE_ID:
                return TYPE_CURSOR_ITEM + MovieColumns.TABLE_NAME;

            case URI_TYPE_POPULAR_MOVIES:
                return TYPE_CURSOR_DIR + PopularMoviesColumns.TABLE_NAME;
            case URI_TYPE_POPULAR_MOVIES_ID:
                return TYPE_CURSOR_ITEM + PopularMoviesColumns.TABLE_NAME;

            case URI_TYPE_TOPRATED_MOVIES:
                return TYPE_CURSOR_DIR + TopratedMoviesColumns.TABLE_NAME;
            case URI_TYPE_TOPRATED_MOVIES_ID:
                return TYPE_CURSOR_ITEM + TopratedMoviesColumns.TABLE_NAME;

            case URI_TYPE_UPCOMING_MOVIES:
                return TYPE_CURSOR_DIR + UpcomingMoviesColumns.TABLE_NAME;
            case URI_TYPE_UPCOMING_MOVIES_ID:
                return TYPE_CURSOR_ITEM + UpcomingMoviesColumns.TABLE_NAME;

            case URI_TYPE_VIDEO:
                return TYPE_CURSOR_DIR + VideoColumns.TABLE_NAME;
            case URI_TYPE_VIDEO_ID:
                return TYPE_CURSOR_ITEM + VideoColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_COMMENT:
            case URI_TYPE_COMMENT_ID:
                res.table = CommentColumns.TABLE_NAME;
                res.idColumn = CommentColumns._ID;
                res.tablesWithJoins = CommentColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + CommentColumns.PREFIX_MOVIE + " ON " + CommentColumns.TABLE_NAME + "." + CommentColumns.MOVIE_ID + "=" + CommentColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = CommentColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_FAVORITE_MOVIES:
            case URI_TYPE_FAVORITE_MOVIES_ID:
                res.table = FavoriteMoviesColumns.TABLE_NAME;
                res.idColumn = FavoriteMoviesColumns._ID;
                res.tablesWithJoins = FavoriteMoviesColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + FavoriteMoviesColumns.PREFIX_MOVIE + " ON " + FavoriteMoviesColumns.TABLE_NAME + "." + FavoriteMoviesColumns.MOVIE_ID + "=" + FavoriteMoviesColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = FavoriteMoviesColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_MOVIE:
            case URI_TYPE_MOVIE_ID:
                res.table = MovieColumns.TABLE_NAME;
                res.idColumn = MovieColumns._ID;
                res.tablesWithJoins = MovieColumns.TABLE_NAME;
                res.orderBy = MovieColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_POPULAR_MOVIES:
            case URI_TYPE_POPULAR_MOVIES_ID:
                res.table = PopularMoviesColumns.TABLE_NAME;
                res.idColumn = PopularMoviesColumns._ID;
                res.tablesWithJoins = PopularMoviesColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + PopularMoviesColumns.PREFIX_MOVIE + " ON " + PopularMoviesColumns.TABLE_NAME + "." + PopularMoviesColumns.MOVIE_ID + "=" + PopularMoviesColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = PopularMoviesColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_TOPRATED_MOVIES:
            case URI_TYPE_TOPRATED_MOVIES_ID:
                res.table = TopratedMoviesColumns.TABLE_NAME;
                res.idColumn = TopratedMoviesColumns._ID;
                res.tablesWithJoins = TopratedMoviesColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + TopratedMoviesColumns.PREFIX_MOVIE + " ON " + TopratedMoviesColumns.TABLE_NAME + "." + TopratedMoviesColumns.MOVIE_ID + "=" + TopratedMoviesColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = TopratedMoviesColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_UPCOMING_MOVIES:
            case URI_TYPE_UPCOMING_MOVIES_ID:
                res.table = UpcomingMoviesColumns.TABLE_NAME;
                res.idColumn = UpcomingMoviesColumns._ID;
                res.tablesWithJoins = UpcomingMoviesColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + UpcomingMoviesColumns.PREFIX_MOVIE + " ON " + UpcomingMoviesColumns.TABLE_NAME + "." + UpcomingMoviesColumns.MOVIE_ID + "=" + UpcomingMoviesColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = UpcomingMoviesColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_VIDEO:
            case URI_TYPE_VIDEO_ID:
                res.table = VideoColumns.TABLE_NAME;
                res.idColumn = VideoColumns._ID;
                res.tablesWithJoins = VideoColumns.TABLE_NAME;
                if (MovieColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + MovieColumns.TABLE_NAME + " AS " + VideoColumns.PREFIX_MOVIE + " ON " + VideoColumns.TABLE_NAME + "." + VideoColumns.MOVIE_ID + "=" + VideoColumns.PREFIX_MOVIE + "." + MovieColumns._ID;
                }
                res.orderBy = VideoColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_COMMENT_ID:
            case URI_TYPE_FAVORITE_MOVIES_ID:
            case URI_TYPE_MOVIE_ID:
            case URI_TYPE_POPULAR_MOVIES_ID:
            case URI_TYPE_TOPRATED_MOVIES_ID:
            case URI_TYPE_UPCOMING_MOVIES_ID:
            case URI_TYPE_VIDEO_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
