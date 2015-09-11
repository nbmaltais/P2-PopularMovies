package ca.nbmaltais.popularmovies.provider.topratedmovies;

import ca.nbmaltais.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * List of top rated movies
 */
public interface TopratedMoviesModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();
}
