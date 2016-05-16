package be.krivi.ucll.ip.api.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Krivi on 25/04/16.
 */
public class AppInitializer implements WebApplicationInitializer{

    private static final String CONFIG_LOCATION = "be.krivi.ucll.ip.api.config";
    private static final String MAPPING_URL = "/*";

    @Override
    public void onStartup( ServletContext servletContext ) throws ServletException{
        WebApplicationContext context = getContext();

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding( "UTF-8" );
        encodingFilter.setForceEncoding( true );

        servletContext.addListener( new ContextLoaderListener( context ) );
        servletContext.addFilter( "encodingFilter", encodingFilter );

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet( "DispatcherServlet", new DispatcherServlet( context ) );

        dispatcher.setLoadOnStartup( 1 );
        dispatcher.addMapping( MAPPING_URL );
    }

    private AnnotationConfigWebApplicationContext getContext(){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation( CONFIG_LOCATION );
        return context;
    }
}
