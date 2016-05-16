package be.krivi.ucll.ip.api.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Krivi on 01/04/16.
 */
public class ProtectedNetworkTO{

    @NotBlank( message = "{NotBlank.ProtectedNetworkTO.ssid}" )
    @Size( min = 1, max = 32, message = "{Size.ProtectedNetworkTO.ssid}" )
    private String ssid;

    @NotBlank( message = "{NotBlank.ProtectedNetworkTO.password}" )
    @Size( min = 5, max = 63, message = "{Size.ProtectedNetworkTO.password}" )
    private String password;

    @NotNull( message = "{NotNull.ProtectedNetworkTO.location}" )
    private LocationTO location;

    public ProtectedNetworkTO(){
    }

    public String getSsid(){
        return ssid;
    }

    public void setSsid( String ssid ){
        this.ssid = ssid;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword( String password ){
        this.password = password;
    }

    public LocationTO getLocation(){
        return location;
    }

    public void setLocation( LocationTO location ){
        this.location = location;
    }
}
