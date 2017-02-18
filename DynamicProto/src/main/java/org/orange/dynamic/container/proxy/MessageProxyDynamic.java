package org.orange.dynamic.container.proxy;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.Message;
import lombok.Getter;
import org.orange.dynamic.DynamicDescriptorPool;
import org.orange.utils.ProtoUtils;

/**
 * Created by SAM on 2017/2/18.
 */
public class MessageProxyDynamic implements MessageProxy
{
    @Getter
    private final Descriptors.Descriptor rawDescriptor;

    public MessageProxyDynamic(Descriptors.Descriptor rawDescriptor)
    {
        this.rawDescriptor = rawDescriptor;
    }

    @Override
    public Message.Builder newBuilder()
    {
        return DynamicMessage.newBuilder(rawDescriptor);
    }

    @Override
    public Message getDefaultInstance()
    {
        return DynamicMessage.getDefaultInstance(rawDescriptor);
    }

    @Override
    public MessageProxy findFieldProtoProxy(String fieldName)
    {
        Descriptors.FieldDescriptor fieldByName = rawDescriptor.findFieldByName(fieldName);
        if (null == fieldByName || ProtoUtils.isProtoBasicType(fieldByName.getType()))
        {
            return null;
        }
        return DynamicDescriptorPool.getInstance().findProtoClazz(fieldByName.getMessageType());
    }
}
