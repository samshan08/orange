package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.exceptions.RepeatedMismatchException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sam on 2017/2/7.
 */
public class SubMessageValue
{

    private List<DynamicMessage> messageContainers = new ArrayList<DynamicMessage>();

    private final Descriptors.FieldDescriptor fieldDescriptor;

    public SubMessageValue(Descriptors.FieldDescriptor fieldDescriptor)
    {
        this.fieldDescriptor = fieldDescriptor;
        if (!fieldDescriptor.isRepeated())
        {
            messageContainers.add(new DynamicMessageImpl(fieldDescriptor.getMessageType()));
        }
    }

    public SubMessageValue from(Object value)
    {
        if (null == value)
        {
            return this;
        }
        if (!fieldDescriptor.isRepeated())
        {
            messageContainers.get(0).fromProtoMessage((com.google.protobuf.DynamicMessage)value);
        }
        else
        {
            List<?> protoMessages = (List<?>)value;
            for (Object protoMessage : protoMessages)
            {
                DynamicMessage msg = new DynamicMessageImpl(fieldDescriptor.getMessageType());
                messageContainers.add(msg.fromProtoMessage((com.google.protobuf.DynamicMessage)protoMessage));
            }
        }
        return this;
    }

    public Object getContainerValues()
    {
        if (fieldDescriptor.isRepeated())
        {
            return messageContainers;
        }
        return messageContainers.get(0);
    }

    private void buildContainer(Object item)
    {
        DynamicMessageImpl container = new DynamicMessageImpl(fieldDescriptor.getMessageType());
        messageContainers.add(container.fill(item));
    }

    public SubMessageValue fill(Object value)
    {
        if (null == value)
        {
            return this;
        }
        if (!fieldDescriptor.isRepeated())
        {
            messageContainers.clear();
            buildContainer(value);
        }
        else
        {
            if (value instanceof Collection)
            {
                for (Object item : (Collection<?>)value)
                {
                    buildContainer(item);
                }
            }
            else
            {
                throw new RepeatedMismatchException(fieldDescriptor.getName(),
                        fieldDescriptor.getMessageType().getFullName(), value.getClass().getCanonicalName());
            }
        }
        return this;
    }

    public void buildProtoMessage(com.google.protobuf.DynamicMessage.Builder dynamicBuilder)
    {
        if (fieldDescriptor.isRepeated())
        {
            for (DynamicMessage msg : messageContainers)
            {
                dynamicBuilder.addRepeatedField(fieldDescriptor, msg.toProtoMessage());
            }
        }
        else
        {
            dynamicBuilder.setField(fieldDescriptor, messageContainers.get(0).toProtoMessage());
        }
    }



}
