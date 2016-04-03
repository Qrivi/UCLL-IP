package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUDJPADatabase;
import be.krivi.ucll.ip.domain.core.Password;

/**
 * Created by Krivi on 21/02/16.
 */
public class PasswordRDB extends CRUDJPADatabase<Password> implements PasswordDB{

    public PasswordRDB( String name ){
        super( name, Password.class );
    }
}
