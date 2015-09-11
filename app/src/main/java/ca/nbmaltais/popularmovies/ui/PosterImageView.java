package ca.nbmaltais.popularmovies.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Nicolas on 2015-08-04.
 * Image view with a fixed width/height ratio matching poster images.
 * Seems to fix some layout measurements problems.
 * Inspired by https://github.com/antoniolg/MaterializeYourApp/blob/master/app/src/main/java/com/antonioleiva/materializeyourapp/SquareImageView.java
 *
 */
public class PosterImageView extends ImageView
{

    public PosterImageView(Context context) {
        super(context);
    }

    public PosterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PosterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = width*277/185;
        setMeasuredDimension(width, height);
    }
}
