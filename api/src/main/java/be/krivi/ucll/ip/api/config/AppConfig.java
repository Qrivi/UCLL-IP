package be.krivi.ucll.ip.api.config;

import be.krivi.ucll.ip.api.listener.ContextListener;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Krivi on 25/04/16.
 */
@Configuration
@ComponentScan( "be.krivi.ucll.ip.api.controller" )
@EnableWebMvc
public class AppConfig{

    private static final String DB_CONFIG = "DatabaseConfig.properties";

    @Bean
    public NetworkService service(){

        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try( InputStream resourceStream = loader.getResourceAsStream( DB_CONFIG ) ){
            properties.load( resourceStream );
        }catch( IOException e ){
            // spring is vies
            // TODO beter opvangen :0))
        }

        return new NetworkService( properties );
    }

    @Bean
    public ContextListener eventListenerBean(){
        return new ContextListener();
        //<bean id="eventListenerBean" class="be.krivi.ucll.ip.web.listener.ContextListener"/>
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding( "UTF-8" );
        filter.setForceEncoding( true );
        return filter;
    }

}
