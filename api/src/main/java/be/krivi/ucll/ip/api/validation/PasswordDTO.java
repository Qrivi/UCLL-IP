package be.krivi.ucll.ip.api.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Krivi on 01/04/16.
 */

@JsonIgnoreProperties( ignoreUnknown = true )
public class PasswordDTO{

    @NotBlank( message = "{NotBlank.PasswordDTO.password}" )
    private String password;

    public PasswordDTO(){
    }

    public String getPassword(){
        return password;
    }

    public void setPassword( String password ){
        this.password = password;
    }
}
