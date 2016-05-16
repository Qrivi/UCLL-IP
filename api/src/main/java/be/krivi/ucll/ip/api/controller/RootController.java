package be.krivi.ucll.ip.api.controller;

import be.krivi.ucll.ip.domain.network.Network;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by Krivi on 16/05/16.
 */
@Controller
@RequestMapping( "/" )
public class RootController{

    @RequestMapping( method = RequestMethod.GET )
    public String redirectToDocumentation(){
        return "redirect:http://193.191.187.14:10226/docs";
    }
}
