package be.krivi.ucll.ip.api.validation.error;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Krivi on 17/05/16.
 */

public class ErrorDTO{

    private Map<String, Map> errors;

    public ErrorDTO( BindingResult result ){
        this.errors = new HashMap<>();
        Map<String, String> networkErrors = new HashMap<>();
        Map<String, String> locationErrors = new HashMap<>();

        for( ObjectError error : result.getAllErrors() ){
            FieldError e = (FieldError)error;

            if( e.getField().contains( "." ) )
                locationErrors.put( e.getField().replace( "location.", "" ), error.getDefaultMessage() );
            else
                networkErrors.put( e.getField(), error.getDefaultMessage() );

            if( !networkErrors.isEmpty() )
                errors.put( "network", networkErrors );
            if( !locationErrors.isEmpty() )
                errors.put( "location", locationErrors );
        }
    }

    public ErrorDTO( String type, Map<String, String> map ){
        this.errors = new HashMap<>();
        errors.put( type, map );
    }

    public ErrorDTO( Map<String, Map> map ){
        this.errors = map;
    }

    public Map<String, Map> getErrors(){
        return errors;
    }

    public void setErrors( Map<String, Map> errors ){
        this.errors = errors;
    }
}
