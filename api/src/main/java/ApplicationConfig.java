import be.krivi.ucll.ip.domain.service.NetworkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Krivi on 25/04/16.
 */
@Configuration
@ComponentScan( "be.krivi.ucll.ip.domain.service" )
@EnableWebMvc
public class ApplicationConfig{
    @Bean
    public NetworkService service(){

        Properties properties = new Properties();
        try{
            properties.load( new FileInputStream( "DatabaseConfig.properties" ) );
        }catch( IOException e ){
            // spring is vies
        }

        return new NetworkService( properties );
    }
}
