package ca.nbmaltais.popularmovies.adapter;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ca.nbmaltais.popularmovies.R;

/**
 * Created by Nicolas on 2015-07-26.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder
{
    TextView mHeader;

    public HeaderViewHolder(View itemView)
    {
        super(itemView);
        mHeader = (TextView)itemView.findViewById(R.id.header);
    }

    public void setHeaderText(String text)
    {
        mHeader.setText(text);
    }

    public void setHeaderText(@StringRes int text)
    {
        mHeader.setText(text);
    }
}
