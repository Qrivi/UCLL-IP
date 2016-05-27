package model;

import model.exception.DomainException;
import model.wrapper.Location;
import model.wrapper.Password;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krivi on 21/02/16.
 */
public class ProtectedNetwork extends Network{

    private List<Password> passwords;

    public ProtectedNetwork( String ssid, Location location ) throws DomainException{
        super( ssid, location );

        passwords = new ArrayList<>();
    }

    @Override
    public NetworkType getType(){
        return NetworkType.PROTECTED;
    }

    public void addPassword( Password password ){
        passwords.add( password );
    }

    public void removePassword( Password password ){
        passwords.remove( password );
    }

    public List<Password> getPasswords(){
        return passwords;
    }
}
