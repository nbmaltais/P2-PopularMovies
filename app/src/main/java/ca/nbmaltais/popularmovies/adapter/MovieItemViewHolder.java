package ca.nbmaltais.popularmovies.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.Utils;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesColumns;

/**
 * Created by Nicolas on 2015-07-22.
 */
public class MovieItemViewHolder extends RecyclerView.ViewHolder
{
    public final ImageView movieImage;
    private OnMovieListAdapterClickHandler mClickHandler;
    private long mMovieId;

    //TextView movieName;

    public MovieItemViewHolder(View itemView, OnMovieListAdapterClickHandler onClickHandler)
    {
        super(itemView);
        mClickHandler = onClickHandler;

        //movieName = (TextView)itemView.findViewById(R.id.movie_name);
        movieImage = (ImageView) itemView.findViewById(R.id.movie_image);


        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mClickHandler.onMovieItemClicked(MovieItemViewHolder.this, mMovieId);
            }
        });
    }

    public void bind(MovieCursor movie)
    {
        // The query comes from a pivot table. the _ID columns is the id in the pivot table. We must
        // get the movie id! We should have recevied a PopularMoviesCursor|FavoriteMovieCursor, etc, but since they don't
        // extends a comon base class we would need to have multiple bind method. So we do this little hack...
        mMovieId = movie.getLongOrNull(PopularMoviesColumns.MOVIE_ID);

        ViewCompat.setTransitionName(movieImage, "posertview" + mMovieId);
        Context ctx = itemView.getContext();
        //movieName.setText(movie.getOriginalTitle());
        // generate URL from configuration
        Picasso.with(ctx).load( Utils.makeImageUrl185px( movie.getPosterPath()))
                //.fit()
                //.centerCrop()
                .into(movieImage);
    }


}
