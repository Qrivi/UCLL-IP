package be.krivi.ucll.ip.web.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Krivi on 01/04/16.
 */
public class NetworkDTO{

    private Integer networkId;

    private String locationName;

    @NotBlank( message = "{NotBlank.NetworkDTO.locationAddress}" )
    private String locationAddress;
    private String locationCrossStreet;

    @NotNull( message = "{NotNull.NetworkDTO.locationZip}" )
    private Integer locationZip;

    @NotBlank( message = "{NotBlank.NetworkDTO.locationCity}" )
    private String locationCity;

    @NotBlank( message = "{NotBlank.NetworkDTO.locationCountry}" )
    private String locationCountry;

    @NotBlank( message = "{NotBlank.NetworkDTO.networkSsid}" )
    @Size( min = 1, max = 32, message = "{Size.NetworkDTO.networkSsid}" )
    private String networkSsid;

    private Boolean networkProtected;
    private String networkPassword;

    public NetworkDTO(){
    }

    public Integer getNetworkId(){
        return networkId;
    }

    public void setNetworkId( Integer networkId ){
        this.networkId = networkId;
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

    public void setLocationZip( String locationZip ){
        setLocationZip( Integer.parseInt( locationZip ) );
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

    public Boolean getNetworkProtected(){
        return networkProtected;
    }

    // Spring Forms setters

    public void setNetworkProtected( String networkProtected ){
        setNetworkProtected( Boolean.valueOf( networkProtected ) );
    }

    public void setNetworkProtected( Boolean networkProtected ){
        this.networkProtected = networkProtected;
    }
}
