package be.krivi.ucll.ip.web.controller;

import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Krivi on 14/03/16.
 */

@Controller
@RequestMapping(value="/")
public class RootController{

    @Autowired
    private NetworkService service;


    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView getRoot() throws Exception{
        //TODO naar waar throwt dit de exception door feitelijk?
        return new ModelAndView( "index", "networks", service.getAllNetworks() );
    }


}
