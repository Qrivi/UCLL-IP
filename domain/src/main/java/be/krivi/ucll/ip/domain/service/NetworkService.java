package be.krivi.ucll.ip.domain.service;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.db.DatabaseFactory;
import be.krivi.ucll.ip.domain.db.OpenNetworkDB;
import be.krivi.ucll.ip.domain.db.ProtectedNetworkDB;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

import java.util.Collection;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Krivi on 21/02/16.
 */
public class NetworkService{

    private OpenNetworkDB openNetworkDB;
    private ProtectedNetworkDB protectedNetworkDB;

    public NetworkService( Properties properties ){
        openNetworkDB = DatabaseFactory.createOpenNetworkDB( properties );
        protectedNetworkDB = DatabaseFactory.createProtectedNetworkDB( properties );

        if( properties.getProperty( "type" ).equals( "MAP" ) )
            populateMap();
    }

    private void populateMap(){
        try{
            addProtectedNetwork(
                    new ProtectedNetwork(
                            "McDonalds Free WiFi",
                            new Location(
                                    "McDonald's Leuven",
                                    50.879844,
                                    4.700518,
                                    "Kortestraat 7",
                                    "",
                                    "Leuven",
                                    3000,
                                    "Belgium" ),
                            "FriesAreDope"
                    )
            );
            addProtectedNetwork(
                    new ProtectedNetwork(
                            "Leuven HotSpot",
                            new Location(
                                    "",
                                    50.879175,
                                    4.701888,
                                    "Grote Markt",
                                    "at Bondgenotenlaan",
                                    "Leuven",
                                    3000,
                                    "Belgium" ),
                            "LeuvenConnects"
                    )
            );
            addOpenNetwork(
                    new OpenNetwork(
                            "OnanConnect",
                            new Location(
                                    "Koffie Onan",
                                    50.878573,
                                    4.699040,
                                    "Parijsstraat 28",
                                    "",
                                    "Leuven",
                                    3000,
                                    "Belgium" )
                    )
            );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    public void openConnection(){
        openNetworkDB.openConnexion();
        protectedNetworkDB.openConnexion();
    }

    public void closeConnection(){
        openNetworkDB.closeConnexion();
        protectedNetworkDB.closeConnexion();
    }

    //****************************************************************
    // region OpenNetwork
    //****************************************************************

    public OpenNetwork getOpenNetworkById( Integer id ) throws DatabaseException{
        return openNetworkDB.getById( id );
    }

    public Collection<OpenNetwork> getAllOpenNetworks() throws DatabaseException{
        return openNetworkDB.getAll();
    }

    public void addOpenNetwork( OpenNetwork openNetwork ) throws DatabaseException{
        openNetworkDB.add( openNetwork );
    }

    public void deleteOpenNetwork( OpenNetwork openNetwork ) throws DatabaseException{
        openNetworkDB.delete( openNetwork );
    }

    public void updateOpenNetwork( OpenNetwork openNetwork ) throws DatabaseException{
        openNetworkDB.update( openNetwork );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region ProtectedNetwork
    //****************************************************************

    public ProtectedNetwork getProtectedNetworkById( Integer id ) throws DatabaseException{
        return protectedNetworkDB.getById( id );
    }

    public Collection<ProtectedNetwork> getAllProtectedNetworks() throws DatabaseException{
        return protectedNetworkDB.getAll();
    }

    public void addProtectedNetwork( ProtectedNetwork protectedNetwork ) throws DatabaseException{
        protectedNetworkDB.add( protectedNetwork );
    }

    public void deleteProtectedNetwork( ProtectedNetwork protectedNetwork ) throws DatabaseException{
        protectedNetworkDB.delete( protectedNetwork );
    }

    public void updateProtectedNetwork( ProtectedNetwork protectedNetwork ) throws DatabaseException{
        protectedNetworkDB.update( protectedNetwork );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region Network
    //****************************************************************

    public Collection<Network> getAllNetworks() throws DatabaseException{
        return Stream.concat(
                getAllOpenNetworks().stream(),
                getAllProtectedNetworks().stream()
        ).collect( Collectors.toList());
    }

    //****************************************************************
    // endregion
    //****************************************************************

}
