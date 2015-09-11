package ca.nbmaltais.popularmovies.provider.favoritemovies;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ca.nbmaltais.popularmovies.provider.base.AbstractSelection;
import ca.nbmaltais.popularmovies.provider.movie.*;

/**
 * Selection for the {@code favorite_movies} table.
 */
public class FavoriteMoviesSelection extends AbstractSelection<FavoriteMoviesSelection> {
    @Override
    protected Uri baseUri() {
        return FavoriteMoviesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteMoviesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteMoviesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteMoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public FavoriteMoviesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code FavoriteMoviesCursor} object, which is positioned before the first entry, or null.
     */
    public FavoriteMoviesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new FavoriteMoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public FavoriteMoviesCursor query(Context context) {
        return query(context, null);
    }


    public FavoriteMoviesSelection id(long... value) {
        addEquals("favorite_movies." + FavoriteMoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection idNot(long... value) {
        addNotEquals("favorite_movies." + FavoriteMoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection orderById(boolean desc) {
        orderBy("favorite_movies." + FavoriteMoviesColumns._ID, desc);
        return this;
    }

    public FavoriteMoviesSelection orderById() {
        return orderById(false);
    }

    public FavoriteMoviesSelection movieId(long... value) {
        addEquals(FavoriteMoviesColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection movieIdNot(long... value) {
        addNotEquals(FavoriteMoviesColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection movieIdGt(long value) {
        addGreaterThan(FavoriteMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteMoviesSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(FavoriteMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteMoviesSelection movieIdLt(long value) {
        addLessThan(FavoriteMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteMoviesSelection movieIdLtEq(long value) {
        addLessThanOrEquals(FavoriteMoviesColumns.MOVIE_ID, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieId(boolean desc) {
        orderBy(FavoriteMoviesColumns.MOVIE_ID, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieId() {
        orderBy(FavoriteMoviesColumns.MOVIE_ID, false);
        return this;
    }

    public FavoriteMoviesSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public FavoriteMoviesSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteMoviesSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteMoviesSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteMoviesSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteMoviesSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteMoviesSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public FavoriteMoviesSelection movieVoteAverage(double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection movieVoteAverageNot(double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteMoviesSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteMoviesSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteMoviesSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public FavoriteMoviesSelection movieVoteCount(int... value) {
        addEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection movieVoteCountNot(int... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteMoviesSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteMoviesSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteMoviesSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public FavoriteMoviesSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteMoviesSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteMoviesSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteMoviesSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteMoviesSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteMoviesSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public FavoriteMoviesSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public FavoriteMoviesSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public FavoriteMoviesSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public FavoriteMoviesSelection moviePopularity(double... value) {
        addEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection moviePopularityNot(double... value) {
        addNotEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public FavoriteMoviesSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteMoviesSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteMoviesSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteMoviesSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public FavoriteMoviesSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public FavoriteMoviesSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }
}
