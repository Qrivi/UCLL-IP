package be.krivi.ucll.ip.api.config;

import be.krivi.ucll.ip.api.listener.ContextListener;
import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Krivi on 25/04/16.
 */
@EnableWebMvc
@Configuration
@ComponentScan( "be.krivi.ucll.ip.api.controller" )
public class AppConfig /* extends WebMvcConfigurerAdapter*/{

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
    public ContextListener eventListener(){
        return new ContextListener();
        //<bean id="eventListenerBean" class="be.krivi.ucll.ip.web.listener.ContextListener"/>
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename( "ValidationMessages" );
        source.setDefaultEncoding( "UTF-8" );

        return source;
    }

    //    @Override
    //    public void configureMessageConverters( List<HttpMessageConverter<?>> converters )
    //    {
    //        ObjectMapper mapper = new ObjectMapper();
    //        mapper.registerModule(new JaxbAnnotationModule());
    //        converters.add(new Jaxb2RootElementHttpMessageConverter());
    //        converters.add(new MappingJackson2HttpMessageConverter(mapper));
    //        super.configureMessageConverters(converters);
    //    }

    //    @Bean
    //    public MappingJackson2HttpMessageConverter jsonConverter(){
    //        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    //
    //        converter.setPrefixJson( false );
    //        converter.setSupportedMediaTypes( new ArrayList<MediaType>(){{
    //            add( MediaType.APPLICATION_JSON );
    //            add( MediaType.APPLICATION_JSON_UTF8 );
    //        }} );
    //
    //        return converter;
    //    }

    //    @Bean
    //    public AnnotationMethodHandlerAdapter methodHandlerAdapter(){
    //        AnnotationMethodHandlerAdapter adapter = new AnnotationMethodHandlerAdapter();
    //
    //        HttpMessageConverter<?>[] converters = new HttpMessageConverter[1];
    //        converters[0] = httpMessageConverter();
    //
    //        adapter.setWebBindingInitializer( webBindingInitializer() );
    //        adapter.setMessageConverters( converters );
    //
    //        return adapter;
    //    }
    //
    //    @Bean
    //    public ConfigurableWebBindingInitializer webBindingInitializer(){
    //        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
    //        initializer.setValidator( new LocalValidatorFactoryBean() );
    //        return initializer;
    //    }
    //
    //    @Bean
    //    public MappingJackson2HttpMessageConverter httpMessageConverter(){
    //        return new MappingJackson2HttpMessageConverter(  );
    //    }
}
