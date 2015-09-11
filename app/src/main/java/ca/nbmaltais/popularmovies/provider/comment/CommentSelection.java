package ca.nbmaltais.popularmovies.provider.comment;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ca.nbmaltais.popularmovies.provider.base.AbstractSelection;
import ca.nbmaltais.popularmovies.provider.movie.*;

/**
 * Selection for the {@code comment} table.
 */
public class CommentSelection extends AbstractSelection<CommentSelection> {
    @Override
    protected Uri baseUri() {
        return CommentColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CommentCursor} object, which is positioned before the first entry, or null.
     */
    public CommentCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CommentCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public CommentCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code CommentCursor} object, which is positioned before the first entry, or null.
     */
    public CommentCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new CommentCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public CommentCursor query(Context context) {
        return query(context, null);
    }


    public CommentSelection id(long... value) {
        addEquals("comment." + CommentColumns._ID, toObjectArray(value));
        return this;
    }

    public CommentSelection idNot(long... value) {
        addNotEquals("comment." + CommentColumns._ID, toObjectArray(value));
        return this;
    }

    public CommentSelection orderById(boolean desc) {
        orderBy("comment." + CommentColumns._ID, desc);
        return this;
    }

    public CommentSelection orderById() {
        return orderById(false);
    }

    public CommentSelection movieId(long... value) {
        addEquals(CommentColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public CommentSelection movieIdNot(long... value) {
        addNotEquals(CommentColumns.MOVIE_ID, toObjectArray(value));
        return this;
    }

    public CommentSelection movieIdGt(long value) {
        addGreaterThan(CommentColumns.MOVIE_ID, value);
        return this;
    }

    public CommentSelection movieIdGtEq(long value) {
        addGreaterThanOrEquals(CommentColumns.MOVIE_ID, value);
        return this;
    }

    public CommentSelection movieIdLt(long value) {
        addLessThan(CommentColumns.MOVIE_ID, value);
        return this;
    }

    public CommentSelection movieIdLtEq(long value) {
        addLessThanOrEquals(CommentColumns.MOVIE_ID, value);
        return this;
    }

    public CommentSelection orderByMovieId(boolean desc) {
        orderBy(CommentColumns.MOVIE_ID, desc);
        return this;
    }

    public CommentSelection orderByMovieId() {
        orderBy(CommentColumns.MOVIE_ID, false);
        return this;
    }

    public CommentSelection movieOriginalTitle(String... value) {
        addEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public CommentSelection movieOriginalTitleNot(String... value) {
        addNotEquals(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public CommentSelection movieOriginalTitleLike(String... value) {
        addLike(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public CommentSelection movieOriginalTitleContains(String... value) {
        addContains(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public CommentSelection movieOriginalTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public CommentSelection movieOriginalTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.ORIGINAL_TITLE, value);
        return this;
    }

    public CommentSelection orderByMovieOriginalTitle(boolean desc) {
        orderBy(MovieColumns.ORIGINAL_TITLE, desc);
        return this;
    }

    public CommentSelection orderByMovieOriginalTitle() {
        orderBy(MovieColumns.ORIGINAL_TITLE, false);
        return this;
    }

    public CommentSelection movieOverview(String... value) {
        addEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public CommentSelection movieOverviewNot(String... value) {
        addNotEquals(MovieColumns.OVERVIEW, value);
        return this;
    }

    public CommentSelection movieOverviewLike(String... value) {
        addLike(MovieColumns.OVERVIEW, value);
        return this;
    }

    public CommentSelection movieOverviewContains(String... value) {
        addContains(MovieColumns.OVERVIEW, value);
        return this;
    }

    public CommentSelection movieOverviewStartsWith(String... value) {
        addStartsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public CommentSelection movieOverviewEndsWith(String... value) {
        addEndsWith(MovieColumns.OVERVIEW, value);
        return this;
    }

    public CommentSelection orderByMovieOverview(boolean desc) {
        orderBy(MovieColumns.OVERVIEW, desc);
        return this;
    }

    public CommentSelection orderByMovieOverview() {
        orderBy(MovieColumns.OVERVIEW, false);
        return this;
    }

    public CommentSelection movieVoteAverage(double... value) {
        addEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public CommentSelection movieVoteAverageNot(double... value) {
        addNotEquals(MovieColumns.VOTE_AVERAGE, toObjectArray(value));
        return this;
    }

    public CommentSelection movieVoteAverageGt(double value) {
        addGreaterThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public CommentSelection movieVoteAverageGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public CommentSelection movieVoteAverageLt(double value) {
        addLessThan(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public CommentSelection movieVoteAverageLtEq(double value) {
        addLessThanOrEquals(MovieColumns.VOTE_AVERAGE, value);
        return this;
    }

    public CommentSelection orderByMovieVoteAverage(boolean desc) {
        orderBy(MovieColumns.VOTE_AVERAGE, desc);
        return this;
    }

    public CommentSelection orderByMovieVoteAverage() {
        orderBy(MovieColumns.VOTE_AVERAGE, false);
        return this;
    }

    public CommentSelection movieVoteCount(int... value) {
        addEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public CommentSelection movieVoteCountNot(int... value) {
        addNotEquals(MovieColumns.VOTE_COUNT, toObjectArray(value));
        return this;
    }

    public CommentSelection movieVoteCountGt(int value) {
        addGreaterThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public CommentSelection movieVoteCountGtEq(int value) {
        addGreaterThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public CommentSelection movieVoteCountLt(int value) {
        addLessThan(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public CommentSelection movieVoteCountLtEq(int value) {
        addLessThanOrEquals(MovieColumns.VOTE_COUNT, value);
        return this;
    }

    public CommentSelection orderByMovieVoteCount(boolean desc) {
        orderBy(MovieColumns.VOTE_COUNT, desc);
        return this;
    }

    public CommentSelection orderByMovieVoteCount() {
        orderBy(MovieColumns.VOTE_COUNT, false);
        return this;
    }

    public CommentSelection movieReleaseDate(String... value) {
        addEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public CommentSelection movieReleaseDateNot(String... value) {
        addNotEquals(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public CommentSelection movieReleaseDateLike(String... value) {
        addLike(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public CommentSelection movieReleaseDateContains(String... value) {
        addContains(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public CommentSelection movieReleaseDateStartsWith(String... value) {
        addStartsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public CommentSelection movieReleaseDateEndsWith(String... value) {
        addEndsWith(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public CommentSelection orderByMovieReleaseDate(boolean desc) {
        orderBy(MovieColumns.RELEASE_DATE, desc);
        return this;
    }

    public CommentSelection orderByMovieReleaseDate() {
        orderBy(MovieColumns.RELEASE_DATE, false);
        return this;
    }

    public CommentSelection movieBackdropPath(String... value) {
        addEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public CommentSelection movieBackdropPathNot(String... value) {
        addNotEquals(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public CommentSelection movieBackdropPathLike(String... value) {
        addLike(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public CommentSelection movieBackdropPathContains(String... value) {
        addContains(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public CommentSelection movieBackdropPathStartsWith(String... value) {
        addStartsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public CommentSelection movieBackdropPathEndsWith(String... value) {
        addEndsWith(MovieColumns.BACKDROP_PATH, value);
        return this;
    }

    public CommentSelection orderByMovieBackdropPath(boolean desc) {
        orderBy(MovieColumns.BACKDROP_PATH, desc);
        return this;
    }

    public CommentSelection orderByMovieBackdropPath() {
        orderBy(MovieColumns.BACKDROP_PATH, false);
        return this;
    }

    public CommentSelection moviePosterPath(String... value) {
        addEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public CommentSelection moviePosterPathNot(String... value) {
        addNotEquals(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public CommentSelection moviePosterPathLike(String... value) {
        addLike(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public CommentSelection moviePosterPathContains(String... value) {
        addContains(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public CommentSelection moviePosterPathStartsWith(String... value) {
        addStartsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public CommentSelection moviePosterPathEndsWith(String... value) {
        addEndsWith(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public CommentSelection orderByMoviePosterPath(boolean desc) {
        orderBy(MovieColumns.POSTER_PATH, desc);
        return this;
    }

    public CommentSelection orderByMoviePosterPath() {
        orderBy(MovieColumns.POSTER_PATH, false);
        return this;
    }

    public CommentSelection movieTitle(String... value) {
        addEquals(MovieColumns.TITLE, value);
        return this;
    }

    public CommentSelection movieTitleNot(String... value) {
        addNotEquals(MovieColumns.TITLE, value);
        return this;
    }

    public CommentSelection movieTitleLike(String... value) {
        addLike(MovieColumns.TITLE, value);
        return this;
    }

    public CommentSelection movieTitleContains(String... value) {
        addContains(MovieColumns.TITLE, value);
        return this;
    }

    public CommentSelection movieTitleStartsWith(String... value) {
        addStartsWith(MovieColumns.TITLE, value);
        return this;
    }

    public CommentSelection movieTitleEndsWith(String... value) {
        addEndsWith(MovieColumns.TITLE, value);
        return this;
    }

    public CommentSelection orderByMovieTitle(boolean desc) {
        orderBy(MovieColumns.TITLE, desc);
        return this;
    }

    public CommentSelection orderByMovieTitle() {
        orderBy(MovieColumns.TITLE, false);
        return this;
    }

    public CommentSelection moviePopularity(double... value) {
        addEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public CommentSelection moviePopularityNot(double... value) {
        addNotEquals(MovieColumns.POPULARITY, toObjectArray(value));
        return this;
    }

    public CommentSelection moviePopularityGt(double value) {
        addGreaterThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public CommentSelection moviePopularityGtEq(double value) {
        addGreaterThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public CommentSelection moviePopularityLt(double value) {
        addLessThan(MovieColumns.POPULARITY, value);
        return this;
    }

    public CommentSelection moviePopularityLtEq(double value) {
        addLessThanOrEquals(MovieColumns.POPULARITY, value);
        return this;
    }

    public CommentSelection orderByMoviePopularity(boolean desc) {
        orderBy(MovieColumns.POPULARITY, desc);
        return this;
    }

    public CommentSelection orderByMoviePopularity() {
        orderBy(MovieColumns.POPULARITY, false);
        return this;
    }

    public CommentSelection stringId(String... value) {
        addEquals(CommentColumns.STRING_ID, value);
        return this;
    }

    public CommentSelection stringIdNot(String... value) {
        addNotEquals(CommentColumns.STRING_ID, value);
        return this;
    }

    public CommentSelection stringIdLike(String... value) {
        addLike(CommentColumns.STRING_ID, value);
        return this;
    }

    public CommentSelection stringIdContains(String... value) {
        addContains(CommentColumns.STRING_ID, value);
        return this;
    }

    public CommentSelection stringIdStartsWith(String... value) {
        addStartsWith(CommentColumns.STRING_ID, value);
        return this;
    }

    public CommentSelection stringIdEndsWith(String... value) {
        addEndsWith(CommentColumns.STRING_ID, value);
        return this;
    }

    public CommentSelection orderByStringId(boolean desc) {
        orderBy(CommentColumns.STRING_ID, desc);
        return this;
    }

    public CommentSelection orderByStringId() {
        orderBy(CommentColumns.STRING_ID, false);
        return this;
    }

    public CommentSelection author(String... value) {
        addEquals(CommentColumns.AUTHOR, value);
        return this;
    }

    public CommentSelection authorNot(String... value) {
        addNotEquals(CommentColumns.AUTHOR, value);
        return this;
    }

    public CommentSelection authorLike(String... value) {
        addLike(CommentColumns.AUTHOR, value);
        return this;
    }

    public CommentSelection authorContains(String... value) {
        addContains(CommentColumns.AUTHOR, value);
        return this;
    }

    public CommentSelection authorStartsWith(String... value) {
        addStartsWith(CommentColumns.AUTHOR, value);
        return this;
    }

    public CommentSelection authorEndsWith(String... value) {
        addEndsWith(CommentColumns.AUTHOR, value);
        return this;
    }

    public CommentSelection orderByAuthor(boolean desc) {
        orderBy(CommentColumns.AUTHOR, desc);
        return this;
    }

    public CommentSelection orderByAuthor() {
        orderBy(CommentColumns.AUTHOR, false);
        return this;
    }

    public CommentSelection content(String... value) {
        addEquals(CommentColumns.CONTENT, value);
        return this;
    }

    public CommentSelection contentNot(String... value) {
        addNotEquals(CommentColumns.CONTENT, value);
        return this;
    }

    public CommentSelection contentLike(String... value) {
        addLike(CommentColumns.CONTENT, value);
        return this;
    }

    public CommentSelection contentContains(String... value) {
        addContains(CommentColumns.CONTENT, value);
        return this;
    }

    public CommentSelection contentStartsWith(String... value) {
        addStartsWith(CommentColumns.CONTENT, value);
        return this;
    }

    public CommentSelection contentEndsWith(String... value) {
        addEndsWith(CommentColumns.CONTENT, value);
        return this;
    }

    public CommentSelection orderByContent(boolean desc) {
        orderBy(CommentColumns.CONTENT, desc);
        return this;
    }

    public CommentSelection orderByContent() {
        orderBy(CommentColumns.CONTENT, false);
        return this;
    }

    public CommentSelection url(String... value) {
        addEquals(CommentColumns.URL, value);
        return this;
    }

    public CommentSelection urlNot(String... value) {
        addNotEquals(CommentColumns.URL, value);
        return this;
    }

    public CommentSelection urlLike(String... value) {
        addLike(CommentColumns.URL, value);
        return this;
    }

    public CommentSelection urlContains(String... value) {
        addContains(CommentColumns.URL, value);
        return this;
    }

    public CommentSelection urlStartsWith(String... value) {
        addStartsWith(CommentColumns.URL, value);
        return this;
    }

    public CommentSelection urlEndsWith(String... value) {
        addEndsWith(CommentColumns.URL, value);
        return this;
    }

    public CommentSelection orderByUrl(boolean desc) {
        orderBy(CommentColumns.URL, desc);
        return this;
    }

    public CommentSelection orderByUrl() {
        orderBy(CommentColumns.URL, false);
        return this;
    }
}
