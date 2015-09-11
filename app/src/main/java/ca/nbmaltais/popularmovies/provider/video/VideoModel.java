package ca.nbmaltais.popularmovies.provider.video;

import ca.nbmaltais.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Video
 */
public interface VideoModel extends BaseModel {

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
     * Get the {@code key} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getKey();

    /**
     * Get the {@code name} value.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Get the {@code site} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSite();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    String getType();

    /**
     * Get the {@code size} value.
     * Can be {@code null}.
     */
    @Nullable
    Integer getSize();
}
