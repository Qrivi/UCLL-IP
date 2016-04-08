package be.krivi.ucll.ip.domain.core;

import be.krivi.ucll.ip.domain.common.Entity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Krivi on 08/02/16.
 */
@javax.persistence.Entity
@Table( name = "location" )
public class Location extends Entity{

    @Column( name = "name" )
    private String name;

    @Min( value = -90, message = "{Min.Location.lat}" )
    @Max( value = 90, message = "{Max.Location.lat}" )
    @Column( name = "lat" )
    private Double lat;

    @Min( value = -180, message = "{Min.Location.lon}" )
    @Max( value = 180, message = "{Max.Location.lon}" )
    @Column( name = "lon" )
    private Double lon;

    @NotBlank( message = "{NotBlank.Location.address}" )
    @Column( name = "address" )
    private String address;

    @Column( name = "crossstreet" )
    private String crossStreet;

    @NotBlank( message = "{NotBlank.Location.city}" )
    @Column( name = "city" )
    private String city;

    @NotNull( message = "{NotNull.Location.zip}" )
    @Column( name = "zip" )
    private int zip;

    @NotBlank( message = "{NotBlank.Location.country}" )
    @Column( name = "country" )
    private String country;

    public Location(){
    }

    public Location( String name, Double lat, Double lon, String address, String crossStreet, String city, int zip, String country ){
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.crossStreet = crossStreet;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }

    public String toString(){
        if( crossStreet.equals( "" ) )
            return address + "\n" + zip + " " + city + "\n" + country;
        return address + "(" + crossStreet + ")" + "\n" + zip + " " + city + "\n" + country;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
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

    public String getCity(){
        return city;
    }

    public void setCity( String city ){
        this.city = city;
    }

    public int getZip(){
        return zip;
    }

    public void setZip( int zip ){
        this.zip = zip;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry( String country ){
        this.country = country;
    }
}
