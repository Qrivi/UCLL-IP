package be.krivi.ucll.ip.api.controller;

import be.krivi.ucll.ip.api.validation.OpenNetworkDTO;
import be.krivi.ucll.ip.api.validation.PasswordDTO;
import be.krivi.ucll.ip.api.validation.ProtectedNetworkDTO;
import be.krivi.ucll.ip.api.validation.error.ErrorDTO;
import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping( "/networks" )
public class NetworkController{

    @Autowired
    NetworkService service;

    //****************************************************************
    // region RequestMethod.GET
    //****************************************************************

    //TODO localize exception messages (will localized ValidationMessages be used since cookie from /web counts for whole domain?)

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "" )
    public Collection<Network> getAllNetworks(){
        return service.getAllNetworks();
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/open" )
    public Collection<OpenNetwork> getAllOpenNetworks(){
        return service.getAllOpenNetworks();
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/protected" )
    public Collection<ProtectedNetwork> getAllProtectedNetworks(){
        return service.getAllProtectedNetworks();
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/{id}" )
    public Network getNetworkById( @PathVariable( "id" ) Integer id ) throws NullPointerException{
        Network network = service.getNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.Network}" );
        return network;
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/open/{id}" )
    public OpenNetwork getOpenNetworkById( @PathVariable( "id" ) Integer id ) throws NullPointerException{
        OpenNetwork network = service.getOpenNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.OpenNetwork}" );
        return network;
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/protected/{id}" )
    public ProtectedNetwork getProtectedNetworkById( @PathVariable( "id" ) Integer id ) throws NullPointerException{
        ProtectedNetwork network = service.getProtectedNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.ProtectedNetwork}" );
        return network;
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/city/{city}" )
    public Collection<Network> getAllNetworksFromCity( @PathVariable( "city" ) String city ){
        return service.getAllNetworksFromCity( city );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.POST, value = "/open" )
    public ResponseEntity addOpenNetwork( @Valid @RequestBody OpenNetworkDTO networkDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        OpenNetwork network = new OpenNetwork(
                networkDTO.getSsid(),
                new Date(),
                new Location(
                        networkDTO.getLocation().getName(),
                        networkDTO.getLocation().getLat(),
                        networkDTO.getLocation().getLon(),
                        networkDTO.getLocation().getAddress(),
                        networkDTO.getLocation().getCrossStreet(),
                        networkDTO.getLocation().getCity(),
                        networkDTO.getLocation().getZip(),
                        networkDTO.getLocation().getCountry()
                )
        );

        service.addOpenNetwork( network );
        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.POST, value = "/protected" )
    public ResponseEntity addProtectedNetwork( @Valid @RequestBody ProtectedNetworkDTO networkDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        ProtectedNetwork network = new ProtectedNetwork(
                networkDTO.getSsid(),
                new Date(),
                new Location(
                        networkDTO.getLocation().getName(),
                        networkDTO.getLocation().getLat(),
                        networkDTO.getLocation().getLon(),
                        networkDTO.getLocation().getAddress(),
                        networkDTO.getLocation().getCrossStreet(),
                        networkDTO.getLocation().getCity(),
                        networkDTO.getLocation().getZip(),
                        networkDTO.getLocation().getCountry()
                ),
                networkDTO.getPassword()
        );

        service.addProtectedNetwork( network );
        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.POST, value = "/protected/{id}/passwords" )
    public ResponseEntity addProtectedNetworkPassword( @PathVariable( "id" ) Integer id, @Valid @RequestBody PasswordDTO passwordDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        ProtectedNetwork network = service.getProtectedNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.ProtectedNetwork}" );

        network.addPassword( new Password( passwordDTO.getPassword() ) );
        network.setTimestamp( new Date() );

        service.updateProtectedNetwork( network );
        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.PUT
    //****************************************************************

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.PUT, value = "/open/{id}" )
    public ResponseEntity updateOpenNetworkById( @PathVariable( "id" ) Integer id, @Valid @RequestBody OpenNetworkDTO networkDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        OpenNetwork network = service.getOpenNetworkById( id );

        network.setSsid( networkDTO.getSsid() );
        network.setLocation( new Location(
                networkDTO.getLocation().getName(),
                networkDTO.getLocation().getLat(),
                networkDTO.getLocation().getLon(),
                networkDTO.getLocation().getAddress(),
                networkDTO.getLocation().getCrossStreet(),
                networkDTO.getLocation().getCity(),
                networkDTO.getLocation().getZip(),
                networkDTO.getLocation().getCountry()
        ) );
        network.setTimestamp( new Date() );

        service.updateOpenNetwork( network );
        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.PUT, value = "/protected/{id}" )
    public ResponseEntity updateProtectedNetworkById( @PathVariable( "id" ) Integer id, @Valid @RequestBody ProtectedNetworkDTO networkDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        ProtectedNetwork network = service.getProtectedNetworkById( id );

        network.setSsid( networkDTO.getSsid() );
        network.setPassword( new Password( networkDTO.getPassword() ) );
        network.setLocation( new Location(
                networkDTO.getLocation().getName(),
                networkDTO.getLocation().getLat(),
                networkDTO.getLocation().getLon(),
                networkDTO.getLocation().getAddress(),
                networkDTO.getLocation().getCrossStreet(),
                networkDTO.getLocation().getCity(),
                networkDTO.getLocation().getZip(),
                networkDTO.getLocation().getCountry()
        ) );
        network.setTimestamp( new Date() );

        service.updateProtectedNetwork( network );
        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.DELETE
    //****************************************************************

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.DELETE, value = "/{id}" )
    public ResponseEntity removeNetworkById( @PathVariable( "id" ) Integer id ) throws NullPointerException{
        Network network = service.getNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.Network}" );

        service.deleteNetwork( network );
        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.DELETE, value = "/open/{id}" )
    public ResponseEntity removeOpenNetworkById( @PathVariable( "id" ) Integer id ){
        return removeNetworkById( id );
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.DELETE, value = "/protected/{id}" )
    public ResponseEntity removeProtectedNetworkById( @PathVariable( "id" ) Integer id ){
        return removeNetworkById( id );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    @Produces( "application/json" )
    @ExceptionHandler( NullPointerException.class )
    public ResponseEntity handleException( Exception e ){
        Map<String, String> errors = new HashMap<>();
        errors.put( "404", "Not Found: " + e.getMessage() );

        return new ResponseEntity<>( new ErrorDTO( "database", errors ), HttpStatus.NOT_FOUND );
    }
}