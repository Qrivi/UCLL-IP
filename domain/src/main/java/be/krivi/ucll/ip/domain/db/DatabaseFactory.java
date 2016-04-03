package be.krivi.ucll.ip.domain.db;

import java.util.Properties;

/**
 * Created by Krivi on 21/02/16.
 */
public class DatabaseFactory{

    public static OpenNetworkDB createOpenNetworkDB( Properties properties ){

        String type = properties.getProperty( "type" );
        String name = properties.getProperty( "name" );

        if( type.equals( "MAP" ) )
            return new OpenNetworkMapDB();
        if( type.equals( "DB" ) )
            return new OpenNetworkRDB( name );
        return null;
    }

    public static ProtectedNetworkDB createProtectedNetworkDB( Properties properties ){

        String type = properties.getProperty( "type" );
        String name = properties.getProperty( "name" );

        if( type.equals( "MAP" ) )
            return new ProtectedNetworkMapDB();
        if( type.equals( "DB" ) )
            return new ProtectedNetworkRDB( name );
        return null;
    }

    public static CommentDB createCommentDB( Properties properties ){

        String type = properties.getProperty( "type" );
        String name = properties.getProperty( "name" );

        if( type.equals( "MAP" ) )
            return new CommentMapDB();
        if( type.equals( "DB" ) )
            return new CommentRDB( name );
        return null;
    }

    public static LocationDB createLocationDB( Properties properties ){

        String type = properties.getProperty( "type" );
        String name = properties.getProperty( "name" );

        if( type.equals( "MAP" ) )
            return new LocationMapDB();
        if( type.equals( "DB" ) )
            return new LocationRDB( name );
        return null;
    }

    public static PasswordDB createPasswordDB( Properties properties ){

        String type = properties.getProperty( "type" );
        String name = properties.getProperty( "name" );

        if( type.equals( "MAP" ) )
            return new PasswordMapDB();
        if( type.equals( "DB" ) )
            return new PasswordRDB( name );
        return null;
    }
}
