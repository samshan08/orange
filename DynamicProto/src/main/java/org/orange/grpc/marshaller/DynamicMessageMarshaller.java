package org.orange.grpc.marshaller;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.container.impl.DynamicMessageImpl;

import java.io.InputStream;

/**
 * Created by SAM on 2017/2/11.
 */
public class DynamicMessageMarshaller implements MethodDescriptor.Marshaller<DynamicMessage>
{
    private final Descriptors.Descriptor descriptor;

    private final MethodDescriptor.Marshaller<Message> protoMarshaller;

    /**
     * Constructor
     * @param descriptor
     */
    public DynamicMessageMarshaller(Descriptors.Descriptor descriptor, Message defaultInstance)
    {
        this.descriptor = descriptor;
        protoMarshaller = ProtoLiteUtils.marshaller(defaultInstance);
    }

    @Override
    public InputStream stream(DynamicMessage dynamicMessage)
    {
        return protoMarshaller.stream(dynamicMessage.toProtoMessage());
    }

    @Override
    public DynamicMessage parse(InputStream inputStream)
    {
        DynamicMessage dynamicMessage = new DynamicMessageImpl(descriptor);
        return dynamicMessage.fromProtoMessage(protoMarshaller.parse(inputStream));
    }
}
