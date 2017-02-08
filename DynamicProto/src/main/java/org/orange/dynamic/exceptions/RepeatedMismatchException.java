package org.orange.dynamic.exceptions;

import java.text.MessageFormat;

/**
 * Created by Sam on 2017/2/7.
 */
public class RepeatedMismatchException extends RuntimeException {

    private final String fieldName;

    private final String messageType;

    private final String actualType;

    public RepeatedMismatchException(String fieldName, String messageType, String actualType) {
        super(MessageFormat.format("Filed {} in MessageType {} is repeated, " +
                "but the value with type {} is not collection or array",
                fieldName,
                messageType,
                actualType));
        this.fieldName = fieldName;
        this.messageType = messageType;
        this.actualType = actualType;
    }
}
