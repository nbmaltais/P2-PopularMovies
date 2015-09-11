package ca.nbmaltais.popularmovies.api.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

//import javax.annotation.Generated;

//@Generated("org.jsonschema2pojo")
public class Movies {

    @Expose
    private Integer page;

    @SerializedName("results")
    @Expose
    private List<Movie> mMovies = new ArrayList<Movie>();

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     * The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Movie> getMovies() {
        return mMovies;
    }

    /**
     *
     * @param movies
     * The results
     */
    public void setMovies(List<Movie> movies) {
        this.mMovies = movies;
    }

}