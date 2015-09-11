package ca.nbmaltais.popularmovies.data;

import android.app.IntentService;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.nbmaltais.popularmovies.Utils;
import ca.nbmaltais.popularmovies.api.TheMovieDb;
import ca.nbmaltais.popularmovies.api.models.movie.Movie;
import ca.nbmaltais.popularmovies.api.models.movie.Review;
import ca.nbmaltais.popularmovies.api.models.movie.Reviews;
import ca.nbmaltais.popularmovies.api.models.movie.Video;
import ca.nbmaltais.popularmovies.api.models.movie.Videos;
import ca.nbmaltais.popularmovies.provider.PopularMoviesProvider;
import ca.nbmaltais.popularmovies.provider.comment.CommentColumns;
import ca.nbmaltais.popularmovies.provider.comment.CommentContentValues;
import ca.nbmaltais.popularmovies.provider.comment.CommentSelection;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesColumns;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesContentValues;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesCursor;
import ca.nbmaltais.popularmovies.provider.favoritemovies.FavoriteMoviesSelection;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;
import ca.nbmaltais.popularmovies.provider.movie.MovieContentValues;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;
import ca.nbmaltais.popularmovies.provider.movie.MovieSelection;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesColumns;
import ca.nbmaltais.popularmovies.provider.popularmovies.PopularMoviesContentValues;
import ca.nbmaltais.popularmovies.provider.topratedmovies.TopratedMoviesColumns;
import ca.nbmaltais.popularmovies.provider.topratedmovies.TopratedMoviesContentValues;
import ca.nbmaltais.popularmovies.provider.upcomingmovies.UpcomingMoviesColumns;
import ca.nbmaltais.popularmovies.provider.upcomingmovies.UpcomingMoviesContentValues;
import ca.nbmaltais.popularmovies.provider.video.VideoColumns;
import ca.nbmaltais.popularmovies.provider.video.VideoContentValues;
import ca.nbmaltais.popularmovies.provider.video.VideoSelection;
import retrofit.RetrofitError;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class FetchMoviesService extends IntentService
{
    static final String LOGTAG = FetchMoviesService.class.getSimpleName();
    public static final String ACTION_FETCH_MOVIES = "ca.nbmaltais.popularmovies.data.action.FETCH_MOVIES";
    public static final String ACTION_FETCH_COMMENTS = "ca.nbmaltais.popularmovies.data.action.FETCH_COMMENTS";
    public static final String ACTION_FETCH_VIDEOS = "ca.nbmaltais.popularmovies.data.action.FETCH_VIDEOS";
    public static final String ACTION_SET_FAVORITE = "ca.nbmaltais.popularmovies.data.action.SET_FAVORITE";

    // The moviedb doc says the results are updated each day
    static final int SYNC_INTERVAL_HOURS = 24;
    // For debugging
    public static final String ACTION_DELETE_MOVIES = "ca.nbmaltais.popularmovies.data.action.DELETE_MOVIES";

    public static final String EXTRA_MOVIE_ID = "ca.nbmaltais.popularmovies.data.extra.MOVIE_ID";
    public static final String EXTRA_FAVORITE = "ca.nbmaltais.popularmovies.data.extra.FAVORITE";

    private TheMovieDb mTheMovieDbService;
    private ContentResolver mContentResolver;



    public static void fetchMovies(Context ctx, boolean force)
    {
        if(Utils.isNetworkAvailable(ctx))
        {
            double hours = Utils.getHoursSinceLastSync(ctx);
            if( force || hours > SYNC_INTERVAL_HOURS)
            {
                Intent intent = new Intent(ctx, FetchMoviesService.class);
                intent.setAction(ACTION_FETCH_MOVIES);
                ctx.startService(intent);
            }
            else
            {
                Log.d(LOGTAG,"Skipping sync, hours since last sync="+hours);
            }
        }
    }

    public static void fetchVideos(Context ctx,long movieId)
    {
        if(Utils.isNetworkAvailable(ctx))
        {
            Intent intent = new Intent(ctx, FetchMoviesService.class);
            intent.setAction(ACTION_FETCH_VIDEOS);
            intent.putExtra(EXTRA_MOVIE_ID, movieId);
            ctx.startService(intent);
        }
    }

    public static void fetchComments(Context ctx,long movieId)
    {
        if(Utils.isNetworkAvailable(ctx))
        {
            Intent intent = new Intent(ctx, FetchMoviesService.class);
            intent.setAction(ACTION_FETCH_COMMENTS);
            intent.putExtra(EXTRA_MOVIE_ID, movieId);
            ctx.startService(intent);
        }
    }

    public static void setFavoriteMovie(Context ctx,long movieId, boolean favorite)
    {
        Intent intent = new Intent(ctx, FetchMoviesService.class);
        intent.setAction(ACTION_SET_FAVORITE);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
        intent.putExtra(EXTRA_FAVORITE, favorite);
        ctx.startService(intent);
    }

    public static void deleteMovies(Context ctx)
    {
        Intent intent = new Intent(ctx,FetchMoviesService.class);
        intent.setAction(ACTION_DELETE_MOVIES);
        ctx.startService(intent);
    }

    public FetchMoviesService()
    {
        super("FetchMoviesService");
    }

    @Override
    public void onCreate()
    {
        Log.d(LOGTAG,"onCreate");
        super.onCreate();
        mTheMovieDbService = Utils.createTheMovieDbService();
        mContentResolver = getContentResolver();
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.d(LOGTAG, "onHandleIntent");
        try
        {
            if (intent != null)
            {
                final String action = intent.getAction();
                if (ACTION_FETCH_MOVIES.equals(action))
                {
                    handleFetchMovies();
                }
                else if(ACTION_DELETE_MOVIES.equals(action))
                {
                    handleDeleteMovies();
                }
                else if (ACTION_FETCH_COMMENTS.equals(action))
                {
                    final long movieId = intent.getLongExtra(EXTRA_MOVIE_ID, 0);
                    handleFetchComments(movieId);
                }
                else if (ACTION_FETCH_VIDEOS.equals(action))
                {
                    final long movieId = intent.getLongExtra(EXTRA_MOVIE_ID, 0);
                    handleFetchVideos(movieId);
                }
                else if(ACTION_SET_FAVORITE.equals(action))
                {
                    final long movieId = intent.getLongExtra(EXTRA_MOVIE_ID, 0);
                    final boolean favorite = intent.getBooleanExtra(EXTRA_FAVORITE,false);
                    handleSetFavorite(movieId, favorite);
                }
            }
        }
        catch(RetrofitError e)
        {
            Log.e(LOGTAG,"Error occurred",e);
        }
        catch (RemoteException e)
        {
            Log.e(LOGTAG,"Error occurred",e);
        }
        catch(OperationApplicationException e)
        {
            Log.e(LOGTAG,"Error occurred",e);
        }
    }

    private void handleSetFavorite(long movieId, boolean favorite)
    {
        if(favorite)
        {
            FavoriteMoviesContentValues values = new FavoriteMoviesContentValues();
            values.putMovieId(movieId);

            mContentResolver.insert(FavoriteMoviesColumns.CONTENT_URI, values.values());

            downloadMovieArtwork(movieId);
        }
        else
        {
            FavoriteMoviesSelection sel = new FavoriteMoviesSelection();
            sel.movieId(movieId);
            sel.delete(mContentResolver);

            clearCachedMovieArtwork(movieId);
        }
    }

    private void clearCachedMovieArtwork(long movieId)
    {
        // TODO: it seems picasso caching is enough. If not should be implemented to explicitly download artwork to the cache dir
    }

    private void downloadMovieArtwork(long movieId)
    {
        // TODO
    }

    private void handleDeleteMovies()
    {
        Log.d(LOGTAG,"handleDeleteMovies");
        mContentResolver.delete(MovieColumns.CONTENT_URI, null, null);

        // This is necessary because the ondelete cascade constraints does not trigger the content provider
        // TODO: once the contentprovider is final , add this code to the content provider. If we add it now, it will be overriden if we regenerate it...
        mContentResolver.notifyChange(PopularMoviesColumns.CONTENT_URI, null, false);
        mContentResolver.notifyChange(TopratedMoviesColumns.CONTENT_URI, null, false);
        mContentResolver.notifyChange(UpcomingMoviesColumns.CONTENT_URI, null, false);
    }

    /**
     * Fetch all video for the specified movie
     * @param movieId
     */
    private void handleFetchVideos(long movieId) throws RemoteException, OperationApplicationException
    {
        Log.d(LOGTAG, "handleFetchVideos, movieId" + movieId);

        Videos videos = mTheMovieDbService.getVideos((int) movieId);

        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        // Delete existing entry to prevent conflict
        VideoSelection sel = new VideoSelection();
        sel.movieId(movieId).delete(mContentResolver);

        for( Video video : videos.getVideos())
        {
            VideoContentValues values = new VideoContentValues();

            values.putMovieId(movieId);
            values.putStringId(video.getId());
            values.putKey(video.getKey());
            values.putName(video.getName());
            values.putSite(video.getSite());
            values.putSize(video.getSize());
            values.putType(video.getType());

            batch.add(ContentProviderOperation.newInsert(VideoColumns.CONTENT_URI).withValues(values.values()).build());
        }

        mContentResolver.applyBatch(PopularMoviesProvider.AUTHORITY, batch);



    }

    /**
     * Fetch all comments for the specified movie
     * @param movieId
     */
    private void handleFetchComments(long movieId) throws RemoteException, OperationApplicationException
    {
        Log.d(LOGTAG,"handleFetchComments, movieId" + movieId);
        Log.d(LOGTAG, "handleFetchVideos, movieId" + movieId);

        // Delete existing entry to prevent conflict
        CommentSelection sel = new CommentSelection();
        sel.movieId(movieId).delete(mContentResolver);

        Reviews reviews = mTheMovieDbService.getReviews((int) movieId);

        ArrayList<ContentProviderOperation> batch = new ArrayList<>();
        for( Review review : reviews.getResults())
        {
            CommentContentValues values = new CommentContentValues();

            values.putMovieId(movieId);
            values.putStringId(review.getId());

            values.putContent(review.getContent());
            values.putAuthor(review.getAuthor());
            values.putUrl(review.getUrl());

            batch.add(ContentProviderOperation.newInsert(CommentColumns.CONTENT_URI).withValues(values.values()).build());
        }

        mContentResolver.applyBatch(PopularMoviesProvider.AUTHORITY, batch);
    }


    private static void insertUnique(HashMap<Integer,Movie> allMovies, List<Movie> movies)
    {
        for(Movie m : movies)
        {
            if(!allMovies.containsKey( m.getId()) )
            {
                allMovies.put(m.getId(), m);
            }
        }
    }

    static Collection<Movie> filterMovieIdUnique(List<Movie> movies)
    {
        HashMap<Integer,Movie> allMovies = new HashMap<>();
        insertUnique(allMovies,movies);
        return allMovies.values();
    }

    /**
     * Fetch all movies from the category that we care about
     */
    private void handleFetchMovies() throws RemoteException, OperationApplicationException
    {
        Log.d(LOGTAG,"handleFetchMovies");

        Log.d(LOGTAG,"Fetching data from themoviedb.org");
        // Fetch all movies

        List<Movie> popular = new ArrayList<>();
        List<Movie> topRated= new ArrayList<>();
        List<Movie> upcoming = new ArrayList<>();
        final int PAGE_COUNT=4;
        for(int page=1;page<=PAGE_COUNT;page++)
        {
            popular.addAll(mTheMovieDbService.discoverMovies("popularity.desc", page).getMovies());
            topRated.addAll(mTheMovieDbService.getTopRatedMovies(page).getMovies());
            upcoming.addAll(mTheMovieDbService.getUpcomingMovies(page).getMovies());
        }

        Log.d(LOGTAG,"Removing old data from DB");

        // Nuke the movie table content, we will recreate it from scratch.
        // Favorite movies must not be deleted!!

        // Delete the pivot tables
        /*mContentResolver.delete(PopularMoviesColumns.CONTENT_URI,null,null);
        mContentResolver.delete(TopratedMoviesColumns.CONTENT_URI, null, null);
        mContentResolver.delete(UpcomingMoviesColumns.CONTENT_URI,null,null);*/


        // Add all movies.
        // Remove duplicates entry fisrt

        HashMap<Integer,Movie> allMovies = new HashMap<>();
        insertUnique(allMovies,popular);
        insertUnique(allMovies, topRated);
        insertUnique(allMovies, upcoming);


        Log.d(LOGTAG, "Inserting movies into DB");
        createMovieMergeSolution(allMovies);


        Log.d(LOGTAG,"Creating category pivot table");
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();



        batch.add(ContentProviderOperation.newDelete(PopularMoviesColumns.CONTENT_URI).build());
        batch.add(ContentProviderOperation.newDelete(TopratedMoviesColumns.CONTENT_URI).build());
        batch.add(ContentProviderOperation.newDelete(UpcomingMoviesColumns.CONTENT_URI).build());

        // Create the popular pivot
        for(Movie movie : filterMovieIdUnique(popular))
        {

            PopularMoviesContentValues values = new PopularMoviesContentValues();
            values.putMovieId(movie.getId());

            batch.add(ContentProviderOperation.newInsert(PopularMoviesColumns.CONTENT_URI).withValues(values.values()).build());

        }

        // Create the popular pivot
        for(Movie movie : filterMovieIdUnique(topRated))
        {

            TopratedMoviesContentValues values = new TopratedMoviesContentValues();
            values.putMovieId( movie.getId() );

            batch.add(ContentProviderOperation.newInsert(TopratedMoviesColumns.CONTENT_URI).withValues(values.values()).build());

        }

        // Create the upcoming pivot
        for(Movie movie : filterMovieIdUnique(upcoming))
        {
            UpcomingMoviesContentValues values = new UpcomingMoviesContentValues();
            values.putMovieId( movie.getId() );

            batch.add(ContentProviderOperation.newInsert(UpcomingMoviesColumns.CONTENT_URI).withValues(values.values()).build());

        }

        mContentResolver.applyBatch(PopularMoviesProvider.AUTHORITY, batch);

        // TODO: test if this is necessary, the content provider should have called this?
        mContentResolver.notifyChange( PopularMoviesColumns.CONTENT_URI, null, false);
        mContentResolver.notifyChange( TopratedMoviesColumns.CONTENT_URI, null, false);
        mContentResolver.notifyChange( UpcomingMoviesColumns.CONTENT_URI, null, false);


        Utils.setSyncMovieTime(this);
    }


    private void createMovieMergeSolution( Map<Integer,Movie> movies ) throws RemoteException, OperationApplicationException
    {
        ArrayList<ContentProviderOperation> batch = new ArrayList<>();

        List<Long> favoriteIds = new ArrayList<>();

        FavoriteMoviesSelection favoriteSelection = new FavoriteMoviesSelection();
        MovieSelection allSelection = new MovieSelection();

        // make a list of favoites
        FavoriteMoviesCursor favCursor = favoriteSelection.query(mContentResolver, new String[]{FavoriteMoviesColumns.MOVIE_ID});
        while(favCursor.moveToNext())
        {
            favoriteIds.add(favCursor.getMovieId());
        }
        favCursor.close();

        // Check if we must update of delete movies
        MovieCursor cursor = allSelection.query(mContentResolver, new String[]{MovieColumns._ID});
        while(cursor.moveToNext())
        {
            long movieId = cursor.getId();

            Movie movie = movies.get(movieId);
            if(movie!=null)
            {
                // Remove it now so we don't add it later
                movies.remove(movieId);
                // update
                MovieContentValues values = Utils.movieToContentValues(movie,false);
                Uri uri = MovieColumns.CONTENT_URI.buildUpon().appendPath(Long.toString(movieId)).build();
                ContentProviderOperation op = ContentProviderOperation.newUpdate(uri).withValues(values.values()).build();
                batch.add(op);
            }
            else
            {
                // delete if not a favorite
                if( !favoriteIds.contains(movieId) )
                {
                    Uri uri = MovieColumns.CONTENT_URI.buildUpon().appendPath(Long.toString(movieId)).build();
                    ContentProviderOperation op = ContentProviderOperation.newDelete(uri).build();
                    batch.add(op);
                }


            }

        }
        cursor.close();

        // add remaining movies
        for( Movie movie : movies.values())
        {

            long id = movie.getId();
            if( favoriteIds.contains(id) )
            {
                //update
                MovieContentValues values = Utils.movieToContentValues(movie, false);
                Uri uri = MovieColumns.CONTENT_URI.buildUpon().appendPath(Long.toString(id)).build();
                ContentProviderOperation op = ContentProviderOperation.newUpdate(uri).withValues(values.values()).build();
                batch.add(op);
            }
            else
            {
                //insert
                MovieContentValues values = Utils.movieToContentValues(movie,true);
                ContentProviderOperation op = ContentProviderOperation.newInsert(MovieColumns.CONTENT_URI).withValues(values.values()).build();
                batch.add(op);
            }



        }

        mContentResolver.applyBatch(PopularMoviesProvider.AUTHORITY, batch);

        // TODO: test if this is necessary, the content provider should have called this?
        mContentResolver.notifyChange( MovieColumns.CONTENT_URI, // URI where data was modified
                null,                           // No local observer
                false);


    }

}
