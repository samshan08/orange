package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.exceptions.RepeatedMismatchException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sam on 2017/2/7.
 */
public abstract class SubMessageValue
{

    private List<DynamicMessage> messageContainers;

    protected final Descriptors.FieldDescriptor fieldDescriptor;

    public SubMessageValue(Descriptors.FieldDescriptor fieldDescriptor)
    {
        this.fieldDescriptor = fieldDescriptor;
    }

    /**
     * call in the subclasses
     */
    protected void init() {
        if (!fieldDescriptor.isRepeated())
        {
            messageContainers = new ArrayList<DynamicMessage>(1);
            messageContainers.add(buildDynamicMessage());
        }
        else
        {
            messageContainers = new ArrayList<DynamicMessage>();
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
            messageContainers.get(0).fromProtoMessage((Message)value);
        }
        else
        {
            List<?> protoMessages = (List<?>)value;
            for (Object protoMessage : protoMessages)
            {
                DynamicMessage msg = buildDynamicMessage();
                messageContainers.add(msg.fromProtoMessage((Message)protoMessage));
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
        messageTypeCheck(item);
        if (item instanceof DynamicMessage)
        {
            messageContainers.add((DynamicMessage)item);
        }
        else
        {
            //This is message
            DynamicMessage container = buildDynamicMessage();
            messageContainers.add(container.fromProtoMessage((Message)item));
        }
    }

    protected SubMessageValue fill(Object value)
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

    public void buildProtoMessage(Message.Builder dynamicBuilder)
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

    protected abstract DynamicMessage buildDynamicMessage();

    protected abstract void messageTypeCheck(Object item);
}
