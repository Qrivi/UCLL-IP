package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Krivi on 14/03/16.
 */
@Controller
@RequestMapping( "/" )
public class NetworkController{

    @Autowired
    private NetworkService service;


    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView getRoot() throws DatabaseException{
        //TODO naar waar throwt dit de exception door feitelijk?
        return new ModelAndView( "index", "networks", service.getAllNetworks() );
    }

    @ExceptionHandler( DatabaseException.class )
    public ModelAndView handleException( Exception e ){
        return null;
    }


}

