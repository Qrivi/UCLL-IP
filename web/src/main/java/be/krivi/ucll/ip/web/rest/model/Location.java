package be.krivi.ucll.ip.web.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Krivi on 02/05/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location{

    double lat;
    double lng;

    public double getLat(){
        return lat;
    }

    public void setLat( double lat ){
        this.lat = lat;
    }

    public double getLng(){
        return lng;
    }

    public void setLng( double lng ){
        this.lng = lng;
    }
}
