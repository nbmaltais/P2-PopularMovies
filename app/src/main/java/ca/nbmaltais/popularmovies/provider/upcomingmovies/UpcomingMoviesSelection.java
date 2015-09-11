package ca.nbmaltais.popularmovies.provider.upcomingmovies;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ca.nbmaltais.popularmovies.provider.base.AbstractSelection;
import ca.nbmaltais.popularmovies.provider.movie.*;

/**
 * Selection for the {@code upcoming_movies} table.
 */
public class UpcomingMoviesSelection extends AbstractSelection<UpcomingMoviesSelection> {
    @Override
    protected Uri baseUri() {
        return UpcomingMoviesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UpcomingMoviesCursor} object, which is positioned before the first entry, or null.
     */
    public UpcomingMoviesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UpcomingMoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public UpcomingMoviesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code UpcomingMoviesCursor} object, which is positioned before the first entry, or null.
     */
    public UpcomingMoviesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new UpcomingMoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public UpcomingMoviesCursor query(Context context) {
        return query(context, null);
    }


    public UpcomingMoviesSelection id(long... value) {
        addEquals("upcoming_movies." + UpcomingMoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection idNot(long... value) {
        addNotEquals("upcoming_movies." + UpcomingMoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection orderById(boolean desc) {
        orderBy("upcoming_movies." + UpcomingMoviesColumns._ID, desc);
        return this;
    }

    public UpcomingMoviesSelection orderById() {
        return orderById(false);
    }

    public UpcomingMoviesSelection movieId(long... value) {
        addEquals(UpcomingMoviesColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection movieIdNot(long... value) {
        addNotEquals(UpcomingMoviesColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection movieIdGt(long value) {
        addGreaterThan(UpcomingMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public UpcomingMoviesSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(UpcomingMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public UpcomingMoviesSelection movieIdLt(long value) {
        addLessThan(UpcomingMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public UpcomingMoviesSelection movieIdLtEq(long value) {
        addLessThanOrEquals(UpcomingMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieId(boolean desc) {
        orderBy(UpcomingMoviesColumns.MOVIE_ID, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieId() {
        orderBy(UpcomingMoviesColumns.MOVIE_ID, false);
        return this;
    }

    public UpcomingMoviesSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public UpcomingMoviesSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public UpcomingMoviesSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public UpcomingMoviesSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public UpcomingMoviesSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public UpcomingMoviesSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public UpcomingMoviesSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public UpcomingMoviesSelection movieVoteAverage(double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection movieVoteAverageNot(double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public UpcomingMoviesSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public UpcomingMoviesSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public UpcomingMoviesSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public UpcomingMoviesSelection movieVoteCount(int... value) {
        addEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection movieVoteCountNot(int... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public UpcomingMoviesSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public UpcomingMoviesSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public UpcomingMoviesSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public UpcomingMoviesSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public UpcomingMoviesSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public UpcomingMoviesSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public UpcomingMoviesSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public UpcomingMoviesSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public UpcomingMoviesSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public UpcomingMoviesSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public UpcomingMoviesSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public UpcomingMoviesSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public UpcomingMoviesSelection moviePopularity(double... value) {
        addEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection moviePopularityNot(double... value) {
        addNotEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public UpcomingMoviesSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public UpcomingMoviesSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public UpcomingMoviesSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public UpcomingMoviesSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public UpcomingMoviesSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public UpcomingMoviesSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }
}
