package org.sam.fortuneteller.data;

import org.sam.fortuneteller.exception.ItemNotFoundException;
import org.sam.fortuneteller.model.Results;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sam on 2017/12/3.
 */
public class DataManager {

    private static final DataManager instance = new DataManager();

    public static DataManager getInstance() {
        return instance;
    }

    private Map<String, Results> storage = new HashMap<>();

    public void save(Results results)
    {
        storage.put(results.getName(), results);
    }

    public Results get(String name) throws ItemNotFoundException
    {
        if (!storage.containsKey(name))
        {
            throw new ItemNotFoundException(name);
        }
        return storage.get(name);
    }

    public Results newAndSave(String name)
    {
        Results results = new Results();
        save(results);
        return results;
    }

}
