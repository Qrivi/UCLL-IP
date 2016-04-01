package be.krivi.ucll.ip.web.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Krivi on 01/04/16.
 */
public class NetworkForm{

    private String locationName;

    @NotEmpty( message = "{NotEmpty.NetworkForm.locationAddress}" )
    private String locationAddress;
    private String locationCrossStreet;

    @NotNull( message = "{NotNull.NetworkForm.locationZip}" )
    private Integer locationZip;

    @NotEmpty( message = "{NotEmpty.NetworkForm.locationCity}" )
    private String locationCity;

    @NotEmpty( message = "{NotEmpty.NetworkForm.locationCountry}" )
    private String locationCountry;

    @NotEmpty( message = "{NotEmpty.NetworkForm.networkSsid}" )
    private String networkSsid;

    private Boolean networkHasPassword;
    private String networkPassword;

    public NetworkForm(){
    }

    public String getLocationName(){
        return locationName;
    }

    public void setLocationName( String locationName ){
        this.locationName = locationName;
    }

    public String getLocationAddress(){
        return locationAddress;
    }

    public void setLocationAddress( String locationAddress ){
        this.locationAddress = locationAddress;
    }

    public String getLocationCrossStreet(){
        return locationCrossStreet;
    }

    public void setLocationCrossStreet( String locationCrossStreet ){
        this.locationCrossStreet = locationCrossStreet;
    }

    public Integer getLocationZip(){
        return locationZip;
    }

    public void setLocationZip( Integer locationZip ){
        this.locationZip = locationZip;
    }

    public String getLocationCity(){
        return locationCity;
    }

    public void setLocationCity( String locationCity ){
        this.locationCity = locationCity;
    }

    public String getLocationCountry(){
        return locationCountry;
    }

    public void setLocationCountry( String locationCountry ){
        this.locationCountry = locationCountry;
    }

    public String getNetworkSsid(){
        return networkSsid;
    }

    public void setNetworkSsid( String networkSsid ){
        this.networkSsid = networkSsid;
    }

    public String getNetworkPassword(){
        return networkPassword;
    }

    public void setNetworkPassword( String networkPassword ){
        this.networkPassword = networkPassword;
    }

    public Boolean getNetworkHasPassword(){
        return networkHasPassword;
    }

    public void setNetworkHasPassword( Boolean networkHasPassword ){
        this.networkHasPassword = networkHasPassword;
    }

    // Spring Forms setters

    public void setLocationZip( String locationZip ){
        this.locationZip = Integer.parseInt( locationZip );
    }

    public void setNetworkHasPassword( String networkHasPassword ){
        this.networkHasPassword = Boolean.valueOf( networkHasPassword );
    }
}
