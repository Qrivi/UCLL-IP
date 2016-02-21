package model.wrapper;

import model.exception.DomainException;

/**
 * Created by Krivi on 08/02/16.
 */
public class Location{

    private String name;

    private double lat;
    private double lon;

    private String address;
    private String crossStreet;
    private String city;
    private int zip;

    private String region;
    private String country;

    public Location( String name, double lat, double lon, String address, String crossStreet, String city, int zip, String region, String country ) throws DomainException{
        setName( name );
        setLat( lat );
        setLon( lon );
        setAddress( address );
        setCrossStreet( crossStreet );
        setCity( city );
        setZip( zip );
        setRegion( region );
        setCountry( country );

        validateLocation();
    }

    public boolean validateLocation() throws DomainException{
        if( lat == 0 || lon == 0 || lat > 90 || lon > 180 || lat < -90 || lon < -180 )
            throw new DomainException( "Location does not have valid coordinates" );
        if( country.equals( "" ) )
            throw new DomainException( "Location requires a country" );
        if( region.equals( "" ) )
            throw new DomainException( "Location requires a region" );
        if( city.equals( "" ) )
            throw new DomainException( "Location requires a city" );
        if( zip == 0 )
            throw new DomainException( "Location requires a zip" );
        return true;
    }

    public String toString(){
        if( crossStreet.equals( "" ) )
            return address + "\n" + zip + " " + city + "\n" + region + ", " + country;
        return address + "(" + crossStreet + ")" + "\n" + zip + " " + city + "\n" + region + ", " + country;
    }

    public String getName(){
        return name;
    }

    public void setName( String name ){
        this.name = name;
    }

    public double getLat(){
        return lat;
    }

    public void setLat( double lat ){
        this.lat = lat;
    }

    public double getLon(){
        return lon;
    }

    public void setLon( double lon ){
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

    public void setCity( String city ) throws DomainException{
        if( city.equals( "" ) )
            throw new DomainException( "City is a required field" );
        this.city = city;
    }

    public int getZip(){
        return zip;
    }

    public void setZip( int zip ) throws DomainException{
        if( zip == 0 )
            throw new DomainException( "Zip is a required field" );
        this.zip = zip;
    }

    public String getRegion(){
        return region;
    }

    public void setRegion( String region ) throws DomainException{
        if( region.equals( "" ) )
            throw new DomainException( "Region is a required field" );
        this.region = region;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry( String country ) throws DomainException{
        if( country.equals( "" ) )
            throw new DomainException( "Country is a required field" );
        this.country = country;
    }
}
