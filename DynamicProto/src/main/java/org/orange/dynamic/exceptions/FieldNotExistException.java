package org.orange.dynamic.exceptions;

import java.text.MessageFormat;

/**
 * Created by Sam on 2017/2/7.
 */
public class FieldNotExistException extends RuntimeException {
    private final String fieldName;

    private final String messageType;

    public FieldNotExistException(String fieldName, String messageType) {
        super(MessageFormat.format("Filed {} not found. MessageType is {}", fieldName, messageType));
        this.fieldName = fieldName;
        this.messageType = messageType;
    }
}
