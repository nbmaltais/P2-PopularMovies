package ca.nbmaltais.popularmovies.ui;


import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ca.nbmaltais.popularmovies.MovieLoader;
import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.adapter.MovieItemViewHolder;
import ca.nbmaltais.popularmovies.adapter.MovieListCursorAdapter;
import ca.nbmaltais.popularmovies.adapter.OnMovieListAdapterClickHandler;
import ca.nbmaltais.popularmovies.data.FetchMoviesService;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesColumns;
import ca.nbmaltais.popularmovies.provider.topratedmovies.TopratedMoviesColumns;
import ca.nbmaltais.popularmovies.provider.upcomingmovies.UpcomingMoviesColumns;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>
{
    static final String LOGTAG = MovieListFragment.class.getSimpleName();

    private RecyclerView mMoviesRecyclerView;
    private MovieListCursorAdapter mAdapter;
    private Host mHost;


    public interface Host
    {
        void onMovieClicked(long movieId, View posterView);
    }

    @MovieLoader.MoviesType int mMoviesType = MovieLoader.POPULAR_MOVIES;

    public MovieListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mHost = (Host)activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        ActivityCompat.postponeEnterTransition(getActivity());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_movie_list, container, false);

        Context ctx = getActivity();
        // Get the number of coulmns, depends on device size and orientation
        int columnsCount = ctx.getResources().getInteger(R.integer.movie_list_columns);

        mMoviesRecyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);
        mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(ctx, columnsCount));

        mAdapter = new MovieListCursorAdapter(new OnMovieListAdapterClickHandler(){

            @Override
            public void onMovieItemClicked(MovieItemViewHolder viewHolder, long movieId)
            {
                mHost.onMovieClicked(movieId,viewHolder.movieImage);
            }
        });
        mAdapter.setHasStableIds(true);
        mMoviesRecyclerView.setAdapter(mAdapter);


        getLoaderManager().initLoader(0, null, this);//.forceLoad();

        updateTitle();

        return root;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // Try to refresh. If a sync was perfrom too recently, it will do nothing
        FetchMoviesService.fetchMovies(getActivity(), false);
    }

    private void updateTitle()
    {
        String title=null;
        switch(mMoviesType)
        {
            case MovieLoader.POPULAR_MOVIES:
                title = getActivity().getString(R.string.title_popular_movies);
                break;
            case MovieLoader.TOPRATED_MOVIES:
                title = getActivity().getString(R.string.title_toprated_movies);
                break;
            case MovieLoader.UPCOMING_MOVIES:
                title = getActivity().getString(R.string.title_upcoming_movies);
                break;
            case MovieLoader.FAVORITE_MOVIES:
                title = getActivity().getString(R.string.action_favorite_movies);
                break;
        }

        getActivity().setTitle(title);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)
    {
        Log.d(LOGTAG, "onCreateLoader");

        Uri contentUri=null;
        String sortOrder=null;

        switch (mMoviesType)
        {
            default:
            case MovieLoader.POPULAR_MOVIES:
                contentUri = PopularMoviesColumns.CONTENT_URI;
                sortOrder = MovieColumns.POPULARITY + " DESC";
                break;
            case MovieLoader.UPCOMING_MOVIES:
                contentUri = UpcomingMoviesColumns.CONTENT_URI;
                sortOrder = MovieColumns.RELEASE_DATE + " ASC";
                break;
            case MovieLoader.TOPRATED_MOVIES:
                contentUri = TopratedMoviesColumns.CONTENT_URI;
                sortOrder = MovieColumns.VOTE_AVERAGE + " DESC";
                break;
            case MovieLoader.FAVORITE_MOVIES:
                contentUri = FavoriteMoviesColumns.CONTENT_URI;
                break;
        }

        String[] columns = new String[]{
                PopularMoviesColumns.MOVIE_ID,
                MovieColumns.ORIGINAL_TITLE,
                MovieColumns.OVERVIEW,
                MovieColumns.VOTE_AVERAGE,
                MovieColumns.VOTE_COUNT,
                MovieColumns.RELEASE_DATE,
                MovieColumns.BACKDROP_PATH,
                MovieColumns.POSTER_PATH,
                MovieColumns.TITLE,
                MovieColumns.POPULARITY
        } ;

        return new CursorLoader( getActivity(), contentUri, columns,null,null,sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader,Cursor data)
    {
        Log.d(LOGTAG, "onLoadFinished");

        MovieCursor cursor = new MovieCursor(data);
        mAdapter.setMovies(cursor);

        showEmptyView(cursor.getCount()==0);

        ActivityCompat.startPostponedEnterTransition(getActivity());


    }

    private void showEmptyView(boolean show)
    {
        // TODO: show a view informing the user that no data has been loaded
        if(show)
        {
            Log.d(LOGTAG,"No movies loaded.");
            Toast.makeText(getActivity(),R.string.no_movies,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        mAdapter.setMovies(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_movie_list, menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        boolean handled = false;
        if(id==R.id.action_refresh)
        {
            FetchMoviesService.fetchMovies(getActivity(),true);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void selectMovieType(@MovieLoader.MoviesType int movieType)
    {
        mMoviesType=movieType;
        updateTitle();
        getLoaderManager().restartLoader(0, null, this);//.forceLoad();
    }

    public int getMovieType()
    {
        return mMoviesType;
    }

}
