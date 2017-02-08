package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import lombok.Getter;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.container.DynamicMessageList;

/**
 * Created by Sam on 2017/2/7.
 */
public class SubMessageValue {

    private DynamicMessage messageContainer;

    private DynamicMessageList messageContainers;

    @Getter
    private final Descriptors.FieldDescriptor fieldDescriptor;

    public SubMessageValue(Descriptors.FieldDescriptor fieldDescriptor) {
        this.fieldDescriptor = fieldDescriptor;
        if (fieldDescriptor.isRepeated()){
            messageContainer = new DynamicMessageImpl(fieldDescriptor.getMessageType());
        } else {
            messageContainers = new DynamicMessageListImpl(fieldDescriptor.getName(), fieldDescriptor.getMessageType());
        }
    }

    public SubMessageValue fill(Object value) {
        if (null == value) {
            return this;
        }
        if (fieldDescriptor.isRepeated()) {
            messageContainers.fill(value);
        } else {
            messageContainer.fill(value);
        }
        return this;
    }

    public Object getContainerValues() {
        if (fieldDescriptor.isRepeated()) {
            return messageContainers;
        } else {
            return messageContainer;
        }
    }

    public Object toProtoMessage() {
        if (fieldDescriptor.isRepeated()) {
            return messageContainers.toProtoMessages();
        } else {
            return messageContainer.toProtoMessage();
        }
    }

}
