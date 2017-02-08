package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.container.DynamicMessageList;
import org.orange.dynamic.exceptions.RepeatedMismatchException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sam on 2017/2/7.
 */
@AllArgsConstructor
public class DynamicMessageListImpl extends ArrayList<DynamicMessage> implements DynamicMessageList {

    private final String fieldName;

    private final Descriptors.Descriptor containingDescriptor;

    public List<com.google.protobuf.DynamicMessage> toProtoMessages() {
        List<com.google.protobuf.DynamicMessage> dynamicMessages = new ArrayList<com.google.protobuf.DynamicMessage>();
        for (DynamicMessage container : this) {
            dynamicMessages.add(container.toProtoMessage());
        }
        return dynamicMessages;
    }

    public DynamicMessageList fill(Object value) {
        if (value instanceof Collection) {
            for (Object item : (Collection<?>) value) {
                buildContainer(item);
            }
        } else if (value.getClass().isArray()) {
            for (Object item : (Object[]) value) {
                buildContainer(item);
            }
        } else {
            throw new RepeatedMismatchException(fieldName,
                    containingDescriptor.getFullName(), value.getClass().getCanonicalName());
        }
        return this;
    }

    private void buildContainer(Object item) {
        DynamicMessage container = new DynamicMessageImpl(containingDescriptor);
        add(container.fill(item));
    }


    @Override
    public DynamicMessage get(int index) {
        if (index == size()) {
            add(new DynamicMessageImpl(containingDescriptor));
        }
        return super.get(index);
    }
}
