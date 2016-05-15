package be.krivi.ucll.ip.api.controller;

import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping( "/networks" )
public class NetworkController{

    @Autowired
    NetworkService service;

    //    @RequestMappingg(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    //    public void addSpecialty(@RequestBody Specialty specialty){
    //        service.addSpecialty(specialty);
    //    }

    @RequestMapping( method = RequestMethod.GET, value = "", produces = MediaType.APPLICATION_JSON_VALUE )
    public Collection<Network> getAllNetworks(){
        return service.getAllNetworks();
    }

    @RequestMapping( method = RequestMethod.GET, value = "/test", produces = MediaType.APPLICATION_JSON_VALUE )
    public String test(){
        return "Hello API";
    }
}