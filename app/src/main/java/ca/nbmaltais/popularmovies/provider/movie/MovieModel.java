package ca.nbmaltais.popularmovies.provider.movie;

import ca.nbmaltais.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A movie entry in themoviedb.org
 */
public interface MovieModel extends BaseModel {

    /**
     * Get the {@code original_title} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getOriginalTitle();

    /**
     * Get the {@code overview} value.
     * Can be {@code null}.
     */
    @Nullable
    String getOverview();

    /**
     * Get the {@code vote_average} value.
     */
    double getVoteAverage();

    /**
     * Get the {@code vote_count} value.
     */
    int getVoteCount();

    /**
     * Get the {@code release_date} value.
     * Can be {@code null}.
     */
    @Nullable
    String getReleaseDate();

    /**
     * Get the {@code backdrop_path} value.
     * Can be {@code null}.
     */
    @Nullable
    String getBackdropPath();

    /**
     * Get the {@code poster_path} value.
     * Can be {@code null}.
     */
    @Nullable
    String getPosterPath();

    /**
     * Get the {@code title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Get the {@code popularity} value.
     */
    double getPopularity();
}
