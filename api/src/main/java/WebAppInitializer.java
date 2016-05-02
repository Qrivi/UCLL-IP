import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Krivi on 25/04/16.
 */
public class WebAppInitializer implements WebApplicationInitializer{

    public void onStartup( ServletContext servletContext ) throws ServletException{
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register( ApplicationConfig.class );
        ctx.setServletContext( servletContext );

        ServletRegistration.Dynamic dynamic = servletContext.addServlet( "dispatcher", new DispatcherServlet( ctx ) );
        dynamic.addMapping( "/" );
        dynamic.setLoadOnStartup( 1 );
    }
}
