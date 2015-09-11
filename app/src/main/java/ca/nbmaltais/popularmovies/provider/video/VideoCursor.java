package ca.nbmaltais.popularmovies.provider.video;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ca.nbmaltais.popularmovies.provider.base.AbstractCursor;
import ca.nbmaltais.popularmovies.provider.movie.*;

/**
 * Cursor wrapper for the {@code video} table.
 */
public class VideoCursor extends AbstractCursor implements VideoModel {
    public VideoCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(VideoColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_id} value.
     */
    public long getMovieId() {
        Long res = getLongOrNull(VideoColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code original_title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getMovieOriginalTitle() {
        String res = getStringOrNull(MovieColumns.ORIGINAL_TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'original_title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code overview} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        return res;
    }

    /**
     * Get the {@code vote_average} value.
     */
    public double getMovieVoteAverage() {
        Double res = getDoubleOrNull(MovieColumns.VOTE_AVERAGE);
        if (res == null)
            throw new NullPointerException("The value of 'vote_average' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_count} value.
     */
    public int getMovieVoteCount() {
        Integer res = getIntegerOrNull(MovieColumns.VOTE_COUNT);
        if (res == null)
            throw new NullPointerException("The value of 'vote_count' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code release_date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieReleaseDate() {
        String res = getStringOrNull(MovieColumns.RELEASE_DATE);
        return res;
    }

    /**
     * Get the {@code backdrop_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieBackdropPath() {
        String res = getStringOrNull(MovieColumns.BACKDROP_PATH);
        return res;
    }

    /**
     * Get the {@code poster_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMoviePosterPath() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH);
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code popularity} value.
     */
    public double getMoviePopularity() {
        Double res = getDoubleOrNull(MovieColumns.POPULARITY);
        if (res == null)
            throw new NullPointerException("The value of 'popularity' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code string_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getStringId() {
        String res = getStringOrNull(VideoColumns.STRING_ID);
        if (res == null)
            throw new NullPointerException("The value of 'string_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getKey() {
        String res = getStringOrNull(VideoColumns.KEY);
        if (res == null)
            throw new NullPointerException("The value of 'key' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(VideoColumns.NAME);
        return res;
    }

    /**
     * Get the {@code site} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getSite() {
        String res = getStringOrNull(VideoColumns.SITE);
        return res;
    }

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getType() {
        String res = getStringOrNull(VideoColumns.TYPE);
        return res;
    }

    /**
     * Get the {@code size} value.
     * Can be {@code null}.
     */
    @Nullable
    public Integer getSize() {
        Integer res = getIntegerOrNull(VideoColumns.SIZE);
        return res;
    }
}
