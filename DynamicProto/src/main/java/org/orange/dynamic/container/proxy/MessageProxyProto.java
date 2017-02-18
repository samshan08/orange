package org.orange.dynamic.container.proxy;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import lombok.Getter;
import org.orange.dynamic.DynamicDescriptorPool;
import org.orange.utils.ProtoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * Created by SAM on 2017/2/18.
 */
public class MessageProxyProto implements MessageProxy
{
    private final static String METHOD_newBuilder = "newBuilder";

    private static final String METHOD_getDefaultInstance = "getDefaultInstance";

    private final static Logger logger = LoggerFactory.getLogger(MessageProxy.class);

    @Getter
    private final Class<?> clazz;

    @Getter
    private final Descriptors.Descriptor rawDescriptor;

    private Method newBuilder;

    private Method getDefaultInstance;

    public MessageProxyProto(Class<?> clazz, Descriptors.Descriptor rawDescriptor)
    {
        this.clazz = clazz;
        this.rawDescriptor = rawDescriptor;
    }

    public boolean buildMethods()
    {
        newBuilder = resolveMethod(METHOD_newBuilder);
        if (null == newBuilder)
        {
            //有一个方法缺失都不行
            return false;
        }
        getDefaultInstance = ReflectionUtils.findMethod(clazz, METHOD_getDefaultInstance);
        if (null == getDefaultInstance)
        {
            //有一个方法缺失都不行
            return false;
        }
        return true;
    }

    @Override
    public Message.Builder newBuilder()
    {
        return (Message.Builder) ReflectionUtils.invokeMethod(newBuilder, null);
    }

    @Override
    public Message getDefaultInstance()
    {
        return (Message) ReflectionUtils.invokeMethod(getDefaultInstance, null);
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

    private Method resolveMethod(String name)
    {
        Method method = ReflectionUtils.findMethod(clazz, name);
        if (null == method)
        {
            logger.warn("Unable to find method {} from class {}", name, clazz.getName());
            return null;
        }
        return method;

    }

}
