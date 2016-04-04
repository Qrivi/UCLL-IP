package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.network.NetworkType;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;
import be.krivi.ucll.ip.domain.service.NetworkService;
import be.krivi.ucll.ip.web.converter.Converter;
import be.krivi.ucll.ip.web.validation.CommentForm;
import be.krivi.ucll.ip.web.validation.NetworkForm;
import be.krivi.ucll.ip.web.validation.PasswordForm;
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
    public ModelAndView getIndex(){
        return new ModelAndView( "index", "networks", service.getAllNetworks() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/add" )
    public ModelAndView getAddNetwork(){
        return new ModelAndView( "pages/addnetwork", "networkform", new NetworkForm() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/edit/{id}" )
    public ModelAndView getEditNetwork( @PathVariable( "id" ) Integer id ){

        NetworkForm networkForm = new NetworkForm();
        Network network = service.getNetworkById( id );

        if( network.getType() == NetworkType.PROTECTED ){
            ProtectedNetwork protectedNetwork = (ProtectedNetwork)network;

            networkForm.setNetworkId( protectedNetwork.getId() );

            networkForm.setNetworkSsid( protectedNetwork.getSsid() );
            networkForm.setNetworkProtected( true );
            networkForm.setNetworkPassword( protectedNetwork.getTopPassword().getPassword() );

            networkForm.setLocationName( protectedNetwork.getLocation().getName() );
            networkForm.setLocationAddress( protectedNetwork.getLocation().getAddress() );
            networkForm.setLocationCrossStreet( protectedNetwork.getLocation().getCrossStreet() );
            networkForm.setLocationZip( protectedNetwork.getLocation().getZip() );
            networkForm.setLocationCity( protectedNetwork.getLocation().getCity() );
            networkForm.setLocationCountry( protectedNetwork.getLocation().getCountry() );

            return new ModelAndView( "pages/editnetwork", "networkform", networkForm );
        }else if( network.getType() == NetworkType.OPEN ){
            OpenNetwork openNetwork = (OpenNetwork)network;

            networkForm.setNetworkId( openNetwork.getId() );

            networkForm.setNetworkSsid( openNetwork.getSsid() );
            networkForm.setNetworkProtected( false );

            networkForm.setLocationName( openNetwork.getLocation().getName() );
            networkForm.setLocationAddress( openNetwork.getLocation().getAddress() );
            networkForm.setLocationCrossStreet( openNetwork.getLocation().getCrossStreet() );
            networkForm.setLocationZip( openNetwork.getLocation().getZip() );
            networkForm.setLocationCity( openNetwork.getLocation().getCity() );
            networkForm.setLocationCountry( openNetwork.getLocation().getCountry() );

            return new ModelAndView( "pages/editnetwork", "networkform", networkForm );
        }else{
            return new ModelAndView( "error" );
        }
    }

    @RequestMapping( method = RequestMethod.GET, value = "/edit/{id}/password" )
    public ModelAndView getEditNetworkPasswords( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setSsid( network.getSsid() );
        passwordForm.setLocationCity( network.getLocation().getCity() );

        return new ModelAndView( "pages/editpasswords", "passwordform", passwordForm );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/comments/{id}" )
    public ModelAndView getComments( @PathVariable( "id" ) Integer id ){
        Network network = service.getNetworkById( id );
        CommentForm commentForm = new CommentForm();
        commentForm.setSsid( network.getSsid() );
        commentForm.setComments( network.getComments() );

        return new ModelAndView( "pages/comments", "commentform", commentForm );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @RequestMapping( method = RequestMethod.POST, value = "/add" )
    public String postAddNetwork( @Valid @ModelAttribute( "networkform" ) NetworkForm networkForm, BindingResult result ){
        if( networkForm.getNetworkProtected() && networkForm.getNetworkPassword().equals( "" ) )
            result.rejectValue( "networkPassword", "NotEmpty.NetworkForm.networkPassword" );
        if( result.hasErrors() ) return "pages/addnetwork";

        Pair<Double, Double> latLon = Converter.getLatLon( networkForm );

        if( networkForm.getNetworkProtected() ){
            service.addProtectedNetwork(
                    new ProtectedNetwork(
                            networkForm.getNetworkSsid(),
                            new Date(),
                            new Location(
                                    networkForm.getLocationName(),
                                    latLon.fst,
                                    latLon.snd,
                                    networkForm.getLocationAddress(),
                                    networkForm.getLocationCrossStreet(),
                                    networkForm.getLocationCity(),
                                    networkForm.getLocationZip(),
                                    networkForm.getLocationCountry()
                            ),
                            networkForm.getNetworkPassword()
                    )
            );
        }else{
            service.addOpenNetwork(
                    new OpenNetwork(
                            networkForm.getNetworkSsid(),
                            new Date(),
                            new Location(
                                    networkForm.getLocationName(),
                                    latLon.fst,
                                    latLon.snd,
                                    networkForm.getLocationAddress(),
                                    networkForm.getLocationCrossStreet(),
                                    networkForm.getLocationCity(),
                                    networkForm.getLocationZip(),
                                    networkForm.getLocationCountry()
                            )
                    )
            );
        }
        return "redirect:/?city=" + networkForm.getLocationCity();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/edit/{id}" )
    public String postSaveNetwork( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "networkform" ) NetworkForm networkForm, BindingResult result ){
        if( networkForm.getNetworkProtected() && networkForm.getNetworkPassword().equals( "" ) )
            result.rejectValue( "networkPassword", "NotEmpty.NetworkForm.networkPassword" );
        if( result.hasErrors() ) return "pages/editnetwork";

        Network network = service.getNetworkById( id );
        Pair<Double, Double> latLon = Converter.getLatLon( networkForm );
        Location location = new Location(
                networkForm.getLocationName(),
                latLon.fst,
                latLon.snd,
                networkForm.getLocationAddress(),
                networkForm.getLocationCrossStreet(),
                networkForm.getLocationCity(),
                networkForm.getLocationZip(),
                networkForm.getLocationCountry()
        );

        if( network.getType() == NetworkType.PROTECTED && networkForm.getNetworkProtected() ){
            // was protected, blijft protected
            ProtectedNetwork protectedNetwork = (ProtectedNetwork)network;

            protectedNetwork.setSsid( networkForm.getNetworkSsid() );
            protectedNetwork.setPassword( new Password( networkForm.getNetworkPassword() ) );
            protectedNetwork.setLocation( location );
            protectedNetwork.setTimestamp( new Date() );

            service.updateProtectedNetwork( protectedNetwork );

        }else if( network.getType() == NetworkType.PROTECTED && !networkForm.getNetworkProtected() ){
            // was protected, nu open
            service.deleteProtectedNetwork( (ProtectedNetwork)network );

            postAddNetwork( networkForm, result );

        }else if( network.getType() == NetworkType.OPEN && networkForm.getNetworkProtected() ){
            // was open, nu protected
            service.deleteOpenNetwork( (OpenNetwork)network );

            postAddNetwork( networkForm, result );

        }else if( network.getType() == NetworkType.OPEN && !networkForm.getNetworkProtected() ){
            // was open, blijft open
            OpenNetwork openNetwork = (OpenNetwork)network;

            openNetwork.setSsid( networkForm.getNetworkSsid() );
            openNetwork.setLocation( location );
            openNetwork.setTimestamp( new Date() );

            service.updateOpenNetwork( openNetwork );

        }else{
            return "redirect:/error";
        }

        return "redirect:/?city=" + networkForm.getLocationCity();
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
    public String postEditPassword( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "passwordform" ) PasswordForm passwordForm, BindingResult result){
        if( result.hasErrors() ) return "pages/editpasswords";

        ProtectedNetwork protectedNetwork = service.getProtectedNetworkById( id );

        protectedNetwork.addPassword( new Password( passwordForm.getPassword() ) );
        protectedNetwork.setTimestamp( new Date() );
        service.updateProtectedNetwork( protectedNetwork );

        //TODO remove city parameter
        return "redirect:/?city=" + passwordForm.getLocationCity();
    }

    @RequestMapping( method = RequestMethod.POST, value = "/comments/{id}" )
    public String postComment( @PathVariable( "id" ) Integer id, @Valid @ModelAttribute( "commentform" ) CommentForm commentForm, BindingResult result ){
        if( result.hasErrors() ) return "pages/comments";

        Network network = service.getNetworkById( id );

        network.addComment( new Comment( commentForm.getMessage() ) );
        service.updateNetwork( network );

        return "redirect:/comments/" + id;
    }

    @RequestMapping( method = RequestMethod.POST, value = "/vote/{network_id}/password/{password_id}" )
    public String postVotePassword(
            @PathVariable( "network_id" ) Integer network_id,
            @PathVariable( "password_id" ) Integer password_id,
            @RequestParam( value = "upvote", required = false ) String upvote,
            @RequestParam( value = "downvote", required = false ) String downvote,
            @RequestParam( value = "city", required = false ) String city ){

        ProtectedNetwork network = service.getProtectedNetworkById( network_id );
        Password password = service.getPasswordById( password_id );

        if( !network.getPasswords().contains( password ) )
            return "redirect:/error";

        if( downvote == null )
            password.upvote();
        else if( upvote == null )
            password.downvote();

        network.setTimestamp( new Date() );
        network.addPassword( password );

        service.updateNetwork( network );
        service.updatePassword( password );

        // TODO remove city parameter
        if( city == null )
            return "redirect:/";
        else
            return "redirect:/?city=" + city;
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //    @ExceptionHandler( DatabaseException.class )
    //    public ModelAndView handleException( Exception e ){
    //        return null;
    //    }
}

