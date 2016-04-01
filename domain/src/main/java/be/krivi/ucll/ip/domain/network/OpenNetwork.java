package be.krivi.ucll.ip.domain.network;

import be.krivi.ucll.ip.domain.core.Location;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Krivi on 9/02/16.
 */
@Entity
@DiscriminatorValue( "open" )
@Table( name = "network_open" )
public class OpenNetwork extends Network{

    public OpenNetwork(){
    }

    public OpenNetwork( String ssid, Location location ){
        super( ssid, location );
    }

    @Override
    public NetworkType getType(){
        return NetworkType.OPEN;
    }
}
