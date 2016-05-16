package be.krivi.ucll.ip.api.controller;

import be.krivi.ucll.ip.api.validation.OpenNetworkTO;
import be.krivi.ucll.ip.api.validation.ProtectedNetworkTO;
import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping( "/networks" )
public class NetworkController{

    @Autowired
    NetworkService service;

    //****************************************************************
    // region RequestMethod.GET
    //****************************************************************

    //TODO fix UTF-8 (without web.xml ;/ )
    @RequestMapping( method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Network> getAllNetworks(){
        return service.getAllNetworks();
    }

    @RequestMapping( method = RequestMethod.GET, value = "/open", produces = MediaType.APPLICATION_JSON_VALUE )
    public Collection<OpenNetwork> getAllOpenNetworks(){
        return service.getAllOpenNetworks();
    }

    @RequestMapping( method = RequestMethod.GET, value = "/protected", produces = MediaType.APPLICATION_JSON_VALUE )
    public Collection<ProtectedNetwork> getAllProtectedNetworks(){
        return service.getAllProtectedNetworks();
    }

    @RequestMapping( method = RequestMethod.GET, value = "/city/{city}", produces = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Network> getAllNetworksFromCity( @PathVariable( "city" ) String city ){
        // TODO implement return service.getAllNetworksFromCity( city )
        return null;
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    //TODO test bean validation cus probably not working
    @RequestMapping( method = RequestMethod.POST, value = "/open", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void addOpenNetwork( @Valid @RequestBody OpenNetworkTO networkTO ){
        service.addOpenNetwork(
                new OpenNetwork(
                        networkTO.getSsid(),
                        new Date(),
                        new Location(
                                networkTO.getLocation().getName(),
                                networkTO.getLocation().getLat(),
                                networkTO.getLocation().getLon(),
                                networkTO.getLocation().getAddress(),
                                networkTO.getLocation().getCrossStreet(),
                                networkTO.getLocation().getCity(),
                                networkTO.getLocation().getZip(),
                                networkTO.getLocation().getCountry()
                        )
                )
        );
    }

    @RequestMapping( method = RequestMethod.POST, value = "/protected", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void addProtectedNetwork( @Valid @RequestBody ProtectedNetworkTO networkTO ){
        service.addProtectedNetwork(
                new ProtectedNetwork(
                        networkTO.getSsid(),
                        new Date(),
                        new Location(
                                networkTO.getLocation().getName(),
                                networkTO.getLocation().getLat(),
                                networkTO.getLocation().getLon(),
                                networkTO.getLocation().getAddress(),
                                networkTO.getLocation().getCrossStreet(),
                                networkTO.getLocation().getCity(),
                                networkTO.getLocation().getZip(),
                                networkTO.getLocation().getCountry()
                        ),
                        networkTO.getPassword()
                )
        );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.PUT
    //****************************************************************

    @RequestMapping( method = RequestMethod.PUT, value = "/open/{id}", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void updateOpenNetwork( @PathVariable( "id" ) Integer id, @Valid @RequestBody OpenNetworkTO networkTO ){
        OpenNetwork network = service.getOpenNetworkById( id );

        network.setSsid( networkTO.getSsid() );
        network.setLocation( new Location(
                networkTO.getLocation().getName(),
                networkTO.getLocation().getLat(),
                networkTO.getLocation().getLon(),
                networkTO.getLocation().getAddress(),
                networkTO.getLocation().getCrossStreet(),
                networkTO.getLocation().getCity(),
                networkTO.getLocation().getZip(),
                networkTO.getLocation().getCountry()
        ) );
        network.setTimestamp( new Date() );

        service.updateOpenNetwork( network );
    }

    @RequestMapping( method = RequestMethod.PUT, value = "/protected/{id}", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void updateProtectedNetwork( @PathVariable( "id" ) Integer id, @Valid @RequestBody ProtectedNetworkTO networkTO ){
        ProtectedNetwork network = service.getProtectedNetworkById( id );

        network.setSsid( networkTO.getSsid() );
        network.setPassword( new Password( networkTO.getPassword() ) );
        network.setLocation( new Location(
                networkTO.getLocation().getName(),
                networkTO.getLocation().getLat(),
                networkTO.getLocation().getLon(),
                networkTO.getLocation().getAddress(),
                networkTO.getLocation().getCrossStreet(),
                networkTO.getLocation().getCity(),
                networkTO.getLocation().getZip(),
                networkTO.getLocation().getCountry()
        ) );
        network.setTimestamp( new Date() );

        service.updateProtectedNetwork( network );
    }

    @RequestMapping( method = RequestMethod.PUT, value = "/protected/{id}/{password}", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void updateProtectedNetworkPassword( @PathVariable( "id" ) Integer id, @PathVariable( "password" ) String password ){
        ProtectedNetwork protectedNetwork = service.getProtectedNetworkById( id );

        protectedNetwork.addPassword( new Password( password ) );
        protectedNetwork.setTimestamp( new Date() );
        service.updateProtectedNetwork( protectedNetwork );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.DELETE
    //****************************************************************

    @RequestMapping( method = RequestMethod.DELETE, value = "/{id}" )
    public void removeNetwork( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );
        service.deleteNetwork( network );
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/open/{id}" )
    public void removeOpenNetwork( @PathVariable( "id" ) Integer id ){
        removeNetwork( id );
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/protected/{id}" )
    public void removeProtectedNetwork( @PathVariable( "id" ) Integer id ){
        removeNetwork( id );
    }

    //****************************************************************
    // endregion
    //****************************************************************
}