package ca.nbmaltais.popularmovies.provider.upcomingmovies;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ca.nbmaltais.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code upcoming_movies} table.
 */
public class UpcomingMoviesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return UpcomingMoviesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable UpcomingMoviesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable UpcomingMoviesSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public UpcomingMoviesContentValues putMovieId(long value) {
        mContentValues.put(UpcomingMoviesColumns.MOVIE_ID, value);
        return this;
    }

}
