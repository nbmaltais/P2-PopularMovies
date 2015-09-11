package ca.nbmaltais.popularmovies.adapter;

/**
 * Created by Nicolas on 2015-07-26.
 */
public interface MovieDetailAdapterCallbacks
{
    void onPosterLoaded();
    void onVideoClicked(String key);
    void onSetFavorite(boolean favorite);
}
