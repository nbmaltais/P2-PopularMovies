package ca.nbmaltais.popularmovies.ui;


import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.Utils;
import ca.nbmaltais.popularmovies.adapter.MovieDetailAdapter;
import ca.nbmaltais.popularmovies.adapter.MovieDetailAdapterCallbacks;
import ca.nbmaltais.popularmovies.data.FetchMoviesService;
import ca.nbmaltais.popularmovies.provider.comment.CommentColumns;
import ca.nbmaltais.popularmovies.provider.comment.CommentCursor;
import ca.nbmaltais.popularmovies.provider.comment.CommentSelection;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesColumns;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesSelection;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;
import ca.nbmaltais.popularmovies.provider.movie.MovieSelection;
import ca.nbmaltais.popularmovies.provider.video.VideoColumns;
import ca.nbmaltais.popularmovies.provider.video.VideoCursor;
import ca.nbmaltais.popularmovies.provider.video.VideoSelection;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, MovieDetailAdapterCallbacks
{
    static final String LOGTAG = MovieDetailFragment.class.getSimpleName();
    //private static final String ARG_MOVIE_RESULT = "ARG_MOVIE_RESULT";
    private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";
    private static final String ARG_RESUME_TRANSITION = "ARG_RESUME_TRANSITION";

    static final int MOVIE_LOADER = 0;
    static final int VIDEO_LOADER = 1;
    static final int COMMENT_LOADER = 2;
    static final int FAVORITE_LOADER = 3;


    private ImageView mBackdropView;
    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mFab;

    private boolean mPostponedContentTransition=true;
    private MovieDetailAdapter mAdapter;
    private long mMovieId;
    private boolean mMovieIsFavorite;
    private boolean mTwoPanes;
    private ShareActionProvider mShareActionProvider;


    public MovieDetailFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMovieId = getArguments().getLong(ARG_MOVIE_ID);
        mPostponedContentTransition = getArguments().getBoolean(ARG_RESUME_TRANSITION);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);


        // Fetch and store ShareActionProvider
        //
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareIntent();
    }

    private void setShareIntent()
    {
        if(mShareActionProvider==null)
            return;
        Intent intent = createShareVideoIntent();
        if(intent==null)
            return;

        mShareActionProvider.setShareIntent(intent);
    }

    private Intent createShareVideoIntent()
    {
        if(mAdapter==null)
            return null;
        VideoCursor cursor = mAdapter.getVideos();
        if(cursor== null || !cursor.moveToFirst() )
            return null;

        String video_url = Utils.makeYoutubeUrl(getActivity(),cursor.getKey()).toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        //shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, video_url);
        return shareIntent;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        FetchMoviesService.fetchVideos(getActivity(), mMovieId);
        FetchMoviesService.fetchComments(getActivity(), mMovieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_movie_detail, container, false);


        mRecyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MovieDetailAdapter(this);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mAdapter);

        AppCompatActivity activity = (AppCompatActivity)getActivity();

        mCollapsingToolbarLayout = (CollapsingToolbarLayout)root.findViewById(R.id.collapsing_toolbar);
        if(mCollapsingToolbarLayout!=null)
        {
            mTwoPanes=false;
            // We are in single pane mode
            Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
            if (toolbar != null)
            {
                activity.setSupportActionBar(toolbar);
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            mBackdropView = (ImageView)root.findViewById(R.id.appbar_image);
        }
        else
        {
            mTwoPanes=true;
        }
        // TODO: if backdrop if white, the title doesn't apprear!
        // We need to set a scrim, but CollapsingToolbarLayout.setContentScrimColor works only
        // when the toolbar is collapsed...

        //ctl.setContentScrimColor( activity.getResources().getColor(R.color.content_scrim) );

        mFab = (FloatingActionButton)root.findViewById(R.id.fab);
        if(mFab!=null)
        {
            mFab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setFavorite(!mMovieIsFavorite);
                }
            });

            updateFabIcon();
        }

        getLoaderManager().initLoader(MOVIE_LOADER, null, this);
        getLoaderManager().initLoader(VIDEO_LOADER,null,this);
        getLoaderManager().initLoader(COMMENT_LOADER,null,this);
        getLoaderManager().initLoader(FAVORITE_LOADER,null,this);
        return root;
    }

    private void updateFabIcon()
    {
        if(mFab!=null)
        {
            mFab.setImageResource(Utils.getFavoriteIcon(mMovieIsFavorite));
        }
    }

    private void updateMovieInfo(MovieCursor movie)
    {
        Activity activity = getActivity();

        if(mCollapsingToolbarLayout!=null)
        {
            mCollapsingToolbarLayout.setTitle(movie.getTitle());
        }

        if(mBackdropView!=null)
        {
            Picasso.with(activity).load(Utils.makeImageUrl500px(movie.getBackdropPath()))
                    .placeholder(R.drawable.backdrop_placeholder)
                    .into(mBackdropView);
        }


        mAdapter.setMovie(movie);

    }

    private void startPostponedContentTransition()
    {
        if(mPostponedContentTransition)
        {
            // Image is done loading, resume animations
            ActivityCompat.startPostponedEnterTransition(getActivity());
            mPostponedContentTransition = false;
        }
    }


    public static MovieDetailFragment newInstance(long movieId, boolean transitionPending)
    {
        MovieDetailFragment fragment  = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_MOVIE_ID, movieId);
        args.putBoolean(ARG_RESUME_TRANSITION,transitionPending);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {

        Log.d(LOGTAG, "onCreateLoader");
        switch(id)
        {
            case MOVIE_LOADER:
            {
                MovieSelection where = new MovieSelection();
                where.id(mMovieId);
                return new CursorLoader(getActivity(), where.uri(), MovieColumns.ALL_COLUMNS, where.sel(), where.args(), null);
            }
            case VIDEO_LOADER:
            {
                VideoSelection where = new VideoSelection();
                // Select only videos from youtube
                where.movieId(mMovieId).and().siteLike("YouTube");
                return new CursorLoader(getActivity(), where.uri(), VideoColumns.ALL_COLUMNS, where.sel(), where.args(), null);
            }
            case COMMENT_LOADER:
            {
                CommentSelection where = new CommentSelection();
                where.movieId(mMovieId);
                return new CursorLoader(getActivity(), where.uri(), CommentColumns.ALL_COLUMNS, where.sel(), where.args(), null);
            }
            case FAVORITE_LOADER:
            {
                FavoriteMoviesSelection where = new FavoriteMoviesSelection();
                where.movieId(mMovieId);
                return new CursorLoader(getActivity(), where.uri(), FavoriteMoviesColumns.ALL_COLUMNS, where.sel(), where.args(), null);
            }
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        Log.d(LOGTAG, "onLoadFinished");
        switch(loader.getId())
        {
            case MOVIE_LOADER:
            {
                MovieCursor cursor = new MovieCursor(data);
                if(cursor.moveToFirst())
                {
                    updateMovieInfo(cursor);
                }
                else
                {
                    // In case we were waiting
                    startPostponedContentTransition();
                }

                break;
            }

            case VIDEO_LOADER:
                mAdapter.setVideos( new VideoCursor(data));
                setShareIntent();
                break;

            case COMMENT_LOADER:
                mAdapter.setComments(new CommentCursor(data));
                break;
            case FAVORITE_LOADER:
                mMovieIsFavorite = data.getCount() != 0;
                updateFabIcon();
                mAdapter.setMovieIsFavorite(mMovieIsFavorite);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        switch(loader.getId())
        {
            case MOVIE_LOADER:
                mAdapter.setMovie(null);
                break;
            case VIDEO_LOADER:
                mAdapter.setVideos(null);
                break;
            case COMMENT_LOADER:
                mAdapter.setComments(null);
                break;
        }

    }

    @Override
    public void onPosterLoaded()
    {
        startPostponedContentTransition();
    }

    @Override
    public void onVideoClicked(String key)
    {
        Log.d(LOGTAG, "Trying to play video with key=" + key);
        Activity activity = getActivity();
        Uri youtubeUri = Utils.makeYoutubeUrl(activity,key);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(youtubeUri);

        PackageManager manager = activity.getPackageManager();
        if(intent.resolveActivity(manager)!=null)
        {
            getActivity().startActivity(intent);
        }

    }

    @Override
    public void onSetFavorite(boolean favorite)
    {
        setFavorite(favorite);
    }

    private void setFavorite(boolean favorite)
    {
        mMovieIsFavorite=favorite;
        updateFabIcon();

        FetchMoviesService.setFavoriteMovie(getActivity(),mMovieId,mMovieIsFavorite);

    }
}
