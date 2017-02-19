package org.orange.dynamic;

import com.google.common.base.Preconditions;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import lombok.AllArgsConstructor;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.container.impl.DynamicMessageImpl;
import org.orange.dynamic.container.proxy.MessageProxy;
import org.orange.dynamic.container.impl.ProtoMessageImpl;
import org.orange.dynamic.container.proxy.MessageProxyDynamic;
import org.orange.dynamic.container.proxy.MessageProxyProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sam on 2017/2/7.
 */
public class DynamicDescriptorPool {

    private final static Logger logger = LoggerFactory.getLogger(DynamicDescriptorPool.class);

    private static final DynamicDescriptorPool INSTANCE = new DynamicDescriptorPool();

    private Map<String, DescriptorProtos.FileDescriptorSet> pathToFileDescSet = new ConcurrentHashMap<String, DescriptorProtos.FileDescriptorSet>();

    private Map<String,SetMethods> pathToSetMethods = new ConcurrentHashMap<String, SetMethods>();

    private Map<String, Descriptors.FileDescriptor> fileDescriptors = new ConcurrentHashMap<String, Descriptors.FileDescriptor>();

    private Map<String, Descriptors.MethodDescriptor> methodDescriptors = new ConcurrentHashMap<String, Descriptors.MethodDescriptor>();

    private final Object lockToClazz = new Object();

    private Map<Descriptors.Descriptor, MessageProxy> descToClazz = new ConcurrentHashMap<Descriptors.Descriptor, MessageProxy>();


    /**
     * 获取单例对象
     * @return
     */
    public static DynamicDescriptorPool getInstance()
    {
        return INSTANCE;
    }


    private DynamicDescriptorPool()
    {

    }


    public void load(String path, InputStream descStream) throws IOException, Descriptors.DescriptorValidationException
    {
        DescriptorProtos.FileDescriptorSet fileDescriptorSet = DescriptorProtos.FileDescriptorSet.parseFrom(descStream);
        pathToFileDescSet.put(path, fileDescriptorSet);
        Set<String> fileDescNames = new HashSet<String>();
        Set<String> methodFullNames = new HashSet<String>();
        for (DescriptorProtos.FileDescriptorProto fileDescriptorProto : fileDescriptorSet.getFileList())
        {
            //This version, we don't consider the proto dependency scenarios
            Descriptors.FileDescriptor fileDescriptor = Descriptors.FileDescriptor.buildFrom(fileDescriptorProto, new Descriptors.FileDescriptor[]{});
            String fileDescName = generateFullName(fileDescriptorProto.getPackage(), fileDescriptorProto.getName());
            fileDescriptors.put(fileDescName, fileDescriptor);
            methodFullNames.addAll(buildServiceDescriptor(fileDescriptor));
            fileDescNames.add(fileDescName);
        }
        pathToSetMethods.put(path, new SetMethods(fileDescNames, methodFullNames));
    }

    public void unload(String path)
    {
        pathToFileDescSet.remove(path);
        SetMethods setMethods = pathToSetMethods.remove(path);
        if (null == setMethods)
        {
            return;
        }
        for (String fileFullName : setMethods.fileDescNames)
        {
            Descriptors.FileDescriptor fileDescriptor = fileDescriptors.remove(fileFullName);
            for (Descriptors.Descriptor messageType : fileDescriptor.getMessageTypes())
            {
                descToClazz.remove(messageType);
            }
        }
        for (String methodFullName : setMethods.methodFullNames)
        {
            methodDescriptors.remove(methodFullName);
        }

    }

    private Set<String> buildServiceDescriptor(Descriptors.FileDescriptor fileDescriptor)
    {
        Set<String> methodFullNames = new HashSet<String>();
        List<Descriptors.ServiceDescriptor> services = fileDescriptor.getServices();
        for (Descriptors.ServiceDescriptor service : services)
        {
            String serviceName = service.getFullName();
            for (Descriptors.MethodDescriptor method: service.getMethods())
            {
                String methodFullName = generateFullName(serviceName, method.getName());
                methodDescriptors.put(methodFullName, method);
                methodFullNames.add(methodFullName);
            }
        }
        return methodFullNames;
    }

