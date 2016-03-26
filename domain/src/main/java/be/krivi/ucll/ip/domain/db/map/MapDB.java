package be.krivi.ucll.ip.domain.db.map;


import be.krivi.ucll.ip.domain.common.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class MapDB<E extends Entity> {

    private int index;
    protected Map<Integer, E> map;

    public MapDB() {
        this.index = 0;
        this.map = new HashMap<>();
    }

    protected int generateId()
    {
        return (index++);
    }
}
