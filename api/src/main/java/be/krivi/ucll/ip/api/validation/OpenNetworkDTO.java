package be.krivi.ucll.ip.api.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Krivi on 01/04/16.
 */

@JsonIgnoreProperties( ignoreUnknown = true )
public class OpenNetworkDTO{

    @NotBlank( message = "{NotBlank.OpenNetworkDTO.ssid}" )
    @Size( min = 1, max = 32, message = "{Size.OpenNetworkDTO.ssid}" )
    private String ssid;

    // @NotNull( message = "{NotNull.OpenNetworkDTO.location}" )
    @Valid
    private LocationDTO location;

    public OpenNetworkDTO(){
    }

    public String getSsid(){
        return ssid;
    }

    public void setSsid( String ssid ){
        this.ssid = ssid;
    }

    public LocationDTO getLocation(){
        return location;
    }

    public void setLocation( LocationDTO location ){
        this.location = location;
    }
}