    /**
     * 生成类型全名
     * @param fullServiceName
     * @param methodName
     * @return 类型全名
     */
    public static String generateFullName(String fullServiceName, String methodName)
    {
        return Preconditions.checkNotNull(fullServiceName, "fullServiceName") + "/" + Preconditions.checkNotNull(methodName, "methodName");
    }

    @AllArgsConstructor
    private static class SetMethods
    {
        Set<String> fileDescNames;

        Set<String> methodFullNames;
    }

    public Descriptors.Descriptor findTypeDescriptor(String typeFullName)
    {
        if (null  == typeFullName || !typeFullName.matches("[a-zA-Z0-9_\\.]*[a-zA-Z0-9_]"))
        {
            throw new IllegalArgumentException(MessageFormat.format("The typeName for {0} is invalid", typeFullName));
        }
        String pkgName = "";
        String messageTypeName = typeFullName;
        if (typeFullName.indexOf('.') != -1)
        {
            pkgName = typeFullName.substring(0,typeFullName.lastIndexOf('.'));
            messageTypeName = typeFullName.substring(typeFullName.lastIndexOf('.') + 1);
        }
        for (Descriptors.FileDescriptor fileDescriptor : fileDescriptors.values())
        {
            if (pkgName.equals(fileDescriptor.getPackage()))
            {
                Descriptors.Descriptor targetDescriptor= fileDescriptor.findMessageTypeByName(messageTypeName);
                if (null != targetDescriptor)
                {
                    findProtoClazz(targetDescriptor);
                }
                return targetDescriptor;
            }
        }
        return null;
    }

    public MessageProxy findProtoClazz(Descriptors.Descriptor descriptor)
    {
        if (descToClazz.containsKey(descriptor))
        {
            return descToClazz.get(descriptor);
        }
        synchronized (lockToClazz)
        {
            if (descToClazz.containsKey(descriptor))
            {
                return descToClazz.get(descriptor);
            }
            Descriptors.FileDescriptor fileDescriptor = descriptor.getFile();
            String className = fileDescriptor.toProto().getOptions()
                    .getJavaPackage()
                    + "."
                    + fileDescriptor.toProto().getOptions()
                        .getJavaOuterClassname()
                    + "$" + descriptor.getName();
            Class<?> clazz;
            try
            {
                clazz = ClassUtils.resolveClassName(className, Thread.currentThread().getContextClassLoader());
            }
            catch (IllegalArgumentException e)
            {
                logger.debug("Class " + className + " not found in current classloader.");
                MessageProxy proxy = new MessageProxyDynamic(descriptor);
                descToClazz.put(descriptor, proxy);
                return proxy;
            }
            MessageProxyProto messageClassCache = new MessageProxyProto(clazz, descriptor);
            if (!messageClassCache.buildMethods())
            {
                throw new IllegalArgumentException("Wrong type for " + className + ", some methods missing.");
            }
            descToClazz.put(descriptor, messageClassCache);
            return messageClassCache;
        }
    }

    public DynamicMessage createDynamicMessageContainer(String typeFullName)
    {
        return createDynamicMessageContainer(typeFullName, false);
    }

    public DynamicMessage createDynamicMessageContainer(String typeFullName, boolean forceDynamic)
    {
        Descriptors.Descriptor descriptor = findTypeDescriptor(typeFullName);
        if (null == descriptor)
        {
            return null;
        }
        if (forceDynamic)
        {
            return new DynamicMessageImpl(descriptor);
        }
        MessageProxy messageProxy = descToClazz.get(descriptor);
        if (messageProxy instanceof MessageProxyProto)
        {
            //服务端与客户端内置，直接使用proto对象
            return new ProtoMessageImpl(messageProxy);
        }
        return new DynamicMessageImpl(descriptor);
    }


    public Descriptors.MethodDescriptor findMethodDescriptor(String methodFullName)
    {
        return methodDescriptors.get(methodFullName);
    }
}
