package org.orange.dynamic;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.container.impl.DynamicMessageImpl;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sam on 2017/2/7.
 */
public class DynamicDescriptorPool {

    private Map<String, Descriptors.FileDescriptor> fileDescriptors = new HashMap<String, Descriptors.FileDescriptor>();


    public void load(InputStream descStream) throws IOException, Descriptors.DescriptorValidationException {
        DescriptorProtos.FileDescriptorSet fileDescriptorSet = DescriptorProtos.FileDescriptorSet.parseFrom(descStream);
        for (DescriptorProtos.FileDescriptorProto fileDescriptorProto : fileDescriptorSet.getFileList()) {
            //This version, we don't consider the proto dependency scenarios
            Descriptors.FileDescriptor fileDescriptor = Descriptors.FileDescriptor.buildFrom(fileDescriptorProto, new Descriptors.FileDescriptor[]{});
            fileDescriptors.put(fileDescriptor.getFullName(), fileDescriptor);
        }
    }

    public DynamicMessage createDynamicMessageContainer(String typeFullName) {
        if (null  == typeFullName || !typeFullName.matches("[a-zA-Z0-9_\\.]*[a-zA-Z0-9_]"))
        {
            throw new IllegalArgumentException(MessageFormat.format("The typeName for {} is invalid", typeFullName));
        }
        String pkgName = "";
        String messageTypeName = typeFullName;
        if (typeFullName.indexOf('.') != -1) {
            pkgName = typeFullName.substring(0,typeFullName.lastIndexOf('.'));
            messageTypeName = typeFullName.substring(typeFullName.indexOf('.') + 1);
        }
        for (Descriptors.FileDescriptor fileDescriptor : fileDescriptors.values()) {
            if (pkgName.equals(fileDescriptor.getPackage())) {
                Descriptors.Descriptor descriptor = fileDescriptor.findMessageTypeByName(messageTypeName);
                if (null == descriptor) {
                    return null;
                }
                return new DynamicMessageImpl(descriptor);
            }
        }
        return null;
    }
}
