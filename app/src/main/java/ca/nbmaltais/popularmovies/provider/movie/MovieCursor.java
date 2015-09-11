package ca.nbmaltais.popularmovies.provider.movie;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ca.nbmaltais.popularmovies.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie} table.
 */
public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code original_title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getOriginalTitle() {
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
    public String getOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        return res;
    }

    /**
     * Get the {@code vote_average} value.
     */
    public double getVoteAverage() {
        Double res = getDoubleOrNull(MovieColumns.VOTE_AVERAGE);
        if (res == null)
            throw new NullPointerException("The value of 'vote_average' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code vote_count} value.
     */
    public int getVoteCount() {
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
    public String getReleaseDate() {
        String res = getStringOrNull(MovieColumns.RELEASE_DATE);
        return res;
    }

    /**
     * Get the {@code backdrop_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getBackdropPath() {
        String res = getStringOrNull(MovieColumns.BACKDROP_PATH);
        return res;
    }

    /**
     * Get the {@code poster_path} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getPosterPath() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH);
        return res;
    }

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        return res;
    }

    /**
     * Get the {@code popularity} value.
     */
    public double getPopularity() {
        Double res = getDoubleOrNull(MovieColumns.POPULARITY);
        if (res == null)
            throw new NullPointerException("The value of 'popularity' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
