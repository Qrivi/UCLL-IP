package be.krivi.ucll.ip.domain.network;

import be.krivi.ucll.ip.domain.core.Location;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Krivi on 9/02/16.
 */
@Entity
@DiscriminatorValue( "open" )
@Table( name = "network_open" )
public class OpenNetwork extends Network{

    public OpenNetwork(){
    }

    public OpenNetwork( String ssid, Date timestamp, Location location ){
        super( ssid, timestamp, location );
    }

    @Override
    public NetworkType getType(){
        return NetworkType.OPEN;
    }
}
