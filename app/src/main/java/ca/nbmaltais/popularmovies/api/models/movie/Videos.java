package ca.nbmaltais.popularmovies.api.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Videos {

    @Expose
    private Integer id;

    @SerializedName("results")
    @Expose
    private List<Video> results = new ArrayList<Video>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Video> getVideos() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Video> results) {
        this.results = results;
    }

}