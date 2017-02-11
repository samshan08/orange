package org.orange.grpc.marshaller;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import io.grpc.MethodDescriptor;
import lombok.AllArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SAM on 2017/2/11.
 */
@AllArgsConstructor
public class ProtoMessageMarshaller  implements MethodDescriptor.Marshaller<DynamicMessage>
{
    private final Descriptors.Descriptor descriptor;

    @Override
    public InputStream stream(DynamicMessage dynamicMessage)
    {
        return new ByteArrayInputStream(dynamicMessage.toByteArray());
    }

    @Override
    public DynamicMessage parse(InputStream inputStream)
    {
        try
        {
            return DynamicMessage.parseFrom(descriptor, inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
