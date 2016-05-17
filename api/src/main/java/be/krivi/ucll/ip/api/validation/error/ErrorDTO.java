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

    private Map<String, String> errors;

    public ErrorDTO( Map<String, String> errors ){
        this.errors = errors;
    }

    public ErrorDTO( BindingResult result ){
        this.errors = new HashMap<>();

        for( ObjectError error : result.getAllErrors() ){
            FieldError e = (FieldError) error;
            errors.put( e.getField(), error.getDefaultMessage() );
        }
    }

    public Map<String, String> getErrors(){
        return errors;
    }

    public void setErrors( Map<String, String> errors ){
        this.errors = errors;
    }
}
