package ca.nbmaltais.popularmovies.provider.comment;

import ca.nbmaltais.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Comments for a movie
 */
public interface CommentModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();

    /**
     * Get the {@code string_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getStringId();

    /**
     * Get the {@code author} value.
     * Can be {@code null}.
     */
    @Nullable
    String getAuthor();

    /**
     * Get the {@code content} value.
     * Can be {@code null}.
     */
    @Nullable
    String getContent();

    /**
     * Get the {@code url} value.
     * Can be {@code null}.
     */
    @Nullable
    String getUrl();
}
