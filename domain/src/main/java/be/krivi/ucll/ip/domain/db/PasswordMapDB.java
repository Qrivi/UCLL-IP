package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;

/**
 * Created by Krivi on 21/02/16.
 */
public class PasswordMapDB extends CRUDMapDB<Password> implements PasswordDB{

    public PasswordMapDB(){
        super();
    }

}
