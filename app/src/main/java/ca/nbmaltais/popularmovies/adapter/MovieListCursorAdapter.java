package ca.nbmaltais.popularmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesColumns;

/**
 * Created by Nicolas on 2015-07-26.
 */
public class MovieListCursorAdapter extends RecyclerView.Adapter<MovieItemViewHolder>
{
    private MovieCursor mData;
    private OnMovieListAdapterClickHandler mOnClickHandler;

    public MovieListCursorAdapter(OnMovieListAdapterClickHandler onClickHandler)
    {
        super();
        mOnClickHandler = onClickHandler;
    }
    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,viewGroup,false);
        MovieItemViewHolder vh = new MovieItemViewHolder(v, mOnClickHandler);
        return vh;
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder viewHolder, int i)
    {
        if(mData.moveToPosition(i))
        {
            viewHolder.bind(mData);
        }
    }

    @Override
    public long getItemId(int position)
    {
        mData.moveToPosition(position);
        return mData.getLongOrNull(PopularMoviesColumns.MOVIE_ID);
    }

    @Override
    public int getItemCount()
    {
        if(mData==null) return 0;

        return mData.getCount();
    }

    public void setMovies(MovieCursor results)
    {
        mData = results;
        notifyDataSetChanged();
    }
}
