package ca.nbmaltais.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ca.nbmaltais.popularmovies.MovieLoader;
import ca.nbmaltais.popularmovies.R;

/**
 * Created by Nicolas on 2015-07-29.
 * Inspired by https://blog.danielbetts.net/2015/01/02/material-design-spinner-toolbar-style-fix/
 */
public class CategorySpinnerAdapter extends BaseAdapter
{
    final String mTitlePopular;
    final String mTitleTopRated;
    final String mTitleUpcoming;
    final String mTitleFavorite;

    public CategorySpinnerAdapter( Context ctx)
    {
        mTitlePopular = ctx.getString(R.string.title_popular_movies);
        mTitleTopRated = ctx.getString(R.string.title_toprated_movies);
        mTitleUpcoming = ctx.getString(R.string.title_upcoming_movies);
        mTitleFavorite = ctx.getString(R.string.title_favorite_movies);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        switch(position)
        {
            case 0: return mTitlePopular;
            case 1: return mTitleTopRated;
            case 2: return mTitleUpcoming;
            case 3: return mTitleFavorite;
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        switch(position)
        {
            case 0: return MovieLoader.POPULAR_MOVIES;
            case 1: return MovieLoader.TOPRATED_MOVIES;
            case 2: return MovieLoader.UPCOMING_MOVIES;
            case 3: return MovieLoader.FAVORITE_MOVIES;
        }

        return -1;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        Context ctx = parent.getContext();

        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = LayoutInflater.from(ctx).inflate(R.layout.toolbar_spinner_dropdown, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Context ctx = parent.getContext();

        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = LayoutInflater.from(ctx).inflate(R.layout. toolbar_spinner_item, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        return view;
    }

    private String getTitle(int position) {
        return (String)getItem(position);
    }
}