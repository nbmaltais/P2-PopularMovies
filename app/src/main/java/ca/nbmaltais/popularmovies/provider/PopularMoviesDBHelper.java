package ca.nbmaltais.popularmovies.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import ca.nbmaltais.popularmovies.BuildConfig;
import ca.nbmaltais.popularmovies.provider.comment.CommentColumns;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesColumns;
import ca.nbmaltais.popularmovies.provider.topratedmovies.TopratedMoviesColumns;
import ca.nbmaltais.popularmovies.provider.upcomingmovies.UpcomingMoviesColumns;
import ca.nbmaltais.popularmovies.provider.video.VideoColumns;

public class PopularMoviesDBHelper extends SQLiteOpenHelper {
    private static final String TAG = PopularMoviesDBHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static PopularMoviesDBHelper sInstance;
    private final Context mContext;
    private final PopularMoviesDBHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_COMMENT = "CREATE TABLE IF NOT EXISTS "
            + CommentColumns.TABLE_NAME + " ( "
            + CommentColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CommentColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + CommentColumns.STRING_ID + " TEXT NOT NULL, "
            + CommentColumns.AUTHOR + " TEXT, "
            + CommentColumns.CONTENT + " TEXT, "
            + CommentColumns.URL + " TEXT "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + CommentColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + ", CONSTRAINT unique_moviedb_id UNIQUE (string_id) ON CONFLICT IGNORE"
            + " );";

    public static final String SQL_CREATE_TABLE_FAVORITE_MOVIES = "CREATE TABLE IF NOT EXISTS "
            + FavoriteMoviesColumns.TABLE_NAME + " ( "
            + FavoriteMoviesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FavoriteMoviesColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + FavoriteMoviesColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE RESTRICT"
            + ", CONSTRAINT unique_movie_id UNIQUE (movie_id) ON CONFLICT IGNORE"
            + " );";

    public static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE IF NOT EXISTS "
            + MovieColumns.TABLE_NAME + " ( "
            + MovieColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MovieColumns.ORIGINAL_TITLE + " TEXT NOT NULL, "
            + MovieColumns.OVERVIEW + " TEXT, "
            + MovieColumns.VOTE_AVERAGE + " REAL NOT NULL, "
            + MovieColumns.VOTE_COUNT + " INTEGER NOT NULL, "
            + MovieColumns.RELEASE_DATE + " TEXT, "
            + MovieColumns.BACKDROP_PATH + " TEXT, "
            + MovieColumns.POSTER_PATH + " TEXT, "
            + MovieColumns.TITLE + " TEXT, "
            + MovieColumns.POPULARITY + " REAL NOT NULL "
            + " );";

    public static final String SQL_CREATE_INDEX_MOVIE_ORIGINAL_TITLE = "CREATE INDEX IDX_MOVIE_ORIGINAL_TITLE "
            + " ON " + MovieColumns.TABLE_NAME + " ( " + MovieColumns.ORIGINAL_TITLE + " );";

    public static final String SQL_CREATE_TABLE_POPULAR_MOVIES = "CREATE TABLE IF NOT EXISTS "
            + PopularMoviesColumns.TABLE_NAME + " ( "
            + PopularMoviesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PopularMoviesColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + PopularMoviesColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + ", CONSTRAINT unique_movie_id UNIQUE (movie_id) ON CONFLICT IGNORE"
            + " );";

    public static final String SQL_CREATE_TABLE_TOPRATED_MOVIES = "CREATE TABLE IF NOT EXISTS "
            + TopratedMoviesColumns.TABLE_NAME + " ( "
            + TopratedMoviesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TopratedMoviesColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + TopratedMoviesColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + ", CONSTRAINT unique_movie_id UNIQUE (movie_id) ON CONFLICT IGNORE"
            + " );";

    public static final String SQL_CREATE_TABLE_UPCOMING_MOVIES = "CREATE TABLE IF NOT EXISTS "
            + UpcomingMoviesColumns.TABLE_NAME + " ( "
            + UpcomingMoviesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UpcomingMoviesColumns.MOVIE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + UpcomingMoviesColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + ", CONSTRAINT unique_movie_id UNIQUE (movie_id) ON CONFLICT IGNORE"
            + " );";

    public static final String SQL_CREATE_TABLE_VIDEO = "CREATE TABLE IF NOT EXISTS "
            + VideoColumns.TABLE_NAME + " ( "
            + VideoColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + VideoColumns.MOVIE_ID + " INTEGER NOT NULL, "
            + VideoColumns.STRING_ID + " TEXT NOT NULL, "
            + VideoColumns.KEY + " TEXT NOT NULL, "
            + VideoColumns.NAME + " TEXT, "
            + VideoColumns.SITE + " TEXT, "
            + VideoColumns.TYPE + " TEXT, "
            + VideoColumns.SIZE + " INTEGER "
            + ", CONSTRAINT fk_movie_id FOREIGN KEY (" + VideoColumns.MOVIE_ID + ") REFERENCES movie (_id) ON DELETE CASCADE"
            + ", CONSTRAINT unique_moviedb_id UNIQUE (string_id) ON CONFLICT IGNORE"
            + " );";

    // @formatter:on

    public static PopularMoviesDBHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static PopularMoviesDBHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static PopularMoviesDBHelper newInstancePreHoneycomb(Context context) {
        return new PopularMoviesDBHelper(context);
    }

    private PopularMoviesDBHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new PopularMoviesDBHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PopularMoviesDBHelper newInstancePostHoneycomb(Context context) {
        return new PopularMoviesDBHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PopularMoviesDBHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PopularMoviesDBHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_COMMENT);
        db.execSQL(SQL_CREATE_TABLE_FAVORITE_MOVIES);
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_INDEX_MOVIE_ORIGINAL_TITLE);
        db.execSQL(SQL_CREATE_TABLE_POPULAR_MOVIES);
        db.execSQL(SQL_CREATE_TABLE_TOPRATED_MOVIES);
        db.execSQL(SQL_CREATE_TABLE_UPCOMING_MOVIES);
        db.execSQL(SQL_CREATE_TABLE_VIDEO);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
