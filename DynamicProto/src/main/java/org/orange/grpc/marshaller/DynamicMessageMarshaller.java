package org.orange.grpc.marshaller;

import com.google.protobuf.Descriptors;
import io.grpc.MethodDescriptor;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.container.impl.DynamicMessageImpl;

import java.io.InputStream;

/**
 * Created by SAM on 2017/2/11.
 */
public class DynamicMessageMarshaller implements MethodDescriptor.Marshaller<DynamicMessage>
{
    private final Descriptors.Descriptor descriptor;

    private final ProtoMessageMarshaller protoMarshaller;

    /**
     * Constructor
     * @param descriptor
     */
    public DynamicMessageMarshaller(Descriptors.Descriptor descriptor)
    {
        this.descriptor = descriptor;
        protoMarshaller = new ProtoMessageMarshaller(descriptor);
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
