package be.krivi.ucll.ip.web.validation;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Krivi on 01/04/16.
 */
public class PasswordForm{

    private String ssid;

    @NotEmpty(message = "{NotEmpty.PasswordForm.password}" )
    private String password;

    //TODO remove city propery
    private String locationCity;

    public PasswordForm(){
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

    public String getLocationCity(){
        return locationCity;
    }

    public void setLocationCity( String locationCity ){
        this.locationCity = locationCity;
    }
}
