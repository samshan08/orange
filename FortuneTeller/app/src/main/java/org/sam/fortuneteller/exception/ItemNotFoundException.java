package org.sam.fortuneteller.exception;

import lombok.RequiredArgsConstructor;

/**
 * Created by Sam on 2017/12/3.
 */
public class ItemNotFoundException extends Exception {

    private final String name;

    public ItemNotFoundException(String name)
    {
        super();
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Item with name " + name  + " not found.";
    }
}
