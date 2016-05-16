package be.krivi.ucll.ip.api.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Krivi on 01/04/16.
 */
public class LocationTO{

    private String name;

    @NotBlank( message = "{NotBlank.LocationTO.address}" )
    private String address;
    private String crossStreet;

    @NotNull( message = "{NotNull.LocationTO.zip}" )
    private Integer zip;

    @NotBlank( message = "{NotBlank.LocationTO.city}" )
    private String city;

    @NotBlank( message = "{NotBlank.LocationTO.country}" )
    private String country;

    @NotNull( message = "{NotNull.LocationTO.lat}" )
    private Double lat;

    @NotNull( message = "{NotNull.LocationTO.lon}" )
    private Double lon;

    public LocationTO(){
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

    public void setZip( String zip ){
        setZip( Integer.parseInt( zip ) );
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
