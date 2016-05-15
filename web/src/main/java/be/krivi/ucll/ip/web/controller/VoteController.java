package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by Krivi on 15/05/16.
 */
@Controller
@RequestMapping( value = "/vote" )
public class VoteController{

    @Autowired
    private NetworkService service;

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @RequestMapping( method = RequestMethod.POST, value = "/{network_id}/password/{password_id}" )
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
}
