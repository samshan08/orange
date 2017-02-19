package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.container.proxy.MessageProxy;
import org.orange.dynamic.container.proxy.MessageProxyProto;
import org.orange.utils.ProtoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by SAM on 2017/2/18.
 */
public class ProtoMessageImpl extends AbstractDynamicMessage
{
    private final static Logger logger = LoggerFactory.getLogger(ProtoMessageImpl.class);

    private MessageProxyProto messageClazz;

    public ProtoMessageImpl(MessageProxy messageClazz)
    {
        this.messageClazz = (MessageProxyProto)messageClazz;
        builder = messageClazz.newBuilder();
        descriptor = builder.getDescriptorForType();
    }

    @Override
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
            SubMessageValue smv = new SubMessageProto(fieldDescriptor, messageClazz);
            subMessages.put(keyStr, smv.from(rvalue));
            return smv.getContainerValues();
        }
        if (subMessages.containsKey(keyStr))
        {
            SubMessageValue smv = subMessages.get(keyStr);
            return smv.getContainerValues();
        }

        //Create a new container for next Set
        SubMessageValue subMessageValue = new SubMessageProto(fieldDescriptor, messageClazz);
        subMessages.put(keyStr, subMessageValue);
        return subMessageValue.getContainerValues();
    }

    @Override
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
            SubMessageValue subMessageValue = new SubMessageProto(fieldDescriptor, messageClazz);
            subMessages.put(key, subMessageValue.fill(value));
        }
        return null;
    }

    private static class SubMessageProto extends SubMessageValue
    {
        private MessageProxyProto subClazzProxy;

        public SubMessageProto(Descriptors.FieldDescriptor fieldDescriptor, MessageProxy parentProxy)
        {
            super(fieldDescriptor);
            subClazzProxy = (MessageProxyProto)parentProxy.findFieldProtoProxy(fieldDescriptor.getName());
            init();
        }

        @Override
        protected DynamicMessage buildDynamicMessage()
        {
            return new ProtoMessageImpl(subClazzProxy);
        }

        @Override
        protected void messageTypeCheck(Object item)
        {
            if (!(item instanceof ProtoMessageImpl) && item.getClass() != subClazzProxy.getClazz())
            {
                logger.error("Value type for key {} should be {} or {}, but we got {}",
                        fieldDescriptor.getName(),
                        ProtoMessageImpl.class.getCanonicalName(),
                        subClazzProxy.getClazz().getCanonicalName(),
                        item.getClass().getCanonicalName());
                throw new IllegalArgumentException("Wrong value type for field " + fieldDescriptor.getName());
            }
        }
    }

}
