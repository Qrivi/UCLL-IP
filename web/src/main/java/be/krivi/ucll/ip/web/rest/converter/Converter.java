package be.krivi.ucll.ip.web.rest.converter;

import be.krivi.ucll.ip.web.rest.model.Geocode;
import be.krivi.ucll.ip.web.rest.model.Geometry;
import be.krivi.ucll.ip.web.validation.NetworkDTO;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.sun.tools.javac.util.Pair;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Krivi on 02/04/16.
 */

// Simple converter class that gets latitude and longitude for a given address.
public class Converter{

    private static final String API_KEY = "google-maps-api-key";

    //****************************************************************
    // region Using Google Geocode Java API
    //****************************************************************

    public static Pair<Double, Double> getLatLon( NetworkDTO networkDTO ){
        GeoApiContext context = new GeoApiContext().setApiKey( API_KEY );
        GeocodingResult[] results;

        try{
            results = GeocodingApi.geocode(
                    context,
                    networkDTO.getLocationAddress() + ", " +
                            networkDTO.getLocationZip() +
                            networkDTO.getLocationCity() + ", " +
                            networkDTO.getLocationCountry()
            ).await();

            return new Pair<>( results[0].geometry.location.lat, results[0].geometry.location.lng );
        }catch( Exception e ){
            return new Pair<>( 0.0, 0.0 );
        }
    }

    public static Pair<Double, Double> getLatLon( String address ){
        GeoApiContext context = new GeoApiContext().setApiKey( API_KEY );
        GeocodingResult[] results;

        try{
            results = GeocodingApi.geocode(
                    context,
                    address
            ).await();

            return new Pair<>( results[0].geometry.location.lat, results[0].geometry.location.lng );
        }catch( Exception e ){
            return new Pair<>( 0.0, 0.0 );
        }
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region Using Google Geocode JSON API
    //****************************************************************

    public static Pair<Double, Double> getLatLonJson( NetworkDTO networkDTO ){
        RestTemplate restTemplate = new RestTemplate();

        try{
            Geocode geocode = restTemplate.getForObject(
                    "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                            networkDTO.getLocationAddress() + ", " +
                            networkDTO.getLocationZip() +
                            networkDTO.getLocationCity() + ", " +
                            networkDTO.getLocationCountry() +
                            "&key=" + API_KEY,
                    Geocode.class
            );
            Geometry geometry = geocode.getFirstResults().getGeometry();

            return new Pair<>( geometry.getLocation().getLat(), geometry.getLocation().getLng() );
        }catch( Exception e ){
            return new Pair<>( 0.0, 0.0 );
        }
    }

    public static Pair<Double, Double> getLatLonJson( String address ){
        RestTemplate restTemplate = new RestTemplate();

        try{
            Geocode geocode = restTemplate.getForObject(
                    "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                            address +
                            "&key=" + API_KEY,
                    Geocode.class
            );
            Geometry geometry = geocode.getFirstResults().getGeometry();

            return new Pair<>( geometry.getLocation().getLat(), geometry.getLocation().getLng() );
        }catch( Exception e ){
            return new Pair<>( 0.0, 0.0 );
        }
    }

    //****************************************************************
    // endregion
    //****************************************************************

    public static String test( NetworkDTO networkDTO ){
        RestTemplate restTemplate = new RestTemplate();

        Geocode results = restTemplate.getForObject(
                "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                        networkDTO.getLocationAddress() + ", " +
                        networkDTO.getLocationZip() +
                        networkDTO.getLocationCity() + ", " +
                        networkDTO.getLocationCountry() +
                        "&key=" + API_KEY,
                Geocode.class
        );

        return "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                networkDTO.getLocationAddress() + ", " +
                networkDTO.getLocationZip() +
                networkDTO.getLocationCity() + ", " +
                networkDTO.getLocationCountry() +
                "&key=" + API_KEY;
    }
}
