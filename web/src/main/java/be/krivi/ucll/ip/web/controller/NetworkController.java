package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.service.NetworkService;
import be.krivi.ucll.ip.web.validation.NetworkForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Krivi on 14/03/16.
 */
@Controller
@RequestMapping( value = "/" )
public class NetworkController{

    @Autowired
    private NetworkService service;

    @RequestMapping( method = RequestMethod.GET, value = "/{id}" )
    public String language( @PathVariable String id, HttpServletResponse response ){
        Cookie cookie = new Cookie( "lang", id );
        cookie.setPath( "/" );
        cookie.setMaxAge( 60 * 60 * 24 * 365 * 10 );
        response.addCookie( cookie );
        return "redirect:/";
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView getIndex(){
        return new ModelAndView( "index", "networks", service.getAllNetworks() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/add" )
    public ModelAndView getAddNewNetwork(){
        return new ModelAndView( "pages/addnetwork", "networkform", new NetworkForm() );
    }

    @RequestMapping( method = RequestMethod.POST, value = "/add" )
    public String postAddNewNetwork( @Valid @ModelAttribute( "networkform" ) NetworkForm networkForm, BindingResult result ){
        if( networkForm.getNetworkProtected() && networkForm.getNetworkPassword().equals( "" ) )
            result.rejectValue( "networkPassword", "NotEmpty.NetworkForm.networkPassword" );
        if( result.hasErrors() ) return "pages/addnetwork";

        if( networkForm.getNetworkProtected() ){
            //closed
        }else{
            // open
        }
        return "redirect:/";
    }

    //    @ExceptionHandler( DatabaseException.class )
    //    public ModelAndView handleException( Exception e ){
    //        return null;
    //    }
}

