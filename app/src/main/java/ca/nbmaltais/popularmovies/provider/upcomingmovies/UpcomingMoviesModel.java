package ca.nbmaltais.popularmovies.provider.upcomingmovies;

import ca.nbmaltais.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * List of upcoming movies
 */
public interface UpcomingMoviesModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();
}
