package org.orange.grpc;

import com.google.protobuf.Descriptors;
import io.grpc.MethodDescriptor;
import org.orange.dynamic.DynamicDescriptorPool;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.grpc.marshaller.DynamicMessageMarshaller;
import org.orange.grpc.marshaller.ProtoMessageMarshaller;

/**
 * Created by SAM on 2017/2/11.
 */
public class MethodBuilder
{
    /**
     * 构建动态消息方法实例
     * @param methodFullName servicefullname + methodName
     * @return 消息方法实例
     */
    public static MethodDescriptor<DynamicMessage, DynamicMessage> buildDynamicMessageMethod(String methodFullName)
    {
        DynamicDescriptorPool poolInstance = DynamicDescriptorPool.getInstance();
        Descriptors.MethodDescriptor methodDescriptor = poolInstance.findMethodDescriptor(methodFullName);
        if (null == methodDescriptor)
        {
            return null;
        }
        Descriptors.Descriptor inputType = methodDescriptor.getInputType();
        Descriptors.Descriptor outputType = methodDescriptor.getOutputType();
        return MethodDescriptor.create(MethodDescriptor.MethodType.UNARY, methodFullName,
                new DynamicMessageMarshaller(inputType, poolInstance.findProtoClazz(inputType).getDefaultInstance()),
                new DynamicMessageMarshaller(outputType, poolInstance.findProtoClazz(outputType).getDefaultInstance()));
    }

    /**
     * 构造protomessage方法实例
     * @param methodFullName servicefullname + methodName
     * @return 消息方法实例
     */
    public static MethodDescriptor<com.google.protobuf.DynamicMessage, com.google.protobuf.DynamicMessage> buildProtoMessageMethod(String methodFullName)
    {
        Descriptors.MethodDescriptor methodDescriptor = DynamicDescriptorPool.getInstance().findMethodDescriptor(methodFullName);
        if (null == methodDescriptor)
        {
            return null;
        }
        return MethodDescriptor.create(MethodDescriptor.MethodType.UNARY, methodFullName,
                new ProtoMessageMarshaller(methodDescriptor.getInputType()), new ProtoMessageMarshaller(methodDescriptor.getOutputType()));
    }

}
