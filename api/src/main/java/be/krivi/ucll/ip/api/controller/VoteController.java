package be.krivi.ucll.ip.api.controller;

import be.krivi.ucll.ip.api.validation.error.ErrorDTO;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( "/passwords" )
public class VoteController{

    @Autowired
    NetworkService service;

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.POST, value = "/{id}" )
    public ResponseEntity addPasswordToProtectedNetwork( @PathVariable( "id" ) Integer id, @RequestParam( value = "action", required = false ) String action ){

        Password password = service.getPasswordById( id );
        if( password == null )
            throw new NullPointerException( "{NullPointerException.Password}" );

        if( action != null && action.equals( "upvote" ) )
            password.upvote();
        else if( action != null && action.equals( "downvote" ) )
            password.downvote();

        service.updatePassword( password );
        return new ResponseEntity<>( password, HttpStatus.OK );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    @Produces( "application/json" )
    @ExceptionHandler( NullPointerException.class )
    public ResponseEntity handleException( Exception e ){
        Map<String, String> errors = new HashMap<>();
        errors.put( "HTTP 404 Not Found", e.getMessage() );

        return new ResponseEntity<>( new ErrorDTO( errors ), HttpStatus.NOT_FOUND );
    }
}