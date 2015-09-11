package ca.nbmaltais.popularmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.provider.comment.CommentCursor;

/**
 * Created by Nicolas on 2015-07-27.
 */
public class CommentItemViewHolder extends RecyclerView.ViewHolder
{
    TextView mContent;
    TextView mAuthor;
    public CommentItemViewHolder(View itemView)
    {
        super(itemView);
        mAuthor = (TextView) itemView.findViewById(R.id.comment_author);
        mContent = (TextView) itemView.findViewById(R.id.comment_content);
    }


    public void bind( CommentCursor cursor)
    {
        mContent.setText(cursor.getContent());
        mAuthor.setText(itemView.getContext().getString(R.string.comment_author, cursor.getAuthor())  );
    }
}
