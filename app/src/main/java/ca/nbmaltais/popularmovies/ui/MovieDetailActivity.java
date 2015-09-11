package ca.nbmaltais.popularmovies.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;

import ca.nbmaltais.popularmovies.R;

public class MovieDetailActivity extends AppCompatActivity
{

    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initActivityTransitions();

        setContentView(R.layout.activity_movie_detail);


        Intent intent = getIntent();
        if(savedInstanceState==null)
        {
            MovieDetailFragment fragment =  MovieDetailFragment.newInstance(intent.getLongExtra(EXTRA_MOVIE_ID,-1),true);

            getFragmentManager().beginTransaction().add(R.id.movie_detail_fragment,fragment).commit();

            supportPostponeEnterTransition();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
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
}
