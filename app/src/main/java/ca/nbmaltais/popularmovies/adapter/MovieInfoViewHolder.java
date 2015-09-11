package ca.nbmaltais.popularmovies.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.Utils;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;

/**
 * Created by Nicolas on 2015-07-26.
 */
public class MovieInfoViewHolder extends RecyclerView.ViewHolder
{
    private final ImageView mPosterView;
    private final ImageView mBackdropView;
    private final TextView mTitleView;
    private final TextView mDescriptionView;
    private final TextView mRatingView;
    private final TextView mReleaseDateView;
    private final FloatingActionButton mFavoriteFab;
    private MovieDetailAdapterCallbacks mCallbacks;
    private boolean mIsFavorite;

    public MovieInfoViewHolder(final View itemView, MovieDetailAdapterCallbacks callbacks)
    {
        super(itemView);
        mCallbacks = callbacks;

        mTitleView = (TextView)itemView.findViewById(R.id.movie_title);
        mPosterView = (ImageView)itemView.findViewById(R.id.movie_poster);
        mBackdropView = (ImageView)itemView.findViewById(R.id.movie_backdrop);
        mDescriptionView = (TextView)itemView.findViewById(R.id.movie_description);
        mRatingView = (TextView)itemView.findViewById(R.id.movie_rating);
        mReleaseDateView = (TextView)itemView.findViewById(R.id.movie_release_date);
        mFavoriteFab = (FloatingActionButton)itemView.findViewById(R.id.fab);

        if(mFavoriteFab!=null)
        {
            mFavoriteFab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(mCallbacks!=null)
                    {
                        mIsFavorite = !mIsFavorite;
                        mCallbacks.onSetFavorite(mIsFavorite);
                        updateFavState();

                    }
                }
            });
        }
    }

    public void bind(MovieCursor movie, boolean isFavorite)
    {
        Context context = itemView.getContext();

        mIsFavorite=isFavorite;
        if(mTitleView!=null)
            mTitleView.setText(movie.getOriginalTitle());

        mRatingView.setText(  context.getString(R.string.rating, movie.getVoteAverage()));
        mDescriptionView.setText(movie.getOverview());
        mReleaseDateView.setText(movie.getReleaseDate());

        if(mPosterView!=null)
        {
            Picasso.with(context).load(Utils.makeImageUrl185px(movie.getPosterPath())).into(mPosterView, new Callback()
            {
                @Override
                public void onSuccess()
                {
                    onPosterLoaded();
                }

                @Override
                public void onError()
                {
                    onPosterLoaded();
                }
            });
        }

        if(mBackdropView!=null)
        {
            Picasso.with(context)
                    .load(Utils.makeImageUrl500px(movie.getBackdropPath()))
                    .placeholder(R.drawable.backdrop_placeholder)
                    .into(mBackdropView);
        }

        updateFavState();

    }

    private void onPosterLoaded()
    {
        if(mCallbacks!=null)
            mCallbacks.onPosterLoaded();
    }

    private void updateFavState()
    {
        if(mFavoriteFab==null)
            return;
        mFavoriteFab.setImageResource(Utils.getFavoriteIcon( mIsFavorite));
    }
}
