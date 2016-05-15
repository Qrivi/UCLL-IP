package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.network.NetworkType;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;
import be.krivi.ucll.ip.domain.service.NetworkService;
import be.krivi.ucll.ip.web.rest.converter.Converter;
import be.krivi.ucll.ip.web.validation.NetworkTO;
import be.krivi.ucll.ip.web.validation.PasswordTO;
import com.sun.tools.javac.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Krivi on 14/03/16.
 */
@Controller
@RequestMapping( value = "/" )
public class NetworkController{

    @Autowired
    private NetworkService service;

    //****************************************************************
    // region RequestMethod.GET
    //****************************************************************

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView getIndex( @RequestParam( value = "city", required = false ) String city ){
        if( city == null || city.equals( "" ) )
            return new ModelAndView( "index", "networks", service.getAllNetworks() );

        //TODO implement GET /city={city} to filter shown networks by city
        // postponed for testing purposes
        // return new ModelAndView( "index", "networks", service.getAllNetworksFromCity( city ) );
        return new ModelAndView( "index", "networks", service.getAllNetworks() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/add" )
    public ModelAndView getAddNetwork(){
        return new ModelAndView( "pages/addnetwork", "networkform", new NetworkTO() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/edit/{id}" )
    public ModelAndView getEditNetwork( @PathVariable( "id" ) Integer id ){

        NetworkTO networkTO = new NetworkTO();
        Network network = service.getNetworkById( id );

        if( network.getType() == NetworkType.PROTECTED ){
            ProtectedNetwork protectedNetwork = (ProtectedNetwork)network;

            networkTO.setNetworkId( protectedNetwork.getId() );

            networkTO.setNetworkSsid( protectedNetwork.getSsid() );
            networkTO.setNetworkProtected( true );
            networkTO.setNetworkPassword( protectedNetwork.getTopPassword().getPassword() );

            networkTO.setLocationName( protectedNetwork.getLocation().getName() );
            networkTO.setLocationAddress( protectedNetwork.getLocation().getAddress() );
            networkTO.setLocationCrossStreet( protectedNetwork.getLocation().getCrossStreet() );
            networkTO.setLocationZip( protectedNetwork.getLocation().getZip() );
            networkTO.setLocationCity( protectedNetwork.getLocation().getCity() );
            networkTO.setLocationCountry( protectedNetwork.getLocation().getCountry() );

            return new ModelAndView( "pages/editnetwork", "networkform", networkTO );
        }else if( network.getType() == NetworkType.OPEN ){
            OpenNetwork openNetwork = (OpenNetwork)network;

            networkTO.setNetworkId( openNetwork.getId() );

            networkTO.setNetworkSsid( openNetwork.getSsid() );
            networkTO.setNetworkProtected( false );

            networkTO.setLocationName( openNetwork.getLocation().getName() );
            networkTO.setLocationAddress( openNetwork.getLocation().getAddress() );
            networkTO.setLocationCrossStreet( openNetwork.getLocation().getCrossStreet() );
            networkTO.setLocationZip( openNetwork.getLocation().getZip() );
            networkTO.setLocationCity( openNetwork.getLocation().getCity() );
            networkTO.setLocationCountry( openNetwork.getLocation().getCountry() );

            return new ModelAndView( "pages/editnetwork", "networkform", networkTO );
        }else{
            return new ModelAndView( "error" );
        }
    }

    @RequestMapping( method = RequestMethod.GET, value = "/edit/{id}/password" )
    public ModelAndView getEditNetworkPasswords( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );
        PasswordTO passwordTO = new PasswordTO();
        passwordTO.setSsid( network.getSsid() );
        passwordTO.setLocationCity( network.getLocation().getCity() );

        return new ModelAndView( "pages/editpasswords", "passwordform", passwordTO );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @RequestMapping( method = RequestMethod.POST, value = "/add" )
    public String postAddNetwork( @Valid @ModelAttribute( "networkform" ) NetworkTO networkTO, BindingResult result ){
        if( networkTO.getNetworkProtected() && networkTO.getNetworkPassword().trim().equals( "" ) )
            result.rejectValue( "networkPassword", "NotBlank.NetworkForm.networkPassword" );
        if( result.hasErrors() )
            return "pages/addnetwork";

        Pair<Double, Double> latLon = Converter.getLatLonJson( networkTO );

        if( networkTO.getNetworkProtected() ){
            service.addProtectedNetwork(
                    new ProtectedNetwork(
                            networkTO.getNetworkSsid(),
                            new Date(),
                            new Location(
                                    networkTO.getLocationName(),
                                    latLon.fst,
                                    latLon.snd,
                                    networkTO.getLocationAddress(),
                                    networkTO.getLocationCrossStreet(),
                                    networkTO.getLocationCity(),
                                    networkTO.getLocationZip(),
                                    networkTO.getLocationCountry()
                            ),
                            networkTO.getNetworkPassword()
                    )
            );
        }else{
            service.addOpenNetwork(
                    new OpenNetwork(
                            networkTO.getNetworkSsid(),
                            new Date(),
                            new Location(
                                    networkTO.getLocationName(),
                                    latLon.fst,
                                    latLon.snd,
                                    networkTO.getLocationAddress(),
                                    networkTO.getLocationCrossStreet(),
                                    networkTO.getLocationCity(),
                                    networkTO.getLocationZip(),
                                    networkTO.getLocationCountry()
                            )
                    )
            );
        }
        return "redirect:/?city=" + networkTO.getLocationCity();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/edit/{id}" )
    public String postSaveNetwork( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "networkform" ) NetworkTO networkTO, BindingResult result ){
        if( networkTO.getNetworkProtected() && networkTO.getNetworkPassword().trim().equals( "" ) )
            result.rejectValue( "networkPassword", "NotBlank.NetworkForm.networkPassword" );
        if( result.hasErrors() ) return "pages/editnetwork";

        Network network = service.getNetworkById( id );
        Pair<Double, Double> latLon = Converter.getLatLonJson( networkTO );
        Location location = new Location(
                networkTO.getLocationName(),
                latLon.fst,
                latLon.snd,
                networkTO.getLocationAddress(),
                networkTO.getLocationCrossStreet(),
                networkTO.getLocationCity(),
                networkTO.getLocationZip(),
                networkTO.getLocationCountry()
        );

        if( network.getType() == NetworkType.PROTECTED && networkTO.getNetworkProtected() ){
            // was protected, blijft protected
            ProtectedNetwork protectedNetwork = (ProtectedNetwork)network;

            protectedNetwork.setSsid( networkTO.getNetworkSsid() );
            protectedNetwork.setPassword( new Password( networkTO.getNetworkPassword() ) );
            protectedNetwork.setLocation( location );
            protectedNetwork.setTimestamp( new Date() );

            service.updateProtectedNetwork( protectedNetwork );

        }else if( network.getType() == NetworkType.PROTECTED && !networkTO.getNetworkProtected() ){
            // was protected, nu open
            service.deleteProtectedNetwork( (ProtectedNetwork)network );

            postAddNetwork( networkTO, result );

        }else if( network.getType() == NetworkType.OPEN && networkTO.getNetworkProtected() ){
            // was open, nu protected
            service.deleteOpenNetwork( (OpenNetwork)network );

            postAddNetwork( networkTO, result );

        }else if( network.getType() == NetworkType.OPEN && !networkTO.getNetworkProtected() ){
            // was open, blijft open
            OpenNetwork openNetwork = (OpenNetwork)network;

            openNetwork.setSsid( networkTO.getNetworkSsid() );
            openNetwork.setLocation( location );
            openNetwork.setTimestamp( new Date() );

            service.updateOpenNetwork( openNetwork );

        }else{
            return "redirect:/error";
        }

        return "redirect:/?city=" + networkTO.getLocationCity();
    }

    //TODO change request method to post or delete
    // was post maar spring forms wist forms inside springforms om een of andere reden :(
    @RequestMapping( method = RequestMethod.GET, value = "/remove/{id}" )
    public String postRemoveNetwork( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );
        service.deleteNetwork( network );

        return "redirect:/?city=" + network.getLocation().getCity();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/edit/{id}/password" )
    public String postEditPassword( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "passwordform" ) PasswordTO passwordTO, BindingResult result ){
        if( result.hasErrors() ) return "pages/editpasswords";

        ProtectedNetwork protectedNetwork = service.getProtectedNetworkById( id );

        protectedNetwork.addPassword( new Password( passwordTO.getPassword() ) );
        protectedNetwork.setTimestamp( new Date() );
        service.updateProtectedNetwork( protectedNetwork );

        //TODO remove city parameter
        return "redirect:/?city=" + passwordTO.getLocationCity();
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //    @ExceptionHandler( DatabaseException.class )
    //    public ModelAndView handleException( Exception e ){
    //        return null;
    //    }
}

