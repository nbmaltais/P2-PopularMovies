package ca.nbmaltais.popularmovies.provider.popularmovies;

import ca.nbmaltais.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * List of popular movies
 */
public interface PopularMoviesModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();
}
