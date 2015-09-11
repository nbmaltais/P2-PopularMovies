package ca.nbmaltais.popularmovies.provider.comment;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ca.nbmaltais.popularmovies.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code comment} table.
 */
public class CommentContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CommentColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CommentSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable CommentSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CommentContentValues putMovieId(long value) {
        mContentValues.put(CommentColumns.MOVIE_ID, value);
        return this;
    }


    public CommentContentValues putStringId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("stringId must not be null");
        mContentValues.put(CommentColumns.STRING_ID, value);
        return this;
    }


    public CommentContentValues putAuthor(@Nullable String value) {
        mContentValues.put(CommentColumns.AUTHOR, value);
        return this;
    }

    public CommentContentValues putAuthorNull() {
        mContentValues.putNull(CommentColumns.AUTHOR);
        return this;
    }

    public CommentContentValues putContent(@Nullable String value) {
        mContentValues.put(CommentColumns.CONTENT, value);
        return this;
    }

    public CommentContentValues putContentNull() {
        mContentValues.putNull(CommentColumns.CONTENT);
        return this;
    }

    public CommentContentValues putUrl(@Nullable String value) {
        mContentValues.put(CommentColumns.URL, value);
        return this;
    }

    public CommentContentValues putUrlNull() {
        mContentValues.putNull(CommentColumns.URL);
        return this;
    }
}
