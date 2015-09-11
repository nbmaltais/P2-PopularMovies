package ca.nbmaltais.popularmovies.provider.video;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ca.nbmaltais.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code video} table.
 */
public class VideoContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return VideoColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable VideoSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable VideoSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public VideoContentValues putMovieId(long value) {
        mContentValues.put(VideoColumns.MOVIE_ID, value);
        return this;
    }


    public VideoContentValues putStringId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("stringId must not be null");
        mContentValues.put(VideoColumns.STRING_ID, value);
        return this;
    }


    public VideoContentValues putKey(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("key must not be null");
        mContentValues.put(VideoColumns.KEY, value);
        return this;
    }


    public VideoContentValues putName(@Nullable String value) {
        mContentValues.put(VideoColumns.NAME, value);
        return this;
    }

    public VideoContentValues putNameNull() {
        mContentValues.putNull(VideoColumns.NAME);
        return this;
    }

    public VideoContentValues putSite(@Nullable String value) {
        mContentValues.put(VideoColumns.SITE, value);
        return this;
    }

    public VideoContentValues putSiteNull() {
        mContentValues.putNull(VideoColumns.SITE);
        return this;
    }

    public VideoContentValues putType(@Nullable String value) {
        mContentValues.put(VideoColumns.TYPE, value);
        return this;
    }

    public VideoContentValues putTypeNull() {
        mContentValues.putNull(VideoColumns.TYPE);
        return this;
    }

    public VideoContentValues putSize(@Nullable Integer value) {
        mContentValues.put(VideoColumns.SIZE, value);
        return this;
    }

    public VideoContentValues putSizeNull() {
        mContentValues.putNull(VideoColumns.SIZE);
        return this;
    }
}
