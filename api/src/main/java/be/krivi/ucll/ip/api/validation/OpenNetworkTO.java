package be.krivi.ucll.ip.api.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Krivi on 01/04/16.
 */
public class OpenNetworkTO{

    @NotBlank( message = "{NotBlank.OpenNetworkTO.ssid}" )
    @Size( min = 1, max = 32, message = "{Size.OpenNetworkTO.ssid}" )
    private String ssid;

    @NotNull( message = "{NotNull.OpenNetworkTO.location}" )
    private LocationTO location;

    public OpenNetworkTO(){
    }

    public String getSsid(){
        return ssid;
    }

    public void setSsid( String ssid ){
        this.ssid = ssid;
    }

    public LocationTO getLocation(){
        return location;
    }

    public void setLocation( LocationTO location ){
        this.location = location;
    }
}
