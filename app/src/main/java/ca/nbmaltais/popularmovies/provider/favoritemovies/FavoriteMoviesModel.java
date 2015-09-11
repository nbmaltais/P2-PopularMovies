package ca.nbmaltais.popularmovies.provider.favoritemovies;

import ca.nbmaltais.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * List of favorite movies. The foreigh key use on restrict to prevent deletion of favorite movies from the movie table.
 */
public interface FavoriteMoviesModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    long getMovieId();
}
