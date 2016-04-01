package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.service.NetworkService;
import be.krivi.ucll.ip.web.form.NetworkForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Krivi on 14/03/16.
 */
@Controller
@RequestMapping( value = "/" )
public class NetworkController{

    @Autowired
    private NetworkService service;

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView getIndex(){
        return new ModelAndView( "index", "networks", service.getAllNetworks() );
    }

    @RequestMapping( method = RequestMethod.GET, value = "/add")
    public ModelAndView getAddNewNetwork(){
        return new ModelAndView( "pages/addnetwork", "networkform", new NetworkForm() );
    }

    @RequestMapping( method = RequestMethod.POST, value = "/add")
    public String postAddNewNetwork( @Valid @ModelAttribute("networkform") NetworkForm networkForm, BindingResult result ){
        if( result.hasErrors() ) return "pages/addnetwork";

        return "redirect:/";
    }

//    @ExceptionHandler( DatabaseException.class )
//    public ModelAndView handleException( Exception e ){
//        return null;
//    }
}

