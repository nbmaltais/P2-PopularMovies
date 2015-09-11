package ca.nbmaltais.popularmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.provider.video.VideoCursor;

/**
 * Created by Nicolas on 2015-07-26.
 */
public class VideoItemViewHolder extends RecyclerView.ViewHolder
{
    private TextView mNameView;
    private MovieDetailAdapterCallbacks mCallbacks;
    private String mVideoKey;

    public VideoItemViewHolder(View itemView, MovieDetailAdapterCallbacks callbacks)
    {
        super(itemView);
        mCallbacks = callbacks;
        mNameView = (TextView)itemView.findViewById(R.id.video_name);

        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mCallbacks!=null)
                    mCallbacks.onVideoClicked(mVideoKey);
            }
        });
    }

    public void bind(VideoCursor cursor)
    {
        mNameView.setText(cursor.getName());
        mVideoKey = cursor.getKey();
    }
}
