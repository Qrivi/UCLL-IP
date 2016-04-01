/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.krivi.ucll.ip.web.listener;

import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;

public class ContextListener implements ApplicationListener{

    @Autowired
    private NetworkService service;

    @Override
    public void onApplicationEvent( ApplicationEvent event ){
        if( event instanceof ContextStartedEvent ){
            service.openConnection();
        }else if( event instanceof ContextClosedEvent ){
            service.closeConnection();
        }
    }
}

