package be.krivi.ucll.ip.web.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Krivi on 02/05/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry{

    private Location location;

    public Location getLocation(){
        return location;
    }

    public void setLocation( Location location ){
        this.location = location;
    }
}
