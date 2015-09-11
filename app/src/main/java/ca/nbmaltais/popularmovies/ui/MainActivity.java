package ca.nbmaltais.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ca.nbmaltais.popularmovies.MovieLoader;
import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.adapter.CategorySpinnerAdapter;
import ca.nbmaltais.popularmovies.provider.movie.MovieColumns;


public class MainActivity extends AppCompatActivity implements MovieListFragment.Host
{
    static final String LOGTAG = MainActivity.class.getSimpleName();
    private static final String KEY_MOVIES_TYPE = "KEY_MOVIES_TYPE";
    private boolean mTwoPanes=false;
    private Spinner mCategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar!=null)
        {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mTwoPanes = findViewById(R.id.movie_detail_fragment) != null;

        //  Setup spinner
        mCategorySpinner = (Spinner)findViewById(R.id.category_spinner);

        String[] categories = getResources().getStringArray(R.array.categories_array);

        CategorySpinnerAdapter adapter = new CategorySpinnerAdapter(this);
        mCategorySpinner.setAdapter(adapter);
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                getMovieListFragment().selectMovieType((int)id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        int type=MovieLoader.POPULAR_MOVIES;
        if(savedInstanceState!=null)
        {
            //noinspection ResourceType
            type = savedInstanceState.getInt(KEY_MOVIES_TYPE, type);
        }

        mCategorySpinner.setSelection(type);
    }

    MovieListFragment getMovieListFragment()
    {
        return (MovieListFragment)getFragmentManager().findFragmentById(R.id.movie_list_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieClicked(long movieId, View posterView)
    {
        if(mTwoPanes)
        {
            MovieDetailFragment fragment = MovieDetailFragment.newInstance(movieId,false);
            getFragmentManager().beginTransaction().replace(R.id.movie_detail_fragment,fragment).commit();
        }
        else
        {
            startDetailActivity(movieId,posterView);
        }
    }

    /**
     * Starts the detail activity with a transition
     * TODO: transition problems:
     * 1 - The shared element transition does not start from the right position. The poster image seems to come from the edge of the screen
     * 2 - From the detail activity, pressing back trigger the reverse transition but pressing up does not
     * 3 - When the transition starts, the image is drawn above the toobar, even if I included the fix found int the topeka sample
     * @param movieId
     * @param posterView
     */
    void startDetailActivity( long movieId, View posterView)
    {
        Log.d(LOGTAG, "Starting detail activity, id=" + movieId);

        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.setData(MovieColumns.CONTENT_URI.buildUpon().appendPath(Long.toString(movieId)).build());
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movieId);


        //==============================
        // The next bit is taken from the topeka google sample, it prevent a UI glitch
        // FIXME: does not seem to work...

        // Avoid system UI glitches as described here:
        // https://plus.google.com/+AlexLockwood/posts/RPtwZ5nNebb
        View decor = this.getWindow().getDecorView();
        View toolbar = this.findViewById(R.id.toolbar);
        View statusBar = decor.findViewById(android.R.id.statusBarBackground);
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);

        List<Pair> participants = new ArrayList<>(3);
        participants.add(new Pair<>(posterView, this.getString(R.string.movie_poster_transition_name)));
        addNonNullViewToTransitionParticipants(toolbar, participants);
        addNonNullViewToTransitionParticipants(statusBar, participants);
        addNonNullViewToTransitionParticipants(navBar, participants);

        @SuppressWarnings("unchecked")
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                participants.toArray(new Pair[participants.size()]));

        // Starts the activity with the participants, animating from one to the other.
        final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
        //==============================

        ActivityCompat.startActivity(this, intent, transitionBundle);

    }

    // Taken from the topeka google sample, it prevent a UI glitch
    private void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
        if (view == null) {
            return;
        }
        participants.add(new Pair<>(view, ViewCompat.getTransitionName(view)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_MOVIES_TYPE, getMovieListFragment().getMovieType());
    }
}
