package be.krivi.ucll.ip.domain.db.map;


import be.krivi.ucll.ip.domain.common.Database;
import be.krivi.ucll.ip.domain.common.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class MapDB<E extends Entity> implements Database{

    protected Map<Integer, E> map;
    private int index;

    public MapDB(){
        this.index = 0;
        this.map = new HashMap<>();
    }

    protected int generateId(){
        return ( index++ );
    }

    public void openConnexion(){
        this.map = new HashMap<>();
    }

    public void closeConnexion(){
        this.map.clear();
    }
}
