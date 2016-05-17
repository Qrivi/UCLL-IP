package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.network.NetworkType;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;
import be.krivi.ucll.ip.domain.service.NetworkService;
import be.krivi.ucll.ip.web.rest.converter.Converter;
import be.krivi.ucll.ip.web.validation.NetworkDTO;
import be.krivi.ucll.ip.web.validation.PasswordDTO;
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
            return new ModelAndView( "index", "networks", service.getAllNetworks() ); //TODO why is this sorted upside-down?

        //TODO implement GET /city={city} to filter shown networks by city
        // postponed for testing purposes
        // return new ModelAndView( "index", "networks", service.getAllNetworksFromCity( city ) );
        return new ModelAndView( "index", "networks", service.getAllNetworks() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/add" )
    public ModelAndView getAddNetwork(){
        return new ModelAndView( "pages/addnetwork", "networkform", new NetworkDTO() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/edit/{id}" )
    public ModelAndView getEditNetwork( @PathVariable( "id" ) Integer id ){

        NetworkDTO networkDTO = new NetworkDTO();
        Network network = service.getNetworkById( id );

        switch( network.getType() ){
            case OPEN:
                ProtectedNetwork protectedNetwork = (ProtectedNetwork)network;

                networkDTO.setNetworkId( protectedNetwork.getId() );

                networkDTO.setNetworkSsid( protectedNetwork.getSsid() );
                networkDTO.setNetworkProtected( true );
                networkDTO.setNetworkPassword( protectedNetwork.getTopPassword().getPassword() );

                networkDTO.setLocationName( protectedNetwork.getLocation().getName() );
                networkDTO.setLocationAddress( protectedNetwork.getLocation().getAddress() );
                networkDTO.setLocationCrossStreet( protectedNetwork.getLocation().getCrossStreet() );
                networkDTO.setLocationZip( protectedNetwork.getLocation().getZip() );
                networkDTO.setLocationCity( protectedNetwork.getLocation().getCity() );
                networkDTO.setLocationCountry( protectedNetwork.getLocation().getCountry() );

                return new ModelAndView( "pages/editnetwork", "networkform", networkDTO );

            case PROTECTED:
                OpenNetwork openNetwork = (OpenNetwork)network;

                networkDTO.setNetworkId( openNetwork.getId() );

                networkDTO.setNetworkSsid( openNetwork.getSsid() );
                networkDTO.setNetworkProtected( false );

                networkDTO.setLocationName( openNetwork.getLocation().getName() );
                networkDTO.setLocationAddress( openNetwork.getLocation().getAddress() );
                networkDTO.setLocationCrossStreet( openNetwork.getLocation().getCrossStreet() );
                networkDTO.setLocationZip( openNetwork.getLocation().getZip() );
                networkDTO.setLocationCity( openNetwork.getLocation().getCity() );
                networkDTO.setLocationCountry( openNetwork.getLocation().getCountry() );

                return new ModelAndView( "pages/editnetwork", "networkform", networkDTO );

            default:
                return new ModelAndView( "error" );
        }
    }

    @RequestMapping( method = RequestMethod.GET, value = "/edit/{id}/password" )
    public ModelAndView getEditNetworkPasswords( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setSsid( network.getSsid() );
        passwordDTO.setLocationCity( network.getLocation().getCity() );

        return new ModelAndView( "pages/editpasswords", "passwordform", passwordDTO );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/remove/{id}" )
    public String postRemoveNetwork( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );
        service.deleteNetwork( network );

        return "redirect:/?city=" + network.getLocation().getCity();
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @RequestMapping( method = RequestMethod.POST, value = "/add" )
    public String postAddNetwork( @Valid @ModelAttribute( "networkform" ) NetworkDTO networkDTO, BindingResult result ){
        if( networkDTO.getNetworkProtected() && networkDTO.getNetworkPassword().trim().equals( "" ) )
            result.rejectValue( "networkPassword", "NotBlank.NetworkForm.networkPassword" );
        if( result.hasErrors() )
            return "pages/addnetwork";

        Pair<Double, Double> latLon = Converter.getLatLonJson( networkDTO );

        if( networkDTO.getNetworkProtected() ){
            service.addProtectedNetwork(
                    new ProtectedNetwork(
                            networkDTO.getNetworkSsid(),
                            new Date(),
                            new Location(
                                    networkDTO.getLocationName(),
                                    latLon.fst,
                                    latLon.snd,
                                    networkDTO.getLocationAddress(),
                                    networkDTO.getLocationCrossStreet(),
                                    networkDTO.getLocationCity(),
                                    networkDTO.getLocationZip(),
                                    networkDTO.getLocationCountry()
                            ),
                            networkDTO.getNetworkPassword()
                    )
            );
        }else{
            service.addOpenNetwork(
                    new OpenNetwork(
                            networkDTO.getNetworkSsid(),
                            new Date(),
                            new Location(
                                    networkDTO.getLocationName(),
                                    latLon.fst,
                                    latLon.snd,
                                    networkDTO.getLocationAddress(),
                                    networkDTO.getLocationCrossStreet(),
                                    networkDTO.getLocationCity(),
                                    networkDTO.getLocationZip(),
                                    networkDTO.getLocationCountry()
                            )
                    )
            );
        }
        return "redirect:/?city=" + networkDTO.getLocationCity();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/edit/{id}" )
    public String postSaveNetwork( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "networkform" ) NetworkDTO networkDTO, BindingResult result ){
        if( networkDTO.getNetworkProtected() && networkDTO.getNetworkPassword().trim().equals( "" ) )
            result.rejectValue( "networkPassword", "NotBlank.NetworkForm.networkPassword" );
        if( result.hasErrors() ) return "pages/editnetwork";

        Network network = service.getNetworkById( id );
        Pair<Double, Double> latLon = Converter.getLatLonJson( networkDTO );
        Location location = new Location(
                networkDTO.getLocationName(),
                latLon.fst,
                latLon.snd,
                networkDTO.getLocationAddress(),
                networkDTO.getLocationCrossStreet(),
                networkDTO.getLocationCity(),
                networkDTO.getLocationZip(),
                networkDTO.getLocationCountry()
        );

        if( network.getType() == NetworkType.PROTECTED && networkDTO.getNetworkProtected() ){
            // was protected, blijft protected
            ProtectedNetwork protectedNetwork = (ProtectedNetwork)network;

            protectedNetwork.setSsid( networkDTO.getNetworkSsid() );
            protectedNetwork.setPassword( new Password( networkDTO.getNetworkPassword() ) );
            protectedNetwork.setLocation( location );
            protectedNetwork.setTimestamp( new Date() );

            service.updateProtectedNetwork( protectedNetwork );

        }else if( network.getType() == NetworkType.PROTECTED && !networkDTO.getNetworkProtected() ){
            // was protected, nu open
            service.deleteProtectedNetwork( (ProtectedNetwork)network );

            postAddNetwork( networkDTO, result );

        }else if( network.getType() == NetworkType.OPEN && networkDTO.getNetworkProtected() ){
            // was open, nu protected
            service.deleteOpenNetwork( (OpenNetwork)network );

            postAddNetwork( networkDTO, result );

        }else if( network.getType() == NetworkType.OPEN && !networkDTO.getNetworkProtected() ){
            // was open, blijft open
            OpenNetwork openNetwork = (OpenNetwork)network;

            openNetwork.setSsid( networkDTO.getNetworkSsid() );
            openNetwork.setLocation( location );
            openNetwork.setTimestamp( new Date() );

            service.updateOpenNetwork( openNetwork );

        }else{
            return "redirect:/error";
        }

        return "redirect:/?city=" + networkDTO.getLocationCity();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/edit/{id}/password" )
    public String postEditPassword( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "passwordform" ) PasswordDTO passwordDTO, BindingResult result ){
        if( result.hasErrors() ) return "pages/editpasswords";

        ProtectedNetwork protectedNetwork = service.getProtectedNetworkById( id );

        protectedNetwork.addPassword( new Password( passwordDTO.getPassword() ) );
        protectedNetwork.setTimestamp( new Date() );
        service.updateProtectedNetwork( protectedNetwork );

        return "redirect:/?city=" + passwordDTO.getLocationCity();
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //    @ExceptionHandler( DatabaseException.class )
    //    public ModelAndView handleException( Exception e ){
    //        return null;
    //    }
}

