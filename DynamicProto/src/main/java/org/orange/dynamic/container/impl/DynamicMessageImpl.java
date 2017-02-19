package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.utils.ProtoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Sam on 2017/2/7.
 */
public class DynamicMessageImpl extends AbstractDynamicMessage
{
    private final static Logger logger = LoggerFactory.getLogger(DynamicMessageImpl.class);

    public DynamicMessageImpl(Descriptors.Descriptor descriptor)
    {
        this.descriptor = descriptor;
        builder = com.google.protobuf.DynamicMessage.newBuilder(descriptor);
    }

    public Object get(Object key)
    {
        String keyStr = String.valueOf(key);
        Descriptors.FieldDescriptor fieldDescriptor = preconditionCheck(String.valueOf(key));

        Object rvalue = builder.getAllFields().get(fieldDescriptor);
        if (ProtoUtils.isProtoBasicType(fieldDescriptor.getType()))
        {
            return rvalue;
        }
        if (null != rvalue)
        {
            builder.clearField(fieldDescriptor);
            //we need to lazy build the DynamcMessage now
            SubMessageValue smv = new SubMessageValueDynamic(fieldDescriptor);
            subMessages.put(keyStr, smv.from(rvalue));
            return smv.getContainerValues();
        }
        if (subMessages.containsKey(keyStr))
        {
            SubMessageValue smv = subMessages.get(keyStr);
            return smv.getContainerValues();
        }

        //Create a new container for next Set
        SubMessageValue subMessageValue = new SubMessageValueDynamic(fieldDescriptor);
        subMessages.put(keyStr, subMessageValue);
        return subMessageValue.getContainerValues();
    }

    public Object put(String key, Object value)
    {
        Descriptors.FieldDescriptor fieldDescriptor = preconditionCheck(key);
        if (ProtoUtils.isProtoBasicType(fieldDescriptor.getType()))
        {
            builder.setField(fieldDescriptor, value);
        }
        else
        {
            //due with sub messageTypes
            SubMessageValue subMessageValue = new SubMessageValueDynamic(fieldDescriptor);
            subMessages.put(key, subMessageValue.fill(value));
        }
        return null;
    }

    private static class SubMessageValueDynamic extends SubMessageValue
    {


        public SubMessageValueDynamic(Descriptors.FieldDescriptor fieldDescriptor)
        {
            super(fieldDescriptor);
            init();
        }

        @Override
        protected DynamicMessage buildDynamicMessage()
        {
            return new DynamicMessageImpl(fieldDescriptor.getMessageType());
        }

        @Override
        protected void messageTypeCheck(Object item)
        {
            if (!(item instanceof DynamicMessageImpl) && !(item instanceof com.google.protobuf.DynamicMessage))
            {
                logger.error("Value type for key {} should be {} or {}, but we got {}",
                        fieldDescriptor.getName(),
                        DynamicMessageImpl.class.getCanonicalName(),
                        com.google.protobuf.DynamicMessage.class.getCanonicalName(),
                        item.getClass().getCanonicalName());
                throw new IllegalArgumentException("Wrong value type for field " + fieldDescriptor.getName());
            }
        }
    }



}
