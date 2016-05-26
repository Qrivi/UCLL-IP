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
public class ProtectedNetworkDTO{

    @NotBlank( message = "{NotBlank.ProtectedNetworkDTO.ssid}" )
    @Size( min = 1, max = 32, message = "{Size.ProtectedNetworkDTO.ssid}" )
    private String ssid;

    @NotBlank( message = "{NotBlank.ProtectedNetworkDTO.password}" )
    @Size( min = 5, max = 63, message = "{Size.ProtectedNetworkDTO.password}" )
    private String password;

    // @NotNull( message = "{NotNull.ProtectedNetworkDTO.location}" )
    @Valid
    private LocationDTO location;

    public ProtectedNetworkDTO(){
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

    public LocationDTO getLocation(){
        return location;
    }

    public void setLocation( LocationDTO location ){
        this.location = location;
    }
}
