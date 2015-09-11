package ca.nbmaltais.popularmovies.adapter;

/**
 * Created by Nicolas on 2015-07-22.
 */
public interface OnMovieListAdapterClickHandler
{
    void onMovieItemClicked(MovieItemViewHolder viewHolder, long movieId);
}
