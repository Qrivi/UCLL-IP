package be.krivi.ucll.ip.domain.network;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Krivi on 21/02/16.
 */
@Entity
@DiscriminatorValue( "protected" )
@Table( name = "network_protected" )
public class ProtectedNetwork extends Network{

    @Valid
    @Size( min = 1, max = 3, message = "{Size.ProtectedNetwork.passwords}" )
    @NotNull( message = "{NotNull.ProtectedNetwork.passwords}" )
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
    private Set<Password> passwords;

    public ProtectedNetwork(){
    }

    public ProtectedNetwork( String ssid, Date timestamp, Location location, String password ){
        super( ssid, timestamp, location );
        passwords = new TreeSet<>();
        passwords.add( new Password( password ) );
    }

    public ProtectedNetwork( String ssid, Date timestamp, Location location, Set<Password> passwords ){
        super( ssid, timestamp, location );
        this.passwords = passwords;
    }

    @Override
    public NetworkType getType(){
        return NetworkType.PROTECTED;
    }

    public void addPassword( Password password ){
        if( passwords.size() >= 3 && !passwords.contains( password ) )
            passwords.remove( passwords.stream().min( Password::compareTo ).get() );
        passwords.add( password );
    }

    public void removePassword( Password password ){
        passwords.remove( password );
    }

    public Set<Password> getPasswords(){
        return passwords;
    }

    public Password getTopPassword(){
        return passwords.stream().max( Password::compareTo ).get();
    }

    public void setPassword( Password password ){
        passwords.clear();
        passwords.add( password );
    }
}
