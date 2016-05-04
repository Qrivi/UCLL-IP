package be.krivi.ucll.ip.web.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Krivi on 02/05/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geocode{

    private String status;
    private List<Results> results;

    public String getStatus(){
        return status;
    }

    public void setStatus( String status ){
        this.status = status;
    }

    public List<Results> getResults(){
        return results;
    }

    public void setResults( List<Results> results ){
        this.results = results;
    }

    public Results getFirstResults(){
        return results.get( 0 );
    }
}
