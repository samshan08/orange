package org.orange.dynamic.container.impl;

import com.google.protobuf.Descriptors;
import org.apache.commons.lang.StringUtils;
import org.orange.dynamic.container.DynamicMessage;
import org.orange.dynamic.exceptions.FieldNotExistException;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by Sam on 2017/2/7.
 */
public class DynamicMessageImpl implements DynamicMessage
{
    private final Descriptors.Descriptor containingDescriptor;

    private com.google.protobuf.DynamicMessage.Builder  dynamicBuilder;

    //sub message type values
    private Map<String, SubMessageValue> subMessages = new HashMap<String, SubMessageValue>();

    public DynamicMessageImpl(Descriptors.Descriptor containingDescriptor)
    {
        this.containingDescriptor = containingDescriptor;
        dynamicBuilder = com.google.protobuf.DynamicMessage.newBuilder(containingDescriptor);
    }

    public int size()
    {
        return dynamicBuilder.getAllFields().size();
    }

    public boolean isEmpty()
    {
        return dynamicBuilder.getAllFields().isEmpty();
    }

    public boolean containsKey(Object key)
    {
        for (Entry<Descriptors.FieldDescriptor, Object> entry : dynamicBuilder.getAllFields().entrySet())
        {
            if (StringUtils.equals(entry.getKey().getName(), String.valueOf(key)))
            {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value)
    {
        throw new UnsupportedOperationException("DynamicMessage don't support containsValue");
    }

    public Object get(Object key)
    {
        String keyStr = String.valueOf(key);
        Descriptors.FieldDescriptor fieldDescriptor = preconditionCheck(String.valueOf(key));

        Object rvalue = dynamicBuilder.getAllFields().get(fieldDescriptor);
        if (isProtoBasicType(fieldDescriptor.getType()))
        {
            return rvalue;
        }
        if (null != rvalue)
        {
            dynamicBuilder.clearField(fieldDescriptor);
            //we need to lazy build the DynamcMessage now
            SubMessageValue smv = new SubMessageValue(fieldDescriptor);
            subMessages.put(keyStr, smv.from(rvalue));
            return smv.getContainerValues();
        }
        if (subMessages.containsKey(keyStr))
        {
            SubMessageValue smv = subMessages.get(keyStr);
            return smv.getContainerValues();
        }

        //Create a new container for next Set
        SubMessageValue subMessageValue = new SubMessageValue(fieldDescriptor);
        subMessages.put(keyStr, new SubMessageValue(fieldDescriptor));
        return subMessageValue.getContainerValues();
    }

    public Object put(String key, Object value)
    {
        Descriptors.FieldDescriptor fieldDescriptor = preconditionCheck(key);
        if (isProtoBasicType(fieldDescriptor.getType()))
        {
            dynamicBuilder.setField(fieldDescriptor, value);
        }
        else
        {
            //due with sub messageTypes
            SubMessageValue subMessageValue = new SubMessageValue(fieldDescriptor);
            subMessages.put(key, subMessageValue.fill(value));
        }
        return null;
    }

    private Descriptors.FieldDescriptor preconditionCheck(String key)
    {
        Descriptors.FieldDescriptor fieldDescriptor = containingDescriptor.findFieldByName(key);
        if (null == fieldDescriptor)
        {
            throw new FieldNotExistException(key, containingDescriptor.getFullName());
        }
        return fieldDescriptor;
    }

    public Object remove(Object key)
    {
        String keyStr = String.valueOf(key);
        Descriptors.FieldDescriptor fieldDescriptor = preconditionCheck(keyStr);
        if (subMessages.containsKey(keyStr))
        {
            subMessages.remove(keyStr);
        }
        if (dynamicBuilder.hasField(fieldDescriptor))
        {
            dynamicBuilder.clearField(fieldDescriptor);
        }
        return null;
    }

    public void putAll(Map<? extends String, ?> m)
    {
        for (Map.Entry<? extends String, ?> entry : m.entrySet())
        {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear()
    {
        subMessages.clear();
        dynamicBuilder.clear();
    }

    public Set<String> keySet()
    {
        Set<String> result = new HashSet<String>();
        for (Entry<Descriptors.FieldDescriptor, Object> entry : dynamicBuilder.getAllFields().entrySet())
        {
            result.add(entry.getKey().getName());
        }
        return result;
    }

    public Collection<Object> values()
    {
        throw new UnsupportedOperationException("DynamicMessage don't support values");
    }

    public Set<Map.Entry<String, Object>> entrySet()
    {
        throw new UnsupportedOperationException("DynamicMessage don't support entrySet");
    }

    private static boolean isProtoBasicType(Descriptors.FieldDescriptor.Type type)
    {
        Descriptors.FieldDescriptor.JavaType javaType = type.getJavaType();
        return  javaType == Descriptors.FieldDescriptor.JavaType.DOUBLE ||
                javaType == Descriptors.FieldDescriptor.JavaType.FLOAT ||
                javaType == Descriptors.FieldDescriptor.JavaType.LONG ||
                javaType == Descriptors.FieldDescriptor.JavaType.INT ||
                javaType == Descriptors.FieldDescriptor.JavaType.BOOLEAN ||
                javaType == Descriptors.FieldDescriptor.JavaType.STRING ||
                javaType == Descriptors.FieldDescriptor.JavaType.BYTE_STRING ||
                javaType == Descriptors.FieldDescriptor.JavaType.ENUM;
    }

    public DynamicMessage fill(final Object value)
    {
        if (null == value) {
            return this;
        }
        if (value instanceof Map)
        {
            Map<?, ?> mapValue = (Map<?, ?>)value;
            for (Map.Entry<?, ? > entry : mapValue.entrySet())
            {
                put(String.valueOf(entry.getKey()),entry.getValue());
            }
        }
        else
        {
            ReflectionUtils.doWithFields(value.getClass(), new ReflectionUtils.FieldCallback() {
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    if (!field.isAccessible()) {
                        ReflectionUtils.makeAccessible(field);
                    }
                    put(field.getName(), ReflectionUtils.getField(field, value));
                }
            }, new ReflectionUtils.FieldFilter() {
                public boolean matches(Field field) {
                    return !(Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers()));
                }
            });
        }
        return this;
    }

    @Override
    public DynamicMessage fromProtoMessage(com.google.protobuf.DynamicMessage protoMessage)
    {
        clear();
        Map<Descriptors.FieldDescriptor, Object> allFields = protoMessage.getAllFields();
        for (Entry<Descriptors.FieldDescriptor, Object> entry : allFields.entrySet())
        {
            dynamicBuilder.setField(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public com.google.protobuf.DynamicMessage toProtoMessage()
    {
        for (Map.Entry<String, SubMessageValue> entry : subMessages.entrySet())
        {
            SubMessageValue subMessageValue = entry.getValue();
            subMessageValue.buildProtoMessage(dynamicBuilder);
        }
        return dynamicBuilder.build();
    }




}
