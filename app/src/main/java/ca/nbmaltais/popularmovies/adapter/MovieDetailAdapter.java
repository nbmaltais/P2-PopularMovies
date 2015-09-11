package ca.nbmaltais.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.nbmaltais.popularmovies.R;
import ca.nbmaltais.popularmovies.provider.comment.CommentCursor;
import ca.nbmaltais.popularmovies.provider.movie.MovieCursor;
import ca.nbmaltais.popularmovies.provider.video.VideoCursor;

/**
 * Created by Nicolas on 2015-07-26.
 */
public class MovieDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int VIEW_TYPE_MOVIE_INFO=0;
    private static final int VIEW_TYPE_VIDEO=1;
    private static final int VIEW_TYPE_COMMENT=2;
    private static final int VIEW_TYPE_VIDEO_HEADER = 3;
    private static final int VIEW_TYPE_COMMENT_HEADER = 4;
    private static final int VIEW_TYPE_FOOTER = 5;

    private MovieCursor mMovie;
    private VideoCursor mVideos;
    private CommentCursor mComments;

    private int mMovieInfoPos;
    private int mVideoHeaderPos;
    private int mVideoStartPos;
    private int mVideoEndPos;

    private int mCommentHeaderPos;
    private int mCommentStartPos;
    private int mCommentEndPos;

    private int mFooterPos;

    private MovieDetailAdapterCallbacks mCallbacks;
    private boolean mIsFavorite=false;

    public VideoCursor getVideos()
    {
        return mVideos;
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder
    {

        public EmptyViewHolder(View itemView)
        {
            super(itemView);
        }
    }


    public MovieDetailAdapter(MovieDetailAdapterCallbacks callbacks)
    {
        mCallbacks = callbacks;
        updateSections();
    }

    @Override
    public int getItemViewType(int position)
    {
        if(position==mMovieInfoPos)
            return VIEW_TYPE_MOVIE_INFO;
        else if( position == mVideoHeaderPos )
            return VIEW_TYPE_VIDEO_HEADER;
        else if( position == mCommentHeaderPos )
            return VIEW_TYPE_COMMENT_HEADER;
        else if( position >= mVideoStartPos && position <= mVideoEndPos)
            return VIEW_TYPE_VIDEO;
        else if( position >= mCommentStartPos && position <= mCommentEndPos)
            return VIEW_TYPE_COMMENT;
        else if( position == mFooterPos )
            return VIEW_TYPE_FOOTER;
        else
            throw new RuntimeException("Unexpected position");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        RecyclerView.ViewHolder viewHolder=null;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layout;
        switch(viewType)
        {
            case VIEW_TYPE_COMMENT:
                view = inflater.inflate(R.layout.item_comment,parent,false);
                viewHolder = new CommentItemViewHolder(view);
                break;

            case VIEW_TYPE_VIDEO_HEADER:
            case VIEW_TYPE_COMMENT_HEADER:
                view = inflater.inflate(R.layout.item_detail_section_header,parent,false);
                viewHolder = new HeaderViewHolder(view);
                break;

            case VIEW_TYPE_VIDEO:
                view = inflater.inflate(R.layout.item_video,parent,false);
                viewHolder = new VideoItemViewHolder(view,mCallbacks);
                break;

            case VIEW_TYPE_MOVIE_INFO:
                view = inflater.inflate(R.layout.item_movie_detail,parent,false);
                viewHolder = new MovieInfoViewHolder(view,mCallbacks);
                break;
            case VIEW_TYPE_FOOTER:
                // This view is there only to correct a bug where the last view is not fully displayed
                view = inflater.inflate(R.layout.item_footer_space,parent,false);
                viewHolder = new EmptyViewHolder(view);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        int viewType = getItemViewType(position);
        /*if( holder instanceof MovieItemViewHolder )
        {
            if(mMovie!=null)
            {
                MovieItemViewHolder mivh = (MovieItemViewHolder) holder;
                mivh.bind(mMovie);
            }
        }*/

        switch(viewType)
        {
            case VIEW_TYPE_COMMENT:
            {
                CommentItemViewHolder vh = (CommentItemViewHolder)holder;
                int commentPos = position - mCommentStartPos;
                if(mComments.moveToPosition(commentPos))
                {
                    vh.bind(mComments);
                }
                break;
            }
            case VIEW_TYPE_VIDEO_HEADER:
            {
                HeaderViewHolder hvh = (HeaderViewHolder)holder;
                hvh.setHeaderText(R.string.video_section_header);
                break;
            }
            case VIEW_TYPE_COMMENT_HEADER:
            {
                HeaderViewHolder hvh = (HeaderViewHolder)holder;
                hvh.setHeaderText(R.string.comments_section_header);
                break;
            }
            case VIEW_TYPE_VIDEO:
            {
                VideoItemViewHolder vh = (VideoItemViewHolder)holder;
                int videoPos = position - mVideoStartPos;
                if(mVideos.moveToPosition(videoPos))
                {
                    vh.bind( mVideos );
                }
                break;
            }

            case VIEW_TYPE_MOVIE_INFO:
            {
                if(mMovie!=null)
                {
                    MovieInfoViewHolder mivh = (MovieInfoViewHolder) holder;
                    mivh.bind(mMovie,mIsFavorite);
                }
                break;
            }

        }
    }

    @Override
    public int getItemCount()
    {
        int count=1;

        if(mMovie!=null)
            count+=1;
        if(mVideos!=null)
            count += mVideos.getCount() + 1;
        if(mComments!=null)
            count += mComments.getCount() + 1;

        return count;
    }

    private void updateSections()
    {
        int lastPos=0;
        if(mMovie==null)
            mMovieInfoPos=-1;
        else
            mMovieInfoPos=0;
        lastPos=mMovieInfoPos;

        mVideoHeaderPos = mMovieInfoPos+1;
        if(mVideos==null)
        {
            mVideoHeaderPos=-1;
            mVideoStartPos = -1;
            mVideoEndPos = -1;
        }
        else
        {
            mVideoHeaderPos=lastPos+1;
            mVideoStartPos = mVideoHeaderPos+1;
            mVideoEndPos = mVideoStartPos+mVideos.getCount()-1;
        }
        lastPos = Math.max(lastPos, mVideoEndPos);

        if(mComments==null)
        {
            mCommentHeaderPos=-1;
            mCommentStartPos=-1;
            mCommentEndPos = -1;
        }
        else
        {
            mCommentHeaderPos = lastPos+1;
            mCommentStartPos=mCommentHeaderPos+1;
            mCommentEndPos = mCommentStartPos+mComments.getCount()-1;
        }
        lastPos = Math.max(lastPos, mCommentEndPos);
        mFooterPos = lastPos +1;
    }


    public void setMovie(MovieCursor movie)
    {
        mMovie=movie;
        updateSections();
        notifyDataSetChanged();
    }

    public void setMovieIsFavorite(boolean isFavorite)
    {
        mIsFavorite = isFavorite;
        notifyDataSetChanged();
    }

    public void setVideos(VideoCursor videoCursor)
    {
        mVideos = videoCursor;
        updateSections();
        notifyDataSetChanged();
    }

    public void setComments( CommentCursor commentsCursor)
    {
        mComments=commentsCursor;
        updateSections();
        notifyDataSetChanged();
    }
}
