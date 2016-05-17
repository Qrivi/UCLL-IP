package be.krivi.ucll.ip.api.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Krivi on 01/04/16.
 */

@JsonIgnoreProperties( ignoreUnknown = true )
public class LocationDTO{

    private String name;

    @NotBlank( message = "{NotBlank.LocationDTO.address}" )
    private String address;
    private String crossStreet;

    @NotNull( message = "{NotNull.LocationDTO.zip}" )
    private Integer zip;

    @NotBlank( message = "{NotBlank.LocationDTO.city}" )
    private String city;

    @NotBlank( message = "{NotBlank.LocationDTO.country}" )
    private String country;

    @NotNull( message = "{NotNull.LocationDTO.lat}" )
    private Double lat;

    @NotNull( message = "{NotNull.LocationDTO.lon}" )
    private Double lon;

    public LocationDTO(){
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress( String address ){
        this.address = address;
    }

    public String getCrossStreet(){
        return crossStreet;
    }

    public void setCrossStreet( String crossStreet ){
        this.crossStreet = crossStreet;
    }

    public Integer getZip(){
        return zip;
    }

    public void setZip( Integer zip ){
        this.zip = zip;
    }

    public String getCity(){
        return city;
    }

    public void setCity( String city ){
        this.city = city;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry( String country ){
        this.country = country;
    }

    public Double getLat(){
        return lat;
    }

    public void setLat( Double lat ){
        this.lat = lat;
    }

    public Double getLon(){
        return lon;
    }

    public void setLon( Double lon ){
        this.lon = lon;
    }
}
