package be.krivi.ucll.ip.web.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Krivi on 02/05/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Results{

    private String formattedAddress;
    private Geometry geometry;

    public String getFormattedAddress(){
        return formattedAddress;
    }

    public void setFormattedAddress( String formattedAddress ){
        this.formattedAddress = formattedAddress;
    }

    public Geometry getGeometry(){
        return geometry;
    }

    public void setGeometry( Geometry geometry ){
        this.geometry = geometry;
    }
}
