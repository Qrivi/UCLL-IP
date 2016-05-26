package be.krivi.ucll.ip.api.controller;

import be.krivi.ucll.ip.api.validation.CommentDTO;
import be.krivi.ucll.ip.api.validation.error.ErrorDTO;
import be.krivi.ucll.ip.domain.core.Comment;
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
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping( "/comments" )
public class CommentController{

    @Autowired
    NetworkService service;

    //****************************************************************
    // region RequestMethod.GET
    //****************************************************************

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/{id}" )
    public Set<Comment> getCommentsByNetworkId( @PathVariable( "id" ) Integer id ) throws NullPointerException{
        Network network = service.getNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.Network}" );
        return network.getComments();
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/open/{id}" )
    public Set<Comment> getCommentsByOpenNetworkId( @PathVariable( "id" ) Integer id ) throws NullPointerException{
        OpenNetwork network = service.getOpenNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.OpenNetwork}" );
        return network.getComments();
    }

    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.GET, value = "/protected/{id}" )
    public Set<Comment> getCommentsByProtectedNetworkId( @PathVariable( "id" ) Integer id ) throws NullPointerException{
        ProtectedNetwork network = service.getProtectedNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.ProtectedNetwork}" );
        return network.getComments();
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region RequestMethod.POST
    //****************************************************************

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.POST, value = "/{id}" )
    public ResponseEntity addCommentToNetwork( @PathVariable( "id" ) Integer id, @Valid @RequestBody CommentDTO commentDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        Network network = service.getNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.Network}" );

        network.addComment( new Comment( commentDTO.getMessage() ) );
        service.updateNetwork( network );

        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.POST, value = "/open/{id}" )
    public ResponseEntity addCommentToOpenNetwork( @PathVariable( "id" ) Integer id, @Valid @RequestBody CommentDTO commentDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        OpenNetwork network = service.getOpenNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.OpenNetwork}" );

        network.addComment( new Comment( commentDTO.getMessage() ) );
        service.updateNetwork( network );

        return new ResponseEntity<>( network, HttpStatus.OK );
    }

    @Consumes( "application/json" )
    @Produces( "application/json" )
    @RequestMapping( method = RequestMethod.POST, value = "/protected/{id}" )
    public ResponseEntity addCommentToProtectedNetwork( @PathVariable( "id" ) Integer id, @Valid @RequestBody CommentDTO commentDTO, BindingResult result ){
        if( result.hasErrors() )
            return new ResponseEntity<>( new ErrorDTO( result ), HttpStatus.BAD_REQUEST );

        ProtectedNetwork network = service.getProtectedNetworkById( id );
        if( network == null )
            throw new NullPointerException( "{NullPointerException.ProtectedNetwork}" );

        network.addComment( new Comment( commentDTO.getMessage() ) );
        service.updateNetwork( network );

        return new ResponseEntity<>( network, HttpStatus.OK );
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